package runnable.history.novo;

import java.util.List;

import model.Match;
import strategy.TradingStrategyHistory;
import strategy.Under35Strategy;

public class RunUnder35StrategyHistory extends TradingStrategyHistory {
	
	{
		strategy = new Under35Strategy();
		profitPercentPerMatch = 1.2f;
		lossPercentPerMatch = 1.0f;
	}
	
	public static void main(String[] args) {
		
		TradingStrategyHistory tradingStrategyHistory = new RunUnder35StrategyHistory();
		tradingStrategyHistory.run();
	}
	
	protected void evaluateMatches(String championshipName, List<Match> matchesToInvest) {
		
		for (Match match : matchesToInvest) {
			String matchDisplay =  "";
			matchDisplay += "["+match.getDate()+"] ";
			matchDisplay += match.getHomeTeam().getName() + " x " + match.getAwayTeam().getName();
			if (match.getHomeTeamScore() + match.getAwayTeamScore() < 4) {
				matchDisplay += " ---> ACERTO!";
				setSuccesses(getSuccesses()+1);
			}	
			else {
				matchDisplay += " ---> ERRO!";
				setErrors(getErrors()+1);
			}
			/*if ( 
				 ((match.getTimeHomeTeamFirstGoal() != null) && (match.getTimeHomeTeamFirstGoal() <= 16)) ||
				 ((match.getTimeAwayTeamFirstGoal() != null) && (match.getTimeAwayTeamFirstGoal() <= 16))
			   ){
				matchDisplay += " ---> ERRO!";
				setErrors(getErrors()+1);
			}	
			else {
				matchDisplay += " ---> ACERTO!";
				setSuccesses(getSuccesses()+1);
			}	*/
			System.out.println(matchDisplay);
		}
	}
	
}