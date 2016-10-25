package runnable.history.novo;

import java.util.List;

import model.Match;
import strategy.Over15Strategy;
import strategy.TradingStrategyHistory;

public class RunOver15StrategyHistory extends TradingStrategyHistory {
	
	{
		strategy = new Over15Strategy();
		profitPercentPerMatch = 1.3f;
		lossPercentPerMatch = 1.00f;
	}
	
	public static void main(String[] args) {
		
		TradingStrategyHistory tradingStrategyHistory = new RunOver15StrategyHistory();
		tradingStrategyHistory.run();
	}
	
	protected void evaluateMatches(String championshipName, List<Match> matchesToInvest) {
		
		for (Match match : matchesToInvest) {
			String matchDisplay =  "";
			matchDisplay += "["+match.getDate()+"] ";
			matchDisplay += match.getHomeTeam().getName() + " x " + match.getAwayTeam().getName(); 
			if (match.getHomeTeamScore() + match.getAwayTeamScore() > 1) {
				matchDisplay += " ---> ACERTO!";
				setSuccesses(getSuccesses()+1);
			}	
			else {
				matchDisplay += " ---> ERRO!";
				setErrors(getErrors()+1);
			}	
			System.out.println(matchDisplay);
		}
	}
	
}