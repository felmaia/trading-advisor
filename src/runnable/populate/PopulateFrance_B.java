package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import util.EM;
import util.Parser;

public class PopulateFrance_B {
	
	public static void main(String[] args) {
		
		try {
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("França B", 
					"https://www.academiadasapostasbrasil.com/stats/match/franca-stats/2237908",
					"Ligue 2",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=97815&fase=93171");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
			//parser.populateMatchesTablesLeagueFrom(teams);
			
			parser.extractMatchesOGolFrom(championship);
			
			parser.populateClassificationAcademiaDasApostasFrom(teams);
			
			parser.populateGoalsSoccerWayFrom(teams);
			
			parser.populateOverUnderStatsTablesLeagueFrom(teams);
			
			//parser.updateRankingsPerRoundFromOGol(new ChampionshipQuery().getChampionshipLastRound(championship), championship);  /* O Gol */
			
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
		
		teams.add(new Team("Brest", "Brest", "Brest", "Brest", championship, "http://www.tablesleague.com/teams/brest1/", "http://br.soccerway.com/teams/france/stade-brestois-29/922/statistics/"));
		teams.add(new Team("Amiens", "Amiens", "Amiens", "Amiens", championship, "http://www.tablesleague.com/teams/amiens1/", "http://br.soccerway.com/teams/france/amiens-sporting-club-football/908/statistics/"));
		teams.add(new Team("Reims", "Reims", "Reims", "Stade de Reims", championship, "http://www.tablesleague.com/teams/reims1/", "http://br.soccerway.com/teams/france/stade-de-reims/921/statistics/"));
		teams.add(new Team("Sochaux", "Sochaux", "Sochaux", "Sochaux", championship, "http://www.tablesleague.com/teams/sochaux1/", "http://br.soccerway.com/teams/france/fc-sochaux-montbeliard/887/statistics/"));
		teams.add(new Team("Lens", "Lens", "Lens", "Lens", championship, "http://www.tablesleague.com/teams/lens1/", "http://br.soccerway.com/teams/france/racing-club-de-lens/892/statistics/"));
		teams.add(new Team("Strasbourg", "Strasbourg", "Strasbourg", "Strasbourg", championship, "http://www.tablesleague.com/teams/strasbourg1/", "http://br.soccerway.com/teams/france/racing-club-de-strasbourg/898/statistics/"));
		teams.add(new Team("Le Havre", "Le Havre", "Le Havre", "Le Havre", championship, "http://www.tablesleague.com/teams/le_havre1/", "http://br.soccerway.com/teams/france/le-havre-athletic-club-football-associat/912/statistics/"));
		teams.add(new Team("Nimes", "Nimes", "Nîmes", "Nimes", championship, "http://www.tablesleague.com/teams/nimes1/", "http://br.soccerway.com/teams/france/nimes-olympique/932/statistics/"));
		teams.add(new Team("Troyes", "Troyes", "Troyes", "Troyes", championship, "http://www.tablesleague.com/teams/troyes1/", "http://br.soccerway.com/teams/france/esperance-sportive-troyes-aube-champagne/914/statistics/"));
		teams.add(new Team("Clermont", "Clermont Foot", "Clermont", "Clermont", championship, "http://www.tablesleague.com/teams/clermont_foot1/", "http://br.soccerway.com/teams/france/clermont-foot-auvergne/916/statistics/"));
		teams.add(new Team("Valenciennes", "Valenciennes", "Valenciennes", "Valenciennes", championship, "http://www.tablesleague.com/teams/valenciennes1/", "http://br.soccerway.com/teams/france/valenciennes-anzin-football-club/933/statistics/"));
		teams.add(new Team("Ajaccio", "Ajaccio", "Ajaccio", "AC Ajaccio", championship, "http://www.tablesleague.com/teams/ajaccio1/", "http://br.soccerway.com/teams/france/athletic-club-ajaccien/900/statistics/"));
		teams.add(new Team("Bourg-Peronnas", "Bourg-Peronnas", "Bourg", "FC Bourg-Péronnas", championship, "http://www.tablesleague.com/teams/bourgperonnas1/", "http://br.soccerway.com/teams/france/football-club-bourg-peronnas/943/statistics/"));
		teams.add(new Team("Gazélec", "GFCO Ajaccio", "Gazélec", "GFC Ajaccio", championship, "http://www.tablesleague.com/teams/gfco_ajaccio1/", "http://br.soccerway.com/teams/france/gazelec-fco-ajaccio/931/statistics/"));
		teams.add(new Team("Red Star", "Red Star", "Red Star", "Red Star 93", championship, "http://www.tablesleague.com/teams/red_star1/", "http://br.soccerway.com/teams/france/red-star-fc-93/2378/statistics/"));
		teams.add(new Team("Orléans", "Orléans", "Orléans", "US Orléans", championship, "http://www.tablesleague.com/teams/orlans1/", "http://br.soccerway.com/teams/france/us-orleans/5604/statistics/"));
		teams.add(new Team("Niort", "Niort", "Niort", "Chamois Niortais", championship, "http://www.tablesleague.com/teams/niort1/", "http://br.soccerway.com/teams/france/chamois-niortais-football-club/909/statistics/"));
		teams.add(new Team("Laval", "Laval", "Laval", "Laval", championship, "http://www.tablesleague.com/teams/laval1/", "http://br.soccerway.com/teams/france/stade-lavallois-mayenne-football-club/920/statistics/"));
		teams.add(new Team("Auxerre", "Auxerre", "Auxerre", "Auxerre", championship, "http://www.tablesleague.com/teams/auxerre1/", "http://br.soccerway.com/teams/france/association-de-la-jeunesse-auxerroise/888/statistics/"));
		teams.add(new Team("Tours", "Tours", "Tours", "Tours", championship, "http://www.tablesleague.com/teams/tours1/", "http://br.soccerway.com/teams/france/tours-football-club/935/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
