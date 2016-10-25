package util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.persistence.NoResultException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Championship;
import model.Match;
import model.Standing;
import model.Team;
import query.StandingQuery;
import query.TeamQuery;

public class Parser {
	
	private static Connection connection;
	private static final int CONNECTION_TIME_OUT = 55*1000;
	private static TeamQuery teamQuery = new TeamQuery();
	//private static MatchQuery matchQuery = new MatchQuery();
	private static StandingQuery standingQuery = new StandingQuery();
	
	private static synchronized Connection openConnection(String url) {
		if (connection == null)
			connection = Jsoup.connect(url).header("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4").
			cookie("auto_timezone", "America/Sao_Paulo").cookie("timezone_set_offset", "0").
			timeout(CONNECTION_TIME_OUT);
		return connection;
	}
	
	public static synchronized void closeConnection() {
		connection = null;
	}
	
	public void populateMatchesTablesLeagueFrom(List<Team> teams) throws IOException, ParseException {
		
		Parser parser = new Parser();
		
		Set<Match> matches = new HashSet<Match>();
		
		 for (Team team : teams) {
			matches.addAll(parser.extractMatchesTablesLeagueFrom(team));
		 }
		 
		 for (Match match : matches) {
		 	EM.getInstance().persist(match);
		 }
	}
	
	public void populateOverUnderStatsTablesLeagueFrom(List<Team> teams) throws IOException, ParseException {
		
		 for (Team team : teams) {
			 extractOverUnderStatsTablesLeague(team);
			 EM.getInstance().merge(team);
		 }
	}
	
	private List<Team> getTeamsFromMatches(List<Match> matches) {
		Set<Team> teams = new HashSet<Team>();
		for (Match match : matches) {
			teams.add(match.getHomeTeam());
			teams.add(match.getAwayTeam());
		}
		return new ArrayList<Team>(teams);
	}
	
	public void populateMatchesTablesLeague(List<Match> matches) throws IOException, ParseException {
		List<Match> parsedMatches = extractMatchesTablesLeagueFrom(getTeamsFromMatches(matches));
		for (Match match : matches) {
			populateMatch(match, parsedMatches);
		}
	}
	
	public void populateMatch(Match match, List<Match> parsedMatches) throws IOException, ParseException {
		
		Match parsedMatch = findMatch(parsedMatches, match);
		match.setHomeTeamScore(parsedMatch.getHomeTeamScore());
		match.setHomeTeamHTScore(parsedMatch.getHomeTeamHTScore());
		match.setAwayTeamScore(parsedMatch.getAwayTeamScore());
		match.setAwayTeamHTScore(parsedMatch.getAwayTeamHTScore());
		match.setDate(parsedMatch.getDate());
		match.setUrlMatchStats(parsedMatch.getUrlMatchStats());
		match.setTimeHomeTeamFirstGoal(parsedMatch.getTimeHomeTeamFirstGoal());
		match.setTimeAwayTeamFirstGoal(parsedMatch.getTimeAwayTeamFirstGoal());
	}
	
	public void populateMatch(Match match) throws IOException, ParseException {
		
		List<Match> parsedMatches = extractMatchesTablesLeagueFrom(match.getHomeTeam());
		populateMatch(match, parsedMatches);
	}
	
	private Match findMatch(List<Match> parsedMatches, Match matchToFind) {
		for (Match match : parsedMatches) {
			if ( (match.getHomeTeam().getName().equals(matchToFind.getHomeTeam().getName()))
				&&
				(match.getAwayTeam().getName().equals(matchToFind.getAwayTeam().getName())) )
			return match;	
		}
		return null;
	}
	
	private List<Match> extractMatchesTablesLeagueFrom(List<Team> teams) throws IOException, ParseException {
		Set<Match> matches = new HashSet<Match>();
		for (Team team : teams) {
			matches.addAll(extractMatchesTablesLeagueFrom(team));
		}
		return new ArrayList<Match>(matches);
	}
	
