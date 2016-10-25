package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Under15FHStrategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return 
				(match.getChampionshipRound() >= MINIMO_RODADAS) &&
				homeTeamAvgMatchesUnder15HomeHT * awayTeamAvgMatchesUnder15AwayHT >= TOLERANCIA_ACERTO;
				//(homeTeam.getUnder15FirstHalfHome()/100*awayTeam.getUnder15FirstHalfAway()/100 >= TOLERANCIA_ACERTO );
	}
}
