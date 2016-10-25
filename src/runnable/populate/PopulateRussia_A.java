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

public class PopulateRussia_A {
	
	public static void main(String[] args) {
		
		try {
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Rússia A", 
					"https://www.academiadasapostasbrasil.com/stats/match/russia-stats/2246366",
					"Premier League",
					30,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98126&fase=93763");
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
		
		teams.add(new Team("Spartak Moscow", "Spartak Moscow", "Spartak M" , "Spartak Moskva", championship, "http://www.tablesleague.com/teams/spartak_moscow1/", "http://br.soccerway.com/teams/russia/fk-spartak-moskva/1844/statistics/"));
		teams.add(new Team("CSKA", "CSKA Moscow", "CSKA", "CSKA Moskva", championship, "http://www.tablesleague.com/teams/cska_moscow1/", "http://br.soccerway.com/teams/russia/cska-moskva/1842/statistics/"));
		teams.add(new Team("Amkar", "Amkar", "Amkar" , "Amkar", championship, "http://www.tablesleague.com/teams/amkar1/", "http://br.soccerway.com/teams/russia/fk-amkar-perm/1851/statistics/"));
		teams.add(new Team("Zenit", "Zenit Petersburg", "Zenit" , "Zenit", championship, "http://www.tablesleague.com/teams/zenit_petersburg1/", "http://br.soccerway.com/teams/russia/fk-zenit-sankt-petersburg/1841/statistics/"));
		teams.add(new Team("Anzhi", "Anzhi Makhachkala", "Anzhi" , "FK Anzhi", championship, "http://www.tablesleague.com/teams/anzhi_makhachkala1/", "http://br.soccerway.com/teams/russia/anzhi-makhachkala/1868/statistics/"));
		teams.add(new Team("Terek", "Terek Grozny", "Terek" , "Terek Grozny", championship, "http://www.tablesleague.com/teams/terek_grozny1/", "http://br.soccerway.com/teams/russia/terek-groznyi/1856/statistics/"));
		teams.add(new Team("Rostov", "FK Rostov", "Rostov" , "FK Rostov", championship, "http://www.tablesleague.com/teams/fk_rostov1/", "http://br.soccerway.com/teams/russia/fk-rostov-na-donu/1854/statistics/"));
		teams.add(new Team("Krasnodar", "FC Krasnodar", "Krasnodar", "FK Krasnodar", championship, "http://www.tablesleague.com/teams/fc_krasnodar/", "http://br.soccerway.com/teams/russia/fk-krasnodar/10610/statistics/"));
		teams.add(new Team("Dinamo Ufa", "Dinamo Ufa", "Ufa" , "Ufa", championship, "http://www.tablesleague.com/teams/dinamo_ufa/", "http://br.soccerway.com/teams/russia/bashinformsvyaz-dinamo-ufa/13204/statistics/"));
		teams.add(new Team("Tomsk", "Tomsk", "Tom'" , "Tom Tomsk", championship, "http://www.tablesleague.com/teams/tomsk1/", "http://br.soccerway.com/teams/russia/tom-tomsk/1870/statistics/"));
		teams.add(new Team("Lok. Moscow", "Lok. Moscow", "Lokomotiv M" , "Lokomotiv", championship, "http://www.tablesleague.com/teams/lok_moscow/", "http://br.soccerway.com/teams/russia/fk-lokomotiv-moscow/1843/statistics/"));
		teams.add(new Team("Rubin Kazan", "Rubin Kazan", "Rubin" , "Rubin Kazan", championship, "http://www.tablesleague.com/teams/rubin_kazan1/", "http://br.soccerway.com/teams/russia/rubin-kazan/1852/statistics/"));
		teams.add(new Team("Ural", "Ural S.R", "Ural" , "FK Ural", championship, "http://www.tablesleague.com/teams/ural_sr/", "http://br.soccerway.com/teams/russia/ural-sverdlovskaya-oblast/3750/statistics/"));
		teams.add(new Team("Arsenal Tula", "Arsenal Tula", "Arsenal" , "Arsenal Tula", championship, "http://www.tablesleague.com/teams/arsenal_tula1/", "http://br.soccerway.com/teams/russia/arsenal-tula/1863/statistics/"));
		teams.add(new Team("Gazovik Orenburg", "Gazovik Orenburg", "Gazovik" , "Gazovik Orenburg", championship, "http://www.tablesleague.com/teams/gazovik_orenburg1/", "http://br.soccerway.com/teams/russia/gazovik-orenburg/4049/statistics/"));
		teams.add(new Team("Krylya", "Krylya Sovetov Samara", "Krylya" , "Krylya Sovetov", championship, "http://www.tablesleague.com/teams/krylya_sovetov_samara1/", "http://br.soccerway.com/teams/russia/pfk-kryliya-sovetov-samara/1848/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
