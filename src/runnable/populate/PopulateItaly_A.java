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

public class PopulateItaly_A {
	
	public static void main(String[] args) {
		
		try {
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Itália A", 
					"https://www.academiadasapostasbrasil.com/stats/match/italia-stats/2306131",
					"Serie A",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98469&fase=94415");
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
		
		teams.add(new Team("Napoli", "SSC Napoli", "Napoli", "Napoli", championship, "http://www.tablesleague.com/teams/ssc_napoli1/", "http://br.soccerway.com/teams/italy/ssc-napoli/1270/statistics/"));
		teams.add(new Team("Juventus", "Juventus", "Juventus", "Juventus", championship, "http://www.tablesleague.com/teams/juventus1/", "http://br.soccerway.com/teams/italy/juventus-fc/1242/statistics/"));
		teams.add(new Team("Roma", "Roma", "Roma", "Roma", championship, "http://www.tablesleague.com/teams/roma1/", "http://br.soccerway.com/teams/italy/as-roma/1241/statistics/"));
		teams.add(new Team("Lazio", "Lazio", "Lazio", "Lazio", championship, "http://www.tablesleague.com/teams/lazio1/", "http://br.soccerway.com/teams/italy/ss-lazio-roma/1245/statistics/"));
		teams.add(new Team("Genoa", "Genoa", "Genoa", "Genoa", championship, "http://www.tablesleague.com/teams/genoa1/", "http://br.soccerway.com/teams/italy/genoa-cfc/1276/statistics/"));
		teams.add(new Team("Sampdoria", "Sampdoria", "Sampdoria", "Sampdoria", championship, "http://www.tablesleague.com/teams/sampdoria1/", "http://br.soccerway.com/teams/italy/uc-sampdoria/1247/statistics/"));
		teams.add(new Team("Milan", "AC Milan", "Milan", "Milan", championship, "http://www.tablesleague.com/teams/ac_milan1/", "http://br.soccerway.com/teams/italy/ac-milan/1240/statistics/"));
		teams.add(new Team("Udinese", "Udinese", "Udinese", "Udinese", championship, "http://www.tablesleague.com/teams/udinese1/", "http://br.soccerway.com/teams/italy/udinese-calcio/1246/statistics/"));
		teams.add(new Team("Bologna", "Bologna", "Bologna", "Bologna", championship, "http://www.tablesleague.com/teams/bologna1/", "http://br.soccerway.com/teams/italy/bologna-fc-1909/1249/statistics/"));
		teams.add(new Team("Chievo", "Chievo", "Chievo",  "Chievo", championship, "http://www.tablesleague.com/teams/chievo1/", "http://br.soccerway.com/teams/italy/ac-chievo-verona/1248/statistics/"));
		teams.add(new Team("Pescara", "Pescara", "Pescara", "Pescara", championship, "http://www.tablesleague.com/teams/pescara1/", "http://br.soccerway.com/teams/italy/pescara-calcio/1282/statistics/"));
		teams.add(new Team("Inter", "Inter", "Inter", "Internazionale", championship, "http://www.tablesleague.com/teams/inter/", "http://br.soccerway.com/teams/italy/fc-internazionale-milano/1244/statistics/"));
		teams.add(new Team("Torino", "Torino", "Torino", "Torino", championship, "http://www.tablesleague.com/teams/torino1/", "http://br.soccerway.com/teams/italy/torino-fc/1268/statistics/"));
		teams.add(new Team("Fiorentina", "Fiorentina", "Fiorentina", "Fiorentina", championship, "http://www.tablesleague.com/teams/fiorentina1/", "http://br.soccerway.com/teams/italy/acf-fiorentina/1259/statistics/"));
		teams.add(new Team("Atalanta", "Atalanta", "Atalanta", "Atalanta", championship, "http://www.tablesleague.com/teams/atalanta1/", "http://br.soccerway.com/teams/italy/atalanta-bergamo/1255/statistics/"));
		teams.add(new Team("Empoli", "Empoli", "Empoli", "Empoli", championship, "http://www.tablesleague.com/teams/empoli1/", "http://br.soccerway.com/teams/italy/empoli-fc/1261/statistics/"));
		teams.add(new Team("Sassuolo", "Sassuolo", "Sassuolo", "Sassuolo", championship, "http://www.tablesleague.com/teams/sassuolo1/", "http://br.soccerway.com/teams/italy/us-sassuolo-calcio/5681/statistics/"));
		teams.add(new Team("Cagliari", "Cagliari", "Cagliari", "Cagliari", championship, "http://www.tablesleague.com/teams/cagliari1/", "http://br.soccerway.com/teams/italy/cagliari-calcio/1256/statistics/"));
		teams.add(new Team("Palermo", "Palermo", "Palermo", "Palermo", championship, "http://www.tablesleague.com/teams/palermo1/", "http://br.soccerway.com/teams/italy/us-citta-di-palermo/1254/statistics/"));
		teams.add(new Team("Crotone", "Crotone", "Crotone", "Crotone", championship, "http://www.tablesleague.com/teams/crotone1/", "http://br.soccerway.com/teams/italy/fc-crotone/1280/statistics/"));
		
		
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
