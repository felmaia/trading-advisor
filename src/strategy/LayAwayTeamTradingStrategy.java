package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 * Objetivo: LAY AWAY TEAM OU BACK HOME TEAM - HT
 * Estratégia: entrar no início contra o time visitante e sair se acontecer gol do visitante ou gol do mandante ou HT
 * Dica: apostas "contra" o tempo são mais interessantes no primeiro tempo
 */

/*# TESTAR:
-> jogos em q o time da casa não tomou gol no primeiro contra times piores posicionados (4+ posições) 
   e fez gol no primeiro tempo em pelo menos 70% dos ulitmos 3 jogos e time visitante não fez gol no 
   primeiro tempo contra times melhores posicionados (4+ posições) nos ultimos 3 jogos
*/
public class LayAwayTeamTradingStrategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		
		return 
				//mudar para time visitante nao ter marcado fora primeiro nos ultimos 3 jogos jogando contra times melhores posicionaos
				
				(match.getChampionshipRound() >= MINIMO_RODADAS) &&
				//(awayTeam.getAwayPosition() - homeTeam.getHomePosition() >= 8) &&
				(awayTeamPosition - homeTeamPosition >= 5) &&
			    (homeTeamAvgGoalsScoredHomeHT != null && homeTeamAvgGoalsScoredHomeHT > 1.1*championshipAvgGoalsScoredHomeHT) &&
				(homeTeamAvgGoalsConcededHomeHT != null && homeTeamAvgGoalsConcededHomeHT < championshipAvgGoalsScoredAwayHT) && 
				(awayTeamAvgGoalsConcededAwayHT != null && awayTeamAvgGoalsConcededAwayHT > 1.1*championshipAvgGoalsScoredHomeHT) &&
				//(homeTeamAvgMatchesOver15Home * awayTeamAvgMatchesOver15Away >= 0.6) &&
				//(homeTeamAvgMatchesScoredBeforeConcededHome != null && homeTeamAvgMatchesScoredBeforeConcededHome >= 0.70) &&
				//(awayTeamAvgMatchesConcededBeforeScoredAway != null && awayTeamAvgMatchesConcededBeforeScoredAway >= 0.70) 
				//(awayTeamAvgGoalsScoredAwayHT != null && awayTeamAvgGoalsScoredAwayHT <= championshipAvgGoalsScoredAwayHT)  
			  //(haveTeamScoredGolsHTAtLeastXOfTotalYLastHomeMatches(homeTeam, 2, 3, match.getDate())) &&
			  //(haveTeamConcededGolsHTAtMostXOfTotalYLastHomeMatches(homeTeam, 1, 3, match.getDate())) 
				//(haveTeamConcededGolsHTAtLeastXOfTotalYLastAwayMatches(awayTeam, 2, 3, match.getDate())) &&
			//(awayTeamAvgGoalsConcededAwayHT != null && awayTeamAvgGoalsConcededAwayHT >= championshipAvgGoalsScoredHomeHT) 
				(haveTeamScoredBeforeConcededHTAtLeastXOfTotalYLastHomeMatchesAgainstZLowerRankingTeams(homeTeam, 3, 3, 5, match.getDate())) &&
				(haveTeamConcededBeforeScoredHTAtLeastXOfTotalYLastAwayMatchesAgainstZHigherRankingTeams(awayTeam, 3, 3, 5, match.getDate()))
				//(haveTeamScoredGolsHTAtMostXOfTotalYLastAwayMatches(awayTeam, 1, 4, match.getDate()));
				//(haveTeamWonAtLeastXOfTotalYLastHomeGames(homeTeam, 2, 3));
				;
		
				
	}
}
