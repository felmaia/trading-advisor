package runnable;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import model.Championship;
import model.Match;
import model.Team;
import query.ChampionshipQuery;
import query.MatchQuery;
import query.TeamQuery;
import strategy.Under35InGameStrategy;
import util.EM;
import util.Parser;

public class Tests {
	
	private static Parser parser = new Parser();
	private static MatchQuery matchQuery = new MatchQuery();
	private static TeamQuery teamQuery = new TeamQuery();
	private static ChampionshipQuery championshipQuery = new ChampionshipQuery();

	public static void main(String[] args) {
		
		//testDatabaseConnection();
		//createMatch();
		//testQuery();
		//testStrategy();
		//testMatchStatsURLGrabber();
		//testExtractMatchesFromOGol();
		//textExtractGoalTimesFromOGol();
		//updateMatchesRound();
		//testParseOverUnderStats();
		testUpdateRankingsPerRoundFromOGol();

	}
	
	private static void testUpdateRankingsPerRoundFromOGol() {
		
		EM.getInstance().getTransaction().begin();
		
		Championship championship = championshipQuery.getChampionshipByName("Espanha B");
		
		try {
			parser.updateRankingsPerRoundFromOGol(championshipQuery.getChampionshipLastRound(championship), championship);
		} catch (IOException | ParseException | InterruptedException e) {
			e.printStackTrace();
		}
		EM.getInstance().getTransaction().commit();
	}

	public static void testParseOverUnderStats() {
		
		EM.getInstance().getTransaction().begin();
		
		Team team = teamQuery.getTeamByName("Palmeiras");
		try {
			parser.populateOverUnderStatsTablesLeagueFrom(Arrays.asList(team));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		EM.getInstance().getTransaction().commit();
	}
	
	public static void testQuery() {
		
		Team team = teamQuery.getTeamByName("West Bromwich");
		System.out.println(teamQuery.getTeamAvgGoalsScoredOrConcededAwayHT(team, new Date()));
	}
	
	public static void updateMatchesRound() {
		
		EM.getInstance().getTransaction().begin();
		
		List<Championship> championships = championshipQuery.getAllChampionships();
		for (Championship championship : championships) {
			List<Team> teams = teamQuery.getTeamsFromChampionship(championship);
			for (Team team : teams) {
				List<Match> matches = matchQuery.getMatchsByTeamAndChampionship(team, championship);
				int round = 1;
				for (Match match : matches) {
					if (match.getChampionshipRound() == null) {
						match.setChampionshipRound(round);
					}
					EM.getInstance().merge(match);
					round ++;
				}
			}
		}
		EM.getInstance().getTransaction().commit();
	}
	
	public static void textExtractGoalTimesFromOGol() {
		
		try {
			parser.extractMatchGoalTimesAndHTScoreOGolFrom(matchQuery.getMatchById(71));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}
	
	public static void testExtractMatchesFromOGol()  {
		
		try {
			//EM.getInstance().getTransaction().begin();
			
			parser.extractMatchesOGolFrom(championshipQuery.getChampionshipByName("Brasileiro A"));
			
			//EM.getInstance().getTransaction().commit();
			
			System.out.println("### FIM ###");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void testMatchStatsURLGrabber()  {
		
		try {
			EM.getInstance().getTransaction().begin();
			
			parser.populateMatch(matchQuery.getMatchById(18));
			
			EM.getInstance().getTransaction().commit();
			
			System.out.println("### FIM ###");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void testMatchParser()  {
		
		try {
			parser.extractMatchGoalTimesTablesLeagueFrom(matchQuery.getMatchById(18));
			
			System.out.println("### FIM ###");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void testStrategy() {
		
		Match match = EM.getInstance().find(Match.class, 1870);
		
		//LayAwayTeamStrategy strategy = new LayAwayTeamStrategy();
		//Over05HTStrategy strategy = new Over05HTStrategy();
		Under35InGameStrategy strategy = new Under35InGameStrategy();
		System.out.println(strategy.analyse(match));
	}
	
	public static void createMatch() {
		try {
			Championship championship = new Championship("Brasileiro A", 
					"https://www.academiadasapostasbrasil.com/stats/match/brasil-stats/2015554",
					"Serie A",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=79735&fase=78272");
			EM.getInstance().persist(championship);
			Team homeTeam = new Team("Atletico MG", "Atletico MG", "Atlético MG", "Atlético Mineiro", championship, "http://www.tablesleague.com/teams/atletico_mg1/", "http://br.soccerway.com/teams/brazil/clube-atletico-mineiro/317/statistics/");
			Team awayTeam = new Team("Atletico PR", "Atletico PR", "Atlético PR", "Atlético Paranaense", championship, "http://www.tablesleague.com/teams/atletico_pr1/", "http://br.soccerway.com/teams/brazil/clube-atletico-paranaense/315/statistics/");
			EM.getInstance().persist(homeTeam);
			EM.getInstance().persist(awayTeam);
			Match match = new Match();
			match.setAwayTeam(awayTeam);
			match.setAwayTeamHTScore(0);
			match.setAwayTeamScore(2);
			match.setChampionship(championship);
			match.setDate(new Date());
			match.setHomeTeam(homeTeam);
			match.setHomeTeamHTScore(0);
			match.setHomeTeamScore(2);
			EM.getInstance().persist(match);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
	
	public static void testDatabaseConnection() {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("trading_advisor_pu");
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		Championship championship = new Championship();
		championship.setName("Campeonato Brasileiro");
		
		Team team = new Team();
		team.setName("Atlético Mineiro");
		team.setChampionship(championship);
		
		manager.persist(championship);
		manager.persist(team);
		
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}

}