	public void updateRankingsPerRoundFromOGol(Integer championshipRound, Championship championship) throws IOException, ParseException, InterruptedException {
		
		Document doc = null;
		Standing standing = null;
		List<Team> teams = teamQuery.getTeamsFromChampionship(championship);
		Integer standingRound = standingQuery.getLastStandingRound(championship);
		List<String> excecoes = Arrays.asList("Brasileiro B", "França B", "Itália B", "Portugal B", "Espanha B", "Alemanha B");
		standingRound = standingRound == null ? 1 : standingRound;
		
		for (int i=standingRound; i<=championshipRound; i++) {
			
			String oGolURL = championship.getoGolClassificationURL().replace("$", String.valueOf(i));
			Map<String, String> cookies = new HashMap<String,String>();
			cookies.put("__cfduid", "dd3d1d24431e34022f85f1b4f5244214b1475775908");
			cookies.put("PHPSESSID", "dc775g5d494e13hbh2d1sv4f45");
			cookies.put("jcenable", "1");
			cookies.put("__atuvc", "6%7C40%2C0%7C41%2C2%7C42");
			cookies.put("_zzptremember", "7b0ebd2c89e5a0c2a705d4f037715079");
			//System.out.println(match.getUrlMatchStats());
			doc = Jsoup.connect(oGolURL).header("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
					.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("Accept-Encoding","gzip, deflate, sdch")
					.header("Connection","keep-alive")
					.header("Accept-Language","pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
					.header("Host","www.ogol.com.br")
					.header("Cache-Control","max-age=0")
	                //.header("Referer","http://rivalregions.com/")
	                .header("User-Agent","User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
	                //.header("X-Requested-With", "XMLHttpRequest")
					.header("Upgrade-Insecure-Requests","1")
	                .followRedirects(true).userAgent("Chrome")
					.cookies(cookies)
					.cookie("auto_timezone", "America/Sao_Paulo").cookie("timezone_set_offset", "0").timeout(CONNECTION_TIME_OUT).get();
			
			Element table = doc.select("div#edition_table").get(0);
			
			for (Team team : teams) {
				
				int column = excecoes.contains(championship.getName()) ? 0 : 1;
				String teamPosition = table.select("tr:contains("+team.getNameOGol()+")").first().select("td").get(column).text();
				try {
					standing = standingQuery.getStandingBy(team, championship, i);
				} catch (NoResultException e) {
					standing = new Standing();
				}
				standing.setTeam(team);
				standing.setTeamPosition(Integer.parseInt(teamPosition));
				standing.setChampionship(championship);
				standing.setRound(i);
				if (standing.getId()==null)
					EM.getInstance().persist(standing);
				else
					EM.getInstance().merge(standing);
				System.out.println(String.format("[Rodada %d] %s - %s", i, teamPosition, team.getName()));
			}
			if (i % 5==0) 
				Thread.sleep((new Random().nextInt(60)+90)*1000);
			else
				Thread.sleep((new Random().nextInt(60)+30)*500);
		}
		System.out.println("----- FIM STANDING -----");
	}
	
	public void populateMatchesOGol(List<Match> matches) throws IOException, ParseException {
		for (Match match : matches) {
			extractMatchGoalTimesAndHTScoreOGolFrom(match);
		}
	}
	
	public void extractMatchGoalTimesAndHTScoreOGolFrom(Match match) throws IOException, ParseException {
		
		Map<String, String> cookies = new HashMap<String,String>();
		cookies.put("__cfduid", "ddbf57b28f6df8a111d999a423caf52881473977349");
		cookies.put("PHPSESSID", "3dcsogkqv3p0n4tnb97bhmgeo1");
		cookies.put("jcenable", "1");
		cookies.put("__atuvc", "5%7C37%2C0%7C38%2C10%7C39");
		cookies.put("__atuvs", "57ed36d0864f1317001");
		//System.out.println(match.getUrlMatchStats());
		Document doc = Jsoup.connect(match.getUrlMatchStats()).header("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
				.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Encoding","gzip, deflate, sdch")
				.header("Connection","keep-alive")
				.header("Accept-Language","pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
				.header("Host","www.ogol.com.br")
				.header("Cache-Control","max-age=0")
                //.header("Referer","http://rivalregions.com/")
                .header("User-Agent","User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                //.header("X-Requested-With", "XMLHttpRequest")
				.header("Upgrade-Insecure-Requests","1")
                .followRedirects(true).userAgent("Chrome")
				.cookies(cookies)
				.cookie("auto_timezone", "America/Sao_Paulo").cookie("timezone_set_offset", "0").timeout(CONNECTION_TIME_OUT).get();
		
			Element homeTeamScoreTimeDiv = doc.select("div.scorers").get(0);
			if (homeTeamScoreTimeDiv.hasText()) {
				String firstGoalText = homeTeamScoreTimeDiv.text().replaceAll("\\D", " ").trim();
				firstGoalText = firstGoalText.split(" ")[0];
				match.setTimeHomeTeamFirstGoal(Integer.parseInt(firstGoalText));
			}	
			
			Element awayTeamScoreTimeDiv = doc.select("div.scorers").get(1);
			if (awayTeamScoreTimeDiv.hasText()) {
				String firstGoalText = awayTeamScoreTimeDiv.text().replaceAll("\\D", " ").trim();
				firstGoalText = firstGoalText.split(" ")[0];
				match.setTimeAwayTeamFirstGoal(Integer.parseInt(firstGoalText));
			}	
			
			Element scoreDiv = doc.select("div.score").get(0);
			if (match.getHomeTeamScore() == null && scoreDiv.hasText() && !scoreDiv.text().contains("vs")) {
				Element scoreLink = scoreDiv.select("a").first();
				String score = scoreLink.text(); 
				if (!score.contains("-")) return;
				match.setHomeTeamScore(Integer.parseInt(score.split("-")[0]));
				match.setAwayTeamScore(Integer.parseInt(score.split("-")[1]));
			}
			
			if (scoreDiv.hasText() && !scoreDiv.text().contains("vs")) {
				if (scoreDiv.select("span").isEmpty()) {
					System.out.println("---> Problema ao fazer parser resultado FT. Setando valores para 0. Jogo: " + match.toString());
					match.setHomeTeamHTScore(0);
					match.setAwayTeamHTScore(0);
				}
				else {
					String partialScore = scoreDiv.select("span").get(0).text();
					if (partialScore.contains("-")) {
						match.setHomeTeamHTScore(Integer.parseInt(partialScore.split("-")[0]));
						match.setAwayTeamHTScore(Integer.parseInt(partialScore.split("-")[1]));
					}
				}	
			}	
	}
	
	public void extractMatchesOGolFrom(Championship championship) throws IOException, ParseException, InterruptedException {
		
		Document doc = null;
		
		for (int i=1; i<=championship.getTotalRounds(); i++) {
			
			System.out.println("Início Rodada "+ i);
			String oGolURL = championship.getoGolClassificationURL().replace("$", Integer.toString(i));
			//Map<String, String> cookies = Jsoup.connect(oGolURL).followRedirects(true).userAgent("Chrome").execute().cookies();
			Map<String, String> cookies = new HashMap<String,String>();
			cookies.put("__cfduid", "db00709fa05f9ded70f9590776f1792821473524429");
			cookies.put("PHPSESSID", "s9c55b9415knvd3fs9v20ip5q3");
			cookies.put("_gat", "1");
			cookies.put("jcenable", "1");
			cookies.put("zzptremember", "24b6d096f404d56c5ec1ce59d0bfa641");
			cookies.put("GED_PLAYLIST_ACTIVITY", "W3sidSI6InJXeWEiLCJ0c2wiOjE0NzU3ODkyMTcsIm52IjowLCJ1cHQiOjE0NzU3ODM3MTEsImx0IjoxNDc1Nzg0MDk1fV0.");
			cookies.put("__atuvc", "29%7C37%2C19%7C38%2C67%7C39%2C50%7C40%2C14%7C41");
			cookies.put("__atuvs", "58012edf241c64e6008");
			//Cookie: =; =; __gads=ID=7a36275f6e9d7db3:T=1473977350:S=ALNI_MYGiSNwCdGEDFx2n84Rh3vzUXozaA; _gat=1; _ga=GA1.3.547361423.1473977190;
			doc = Jsoup.connect(oGolURL).header("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
					.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("Accept-Encoding","gzip, deflate, sdch")
					.header("Connection","keep-alive")
					.header("Accept-Language","pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
					.header("Host","www.ogol.com.br")
					.header("Cache-Control","max-age=0")
                    //.header("Referer","http://rivalregions.com/")
                    .header("User-Agent","User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                    //.header("X-Requested-With", "XMLHttpRequest")
					.header("Upgrade-Insecure-Requests","1")
                    .followRedirects(true).userAgent("Chrome")
					.cookies(cookies)
					.cookie("auto_timezone", "America/Sao_Paulo").cookie("timezone_set_offset", "0").timeout(CONNECTION_TIME_OUT).get();
			
			Element div = doc.select("div#fixture_games").first();
			Element table = div.select("table.zztable.stats").get(0);
			Element tableBody = table.select("tbody").get(0);
			Elements tableLines = tableBody.select("tr");
			List<Match> matches = new ArrayList<Match>();
			Match match = null;
			String matchDate = null;
			for (Element tr : tableLines) {
				matchDate = tr.select("td").get(0).text().trim().length() <= 1? matchDate : tr.select("td").get(0).text().trim();
				String matchHour = tr.select("td.vs").text().trim();
				String homeTeamName = tr.select("td").get(1).text().trim();
				String awayTeamName = tr.select("td").get(3).text().trim();
				String matchStatsURL = tr.select("td").get(2).select("a[href]").attr("abs:href");
				String result = tr.select("td.result").text().trim();
				Team homeTeam = teamQuery.getTeamByOGolName(homeTeamName, championship);
				Team awayTeam = teamQuery.getTeamByOGolName(awayTeamName, championship);
				match = new Match();
				match.setChampionship(homeTeam.getChampionship());
				match.setHomeTeam(homeTeam);
				match.setAwayTeam(awayTeam);
				match.setUrlMatchStats(matchStatsURL);
				if (result.contains("-")) { 
					match.setHomeTeamScore(Integer.parseInt(result.split("-")[0]));
					match.setAwayTeamScore(Integer.parseInt(result.split("-")[1]));
				}
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				//sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
				match.setDate(sdf.parse(matchDate+"/"+cal.get(Calendar.YEAR)));
				extractMatchGoalTimesAndHTScoreOGolFrom(match);
				match.setChampionshipRound(i);
				matches.add(match);
				
				String homeTeamScore = match.getHomeTeamScore() == null ? "" :  match.getHomeTeamScore().toString();
				String awayTeamScore = match.getAwayTeamScore() == null ? "" :  match.getAwayTeamScore().toString();
				System.out.println("["+match.getDate() + " " +  matchHour + "] " +
				match.getHomeTeam().getName() + " " + homeTeamScore + " x "+ awayTeamScore + " " + match.getAwayTeam().getName() );
				if (match.getId() == null)
					EM.getInstance().persist(match);
				else 
					EM.getInstance().merge(match);
			}
			System.out.println("Fim Rodada "+ i);
			//Thread.sleep((new Random().nextInt(60)+90)*1000);
			if (i % 5==0) 
				Thread.sleep((new Random().nextInt(60)+90)*1000);
			else
				Thread.sleep((new Random().nextInt(60)+30)*500);
		}
		
	}
	
	public void extractMatchGoalTimesTablesLeagueFrom(Match match) throws IOException, ParseException {
		
		//System.out.println(match.getUrlMatchStatsTablesLeague());
		
		Document doc = Jsoup.connect(match.getUrlMatchStats()).header("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
				.cookie("auto_timezone", "America/Sao_Paulo").cookie("timezone_set_offset", "0").timeout(CONNECTION_TIME_OUT).get();
		
		if (doc.select("div.game_actions").size()==0) {
			System.out.println("Link de partida inexistente: " + match.getHomeTeam().getName() + " x " + match.getAwayTeam().getName());
			return;
		}
		Element parentDiv = doc.select("div.game_actions").get(0);
		Elements homeTeamDiv = parentDiv.select("div.actions_team_a").get(0).select("div.game_row.score");
		if (!homeTeamDiv.isEmpty()) {
			match.setTimeHomeTeamFirstGoal(Integer.parseInt(homeTeamDiv.get(0).select("div.min").get(0).text().replaceAll("'", "")));
		}
		Elements awayTeamDiv = parentDiv.select("div.actions_team_b").get(0).select("div.game_row.score");
		if (!awayTeamDiv.isEmpty()) {
			match.setTimeAwayTeamFirstGoal(Integer.parseInt(awayTeamDiv.get(0).select("div.min").get(0).text().replaceAll("'", "")));
		}
	}
	
	public void extractOverUnderStatsTablesLeague(Team team) throws IOException, ParseException {
		
		Document doc = Jsoup.connect(team.getUrlTablesLeague()).header("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
				.cookie("auto_timezone", "America/Sao_Paulo").cookie("timezone_set_offset", "0").timeout(CONNECTION_TIME_OUT).get();
		
		Elements tables = doc.select("table.html_table");
		Element overUnderTable = tables.get(2);
		Elements rows = overUnderTable.select("tr");
		Element row = rows.get(2);
		Elements cols = row.getElementsByTag("td");
		team.setUnder15Home(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setUnder15Away(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(3);
		cols = row.getElementsByTag("td");
		team.setOver15Home(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setOver15Away(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(4);
		cols = row.getElementsByTag("td");
		team.setUnder25Home(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setUnder25Away(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(5);
		cols = row.getElementsByTag("td");
		team.setOver25Home(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setOver25Away(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(6);
		cols = row.getElementsByTag("td");
		team.setUnder35Home(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setUnder35Away(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(7);
		cols = row.getElementsByTag("td");
		team.setOver35Home(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setOver35Away(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(8);
		cols = row.getElementsByTag("td");
		team.setUnder05FirstHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setUnder05FirstHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(9);
		cols = row.getElementsByTag("td");
		team.setOver05FirstHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setOver05FirstHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(10);
		cols = row.getElementsByTag("td");
		team.setUnder15FirstHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setUnder15FirstHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(11);
		cols = row.getElementsByTag("td");
		team.setOver15FirstHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setOver15FirstHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(14);
		cols = row.getElementsByTag("td");
		team.setUnder05SecondHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setUnder05SecondHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(15);
		cols = row.getElementsByTag("td");
		team.setOver05SecondHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setOver05SecondHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(16);
		cols = row.getElementsByTag("td");
		team.setUnder15SecondHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setUnder15SecondHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
		row = rows.get(17);
		cols = row.getElementsByTag("td");
		team.setOver15SecondHalfHome(Float.parseFloat(cols.get(4).text().replace("%", "")));
		team.setOver15SecondHalfAway(Float.parseFloat(cols.get(6).text().replace("%", "")));
	}
	
	private List<Match> extractMatchesTablesLeagueFrom(Team team) throws IOException, ParseException {
		
		int championshipRound= 1;
		List<Match> matches = new ArrayList<Match>();
		Match match = null;
		TeamQuery teamQuery = new TeamQuery();
		Document doc = Jsoup.connect(team.getUrlTablesLeague()).header("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4")
				.cookie("auto_timezone", "America/Sao_Paulo").cookie("timezone_set_offset", "0").timeout(CONNECTION_TIME_OUT).get();
		Elements tables = doc.select("table#results");
		Elements rows = tables.select("tr");
		for (int i=1; i < rows.size()-1; i++) {
			Element row = rows.get(i);
			Elements cols = row.getElementsByTag("td");
			String championshipName = cols.get(4).text();
			if (!team.getChampionship().getCodeTablesLeague().equalsIgnoreCase(championshipName)) continue;
			String matchStatsURL = cols.get(2).select("a[href]").attr("abs:href");
			String result = cols.get(6).text();
			String resultHT = cols.get(7).text();
			String teamsNames = cols.get(2).text().replaceFirst("\\s\\-\\s", "=");
			String homeTeamName = teamsNames.split("=")[0];
			String awayTeamName = teamsNames.split("=")[1];
			Team homeTeam = teamQuery.getTeamByTablesLeagueName(homeTeamName.trim(), team.getChampionship());
			Team awayTeam = teamQuery.getTeamByTablesLeagueName(awayTeamName.trim(), team.getChampionship());
			match = new Match();
			match.setChampionship(team.getChampionship());
			match.setHomeTeam(homeTeam);
			match.setAwayTeam(awayTeam);
			match.setUrlMatchStats(matchStatsURL);
			if (!"-".equals(result)) { 
				match.setHomeTeamScore(Integer.parseInt(result.split("-")[0]));
				match.setAwayTeamScore(Integer.parseInt(result.split("-")[1]));
				match.setHomeTeamHTScore(Integer.parseInt(resultHT.split("-")[0]));
				match.setAwayTeamHTScore(Integer.parseInt(resultHT.split("-")[1]));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
			match.setDate(sdf.parse(cols.get(1).text()));
			extractMatchGoalTimesTablesLeagueFrom(match);
			match.setChampionshipRound(championshipRound);
			matches.add(match);
			championshipRound++;
		}	
		return matches;
	}
	
	public void populateClassificationAcademiaDasApostasFrom(List<Team> teams) throws IOException {
		for (Team team : teams) {
			populateClassificationAcademiaDasApostasFrom(team);
		}
		closeConnection();
	}
	
	public void populateClassificationAcademiaDasApostasFrom(Team team) throws IOException {
		
		Document doc = openConnection(team.getChampionship().getClassificationTableURL()).get();
		//Document doc = Jsoup.connect(team.getChampionship().getClassificationTableURL()).get();
		Elements tables = doc.select("table.results");//3
		
		Element classificationTable = tables.get(0);
		Element homeClassificationTable = tables.get(1);
		Element awayClassificationTable = tables.get(2);
		
		team.setPosition(extractTeamPosition(team.getNameAcademiaDasApostas(), classificationTable));
		team.setHomePosition(extractTeamPosition(team.getNameAcademiaDasApostas(), homeClassificationTable));
		team.setAwayPosition(extractTeamPosition(team.getNameAcademiaDasApostas(), awayClassificationTable));
	}
	
	private Integer extractTeamPosition(String teamName, Element classificationTable) {
		Integer position = null;
		Elements rows = classificationTable.select("tr");
		for (Element row : rows) {
			Element teamLink = row.getElementsByTag("a").first();
			if ( (teamLink != null) && (teamName.equalsIgnoreCase(teamLink.text())) ) {
				Element teamPosition = row.getElementsByTag("td").first();
				position = Integer.parseInt(teamPosition.text());
			}
		}
		return position;
	}
	
	public void populateGoalsSoccerWayFrom(List<Team> teams) throws IOException {
		for (Team team : teams) {
			populateGoalsSoccerWayFrom(team);
		}
	}
	
	public void populateGoalsSoccerWayFrom(Team team) throws IOException {
		
		Float avgGoalsScoredHome = null;
		Float avgGoalsScoredAway = null;
		Float avgGoalsConcededHome = null;
		Float avgGoalsConcededAway = null;
		Integer avgTimeFirstGoalScoredHome = null;
		Integer avgTimeFirstGoalConcededHome = null;
		Integer avgTimeFirstGoalScoredAway = null;
		Integer avgTimeFirstGoalConcededAway = null;
		
		Document doc = Jsoup.connect(team.getUrlSoccerWay()).timeout(CONNECTION_TIME_OUT).get();
		Element div = doc.select("div#page_team_1_block_team_statistics_3_block_general_statistics_1").first();
		Element table = div.select("table.table.compare").first();
		Elements rows = table.select("tr");
		
		for (Element row : rows) {
			
			if ("Média de gols marcados p/j".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.home").text()))
					avgGoalsScoredHome = Float.parseFloat(row.select("td.team_a_col.home").text());
			}
			if ("Média de gols marcados p/j".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.away").text()))
					avgGoalsScoredAway = Float.parseFloat(row.select("td.team_a_col.away").text());
			}
			if ("Média de gols permitidos p/j".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.home").text()))
					avgGoalsConcededHome = Float.parseFloat(row.select("td.team_a_col.home").text());
			}
			if ("Média de gols permitidos p/j".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.away").text()))
					avgGoalsConcededAway = Float.parseFloat(row.select("td.team_a_col.away").text());
			}
			if ("Tempo médio do primeiro gol marcado".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.home").text()))
					avgTimeFirstGoalScoredHome = Integer.parseInt(row.select("td.team_a_col.home").text().replace("m", ""));
			}
			if ("Tempo médio do primeiro gol marcado".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.away").text()))
					avgTimeFirstGoalScoredAway = Integer.parseInt(row.select("td.team_a_col.away").text().replace("m", ""));
			}
			if ("Tempo médio do primeiro gol permitido.".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.home").text()))
					avgTimeFirstGoalConcededHome = Integer.parseInt(row.select("td.team_a_col.home").text().replace("m", ""));
			}
			if ("Tempo médio do primeiro gol permitido.".equals(row.select("th").text())) {
				if (!"-".equals(row.select("td.team_a_col.away").text()))
					avgTimeFirstGoalConcededAway = Integer.parseInt(row.select("td.team_a_col.away").text().replace("m", ""));
			}
		}
		team.setAvgGoalsScoredHome(avgGoalsScoredHome);
		team.setAvgGoalsScoredAway(avgGoalsScoredAway);
		team.setAvgGoalsConcededHome(avgGoalsConcededHome);
		team.setAvgGoalsConcededAway(avgGoalsConcededAway);
		team.setAvgTimeFirstGoalScoredHome(avgTimeFirstGoalScoredHome);
		team.setAvgTimeFirstGoalConcededHome(avgTimeFirstGoalConcededHome);
		team.setAvgTimeFirstGoalScoredAway(avgTimeFirstGoalScoredAway);
		team.setAvgTimeFirstGoalConcededAway(avgTimeFirstGoalConcededAway);
	}

}
