package runnable.history;

import java.util.List;

import model.Match;
import strategy.TradingStrategyHistory;
import strategy.Under05HTStrategy;

public class RunUnder05StrategyHistory extends TradingStrategyHistory {
	
	{
		strategy = new Under05HTStrategy();
		profitPercentPerMatch = 0.4f;
		lossPercentPerMatch = 0.5f;
	}
	
	public static void main(String[] args) {
		
		TradingStrategyHistory tradingStrategyHistory = new RunUnder05StrategyHistory();
		tradingStrategyHistory.run();
	}
	
	protected void evaluateMatches(String championshipName, List<Match> matchesToInvest) {
		
		for (Match match : matchesToInvest) {
			String matchDisplay =  "";
			matchDisplay += "["+match.getDate()+"] ";
			matchDisplay += match.getHomeTeam().getName() + " x " + match.getAwayTeam().getName();
			if ( 
				 ((match.getTimeHomeTeamFirstGoal() != null) && (match.getTimeHomeTeamFirstGoal() <= 16)) ||
				 ((match.getTimeAwayTeamFirstGoal() != null) && (match.getTimeAwayTeamFirstGoal() <= 16))
			   ){
				matchDisplay += " ---> ERRO!";
				setErrors(getErrors()+1);
			}	
			else {
				matchDisplay += " ---> ACERTO!";
				setSuccesses(getSuccesses()+1);
			}	
			System.out.println(matchDisplay);
		}
	}
	
}