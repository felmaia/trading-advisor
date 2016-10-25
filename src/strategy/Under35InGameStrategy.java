package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 * Objetivo: UNDER 3.5
 * Estratégia: entrar no 2º tempo se tiver saído no máximo 1 gol HT (3.5) e sair se atingir objetivo ou ocorrer gol
 * Objetivo: 20% de lucro
 * Dica: apostas a favor do tempo são mais interessantes no segundo tempo
 */
public class Under35InGameStrategy extends TradingStrategy {

	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		
		//teamQuery.getTeamAvgMatchesScoredAwayBeforeXMinutes(awayTeam, 16, match.getDate())
		//teamQuery.getTeamAvgMatchesScoredHomeBeforeXMinutes(homeTeam, 17, match.getDate())
		//teamQuery.getTeamAvgMatchesScoredOrConcededHomeBeforeXMinutes(homeTeam, 15, match.getDate())
		//(haveTeamScoredOrConcededFirst15MinutesAtMostXOfTotalYLastHomeGames(homeTeam, 1, 3, match.getDate()))
		
		return 
				(   
						(Math.abs(homeTeamPosition - awayTeamPosition) <= 6) &&
						//(teamQuery.getTeamAvgGoalsScoredOrConcededHomeHT(homeTeam, match.getDate()) <= 1) &&
						//(teamQuery.getTeamAvgGoalsScoredOrConcededAwayHT(awayTeam, match.getDate()) <= 1)
						/*(homeTeamAvgGoalsScoredHome != null && homeTeamAvgGoalsScoredHome <= 0.75*championshipAvgGoalsScoredHome) &&
						(awayTeamAvgGoalsScoredAway != null && awayTeamAvgGoalsScoredAway <= 0.75*championshipAvgGoalsScoredAway) &&
						(homeTeamAvgGoalsConcededHome != null && homeTeamAvgGoalsConcededHome <= 0.75*championshipAvgGoalsScoredAway) &&
						(awayTeamAvgGoalsConcededAway != null && awayTeamAvgGoalsConcededAway <= 0.85*championshipAvgGoalsScoredHome)*/
						(homeTeamAvgMatchesUnder35Home != null && homeTeamAvgMatchesUnder35Home >= 0.8) && //championshipAvgMatchesUnder35
						(awayTeamAvgMatchesUnder35Away != null && awayTeamAvgMatchesUnder35Away >= 0.8) && //championshipAvgMatchesUnder35
						(homeTeamAvgMatchesUnder05HomeHT != null && homeTeamAvgMatchesUnder05HomeHT >= 0.6) && 
						(awayTeamAvgMatchesUnder05AwayHT != null && awayTeamAvgMatchesUnder05AwayHT >= 0.6) 
					  
					 /*(!haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastHomeMatches(homeTeam, 2, 5, match.getDate()))
					  &&
					  (!haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastAwayMatches(awayTeam, 2, 5, match.getDate()))*/
			   );
	}
}	
