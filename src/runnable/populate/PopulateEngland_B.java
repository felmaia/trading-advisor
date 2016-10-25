package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import query.ChampionshipQuery;
import util.EM;
import util.Parser;

public class PopulateEngland_B {
	
	public static void main(String[] args) {
		
		try {
			
			EM.getInstance().getTransaction().begin();
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Inglaterra B", 
					"https://www.academiadasapostasbrasil.com/stats/match/inglaterra-stats/2247279",
					"Championship",
					46,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98044&fase=93590");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
			//parser.populateMatchesTablesLeagueFrom(teams);
			
			parser.extractMatchesOGolFrom(championship);
			
			parser.populateClassificationAcademiaDasApostasFrom(teams);
			
			parser.populateGoalsSoccerWayFrom(teams);
			
			parser.populateOverUnderStatsTablesLeagueFrom(teams);
			
			//parser.updateRankingsPerRoundFromOGol(championshipQuery.getChampionshipLastRound(championship), championship);  /* O Gol */
			
			EM.getInstance().getTransaction().commit();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
	}
	
	private static List<Team> createTeams(Championship championship) {
		
		List<Team> teams = new ArrayList<Team>();
		
		teams.add(new Team("Norwich", "Norwich", "Norwich", "Norwich City", championship, "http://www.tablesleague.com/teams/norwich1/", "http://br.soccerway.com/teams/england/norwich-city-fc/677/statistics/"));
		teams.add(new Team("Huddersfield", "Huddersfield", "Huddersfield", "Huddersfield Town", championship, "http://www.tablesleague.com/teams/huddersfield1/", "http://br.soccerway.com/teams/england/huddersfield-town-fc/726/statistics/"));
		teams.add(new Team("Brighton", "Brighton", "Brighton", "Brighton & Hove Albion", championship, "http://www.tablesleague.com/teams/brighton1/", "http://br.soccerway.com/teams/england/brighton--hove-albion-fc/703/statistics/"));
		teams.add(new Team("Reading", "Reading", "Reading", "Reading", championship, "http://www.tablesleague.com/teams/reading1/", "http://br.soccerway.com/teams/england/reading-fc/688/statistics/"));
		teams.add(new Team("Newcastle", "Newcastle", "Newcastle", "Newcastle", championship, "http://www.tablesleague.com/teams/newcastle1/", "http://br.soccerway.com/teams/england/newcastle-united-football-club/664/statistics/"));
		teams.add(new Team("Birmingham", "Birmingham", "Birmingham", "Birmingham City", championship, "http://www.tablesleague.com/teams/birmingham1/", "http://br.soccerway.com/teams/england/birmingham-city-fc/669/statistics/"));
		teams.add(new Team("Barnsley", "Barnsley", "Barnsley", "Barnsley", championship, "http://www.tablesleague.com/teams/barnsley1/", "http://br.soccerway.com/teams/england/barnsley-fc/716/statistics/"));
		teams.add(new Team("Wolverhampton", "Wolverhampton", "Wolverhampton", "Wolverhampton", championship, "http://www.tablesleague.com/teams/wolverhampton1/", "http://br.soccerway.com/teams/england/wolverhampton-wanderers-fc/680/statistics/"));
		teams.add(new Team("Brentford", "Brentford", "Brentford", "Brentford", championship, "http://www.tablesleague.com/teams/brentford1/", "http://br.soccerway.com/teams/england/brentford-fc/722/statistics/"));
		teams.add(new Team("Bristol City", "Bristol C.", "Bristol City", "Bristol City", championship, "http://www.tablesleague.com/teams/bristol_c/", "http://br.soccerway.com/teams/england/bristol-city-fc/707/statistics/"));
		teams.add(new Team("Sheffield", "Sheffield W", "Sheff Wed", "Sheffield Wed.", championship, "http://www.tablesleague.com/teams/sheffield_w1/", "http://br.soccerway.com/teams/england/sheffield-wednesday-fc/719/statistics/"));
		teams.add(new Team("Leeds", "Leeds", "Leeds", "Leeds United", championship, "http://www.tablesleague.com/teams/leeds1/", "http://br.soccerway.com/teams/england/leeds-united-afc/681/statistics/"));
		teams.add(new Team("Fulham", "Fulham", "Fulham", "Fulham", championship, "http://www.tablesleague.com/teams/fulham1/", "http://br.soccerway.com/teams/england/fulham-football-club/667/statistics/"));
		teams.add(new Team("Ipswich", "Ipswich", "Ipswich", "Ipswich Town", championship, "http://www.tablesleague.com/teams/ipswich1/", "http://br.soccerway.com/teams/england/ipswich-town-fc/685/statistics/"));
		teams.add(new Team("Nottingham", "Nottingham F.", "Nottm Forest", "Nottingham Forest", championship, "http://www.tablesleague.com/teams/nottingham_f/", "http://br.soccerway.com/teams/england/nottingham-forest-fc/694/statistics/"));
		teams.add(new Team("QPR", "Queens P.R.", "QPR", "QPR", championship, "http://www.tablesleague.com/teams/queens_pr/", "http://br.soccerway.com/teams/england/queens-park-rangers-fc/702/statistics/"));
		teams.add(new Team("Aston Villa", "Aston Villa", "Aston Villa", "Aston Villa", championship, "http://www.tablesleague.com/teams/aston_villa1/", "http://br.soccerway.com/teams/england/aston-villa-football-club/665/statistics/"));
		teams.add(new Team("Burton", "Burton", "Burton Albion", "Burton Albion", championship, "http://www.tablesleague.com/teams/burton1/", "http://br.soccerway.com/teams/england/burton-albion-fc/764/statistics/"));
		teams.add(new Team("Preston", "Preston", "Preston", "Preston North End", championship, "http://www.tablesleague.com/teams/preston1/", "http://br.soccerway.com/teams/england/preston-north-end-fc/693/statistics/"));
		teams.add(new Team("Blackburn", "Blackburn", "Blackburn", "Blackburn Rovers", championship, "http://www.tablesleague.com/teams/blackburn/", "http://br.soccerway.com/teams/england/blackburn-rovers-football-club/672/statistics/"));
		teams.add(new Team("Cardiff", "Cardiff", "Cardiff", "Cardiff City", championship, "http://www.tablesleague.com/teams/cardiff1/", "http://br.soccerway.com/teams/wales/cardiff-city-fc/691/statistics/"));
		teams.add(new Team("Derby", "Derby", "Derby", "Derby County", championship, "http://www.tablesleague.com/teams/derby1/", "http://br.soccerway.com/teams/england/derby-county-fc/699/statistics/"));
		teams.add(new Team("Rotherham", "Rotherham", "Rotherham", "Rotherham", championship, "http://www.tablesleague.com/teams/rotherham1/", "http://br.soccerway.com/teams/england/rotherham-united-fc/695/statistics/"));
		teams.add(new Team("Wigan", "Wigan", "Wigan", "Wigan Athletic", championship, "http://www.tablesleague.com/teams/wigan1/", "http://br.soccerway.com/teams/england/wigan-athletic-football-club/686/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
