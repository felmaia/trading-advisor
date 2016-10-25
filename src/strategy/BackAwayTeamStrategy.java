package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 * 
 *  4 posicoes diferenca entre homePosition e awayPosition
	nao perdeu como visitante nos ultimos 3 jogos (para times piores colocados)
	away team toma poucos gols como visitante
	home team faz poucos gols como mandante
	time fora nao venceu jogos fora nos ultimos 3 jogos
 */
public class BackAwayTeamStrategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return 
				(homeTeam.getPosition() - awayTeam.getPosition() >= 4) &&
				(homeTeam.getHomePosition() - awayTeam.getAwayPosition() >= 2) &&
				(haveTeamNotLostAtLeastXOfTotalYLastAwayGames(awayTeam, 2, 3, match.getDate())) &&
				(haveTeamNotWonAtLeastXOfTotalYLastHomeGames(homeTeam, 2, 3, match.getDate())) &&
				( (homeTeam.getAvgGoalsConcededHome() > 1.2 )||(awayTeam.getAvgGoalsScoredAway() >  1.2) ) 
				;
	}
}
