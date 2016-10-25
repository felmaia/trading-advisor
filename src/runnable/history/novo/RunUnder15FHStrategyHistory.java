package runnable.history.novo;

import java.util.List;

import model.Match;
import strategy.TradingStrategyHistory;
import strategy.Under15FHStrategy;

public class RunUnder15FHStrategyHistory extends TradingStrategyHistory {
	
	{
		strategy = new Under15FHStrategy();
		profitPercentPerMatch = 1.3f;
		lossPercentPerMatch = 1.00f;
	}
	
	public static void main(String[] args) {
		
		TradingStrategyHistory tradingStrategyHistory = new RunUnder15FHStrategyHistory();
		tradingStrategyHistory.run();
	}
	
	protected void evaluateMatches(String championshipName, List<Match> matchesToInvest) {
		
		for (Match match : matchesToInvest) {
			String matchDisplay =  "";
			matchDisplay += "["+match.getDate()+"] ";
			matchDisplay += match.getHomeTeam().getName() + " x " + match.getAwayTeam().getName(); 
			if (match.getHomeTeamHTScore() + match.getAwayTeamHTScore() < 2) {
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