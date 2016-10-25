package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 * 
 * OU DRAW NO BET HOME TEAM
 *  4 posicoes diferenca entre awayPosition e homePosition
	nao perdeu em casa nos ultimos 3 jogos (para times piores colocados)
	time toma poucos gols em casa
	time fora faz poucos gols como visitante
	time fora nao venceu jogos fora nos ultimos 3 jogos
 */
public class LayAwayTeamStrategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return 
				(awayTeam.getPosition() - homeTeam.getPosition() >= 4) &&
				(awayTeam.getAwayPosition() - homeTeam.getHomePosition() >= 6) &&
				(haveTeamNotLostAtLeastXOfTotalYLastHomeGames(homeTeam, 3, 3, match.getDate())) &&
				(haveTeamNotWonAtLeastXOfTotalYLastAwayGames(awayTeam, 2, 3, match.getDate())) &&
				( (homeTeam.getAvgGoalsConcededHome() < 1.2 )||(awayTeam.getAvgGoalsScoredAway() <  1.2) ) 
				;
	}
}
