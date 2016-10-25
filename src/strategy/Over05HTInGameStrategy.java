package strategy;

import model.Match;
import model.Team;

public class Over05HTInGameStrategy extends TradingStrategy {

	@Override
	public boolean analyse(Match match) {
		
		Team homeTeam = match.getHomeTeam();
		Team awayTeam = match.getAwayTeam();
		
		return 
				( (  ( 
						(Math.abs(homeTeam.getPosition() - awayTeam.getPosition()) >= 5) &&
						(teamQuery.getTeamAvgGoalsScoredOrConcededHomeHT(homeTeam, match.getDate()) >= 1.5 && teamQuery.getTeamAvgGoalsScoredOrConcededAwayHT(awayTeam, match.getDate()) >= 1.5)
						/*(teamQuery.getTeamAvgMatchesScoredHomeHT(homeTeam) >= 0.7 && teamQuery.getTeamAvgMatchesConcededAwayHT(awayTeam) >= 0.7)
						|| 
						(teamQuery.getTeamAvgMatchesConcededHomeHT(homeTeam) >= 0.7 && teamQuery.getTeamAvgMatchesScoredAwayHT(awayTeam) >= 0.7)*/
						/*(teamQuery.getTeamAvgGoalsScoredHomeHT(homeTeam) >= 0.75 && teamQuery.getTeamAvgGoalsConcededAwayHT(awayTeam) >= 0.75)
						|| 
						(teamQuery.getTeamAvgGoalsConcededHomeHT(homeTeam) >= 0.75 && teamQuery.getTeamAvgGoalsScoredAwayHT(awayTeam) >= 0.75)*/
					  )
					  &&
					  (haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastHomeMatches(homeTeam, 2, 3, match.getDate()))
					  &&
					  (haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastAwayMatches(awayTeam, 2, 3, match.getDate()))
				  )
				 /* ||
				  (
					  haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastHomeMatches(homeTeam, 3, 4) 
					  && 
					  haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastAwayMatches(awayTeam, 3, 4)
				   )*/
				);
		
		/**
		 * 
		 87% de acerto em 25/10 e qual o % de jogos sugeridos?
		return 
				( (  ( 
						(doTeamScoreGoalsHTHome(homeTeam) && doTeamConcedeGoalsHTAway(awayTeam))
						|| 
						(doTeamScoreGoalsHTAway(awayTeam) && doTeamConcedeGoalsHTHome(homeTeam))
					  )
					  &&
					  (haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastHomeMatches(homeTeam, 3, 3))
					  &&
					  (haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastAwayMatches(awayTeam, 3, 3))
				  )
				  ||
				  (
					  haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastHomeMatches(homeTeam, 4, 4) 
					  && 
					  haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastAwayMatches(awayTeam, 4, 4)
				   )
				);
		*/	
				
		/*return ( (doTeamScoreGoalsHTHome(homeTeam) && 
				doTeamConcedeGoalsHTAway(awayTeam) && 
				haveTeamScoredGolsHTOnLastXHomeMatches(homeTeam, 2) && 
				haveTeamConcededGolsHTOnLastXAwayMatches(awayTeam, 2) )
				||
				(doTeamScoreGoalsHTAway(awayTeam) && 
				doTeamConcedeGoalsHTHome(homeTeam) && 
				haveTeamScoredGolsHTOnLastXAwayMatches(awayTeam, 2) && 
				haveTeamConcededGolsHTOnLastXHomeMatches(homeTeam, 2)) ); */
		
	}
}
