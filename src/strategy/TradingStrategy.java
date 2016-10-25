package strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Match;
import model.Team;
import query.ChampionshipQuery;
import query.MatchQuery;
import query.TeamQuery;

public abstract class TradingStrategy {
	
	protected static final float TOLERANCIA_ACERTO = 0.80F;
	protected static final float MINIMO_RODADAS = 8;
	protected static TeamQuery teamQuery = new TeamQuery();
	protected static ChampionshipQuery championshipQuery = new ChampionshipQuery();
	
	protected Team homeTeam;
	protected Team awayTeam;
	
	protected Integer homeTeamPosition;
	protected Integer awayTeamPosition;
	
	protected Double homeTeamAvgGoalsScoredHomeHT;
	protected Double homeTeamAvgGoalsConcededHomeHT;
	protected Double awayTeamAvgGoalsScoredAwayHT;
	protected Double awayTeamAvgGoalsConcededAwayHT;
	
	protected Double homeTeamAvgGoalsScoredHome;
	protected Double homeTeamAvgGoalsConcededHome;
	protected Double awayTeamAvgGoalsScoredAway;
	protected Double awayTeamAvgGoalsConcededAway;
	
	protected Double teamAvgGoalsScoredOrConcededHomeHT;
	protected Double teamAvgGoalsScoredOrConcededAwayHT;
	
	protected Double homeTeamAvgTimeFirstGoalScoredHome;
	protected Double homeTeamAvgTimeFirstGoalConcededHome;
	protected Double awayTeamAvgTimeFirstGoalScoredAway;
	protected Double awayTeamAvgTimeFirstGoalConcededAway;
	
	protected float homeTeamAvgMatchesScoredHomeHT;
	protected float homeTeamAvgMatchesConcededHomeHT;
	protected float awayTeamAvgMatchesScoredAwayHT;
	protected float awayTeamAvgMatchesConcededAwayHT;
	
	protected Float homeTeamAvgMatchesScoredBeforeConcededHome;
	protected Float awayTeamAvgMatchesConcededBeforeScoredAway;
	
	protected Double championshipAvgGoalsScoredHomeHT;
	protected Double championshipAvgGoalsScoredAwayHT;
	protected Double championshipAvgGoalsScoredHome;
	protected Double championshipAvgGoalsScoredAway;
	
	protected Float homeTeamAvgMatchesUnder25Home;
	protected Float awayTeamAvgMatchesUnder25Away;
	
	protected Float homeTeamAvgMatchesUnder35Home;
	protected Float awayTeamAvgMatchesUnder35Away;
	protected Float championshipAvgMatchesUnder35;
	
	
	protected Float homeTeamAvgMatchesUnder05HomeHT;
	protected Float awayTeamAvgMatchesUnder05AwayHT;
	protected Float homeTeamAvgMatchesOver05HomeHT;
	protected Float awayTeamAvgMatchesOver05AwayHT;
	protected Float homeTeamAvgMatchesUnder15HomeHT;
	protected Float awayTeamAvgMatchesUnder15AwayHT;
	protected Float homeTeamAvgMatchesOver15Home;
	protected Float awayTeamAvgMatchesOver15Away;
	
	public abstract boolean analyse(Match match);
	
	
	public List<Match> analyse(List<Match> matches) {
		
		List<Match> resultMatches = new ArrayList<Match>();

		for (Match match : matches) {
			
			if (analyse(match)) {
				resultMatches.add(match);
			}
		}
		
		return resultMatches;
	}
	
