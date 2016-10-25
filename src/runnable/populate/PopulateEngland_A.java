package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import query.MatchQuery;
import model.Championship;
import model.Team;
import util.EM;
import util.Parser;

public class PopulateEngland_A {
	
	public static void main(String[] args) {
		
		try {
			
			EM.getInstance().getTransaction().begin();
			
			MatchQuery matchQuery = new MatchQuery();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Inglaterra A", 
					"https://www.academiadasapostasbrasil.com/stats/match/inglaterra-stats/2242138",
					"Premier League",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=97911&fase=93381");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
			//parser.populateMatchesTablesLeagueFrom(teams);
			
			parser.extractMatchesOGolFrom(championship);
			
			parser.populateClassificationAcademiaDasApostasFrom(teams);
			
			parser.populateGoalsSoccerWayFrom(teams);
			
			parser.populateOverUnderStatsTablesLeagueFrom(teams);
			
			//parser.updateRankingsPerRoundFromOGol(matchQuery.getPlayedMatchesFrom(championship)); /* O Gol */
			
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
		
		teams.add(new Team("Manchester City", "Manchester City", "Man City", "Manchester City" , championship, "http://www.tablesleague.com/teams/manchester_city1/", "http://br.soccerway.com/teams/england/manchester-city-football-club/676/statistics/"));
		teams.add(new Team("Chelsea", "Chelsea", "Chelsea", "Chelsea", championship, "http://www.tablesleague.com/teams/chelsea2/", "http://br.soccerway.com/teams/england/chelsea-football-club/661/statistics/"));
		teams.add(new Team("Everton", "Everton", "Everton", "Everton", championship, "http://www.tablesleague.com/teams/everton1/", "http://br.soccerway.com/teams/england/everton-football-club/674/statistics/"));
		teams.add(new Team("Manchester United", "Manchester United", "Man Utd", "Manchester United", championship, "http://www.tablesleague.com/teams/manchester_united12/", "http://br.soccerway.com/teams/england/manchester-united-fc/662/statistics/"));
		teams.add(new Team("Tottenham", "Tottenham", "Tottenham", "Tottenham", championship, "http://www.tablesleague.com/teams/tottenham1/", "http://br.soccerway.com/teams/england/tottenham-hotspur-football-club/675/statistics/"));
		teams.add(new Team("Liverpool", "Liverpool", "Liverpool", "Liverpool", championship, "http://www.tablesleague.com/teams/liverpool1/", "http://br.soccerway.com/teams/england/liverpool-fc/663/statistics/"));
		teams.add(new Team("Arsenal", "Arsenal", "Arsenal", "Arsenal", championship, "http://www.tablesleague.com/teams/arsenal1/", "http://br.soccerway.com/teams/england/arsenal-fc/660/statistics/"));
		teams.add(new Team("Hull", "Hull", "Hull", "Hull City", championship, "http://www.tablesleague.com/teams/hull1/", "http://br.soccerway.com/teams/england/hull-city-afc/725/statistics/"));
		teams.add(new Team("Middlesbrough", "Middlesbrough", "Middlesbrough", "Middlesbrough", championship, "http://www.tablesleague.com/teams/middlesbrough1/", "http://br.soccerway.com/teams/england/middlesbrough-football-club/671/statistics/"));
		teams.add(new Team("Watford", "Watford", "Watford", "Watford", championship, "http://www.tablesleague.com/teams/watford1/", "http://br.soccerway.com/teams/england/watford-football-club/696/statistics/"));
		teams.add(new Team("Crystal Palace", "Crystal Palace", "Crystal Palace", "Crystal Palace", championship, "http://www.tablesleague.com/teams/crystal_palace1/", "http://br.soccerway.com/teams/england/crystal-palace-fc/679/statistics/"));
		teams.add(new Team("West Bromwich", "West Bromwich", "West Bromwich", "West Bromwich", championship, "http://www.tablesleague.com/teams/west_bromwich1/", "http://br.soccerway.com/teams/england/west-bromwich-albion-football-club/678/statistics/"));
		teams.add(new Team("Swansea", "Swansea", "Swansea", "Swansea City", championship, "http://www.tablesleague.com/teams/swansea1/", "http://br.soccerway.com/teams/wales/swansea-city-afc/738/statistics/"));
		teams.add(new Team("Bournemouth", "Bournemouth", "Bournemouth", "AFC Bournemouth", championship, "http://www.tablesleague.com/teams/bournemouth1/", "http://br.soccerway.com/teams/england/afc-bournemouth/711/statistics/"));
		teams.add(new Team("Burnley", "Burnley", "Burnley", "Burnley", championship, "http://www.tablesleague.com/teams/burnley1/", "http://br.soccerway.com/teams/england/burnley-fc/698/statistics/"));
		teams.add(new Team("Leicester", "Leicester", "Leicester", "Leicester City", championship, "http://www.tablesleague.com/teams/leicester1/", "http://br.soccerway.com/teams/england/leicester-city-fc/682/statistics/"));
		teams.add(new Team("West Ham", "West Ham", "West Ham", "West Ham", championship, "http://www.tablesleague.com/teams/west_ham1/", "http://br.soccerway.com/teams/england/west-ham-united-fc/684/statistics/"));
		teams.add(new Team("Southampton", "Southampton", "Southampton", "Southampton", championship, "http://www.tablesleague.com/teams/southampton1/", "http://br.soccerway.com/teams/england/southampton-fc/670/statistics/"));
		teams.add(new Team("Sunderland", "Sunderland", "Sunderland", "Sunderland", championship, "http://www.tablesleague.com/teams/sunderland1/", "http://br.soccerway.com/teams/england/sunderland-association-football-club/683/statistics/"));
		teams.add(new Team("Stoke", "Stoke", "Stoke", "Stoke City", championship, "http://www.tablesleague.com/teams/stoke1/", "http://br.soccerway.com/teams/england/stoke-city-fc/690/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
