package runnable;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import model.Championship;
import model.Team;
import query.ChampionshipQuery;
import query.MatchQuery;
import query.TeamQuery;
import util.EM;
import util.Parser;

public class Update {

	private static ChampionshipQuery championshipQuery = new ChampionshipQuery();
	private static MatchQuery matchQuery = new MatchQuery();
	private static TeamQuery teamQuery = new TeamQuery();
	private static Parser parser = new Parser();
	
	public static void main(String[] args) throws InterruptedException {
		
		try {
			
			List<Championship> championships = championshipQuery.getAllChampionships();
		/*	championships = Arrays.asList(
										  championshipQuery.getChampionshipByName("Itália B"),
										  championshipQuery.getChampionshipByName("Portugal B"),
										  championshipQuery.getChampionshipByName("Espanha B"));*/
			
			for (Championship championship : championships) {
				
				System.out.println("---- INÍCIO " + championship.getName().toUpperCase() +" ----");
				
				EM.getInstance().getTransaction().begin();
				
				List<Team> teams = teamQuery.getTeamsFromChampionship(championship);
				
				parser.populateMatchesOGol((matchQuery.getOutdatedMatches(championship)));
					  //parser.populateMatchesTablesLeague((matchQuery.getOutdatedMatches(championship))); /* Tables League */
				parser.populateOverUnderStatsTablesLeagueFrom(teams);/* Tables League */
				parser.populateClassificationAcademiaDasApostasFrom(teams); /* Academia das Apostas Brasil */
				parser.populateGoalsSoccerWayFrom(teams); /* Soccer Way */
				parser.updateRankingsPerRoundFromOGol(championshipQuery.getChampionshipLastRound(championship), championship); /* O Gol */
				
				EM.getInstance().getTransaction().commit();
				EM.getInstance().clear();
				
				System.out.println("---- FIM " + championship.getName().toUpperCase() +" ----");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}

	}
	
}