	protected void prepareData(Match match) {
		
		homeTeam = match.getHomeTeam();
		awayTeam = match.getAwayTeam();
		
		homeTeamPosition = match.getChampionshipRound()==1 ? 0 : teamQuery.getTeamPositionByRoundAndChampionship(
				homeTeam, match.getChampionshipRound()-1, match.getChampionship());
		awayTeamPosition = match.getChampionshipRound()==1 ? 0 : teamQuery.getTeamPositionByRoundAndChampionship(
				awayTeam, match.getChampionshipRound()-1, match.getChampionship());
		
		homeTeamAvgGoalsScoredHomeHT = teamQuery.getTeamAvgGoalsScoredHomeHT(homeTeam, match.getDate());
		homeTeamAvgGoalsConcededHomeHT = teamQuery.getTeamAvgGoalsConcededHomeHT(homeTeam, match.getDate());
		awayTeamAvgGoalsScoredAwayHT = teamQuery.getTeamAvgGoalsScoredAwayHT(awayTeam, match.getDate());
		awayTeamAvgGoalsConcededAwayHT = teamQuery.getTeamAvgGoalsConcededAwayHT(awayTeam, match.getDate());
		
		homeTeamAvgGoalsScoredHome = teamQuery.getTeamAvgGoalsScoredHome(homeTeam, match.getDate());
		homeTeamAvgGoalsConcededHome = teamQuery.getTeamAvgGoalsConcededHome(homeTeam, match.getDate());
		awayTeamAvgGoalsScoredAway = teamQuery.getTeamAvgGoalsScoredAway(awayTeam, match.getDate());
		awayTeamAvgGoalsConcededAway = teamQuery.getTeamAvgGoalsConcededAway(awayTeam, match.getDate());
		
		teamAvgGoalsScoredOrConcededHomeHT = teamQuery.getTeamAvgGoalsScoredOrConcededHomeHT(homeTeam, match.getDate());
		teamAvgGoalsScoredOrConcededAwayHT = teamQuery.getTeamAvgGoalsScoredOrConcededAwayHT(awayTeam, match.getDate());
		
		homeTeamAvgMatchesScoredHomeHT = teamQuery.getTeamAvgMatchesScoredHomeHT(homeTeam, match.getDate());
		homeTeamAvgMatchesConcededHomeHT = teamQuery.getTeamAvgMatchesConcededHomeHT(homeTeam, match.getDate());
		awayTeamAvgMatchesScoredAwayHT = teamQuery.getTeamAvgMatchesScoredAwayHT(awayTeam, match.getDate());
		awayTeamAvgMatchesConcededAwayHT = teamQuery.getTeamAvgMatchesConcededAwayHT(awayTeam, match.getDate());
		
		homeTeamAvgTimeFirstGoalScoredHome = teamQuery.getTeamAvgTimeFirstGoalScoredHome(homeTeam, match.getDate());
		homeTeamAvgTimeFirstGoalConcededHome = teamQuery.getTeamAvgTimeFirstGoalConcededHome(homeTeam, match.getDate());
		awayTeamAvgTimeFirstGoalScoredAway = teamQuery.getTeamAvgTimeFirstGoalScoredAway(awayTeam, match.getDate());
		awayTeamAvgTimeFirstGoalConcededAway = teamQuery.getTeamAvgTimeFirstGoalConcededAway(awayTeam, match.getDate());
		
		homeTeamAvgMatchesScoredBeforeConcededHome = teamQuery.getTeamAvgMatchesScoredBeforeConcededHomeHT(homeTeam, match.getDate());
		awayTeamAvgMatchesConcededBeforeScoredAway = teamQuery.getTeamAvgMatchesConcededBeforeScoredAwayHT(awayTeam, match.getDate());
		
		homeTeamAvgMatchesUnder25Home = teamQuery.getTeamAvgMatchesUnder25Home(homeTeam, match.getDate());
		awayTeamAvgMatchesUnder25Away = teamQuery.getTeamAvgMatchesUnder25Away(awayTeam, match.getDate());
		
		homeTeamAvgMatchesUnder35Home = teamQuery.getTeamAvgMatchesUnder35Home(homeTeam, match.getDate());
		awayTeamAvgMatchesUnder35Away = teamQuery.getTeamAvgMatchesUnder35Away(awayTeam, match.getDate());
		
		homeTeamAvgMatchesUnder05HomeHT = teamQuery.getTeamAvgMatchesUnder05HomeHT(homeTeam, match.getDate());
		awayTeamAvgMatchesUnder05AwayHT = teamQuery.getTeamAvgMatchesUnder05AwayHT(awayTeam, match.getDate());
		
		homeTeamAvgMatchesOver05HomeHT = teamQuery.getTeamAvgMatchesOver05HomeHT(homeTeam, match.getDate());
		awayTeamAvgMatchesOver05AwayHT = teamQuery.getTeamAvgMatchesOver05AwayHT(awayTeam, match.getDate());
		
		homeTeamAvgMatchesUnder15HomeHT = teamQuery.getTeamAvgMatchesUnder15HomeHT(homeTeam, match.getDate());
		awayTeamAvgMatchesUnder15AwayHT = teamQuery.getTeamAvgMatchesUnder15AwayHT(awayTeam, match.getDate());
		
		homeTeamAvgMatchesOver15Home = teamQuery.getTeamAvgMatchesOver15Home(homeTeam, match.getDate());
		awayTeamAvgMatchesOver15Away = teamQuery.getTeamAvgMatchesOver15Away(awayTeam, match.getDate());
		
		championshipAvgGoalsScoredHomeHT = championshipQuery.getChampionshipAvgGoalsScoredHomeHT(
				match.getChampionship(), match.getDate());
		
		championshipAvgGoalsScoredAwayHT = championshipQuery.getChampionshipAvgGoalsScoredAwayHT(
				match.getChampionship(), match.getDate());
		
		championshipAvgGoalsScoredHome = championshipQuery.getChampionshipAvgGoalsScoredHome(
				match.getChampionship(), match.getDate());
		
		championshipAvgGoalsScoredAway = championshipQuery.getChampionshipAvgGoalsScoredAwayHT(
				match.getChampionship(), match.getDate());
		
		championshipAvgMatchesUnder35 = championshipQuery.getChampionshipAvgMatchesUnder35(match.getChampionship(), match.getDate());
	}
	
