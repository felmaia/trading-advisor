package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import util.EM;
import util.Parser;

public class PopulateGermany_B {
	
	public static void main(String[] args) {
		
		try {
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Alemanha B", 
					"https://www.academiadasapostasbrasil.com/stats/match/alemanha-stats/2255517",
					"2. Bundesliga",
					34,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98150&fase=93846");
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
		
		teams.add(new Team("Braunschweig", "Braunschweig", "Braunschweig", "Eintracht Braunschweig", championship, "http://www.tablesleague.com/teams/braunschweig1/", "http://br.soccerway.com/teams/germany/braunschweiger-tsv-eintracht-von-1895-ev/1015/statistics/"));
		teams.add(new Team("Union Berlin", "Union Berlin", "Union Berlin", "1. FC Union Berlin", championship, "http://www.tablesleague.com/teams/union_berlin1/", "http://br.soccerway.com/teams/germany/1-fc-union-berlin/1026/statistics/"));
		teams.add(new Team("Hannover 96", "Hannover 96", "Hannover 96", "Hannover 96", championship, "http://www.tablesleague.com/teams/hannover_961/", "http://br.soccerway.com/teams/germany/hannover-96/972/statistics/"));
		teams.add(new Team("Heidenheim", "FC Heidenheim", "Heidenheim", "1. FC Heidenheim 1846", championship, "http://www.tablesleague.com/teams/fc_heidenheim1/", "http://br.soccerway.com/teams/germany/1-fc-heidenheim-1846/5862/statistics/"));
		teams.add(new Team("Stuttgart", "VfB Stuttgart", "Stuttgart", "Stuttgart", championship, "http://www.tablesleague.com/teams/vfb_stuttgart1/", "http://br.soccerway.com/teams/germany/vfb-stuttgart-1893-ev/962/statistics/"));
		teams.add(new Team("Würzburger Kickers", "Würzburger Kickers", "Würzburg", "Wurzburger Kickers", championship, "http://www.tablesleague.com/teams/wrzburger_kickers/", "http://br.soccerway.com/teams/germany/fc-wurzburger-kickers/11060/statistics/"));
		teams.add(new Team("Gr.Furth", "Gr.Furth", "Greuther Fürth", "Greuther Furth", championship, "http://www.tablesleague.com/teams/grfurth/", "http://br.soccerway.com/teams/germany/spvgg-greuther-furth/986/statistics/"));
		teams.add(new Team("Dusseldorf", "Fort. Dusseldorf", "Düsseldorf", "Fortuna Düsseldorf", championship, "http://www.tablesleague.com/teams/fort_dusseldorf/", "http://br.soccerway.com/teams/germany/dusseldorfer-tus-fortuna-1895-ev/1029/statistics/"));
		teams.add(new Team("Bochum", "Bochum", "Bochum", "VfL Bochum", championship, "http://www.tablesleague.com/teams/bochum1/", "http://br.soccerway.com/teams/germany/vfl-bochum-1848-fussballgemeinschaft-ev/965/statistics/"));
		teams.add(new Team("Dynamo Dresden", "Dynamo Dresden", "Dresden", "Dynamo Dresden", championship, "http://www.tablesleague.com/teams/dynamo_dresden1/", "http://br.soccerway.com/teams/germany/sg-dynamo-dresden/995/statistics/"));
		teams.add(new Team("1860 Munchen", "1860 Munchen", "1860 München", "TSV 1860 München", championship, "http://www.tablesleague.com/teams/1860_munchen1/", "http://br.soccerway.com/teams/germany/tsv-munchen-1860/978/statistics/"));
		teams.add(new Team("Aue", "Aue", "Aue", "Erzgebirge Aue", championship, "http://www.tablesleague.com/teams/aue1/", "http://br.soccerway.com/teams/germany/fc-erzgebirge-aue/985/statistics/"));
		teams.add(new Team("Karlsruhe", "Karlsruhe", "Karlsruhe", "Karlsruher SC", championship, "http://www.tablesleague.com/teams/karlsruhe1/", "http://br.soccerway.com/teams/germany/karlsruher-sc/989/statistics/"));
		teams.add(new Team("Sandhausen", "Sandhausen", "Sandhausen", "SV Sandhausen", championship, "http://www.tablesleague.com/teams/sandhausen1/", "http://br.soccerway.com/teams/germany/sv-sandhausen/5898/statistics/"));
		teams.add(new Team("St.Pauli", "St.Pauli", "St. Pauli", "FC St. Pauli", championship, "http://www.tablesleague.com/teams/stpauli/", "http://br.soccerway.com/teams/germany/fc-st-pauli-von-1910-ev/1018/statistics/"));
		teams.add(new Team("Nurnberg", "Nurnberg", "Nürnberg", "FC Nürnberg", championship, "http://www.tablesleague.com/teams/nurnberg1/", "http://br.soccerway.com/teams/germany/1-fc-nurnberg/975/statistics/"));
		teams.add(new Team("Kaiserslautern", "Kaiserslautern", "Kaiserslautern", "Kaiserslautern", championship, "http://www.tablesleague.com/teams/kaiserslautern1/", "http://br.soccerway.com/teams/germany/1-fc-kaiserslautern/973/statistics/"));
		teams.add(new Team("Bielefeld", "Bielefeld", "Bielefeld", "Arminia Bielefeld", championship, "http://www.tablesleague.com/teams/bielefeld1/", "http://br.soccerway.com/teams/germany/dsc-arminia-bielefeld/976/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
