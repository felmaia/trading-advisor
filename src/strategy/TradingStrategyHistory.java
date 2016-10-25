package strategy;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import model.Championship;
import model.Match;
import query.ChampionshipQuery;
import query.MatchQuery;
import query.TeamQuery;

public abstract class TradingStrategyHistory {
	
	protected TeamQuery teamQuery = new TeamQuery();
	protected MatchQuery matchQuery = new MatchQuery();
	protected ChampionshipQuery championshipQuery = new ChampionshipQuery();
	protected TradingStrategy strategy;
	protected Float profitPercentPerMatch = null;
	protected Float lossPercentPerMatch = null;
	
	private int successes = 0;
	private int errors = 0;
	private static int totalPlayedMatches = 0;
	
	protected abstract void evaluateMatches(String championshipName, List<Match> matchesToInvest);
	
	public void run() {
		
		List<Championship> championships = championshipQuery.getAllChampionships();
		for (Championship championship : championships) {
			System.out.println("\n####### CAMPEONATO "+ championship.getName().toUpperCase() + " #######\n");
			totalPlayedMatches += matchQuery.getPlayedMatchesFrom(championship).size();
			List<Match> pastSuggestedMatches = findPastSuggestedMatches(championship);
			evaluateMatches(championship.getName(), pastSuggestedMatches);
		}
		printStrategySuccess();
		printFinancialEstimatedResults();
	}
	
	private void printStrategySuccess() {
		
		System.out.println("\n\n####### SUCCESS RATE #######\n");
		int totalSuggestedMatches = successes + errors;
		float percentSuccess = (float)successes/totalSuggestedMatches;
		percentSuccess *=100;
		float percentErrors = (float)errors/totalSuggestedMatches;
		percentErrors *=100;
		float percentSuggestedMatches = (float)totalSuggestedMatches/totalPlayedMatches;
		percentSuggestedMatches *=100;
		System.out.println("Acertos: " + successes + "(" + percentSuccess + "%)");
		System.out.println("Erros: " + errors + "(" + percentErrors + "%)");
		System.out.println("Total Jogos Sugeridos: " + totalSuggestedMatches + " de " + totalPlayedMatches + " (" + percentSuggestedMatches + "%)");
	}
	
	private void printFinancialEstimatedResults() {
		
		System.out.println("\n\n####### ESTIMATED FINANCIAL RESULTS #######\n");
		int gameInvestiment = 100;
		int totalSuggestedMatches = successes + errors;
		/*double profit = (double)gameInvestiment*successes*profitPercentPerMatch;
		double loss = (double)gameInvestiment*errors*lossPercentPerMatch;
		double result = profit - loss;
		double roiPerGame = (double)result/totalSuggestedMatches;
		double percentRoiPerGame = (double)roiPerGame/gameInvestiment;*/
		float successRate =  (profitPercentPerMatch-1) * successes * 100;
		float failRate =  lossPercentPerMatch * errors * 100;
		float performanceRate = successRate - failRate;
		System.out.println("Aplicação por jogo: $" + gameInvestiment);
		System.out.println("Taxa de sucesso: " + successRate + "%");
		System.out.println("Taxa de erro: " + failRate + "%");
		System.out.println("Performance: " + performanceRate + "%");
		/*System.out.println("Lucro: $" + profit);
		System.out.println("Prejuízo: $" + loss);
		System.out.println("Resultado: $" + result);
		System.out.println("ROI por jogo: $" + roiPerGame + "("+percentRoiPerGame*100+"%)");*/
	}
	
	private List<Match> findPastSuggestedMatches(Championship championship) {
		
		Set<Match> resultMatches = new LinkedHashSet<Match>(); 
		List<Match> played = matchQuery.getPlayedMatchesFrom(championship);
		for (Match match : played) {
			if (strategy.analyse(match))
				resultMatches.add(match);
		}
		return new ArrayList<Match>(resultMatches);
	}

	protected int getSuccesses() {
		return successes;
	}

	protected void setSuccesses(int successes) {
		this.successes = successes;
	}

	protected int getErrors() {
		return errors;
	}

	protected void setErrors(int errors) {
		this.errors = errors;
	}

}
