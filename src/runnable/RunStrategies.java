package runnable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import model.Championship;
import model.Match;
import query.ChampionshipQuery;
import query.MatchQuery;
import strategy.LayAwayTeamTradingStrategy;
import strategy.Over15Strategy;
import strategy.TradingStrategy;
import strategy.Under35Strategy;

public class RunStrategies {

	private static MatchQuery matchQuery = new MatchQuery();
	private static ChampionshipQuery championshipQuery = new ChampionshipQuery();
	//private static TeamQuery teamQuery = new TeamQuery();
	
	public static void main(String[] args) {
		
		List<Championship> championships = championshipQuery.getAllChampionships();
		
		/*System.out.println("\n\n\n------------------ INICIO OVER 0.5 HT ------------------\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(over05HTStrategy, championship));
		}*/
		
		/*System.out.println("\n\n\n------------------ INICIO UNDER 3.5 ------------------\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(under05HTStrategy, championship));
		}*/
		
		System.out.println("\n\n\n------------------ INICIO LAY AWAY TEAM TRADING ------------------\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new LayAwayTeamTradingStrategy(), championship));
		}
		
		/*System.out.println("\n\n\n############## INICIO BACK AWAY TEAM OU DRAW NO BET AWAY TEAM ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new BackAwayTeamStrategy(), championship));
		}*/
		
		/*System.out.println("\n\n\n############## INICIO LAY AWAY TEAM OU DRAW NO BET HOME TEAM ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new LayAwayTeamStrategy(), championship));
		}*/
		
		/*System.out.println("\n\n\n############## UNDER 1.5 ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Under15Strategy(), championship));
		}*/
		
		System.out.println("\n\n\n############## OVER 1.5 ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Over15Strategy(), championship));
		}
		
		/*System.out.println("\n\n\n############## UNDER 2.5 ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Under25Strategy(), championship));
		}*/
		
		/*System.out.println("\n\n\n############## OVER 2.5 ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Over25Strategy(), championship));
		}*/
		
		System.out.println("\n\n\n############## UNDER 3.5 ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Under35Strategy(), championship));
		}
		
		/*System.out.println("\n\n\n############## OVER 3.5 ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Over35Strategy(), championship));
		}*/
		
		/*System.out.println("\n\n\n############## UNDER 0.5 FIRST HALF ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Under05FHStrategy(), championship));
		}*/
		
		/*System.out.println("\n\n\n############## OVER 0.5 FIRST HALF ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Over05FHStrategy(), championship));
		}*/
		
		/*System.out.println("\n\n\n############## UNDER 1.5 FIRST HALF ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Under15FHStrategy(), championship));
		}*/
		
		/*System.out.println("\n\n\n############## OVER 1.5 FIRST HALF ##############\n");
		for (Championship championship : championships) {
			printSuggestedMatchesFromChampionship(championship.getName(), findSuggestedNextMatches(new Over15FHStrategy(), championship));
		}*/
	}
	
	private static void printSuggestedMatchesFromChampionship(String championshipName, List<Match> matchesToInvest) {
		
		System.out.println("\n\n\n------- CAMPEONATO "+ championshipName.toUpperCase() + " -------\n");

		for (Match match : matchesToInvest) {
			printMatchDetails(match);
		}
	}
	
	private static void printMatchDetails(Match match) {
		String matchDisplay =  "";
		matchDisplay += "["+match.getDate()+"] ";
		matchDisplay += match.getHomeTeam().getName() + " x " + match.getAwayTeam().getName();
		System.out.println(matchDisplay);
		
		/*Float homeTeamAvgUnder35Home = teamQuery.getTeamAvgMatchesUnder35Home(match.getHomeTeam(), match.getDate());
		Float awayTeamAvgUnder35Away = teamQuery.getTeamAvgMatchesUnder35Away(match.getAwayTeam(), match.getDate());
		Float championshipAvgUnder35 = championshipQuery.getChampionshipAvgUnder35(match.getChampionship(), match.getDate());
		
		System.out.println("H: " + homeTeamAvgUnder35Home + " - A: " + awayTeamAvgUnder35Away + " - C: " + championshipAvgUnder35);*/
	}
	
	private static List<Match> findSuggestedNextMatches(TradingStrategy strategy, Championship championship) {
		
		List<Match> resultMatches = new ArrayList<Match>();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			List<Match> nextMatches = matchQuery.getMatchesFrom(championship, sdf.parse("25/10/2016"));
			///List<Match> nextMatches = matchQuery.getMatchesFrom(championship, championshipQuery.getChampionshipLastRound(championship)+1);
			resultMatches = strategy.analyse(nextMatches);
			/*for (Match match : nextMatches) {
				if (strategy.analyse(match))
					resultMatches.add(match);
			}*/
		} catch (NoResultException e) {
			//campeonato chegou ao fim
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultMatches;
	}

}
