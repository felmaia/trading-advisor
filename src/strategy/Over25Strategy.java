package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Over25Strategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return (homeTeam.getOver25Home()/100*awayTeam.getOver25Away()/100 >= TOLERANCIA_ACERTO );
	}
}
