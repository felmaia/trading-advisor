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

public class PopulateHoland_A {
	
	public static void main(String[] args) {
		
		try {
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Holanda A", 
					"https://www.academiadasapostasbrasil.com/stats/match/holanda-stats/2240924",
					"Eredivisie",
					34,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98460&fase=94391");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
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
		
		teams.add(new Team("Feyenoord", "Feyenoord", "Feyenoord" , "Feyenoord", championship, "http://www.tablesleague.com/teams/feyenoord1/", "http://br.soccerway.com/teams/netherlands/feyenoord-rotterdam-nv/1518/statistics/"));
		teams.add(new Team("PSV", "PSV Eindhoven", "PSV" , "PSV", championship, "http://www.tablesleague.com/teams/psv_eindhoven1/", "http://br.soccerway.com/teams/netherlands/psv-nv/1517/statistics/"));
		teams.add(new Team("Heerenveen", "Heerenveen", "Heerenveen", "Heerenveen", championship, "http://www.tablesleague.com/teams/heerenveen1/", "http://br.soccerway.com/teams/netherlands/sportclub-heerenveen/1519/statistics/"));
		teams.add(new Team("Ajax", "Ajax", "Ajax", "Ajax", championship, "http://www.tablesleague.com/teams/ajax1/", "http://br.soccerway.com/teams/netherlands/afc-ajax/1515/statistics/"));
		teams.add(new Team("Vitesse", "Vitesse", "Vitesse" , "Vitesse", championship, "http://www.tablesleague.com/teams/vitesse1/", "http://br.soccerway.com/teams/netherlands/sbv-vitesse/1530/statistics/"));
		teams.add(new Team("AZ Alkmaar", "AZ Alkmaar", "AZ" , "AZ Alkmaar", championship, "http://www.tablesleague.com/teams/az_alkmaar1/", "http://br.soccerway.com/teams/netherlands/stichting-az/1516/statistics/"));
		teams.add(new Team("Den Haag", "Den Haag", "ADO" , "ADO Den Haag", championship, "http://www.tablesleague.com/teams/den_haag1/", "http://br.soccerway.com/teams/netherlands/hfc-ado-den-haag/1514/statistics/"));
		teams.add(new Team("Excelsior", "Excelsior", "Excelsior" , "Excelsior", championship, "http://www.tablesleague.com/teams/excelsior1/", "http://br.soccerway.com/teams/netherlands/sbv-excelsior/1532/statistics/"));
		teams.add(new Team("Twente", "Twente", "Twente" , "FC Twente", championship, "http://www.tablesleague.com/teams/twente1/", "http://br.soccerway.com/teams/netherlands/stichting-fc-twente-65/1522/statistics/"));
		teams.add(new Team("Sparta", "Sparta", "Sparta" , "Sparta Rotterdam", championship, "http://www.tablesleague.com/teams/sparta1/", "http://br.soccerway.com/teams/netherlands/sparta-rotterdam-bv/1535/statistics/"));
		teams.add(new Team("Willem II", "Willem II", "Willem II" , "Willem II", championship, "http://www.tablesleague.com/teams/willem_ii1/", "http://br.soccerway.com/teams/netherlands/sbv-willem-ii-tilburg/1521/statistics/"));
		teams.add(new Team("Heracles", "Heracles", "Heracles" , "Heracles Almelo", championship, "http://www.tablesleague.com/teams/heracles1/", "http://br.soccerway.com/teams/netherlands/stichting-heracles-almelo/1536/statistics/"));
		teams.add(new Team("Groningen", "Groningen", "Groningen" , "FC Groningen", championship, "http://www.tablesleague.com/teams/groningen1/", "http://br.soccerway.com/teams/netherlands/stichting-fc-groningen/1527/statistics/"));
		teams.add(new Team("NEC Nijmegen", "NEC Nijmegen", "NEC" , "NEC", championship, "http://www.tablesleague.com/teams/nec_nijmegen1/", "http://br.soccerway.com/teams/netherlands/nijmegen-eendracht-combinatie/1528/statistics/"));
		teams.add(new Team("Go Ahead Eagles", "Go Ahead Eagles", "Go Ahead" , "Go Ahead Eagles", championship, "http://www.tablesleague.com/teams/go_ahead_eagles1/", "http://br.soccerway.com/teams/netherlands/stichting-go-ahead-eagles/1540/statistics/"));
		teams.add(new Team("Utrecht", "Utrecht", "Utrecht" , "FC Utrecht", championship, "http://www.tablesleague.com/teams/utrecht1/", "http://br.soccerway.com/teams/netherlands/stichting-fc-utrecht/1523/statistics/"));
		teams.add(new Team("Roda", "Roda", "Roda" , "Roda", championship, "http://www.tablesleague.com/teams/roda1/", "http://br.soccerway.com/teams/netherlands/roda-jc/1520/statistics/"));
		teams.add(new Team("Zwolle", "Zwolle", "Zwolle" , "Zwolle", championship, "http://www.tablesleague.com/teams/zwolle1/", "http://br.soccerway.com/teams/netherlands/bvo-fc-zwolle/1529/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
