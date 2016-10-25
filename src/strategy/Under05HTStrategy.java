package strategy;

import model.Match;
import model.Team;

public class Under05HTStrategy extends TradingStrategy {

	@Override
	public boolean analyse(Match match) {
		
		Team homeTeam = match.getHomeTeam();
		Team awayTeam = match.getAwayTeam();
		
		Double homeTeamAvgTimeFirstGoalScoredHome = teamQuery.getTeamAvgTimeFirstGoalScoredHome(homeTeam, match.getDate());
		Double homeTeamAvgTimeFirstGoalConcededHome = teamQuery.getTeamAvgTimeFirstGoalConcededHome(homeTeam, match.getDate());
		Double awayTeamAvgTimeFirstGoalScoredAway = teamQuery.getTeamAvgTimeFirstGoalScoredAway(awayTeam, match.getDate());
		Double awayTeamAvgTimeFirstGoalConcededAway = teamQuery.getTeamAvgTimeFirstGoalConcededAway(awayTeam, match.getDate());
		
		Double homeTeamAvgGoalsScoredHomeHT = teamQuery.getTeamAvgGoalsScoredHomeHT(homeTeam, match.getDate());
		Double awayTeamAvgGoalsScoredAwayHT = teamQuery.getTeamAvgGoalsScoredAwayHT(awayTeam, match.getDate());
		Double homeTeamAvgGoalsConcededHomeHT = teamQuery.getTeamAvgGoalsConcededHomeHT(homeTeam, match.getDate());
		Double awayTeamAvgGoalsConcededAwayHT = teamQuery.getTeamAvgGoalsConcededAwayHT(awayTeam, match.getDate());
		
		 if ((homeTeamAvgGoalsScoredHomeHT != null && homeTeamAvgGoalsScoredHomeHT >= 0.4) && (awayTeamAvgGoalsScoredAwayHT != null && awayTeamAvgGoalsScoredAwayHT <= 0.4)) {
				return (
						(awayTeamAvgGoalsConcededAwayHT != null && awayTeamAvgGoalsConcededAwayHT <= 0.4) &&
						//(homeTeamAvgTimeFirstGoalScoredHome != null && homeTeamAvgTimeFirstGoalScoredHome > 25) &&
						//(awayTeamAvgTimeFirstGoalConcededAway != null && awayTeamAvgTimeFirstGoalConcededAway > 25) &&
						(teamQuery.getTeamAvgMatchesConcededAwayBeforeXMinutes(awayTeam, 16, match.getDate()) <= 0.15) &&
						(teamQuery.getTeamAvgMatchesScoredHomeBeforeXMinutes(homeTeam, 16, match.getDate()) <= 0.15)
					   );
						
			}
			else if ((awayTeamAvgGoalsScoredAwayHT != null && awayTeamAvgGoalsScoredAwayHT >= 0.4)&&((homeTeamAvgGoalsScoredHomeHT != null && homeTeamAvgGoalsScoredHomeHT <= 0.4))) {
				return (
						(homeTeamAvgGoalsConcededHomeHT != null && homeTeamAvgGoalsConcededHomeHT <= 0.4) &&
						//(awayTeamAvgTimeFirstGoalScoredAway != null && awayTeamAvgTimeFirstGoalScoredAway > 25) &&
						//(homeTeamAvgTimeFirstGoalConcededHome != null && homeTeamAvgTimeFirstGoalConcededHome > 25) && 
						(teamQuery.getTeamAvgMatchesScoredAwayBeforeXMinutes(awayTeam, 16, match.getDate()) <= 0.15) &&
						(teamQuery.getTeamAvgMatchesConcededHomeBeforeXMinutes(homeTeam, 16, match.getDate()) <= 0.15) 
					   );
						
			}
			else if ((homeTeamAvgGoalsScoredHomeHT != null && homeTeamAvgGoalsScoredHomeHT <= 0.4)
					&&(awayTeamAvgGoalsScoredAwayHT != null && awayTeamAvgGoalsScoredAwayHT <= 0.4)) {
				return (
						//(awayTeamAvgTimeFirstGoalConcededAway != null && awayTeamAvgTimeFirstGoalConcededAway > 25) &&
						//(homeTeamAvgTimeFirstGoalConcededHome != null && homeTeamAvgTimeFirstGoalConcededHome > 25) &&
						(homeTeamAvgGoalsConcededHomeHT != null && homeTeamAvgGoalsConcededHomeHT <= 0.4) &&
						(awayTeamAvgGoalsConcededAwayHT != null && awayTeamAvgGoalsConcededAwayHT <= 0.4) &&
						(teamQuery.getTeamAvgMatchesConcededHomeBeforeXMinutes(homeTeam, 16, match.getDate()) <= 0.15) &&
						(teamQuery.getTeamAvgMatchesConcededAwayBeforeXMinutes(awayTeam, 16, match.getDate()) <= 0.15) 
						);
			}
			return false;
		
	/*	return 
				( (  ( 
						//(Math.abs(homeTeam.getPosition() - awayTeam.getPosition()) <= 7) &&
						(homeTeamAvgTimeFirstGoalScoredHome != null && homeTeamAvgTimeFirstGoalScoredHome > 25) &&
						(homeTeamAvgTimeFirstGoalConcededHome != null && homeTeamAvgTimeFirstGoalConcededHome > 25) &&
						(awayTeamAvgTimeFirstGoalScoredAway != null && awayTeamAvgTimeFirstGoalScoredAway > 25) &&
						(awayTeamAvgTimeFirstGoalConcededAway != null && awayTeamAvgTimeFirstGoalConcededAway > 25) &&
						//(haveTeamScoredOrConcededFirst15MinutesAtMostXOfTotalYLastHomeGames(homeTeam, 1, 3, match.getDate())) &&
						//(haveTeamScoredOrConcededFirst15MinutesAtMostXOfTotalYLastAwayGames(awayTeam, 1, 3, match.getDate())) &&
						
						//(teamQuery.getTeamAvgMatchesScoredOrConcededHomeBeforeXMinutes(homeTeam, 15, match.getDate()) < 0.2) &&
						//(teamQuery.getTeamAvgMatchesScoredOrConcededAwayBeforeXMinutes(awayTeam, 15, match.getDate()) < 0.2) &&
						//(teamQuery.getTeamAvgGoalsScoredOrConcededHomeHT(homeTeam, match.getDate()) <= 1) &&
						//(teamQuery.getTeamAvgGoalsScoredOrConcededAwayHT(awayTeam, match.getDate()) <= 1)
						(homeTeamAvgGoalsConcededHomeHT == null || homeTeamAvgGoalsConcededHomeHT <= 0.4) &&
						(awayTeamAvgGoalsConcededAwayHT == null || awayTeamAvgGoalsConcededAwayHT <= 0.4) &&
						(teamQuery.getTeamAvgMatchesScoredHomeBeforeXMinutes(homeTeam, 17, match.getDate()) <= 0.15) &&
						(teamQuery.getTeamAvgMatchesConcededHomeBeforeXMinutes(homeTeam, 17, match.getDate()) <= 0.15) &&
						(teamQuery.getTeamAvgMatchesConcededAwayBeforeXMinutes(awayTeam, 17, match.getDate()) <= 0.15) &&
						(teamQuery.getTeamAvgMatchesScoredAwayBeforeXMinutes(awayTeam, 17, match.getDate()) <= 0.15) 
						
						
					  )
					  &&
					  (!haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastHomeMatches(homeTeam, 2, 3))
					  &&
					  (!haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastAwayMatches(awayTeam, 2, 3))
				  )
				);*/
		
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
