package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Over35Strategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return (homeTeam.getOver35Home()/100*awayTeam.getOver35Away()/100 >= TOLERANCIA_ACERTO );
	}
}
