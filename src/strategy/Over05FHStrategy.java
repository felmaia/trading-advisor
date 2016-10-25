package strategy;

import model.Match;

/**
 * 
 * @author Felipe
 */
public class Over05FHStrategy extends TradingStrategy {
	
	@Override
	public boolean analyse(Match match) {
		
		super.prepareData(match);
		/*if (match.getChampionshipRound() >= MINIMO_RODADAS && homeTeamAvgMatchesOver05HomeHT * awayTeamAvgMatchesOver05AwayHT >= TOLERANCIA_ACERTO) {
			System.out.println(match.getHomeTeam() + " x " + match.getAwayTeam() + " - "  + homeTeamAvgMatchesOver05HomeHT * awayTeamAvgMatchesOver05AwayHT);
		}*/
		return
				(match.getChampionshipRound() >= MINIMO_RODADAS) &&
				homeTeamAvgMatchesOver05HomeHT * awayTeamAvgMatchesOver05AwayHT >= TOLERANCIA_ACERTO;
				//(homeTeam.getOver05FirstHalfHome()/100*awayTeam.getOver05FirstHalfAway()/100 >= TOLERANCIA_ACERTO );
	}
}
