package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Under05FHStrategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return (homeTeam.getUnder05FirstHalfHome()/100*awayTeam.getUnder05FirstHalfAway()/100 >= TOLERANCIA_ACERTO );
	}
}
