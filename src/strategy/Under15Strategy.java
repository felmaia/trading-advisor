package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Under15Strategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return (homeTeam.getUnder15Home()/100*awayTeam.getUnder15Away()/100 >= TOLERANCIA_ACERTO );
	}
}
