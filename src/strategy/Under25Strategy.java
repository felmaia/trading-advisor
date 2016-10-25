package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Under25Strategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return 
				(match.getChampionshipRound() >= MINIMO_RODADAS) &&
				homeTeamAvgMatchesUnder25Home * awayTeamAvgMatchesUnder25Away >= TOLERANCIA_ACERTO;
				//(homeTeam.getUnder25Home()/100*awayTeam.getUnder25Away()/100 >= TOLERANCIA_ACERTO );
	}
}
