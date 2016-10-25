package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import query.ChampionshipQuery;
import util.EM;
import util.Parser;

public class PopulateSpain_A {
	
	public static void main(String[] args) {
		
		try {
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Espanha A", 
					"https://www.academiadasapostasbrasil.com/stats/match/espanha-stats/2283285",
					"Primera Division",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98390&fase=94274");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
			//parser.populateMatchesTablesLeagueFrom(teams);
			
			parser.extractMatchesOGolFrom(championship);
			
			parser.populateClassificationAcademiaDasApostasFrom(teams);
			
			parser.populateGoalsSoccerWayFrom(teams);
			
			parser.populateOverUnderStatsTablesLeagueFrom(teams);
			
			parser.updateRankingsPerRoundFromOGol(championshipQuery.getChampionshipLastRound(championship), championship);  /* O Gol */
			
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
		
		teams.add(new Team("Barcelona", "Barcelona", "Barcelona", "Barcelona", championship, "http://www.tablesleague.com/teams/barcelona1/", "http://br.soccerway.com/teams/spain/futbol-club-barcelona/2017/statistics/"));
		teams.add(new Team("Real Madrid", "Real Madrid", "Real Madrid", "Real Madrid", championship, "http://www.tablesleague.com/teams/real_madrid1/", "http://br.soccerway.com/teams/spain/real-madrid-club-de-futbol/2016/statistics/"));
		teams.add(new Team("Las Palmas", "Las Palmas", "Las Palmas", "Las Palmas", championship, "http://www.tablesleague.com/teams/las_palmas1/", "http://br.soccerway.com/teams/spain/union-deportiva-las-palmas/2055/statistics/"));
		teams.add(new Team("Atl.Madrid", "Atl.Madrid", "Atlético", "Atlético Madrid", championship, "http://www.tablesleague.com/teams/atlmadrid/", "http://br.soccerway.com/teams/spain/club-atletico-de-madrid/2020/statistics/"));
		teams.add(new Team("Sevilla", "Sevilla", "Sevilla", "Sevilla", championship, "http://www.tablesleague.com/teams/sevilla1/", "http://br.soccerway.com/teams/spain/sevilla-futbol-club/2021/statistics/"));
		teams.add(new Team("Eibar", "Eibar", "Eibar", "Eibar", championship, "http://www.tablesleague.com/teams/eibar1/", "http://br.soccerway.com/teams/spain/sociedad-deportiva-eibar/2042/statistics/"));
		teams.add(new Team("Gijon", "Gijon", "Gijón", "Sporting Gijón", championship, "http://www.tablesleague.com/teams/gijon1/", "http://br.soccerway.com/teams/spain/real-sporting-de-gijon/2038/statistics/"));
		teams.add(new Team("Villarreal", "Villarreal", "Villarreal", "Villarreal", championship, "http://www.tablesleague.com/teams/villarreal1/", "http://br.soccerway.com/teams/spain/villarreal-club-de-futbol/2023/statistics/"));
		teams.add(new Team("Alaves", "Alaves", "Alavés", "Alavés", championship, "http://www.tablesleague.com/teams/alaves1/", "http://br.soccerway.com/teams/spain/deportivo-alaves/2037/statistics/"));
		teams.add(new Team("Betis", "Betis", "Betis", "Real Betis", championship, "http://www.tablesleague.com/teams/betis1/", "http://br.soccerway.com/teams/spain/real-betis/2025/statistics/"));
		teams.add(new Team("Deportivo La Coruna", "D.La Coruna", "La Coruña", "Deportivo", championship, "http://www.tablesleague.com/teams/dla_coruna/", "http://br.soccerway.com/teams/spain/real-club-deportivo-de-la-coruna/2018/statistics/"));
		teams.add(new Team("Real Sociedad", "Real Sociedad", "Real Sociedad", "Real Sociedad", championship, "http://www.tablesleague.com/teams/real_sociedad1/", "http://br.soccerway.com/teams/spain/real-sociedad-de-futbol/2028/statistics/"));
		teams.add(new Team("Leganes", "Leganes", "Leganés", "Leganés", championship, "http://www.tablesleague.com/teams/leganes1/", "http://br.soccerway.com/teams/spain/club-deportivo-leganes-sad/2053/statistics/"));
		teams.add(new Team("Athl.Bilbao", "Athl.Bilbao", "Athletic Club", "Athletic", championship, "http://www.tablesleague.com/teams/athlbilbao/", "http://br.soccerway.com/teams/spain/athletic-club/2019/statistics/"));
		teams.add(new Team("Espanyol", "Espanyol", "Espanyol", "Espanyol", championship, "http://www.tablesleague.com/teams/espanyol1/", "http://br.soccerway.com/teams/spain/reial-club-deportiu-espanyol/2032/statistics/"));
		teams.add(new Team("Malaga", "Malaga", "Málaga", "Málaga", championship, "http://www.tablesleague.com/teams/malaga1/", "http://br.soccerway.com/teams/spain/malaga-club-de-futbol/2024/statistics/"));
		teams.add(new Team("Granada", "Granada", "Granada", "Granada", championship, "http://www.tablesleague.com/teams/granada1/", "http://br.soccerway.com/teams/spain/granada-club-de-futbol/7072/statistics/"));
		teams.add(new Team("Osasuna", "Osasuna", "Osasuna", "Osasuna", championship, "http://www.tablesleague.com/teams/osasuna1/", "http://br.soccerway.com/teams/spain/club-atletico-osasuna/2022/statistics/"));
		teams.add(new Team("Valencia", "Valencia", "Valência", "Valencia", championship, "http://www.tablesleague.com/teams/valencia1/", "http://br.soccerway.com/teams/spain/valencia-club-de-futbol/2015/statistics/"));
		teams.add(new Team("Celta Vigo", "Celta Vigo", "Celta de Vigo", "Celta de Vigo", championship, "http://www.tablesleague.com/teams/celta_vigo1/", "http://br.soccerway.com/teams/spain/real-club-celta-de-vigo/2033/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
