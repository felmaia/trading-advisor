package runnable.history;

import java.util.List;

import model.Match;
import strategy.LayAwayTeamTradingStrategy;
import strategy.TradingStrategyHistory;

public class RunLayAwayTeamStrategyHistory extends TradingStrategyHistory {
	
	{
		strategy = new LayAwayTeamTradingStrategy();
		profitPercentPerMatch = 0.3f;
		lossPercentPerMatch = 0.5f;
	}
	
	public static void main(String[] args) {
		
		TradingStrategyHistory tradingStrategyHistory = new RunLayAwayTeamStrategyHistory();
		tradingStrategyHistory.run();
	}
	
	protected void evaluateMatches(String championshipName, List<Match> matchesToInvest) {
		
		for (Match match : matchesToInvest) {
			String matchDisplay =  "";
			matchDisplay += "["+match.getDate()+"] ";
			matchDisplay += match.getHomeTeam().getName() + " " + match.getTimeHomeTeamFirstGoal() +
						"\" x " + match.getTimeAwayTeamFirstGoal() + "\" " + match.getAwayTeam().getName();
			if (
					(match.getAwayTeamHTScore() > 0) &&
					(match.getHomeTeamHTScore() == 0 ||	(match.getTimeAwayTeamFirstGoal() < match.getTimeHomeTeamFirstGoal()))
				) 
			{
				matchDisplay += " ---> ERRO!";
				setErrors(getErrors()+1);
			}	
			else {
				matchDisplay += " ---> ACERTO!";
				setSuccesses(getSuccesses()+1);
			}	
			/*if (
					match.getHomeTeamHTScore() > 0 && 
					(match.getTimeAwayTeamFirstGoal()==null || match.getTimeHomeTeamFirstGoal() < match.getTimeAwayTeamFirstGoal())
				){
				matchDisplay += " ---> ACERTO!";
				setSuccesses(getSuccesses()+1);
			}
			else {
				matchDisplay += " ---> ERRO!";
				setErrors(getErrors()+1);
			}*/
			System.out.println(matchDisplay);
		}
	}
	
}