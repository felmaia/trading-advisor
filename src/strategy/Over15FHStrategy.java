package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Over15FHStrategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return 
				(homeTeam.getOver15FirstHalfHome()/100*awayTeam.getOver15FirstHalfAway()/100 >= TOLERANCIA_ACERTO );
	}
}
