package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import query.ChampionshipQuery;
import query.MatchQuery;
import util.EM;
import util.Parser;

public class PopulateGermany_A {
	
	public static void main(String[] args) {
		
		try {
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Alemanha A", 
					"https://www.academiadasapostasbrasil.com/stats/match/alemanha-stats/2255211",
					"1. Bundesliga",
					34,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98149&fase=93845");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
			//parser.populateMatchesTablesLeagueFrom(teams);
			
			parser.extractMatchesOGolFrom(championship);
			
			parser.populateClassificationAcademiaDasApostasFrom(teams);
			
			parser.populateGoalsSoccerWayFrom(teams);
			
			parser.populateOverUnderStatsTablesLeagueFrom(teams);
			
			parser.updateRankingsPerRoundFromOGol(championshipQuery.getChampionshipLastRound(championship), championship);  /* O Gol */
			
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
		
		teams.add(new Team("Bayern Munchen", "Bayern Munchen", "Bayern München", "Bayern München", championship, "http://www.tablesleague.com/teams/bayern_munchen1/", "http://br.soccerway.com/teams/germany/fc-bayern-munchen/961/statistics/"));
		teams.add(new Team("RB Leipzig", "RasenBallsport Leipzig", "Leipzig", "RB Leipzig", championship, "http://www.tablesleague.com/teams/rasenballsport_leipzig/", "http://br.soccerway.com/teams/germany/rb-leipzig/13410/statistics/"));
		teams.add(new Team("Koln", "FC Koln", "Köln", "FC Köln", championship, "http://www.tablesleague.com/teams/fc_koln1/", "http://br.soccerway.com/teams/germany/1-fc-koln/980/statistics/"));
		teams.add(new Team("Borussia Dortmund", "Bor. Dortmund", "Dortmund", "Borussia Dortmund", championship, "http://www.tablesleague.com/teams/bor_dortmund/", "http://br.soccerway.com/teams/germany/bv-borussia-09-dortmund/964/statistics/"));
		teams.add(new Team("Hertha Berlin", "Hertha Berlin", "Hertha BSC", "Hertha BSC", championship, "http://www.tablesleague.com/teams/hertha_berlin1/", "http://br.soccerway.com/teams/germany/hertha-bsc-berlin/974/statistics/"));
		teams.add(new Team("M'gladbach", "Bor. Monchengladbach", "M'gladbach", "Borussia M´gladbach", championship, "http://www.tablesleague.com/teams/bor_monchengladbach/", "http://br.soccerway.com/teams/germany/borussia-monchengladbach/971/statistics/"));
		teams.add(new Team("Frankfurt", "Eintracht Frankfurt", "Frankfurt", "Eintracht Frankfurt", championship, "http://www.tablesleague.com/teams/eintracht_frankfurt1/", "http://br.soccerway.com/teams/germany/eintracht-frankfurt/979/statistics/"));
		teams.add(new Team("Wolfsburg", "Wolfsburg", "Wolfsburg", "Wolfsburg", championship, "http://www.tablesleague.com/teams/wolfsburg1/", "http://br.soccerway.com/teams/germany/vfl-wolfsburg/968/statistics/"));
		teams.add(new Team("Hoffenheim", "Hoffenheim", "Hoffenheim", "TSG Hoffenheim", championship, "http://www.tablesleague.com/teams/hoffenheim1/", "http://br.soccerway.com/teams/germany/tsg-1899-hoffenheim-ev/1001/statistics/"));
		teams.add(new Team("Bayer Leverkusen", "Bayer Leverkusen", "Leverkusen", "Bayer Leverkusen", championship, "http://www.tablesleague.com/teams/bayer_leverkusen1/", "http://br.soccerway.com/teams/germany/bayer-04-leverkusen/963/statistics/"));
		teams.add(new Team("Augsburg", "Augsburg", "Augsburg", "FC Augsburg", championship, "http://www.tablesleague.com/teams/augsburg1/", "http://br.soccerway.com/teams/germany/fc-augsburg/1000/statistics/"));
		teams.add(new Team("Freiburg", "Freiburg", "Freiburg", "SC Freiburg", championship, "http://www.tablesleague.com/teams/freiburg1/", "http://br.soccerway.com/teams/germany/sc-freiburg/970/statistics/"));
		teams.add(new Team("Darmstadt", "Darmstadt", "Darmstadt 98", "Darmstadt 98", championship, "http://www.tablesleague.com/teams/darmstadt1/", "http://br.soccerway.com/teams/germany/sv-darmstadt-1898-ev/2549/statistics/"));
		teams.add(new Team("Mainz", "Mainz 05", "Mainz 05", "Mainz", championship, "http://www.tablesleague.com/teams/mainz_051/", "http://br.soccerway.com/teams/germany/1-fsv-mainz-05/977/statistics/"));
		teams.add(new Team("Ingolstadt", "Ingolstadt", "Ingolstadt", "FC Ingolstadt 04", championship, "http://www.tablesleague.com/teams/ingolstadt1/", "http://br.soccerway.com/teams/germany/fc-ingolstadt-04/5476/statistics/"));
		teams.add(new Team("Hamburg", "Hamburger SV", "Hamburg", "Hamburger SV", championship, "http://www.tablesleague.com/teams/hamburger_sv1/", "http://br.soccerway.com/teams/germany/hamburger-sv/967/statistics/"));
		teams.add(new Team("Schalke 04", "Schalke 04", "Schalke 04", "Schalke 04", championship, "http://www.tablesleague.com/teams/schalke_041/", "http://br.soccerway.com/teams/germany/fc-schalke-04/966/statistics/"));
		teams.add(new Team("Werder Bremen", "Werder Bremen", "Bremen", "Werder Bremen", championship, "http://www.tablesleague.com/teams/werder_bremen1/", "http://br.soccerway.com/teams/germany/sv-werder-bremen/960/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
