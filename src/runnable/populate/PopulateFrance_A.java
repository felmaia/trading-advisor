package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import query.MatchQuery;
import util.EM;
import util.Parser;

public class PopulateFrance_A {
	
	public static void main(String[] args) {
		
		try {
			
			MatchQuery matchQuery = new MatchQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("França A", 
					"https://www.academiadasapostasbrasil.com/stats/match/franca-stats/2236885",
					"Ligue 1",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=97814&fase=93170");
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
		
		teams.add(new Team("Monaco", "Monaco", "Monaco", "Monaco", championship, "http://www.tablesleague.com/teams/monaco1/", "http://br.soccerway.com/teams/monaco/association-sportive-monaco-fc/885/statistics/"));
		teams.add(new Team("PSG", "Paris SG", "PSG", "Paris SG", championship, "http://www.tablesleague.com/teams/paris_sg1/", "http://br.soccerway.com/teams/france/paris-saint-germain-fc/886/statistics/"));
		teams.add(new Team("Metz", "Metz", "Metz", "Metz", championship, "http://www.tablesleague.com/teams/metz1/", "http://br.soccerway.com/teams/france/fc-de-metz/896/statistics/"));
		teams.add(new Team("Nice", "Nice", "Nice", "Nice", championship, "http://www.tablesleague.com/teams/nice1/", "http://br.soccerway.com/teams/france/olympique-gymnaste-club-de-nice-cote-dazur/894/statistics/"));
		teams.add(new Team("Bordeaux", "Bordeaux", "Bordeaux", "Bordeaux", championship, "http://www.tablesleague.com/teams/bordeaux1/", "http://br.soccerway.com/teams/france/fc-girondins-de-bordeaux/891/statistics/"));
		teams.add(new Team("Toulouse", "Toulouse", "Toulouse", "Toulouse", championship, "http://www.tablesleague.com/teams/toulouse1/", "http://br.soccerway.com/teams/france/toulouse-fc/899/statistics/"));
		teams.add(new Team("Guingamp", "Guingamp", "Guingamp", "Guingamp", championship, "http://www.tablesleague.com/teams/guingamp1/", "http://br.soccerway.com/teams/france/en-avant-de-guingamp/904/statistics/"));
		teams.add(new Team("Rennes", "Rennes", "Rennes", "Rennes", championship, "http://www.tablesleague.com/teams/rennes1/", "http://br.soccerway.com/teams/france/stade-rennais-fc/893/statistics/"));
		teams.add(new Team("Lyon", "Lyon", "Lyon", "Lyon", championship, "http://www.tablesleague.com/teams/lyon1/", "http://br.soccerway.com/teams/france/olympique-lyonnais/884/statistics/"));
		teams.add(new Team("Bastia", "Bastia", "Bastia", "Bastia", championship, "http://www.tablesleague.com/teams/bastia1/", "http://br.soccerway.com/teams/france/sporting-club-de-bastia/897/statistics/"));
		teams.add(new Team("Angers", "Angers", "Angers", "Angers", championship, "http://www.tablesleague.com/teams/angers1/", "http://br.soccerway.com/teams/france/angers-sporting-club-de-louest/918/statistics/"));
		teams.add(new Team("Caen", "Caen", "Caen", "Caen", championship, "http://www.tablesleague.com/teams/caen1/", "http://br.soccerway.com/teams/france/stade-malherbe-caen-calvados-basse-norma/902/statistics/"));
		teams.add(new Team("St.Etienne", "St.Etienne", "Saint-Étienne", "Saint-Étienne", championship, "http://www.tablesleague.com/teams/stetienne/", "http://br.soccerway.com/teams/france/association-sportive-de-saint-etienne-loire/901/statistics/"));
		teams.add(new Team("Montpellier", "Montpellier", "Montpellier", "Montpellier", championship, "http://www.tablesleague.com/teams/montpellier1/", "http://br.soccerway.com/teams/france/montpellier-herault-sport-club/906/statistics/"));
		teams.add(new Team("Marseille", "Marseille", "Marseille", "Marseille", championship, "http://www.tablesleague.com/teams/marseille1/", "http://br.soccerway.com/teams/france/olympique-de-marseille/890/statistics/"));
		teams.add(new Team("Dijon", "Dijon", "Dijon", "Dijon", championship, "http://www.tablesleague.com/teams/dijon1/", "http://br.soccerway.com/teams/france/dijon-football-cote-dor/923/statistics/"));
		teams.add(new Team("Lille", "Lille", "Lille", "Lille", championship, "http://www.tablesleague.com/teams/lille1/", "http://br.soccerway.com/teams/france/lille-olympique-sporting-club-metropole/895/statistics/"));
		teams.add(new Team("Nantes", "Nantes", "Nantes", "Nantes", championship, "http://www.tablesleague.com/teams/nantes1/", "http://br.soccerway.com/teams/france/fc-nantes/889/statistics/"));
		teams.add(new Team("Nancy", "Nancy", "Nancy", "Nancy", championship, "http://www.tablesleague.com/teams/nancy1/", "http://br.soccerway.com/teams/france/association-sportive-nancy-lorraine/911/statistics/"));
		teams.add(new Team("Lorient", "Lorient", "Lorient", "Lorient", championship, "http://www.tablesleague.com/teams/lorient1/", "http://br.soccerway.com/teams/france/football-cub-lorient-bretagne-sud/907/statistics/"));
		
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
