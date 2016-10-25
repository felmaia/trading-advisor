package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Under35Strategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return 
				(match.getChampionshipRound() >= MINIMO_RODADAS) &&
				homeTeamAvgMatchesUnder35Home * awayTeamAvgMatchesUnder35Away >= TOLERANCIA_ACERTO;
				//(homeTeam.getUnder35Home()/100*awayTeam.getUnder35Away()/100 >= TOLERANCIA_ACERTO );
	}
}
