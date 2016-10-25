package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Over15Strategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		return 
				(match.getChampionshipRound() >= MINIMO_RODADAS) &&
				homeTeamAvgMatchesOver15Home * awayTeamAvgMatchesOver15Away >= TOLERANCIA_ACERTO;
				//(homeTeam.getOver15Home()/100*awayTeam.getOver15Away()/100 >= TOLERANCIA_ACERTO );
	}
}
