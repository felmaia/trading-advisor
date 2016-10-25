package runnable.history;

import java.util.List;

import model.Match;
import strategy.Over05HTInGameStrategy;
import strategy.TradingStrategyHistory;

public class RunOver05HTInGameStrategyHistory extends TradingStrategyHistory{

	{
		strategy = new Over05HTInGameStrategy();
		profitPercentPerMatch = 0.25f;
		lossPercentPerMatch = 0.35f;
	}
	
	public static void main(String[] args) {
		
		RunOver05HTInGameStrategyHistory tradingStrategyHistory = new RunOver05HTInGameStrategyHistory();
		tradingStrategyHistory.run();
	}
	
	protected void evaluateMatches(String championshipName, List<Match> matchesToInvest) {
		
		for (Match match : matchesToInvest) {
			String matchDisplay =  "";
			matchDisplay += "["+match.getDate()+"] ";
			matchDisplay += match.getHomeTeam().getName() + " x " + match.getAwayTeam().getName(); 
			if (match.getHomeTeamHTScore()>0 || match.getAwayTeamHTScore()>0) {
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
