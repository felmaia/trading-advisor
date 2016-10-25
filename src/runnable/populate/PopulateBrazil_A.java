package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import query.MatchQuery;
import model.Championship;
import model.Team;
import util.EM;
import util.Parser;

public class PopulateBrazil_A {
	
	public static void main(String[] args) {
		
		try {
			
			MatchQuery matchQuery = new MatchQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Brasileiro A", 
					"https://www.academiadasapostasbrasil.com/stats/match/brasil-stats/2218280",
					"Serie A",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=96361&fase=90953");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
			parser.extractMatchesOGolFrom(championship);
			
			//parser.populateMatchesTablesLeagueFrom(teams);
			
			parser.populateClassificationAcademiaDasApostasFrom(teams);
			
			parser.populateGoalsSoccerWayFrom(teams);
			
			parser.populateOverUnderStatsTablesLeagueFrom(teams);
			
			//parser.updateRankingsPerRoundFromOGol(matchQuery.getPlayedMatchesFrom(championship)); /* O Gol */
			
			EM.getInstance().getTransaction().commit();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
	}
	
	private static List<Team> createTeams(Championship championship) {
		
		List<Team> teams = new ArrayList<Team>();
		
		teams.add(new Team("Atletico MG", "Atletico MG", "Atlético MG", "Atlético Mineiro", championship, "http://www.tablesleague.com/teams/atletico_mg1/", "http://br.soccerway.com/teams/brazil/clube-atletico-mineiro/317/statistics/"));
		teams.add(new Team("Atletico PR", "Atletico PR", "Atlético PR", "Atlético Paranaense", championship, "http://www.tablesleague.com/teams/atletico_pr1/", "http://br.soccerway.com/teams/brazil/clube-atletico-paranaense/315/statistics/"));
		teams.add(new Team("Santa Cruz", "Santa Cruz", "Santa Cruz", "Santa Cruz", championship, "http://www.tablesleague.com/teams/santa_cruz1/", "http://br.soccerway.com/teams/brazil/santa-cruz-futebol-clube/325/statistics/"));
		teams.add(new Team("Chapecoense", "Chapecoense AF", "Chapecoense","Chapecoense", championship, "http://www.tablesleague.com/teams/chapecoense_af/", "http://br.soccerway.com/teams/brazil/associacao-chapecoense-kindermannmastervet/6223/statistics/"));
		teams.add(new Team("Corinthians", "Corinthians", "Corinthians", "Corinthians", championship, "http://www.tablesleague.com/teams/corinthians1/", "http://br.soccerway.com/teams/brazil/sport-club-corinthians-paulista/320/statistics/"));
		teams.add(new Team("Coritiba", "Coritiba", "Coritiba", "Coritiba", championship, "http://www.tablesleague.com/teams/coritiba1/", "http://br.soccerway.com/teams/brazil/coritiba-foot-ball-club-parana/324/statistics/"));
		teams.add(new Team("Cruzeiro", "Cruzeiro", "Cruzeiro", "Cruzeiro", championship, "http://www.tablesleague.com/teams/cruzeiro1/", "http://br.soccerway.com/teams/brazil/cruzeiro-esporte-clube-belo-horizonte/304/statistics/"));
		teams.add(new Team("Figueirense", "Figueirense", "Figueirense", "Figueirense", championship, "http://www.tablesleague.com/teams/figueirense1/", "http://br.soccerway.com/teams/brazil/figueirense-futebol-clube/303/statistics/"));
		teams.add(new Team("Flamengo", "Flamengo", "Flamengo", "Flamengo" , championship, "http://www.tablesleague.com/teams/flamengo1/", "http://br.soccerway.com/teams/brazil/clube-de-regatas-de-flamengo/318/statistics/"));
		teams.add(new Team("Fluminense", "Fluminense", "Fluminense", "Fluminense", championship, "http://www.tablesleague.com/teams/fluminense1/", "http://br.soccerway.com/teams/brazil/fluminense-football-club/312/statistics/"));
		teams.add(new Team("America MG", "America MG", "América MG", "América Mineiro", championship, "http://www.tablesleague.com/teams/america_mg1/", "http://br.soccerway.com/teams/brazil/america-futebol-clube-belo-horizonte/337/statistics/"));
		teams.add(new Team("Gremio", "Gremio", "Grêmio", "Grêmio", championship, "http://www.tablesleague.com/teams/gremio1/", "http://br.soccerway.com/teams/brazil/gremio-foot-ball-porto-alegrense/313/statistics/"));
		teams.add(new Team("Internacional", "Internacional", "Internacional", "Internacional" , championship, "http://www.tablesleague.com/teams/internacional1/", "http://br.soccerway.com/teams/brazil/sport-club-internacional/308/statistics/"));
		teams.add(new Team("Botafogo", "Botafogo RJ", "Botafogo", "Botafogo", championship, "http://www.tablesleague.com/teams/botafogo_rj1/", "http://br.soccerway.com/teams/brazil/botafogo-de-futebol-e-regatas/323/statistics/"));
		teams.add(new Team("Palmeiras", "Palmeiras", "Palmeiras", "Palmeiras", championship, "http://www.tablesleague.com/teams/palmeiras1/", "http://br.soccerway.com/teams/brazil/sociedade-esportiva-palmeiras/310/statistics/"));
		teams.add(new Team("Ponte Preta", "Ponte Preta", "Ponte Preta", "Ponte Preta", championship, "http://www.tablesleague.com/teams/ponte_preta1/", "http://br.soccerway.com/teams/brazil/associacao-atletica-ponte-preta/301/statistics/"));
		teams.add(new Team("Santos", "Santos FC", "Santos", "Santos", championship, "http://www.tablesleague.com/teams/santos_fc2/", "http://br.soccerway.com/teams/brazil/santos-futebol-clube-sao-paulo/319/statistics/"));
		teams.add(new Team("Sao Paulo", "Sao Paulo", "São Paulo", "São Paulo", championship, "http://www.tablesleague.com/teams/sao_paulo12/", "http://br.soccerway.com/teams/brazil/sao-paulo-futebol-clube/302/statistics/"));
		teams.add(new Team("Sport", "Sport", "Sport", "Sport", championship, "http://www.tablesleague.com/teams/sport1/", "http://br.soccerway.com/teams/brazil/sport-club-do-recife/338/statistics/"));
		teams.add(new Team("Vitoria", "Vitoria", "Vitória", "Vitória", championship, "http://www.tablesleague.com/teams/vitoria1/", "http://br.soccerway.com/teams/brazil/esporte-clube-vitoria/306/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