	protected boolean doTeamScoreGoalsHTHome(Team team) {
		return (team.getAvgGoalsScoredHome() > 1 && team.getAvgTimeFirstGoalScoredHome() < 45);
	}
	
	protected boolean doTeamConcedeGoalsHTHome(Team team) {
		return (team.getAvgGoalsConcededHome() > 1 && team.getAvgTimeFirstGoalConcededHome() < 45);
	}
	
	protected boolean doTeamScoreGoalsHTAway(Team team) {
		return (team.getAvgGoalsScoredAway() > 1 && team.getAvgTimeFirstGoalScoredAway() < 45);
	}
	
	protected boolean doTeamConcedeGoalsHTAway(Team team) {
		return (team.getAvgGoalsConcededAway() > 1 && team.getAvgTimeFirstGoalConcededAway() < 45 /* 55? */);
	}
	
	protected boolean haveTeamScoredGolsHTAtLeastXOfTotalYLastHomeMatches(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			if (match.getHomeTeamHTScore() > 0)
				count ++;
		}
		return count >= atLeastMatches;
	}
	
	protected boolean haveTeamScoredBeforeConcededHTAtLeastXOfTotalYLastHomeMatchesAgainstZLowerRankingTeams(
			Team team, int atLeastMatches, int totalMatches, int diffRankings, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		TeamQuery  teamQuery = new TeamQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			int homeTeamPosition = teamQuery.getTeamPositionByRoundAndChampionship(
					match.getHomeTeam(), match.getChampionshipRound(), match.getChampionship());
			int awayTeamPosition = teamQuery.getTeamPositionByRoundAndChampionship(
					match.getAwayTeam(), match.getChampionshipRound(), match.getChampionship());
			if (
				  !(
							(match.getAwayTeamHTScore() > 0) &&
							(match.getHomeTeamHTScore() == 0 ||	(match.getTimeAwayTeamFirstGoal() < match.getTimeHomeTeamFirstGoal())) &&
							(awayTeamPosition - homeTeamPosition  >= diffRankings)
					)
				 
				)
				count ++;
		}
		return count >= atLeastMatches;
	}
	
	protected boolean haveTeamScoredGolsHTAtLeastXOfTotalYLastAwayMatches(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			if (match.getAwayTeamHTScore() > 0)
				count++;
		}
		return count >= atLeastMatches;
	}
	
	protected boolean haveTeamScoredGolsHTAtMostXOfTotalYLastAwayMatches(Team team, int atMostMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			if (match.getAwayTeamHTScore() > 0)
				count++;
		}
		return count <= atMostMatches;
	}
	
	protected boolean haveTeamConcededGolsHTAtLeastXOfTotalYLastHomeMatches(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			if (match.getAwayTeamHTScore() > 0)
				count++;
		}
		return count >= atLeastMatches;
	}
	
	protected boolean haveTeamConcededGolsHTAtMostXOfTotalYLastHomeMatches(Team team, int atMostMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			if (match.getAwayTeamHTScore() > 0)
				count++;
		}
		return count <= atMostMatches;
	}
	
	protected boolean haveTeamConcededGolsHTAtLeastXOfTotalYLastAwayMatches(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			if (match.getHomeTeamHTScore() > 0)
				count++;
		}
		return count >= atLeastMatches;
	}
	
	protected boolean haveTeamConcededBeforeScoredHTAtLeastXOfTotalYLastAwayMatchesAgainstZHigherRankingTeams(
			Team team, int atLeastMatches, int totalMatches, int diffRankings, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		TeamQuery  teamQuery = new TeamQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			int homeTeamPosition = teamQuery.getTeamPositionByRoundAndChampionship(
					match.getHomeTeam(), match.getChampionshipRound(), match.getChampionship());
			int awayTeamPosition = teamQuery.getTeamPositionByRoundAndChampionship(
					match.getAwayTeam(), match.getChampionshipRound(), match.getChampionship());
			if (!
					(
							(match.getAwayTeamHTScore() > 0) &&
							(match.getHomeTeamHTScore() == 0 ||	(match.getTimeAwayTeamFirstGoal() < match.getTimeHomeTeamFirstGoal())) &&
							(awayTeamPosition - homeTeamPosition >= diffRankings)
					)	
				)
				count ++;
		}
		return count >= atLeastMatches;
	}
	
	protected boolean haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastHomeMatches(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		for (Match match : matches) {
			if ( (match.getHomeTeamHTScore() > 0) || (match.getAwayTeamHTScore() > 0) )
				count ++;
		}
		return count >= atLeastMatches;
	}
	
	protected boolean haveTeamScoredOrConcededGolsHTAtLeastXOfTotalYLastAwayMatches(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int count = 0;
		//int matchesWithHTGoals = 0;
		for (Match match : matches) {
			if ( (match.getHomeTeamHTScore() > 0) || (match.getAwayTeamHTScore() > 0) )
				//matchesWithHTGoals++;
				count ++;
		}
		return count >= atLeastMatches;
		//float avgMatchesWithHTGoals = (float)matchesWithHTGoals/5;
		//return avgMatchesWithHTGoals >= 0.75; 
	}
	
	protected boolean haveTeamNotLostAtLeastXOfTotalYLastAwayGames(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int result = 0;
		for (Match match : matches) {
			if (match.getAwayTeamScore() >= match.getHomeTeamScore())
				result++;
		}
		return result >= atLeastMatches; 
	}
	
	protected boolean haveTeamNotLostAtLeastXOfTotalYLastHomeGames(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int result = 0;
		for (Match match : matches) {
			if (match.getHomeTeamScore() >= match.getAwayTeamScore())
				result++;
		}
		return result >= atLeastMatches; 
	}
	
	protected boolean haveTeamNotWonAtLeastXOfTotalYLastHomeGames(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int result = 0;
		for (Match match : matches) {
			if (match.getAwayTeamScore() >= match.getHomeTeamScore())
				result++;
		}
		return result >= atLeastMatches; 
	}
	
	protected boolean haveTeamNotWonAtLeastXOfTotalYLastAwayGames(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int result = 0;
		for (Match match : matches) {
			if (match.getHomeTeamScore() >= match.getAwayTeamScore())
				result++;
		}
		return result >= atLeastMatches; 
	}
	
	protected boolean haveTeamWonAtLeastXOfTotalYLastHomeGames(Team team, int atLeastMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int wonMatches = 0;
		for (Match match : matches) {
			if (match.getHomeTeamScore() > match.getAwayTeamScore())
				wonMatches++;
		}
		return wonMatches >= atLeastMatches; 
	}
	
	protected boolean haveTeamScoredOrConcededFirst15MinutesAtMostXOfTotalYLastHomeGames(Team team, int atMostMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXHomeMatchesFrom(team, totalMatches, dateLimit);
		int first15MinutesGoals = 0;
		for (Match match : matches) {
			if ( (match.getHomeTeamHTScore() > 0 && match.getTimeHomeTeamFirstGoal()  <=  15) ||
					(match.getAwayTeamHTScore() > 0 && match.getTimeAwayTeamFirstGoal()  <=  15) )
				first15MinutesGoals++;
		}
		return first15MinutesGoals <= atMostMatches; 
	}
	
	protected boolean haveTeamScoredOrConcededFirst15MinutesAtMostXOfTotalYLastAwayGames(Team team, int atMostMatches, int totalMatches, Date dateLimit) {
		
		MatchQuery matchQuery = new MatchQuery();
		List<Match> matches = matchQuery.getLastXAwayMatchesFrom(team, totalMatches, dateLimit);
		int first15MinutesGoals = 0;
		for (Match match : matches) {
			if ( (match.getHomeTeamHTScore() > 0 && match.getTimeHomeTeamFirstGoal()  <=  15) ||
					(match.getAwayTeamHTScore() > 0 && match.getTimeAwayTeamFirstGoal()  <=  15) )
				first15MinutesGoals++;
		}
		return first15MinutesGoals <= atMostMatches; 
	}

}
