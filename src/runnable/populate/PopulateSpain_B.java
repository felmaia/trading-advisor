package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import util.EM;
import util.Parser;

public class PopulateSpain_B {
	
	public static void main(String[] args) {
		
		try {
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Espanha B", 
					"https://www.academiadasapostasbrasil.com/stats/match/espanha-stats/2088663",
					"Segunda Division",
					42,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98407&fase=94319");
			EM.getInstance().persist(championship);
			
			List<Team> teams = createTeams(championship);
		
			//parser.populateMatchesTablesLeagueFrom(teams);
			
			parser.extractMatchesOGolFrom(championship);
			
			parser.populateClassificationAcademiaDasApostasFrom(teams);
			
			parser.populateGoalsSoccerWayFrom(teams);
			
			parser.populateOverUnderStatsTablesLeagueFrom(teams);
			
			//parser.updateRankingsPerRoundFromOGol(new ChampionshipQuery().getChampionshipLastRound(championship), championship);  /* O Gol */
			
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
		
		teams.add(new Team("Levante", "Levante", "Levante", "Levante", championship, "http://www.tablesleague.com/teams/levante1/", "http://br.soccerway.com/teams/spain/levante-union-deportiva/2036/statistics/"));
		teams.add(new Team("Lugo", "Lugo", "Lugo", "Lugo", championship, "http://www.tablesleague.com/teams/lugo1/", "http://br.soccerway.com/teams/spain/club-deportivo-lugo/7073/statistics/"));
		teams.add(new Team("Reus", "Reus", "Reus", "CF Reus", championship, "http://www.tablesleague.com/teams/reus1/", "http://br.soccerway.com/teams/spain/cf-reus-deportiu/4799/statistics/"));
		teams.add(new Team("Cordoba", "Cordoba", "Córdoba", "Córdoba", championship, "http://www.tablesleague.com/teams/cordoba1/", "http://br.soccerway.com/teams/spain/cordoba-cf/2050/statistics/"));
		teams.add(new Team("Elche", "Elche", "Elche", "Elche", championship, "http://www.tablesleague.com/teams/elche1/", "http://br.soccerway.com/teams/spain/elche-club-de-futbol/2043/statistics/"));
		teams.add(new Team("Mirandes", "Mirandes", "Mirandés", "Mirandés", championship, "http://www.tablesleague.com/teams/mirandes1/", "http://br.soccerway.com/teams/spain/cd-mirandes/9670/statistics/"));
		teams.add(new Team("Sevilla B", "Sevilla Atletico", "Sevilla II", "Sevilla B", championship, "http://www.tablesleague.com/teams/sevilla_atletico1/", "http://br.soccerway.com/teams/spain/sevilla-futbol-club-ii/2118/statistics"));
		teams.add(new Team("Huesca", "Huesca", "Huesca", "Huesca", championship, "http://www.tablesleague.com/teams/huesca1/", "http://br.soccerway.com/teams/spain/sd-huesca/3003/statistics/"));
		teams.add(new Team("Oviedo", "Oviedo", "Real Oviedo", "Real Oviedo", championship, "http://www.tablesleague.com/teams/oviedo1/", "http://br.soccerway.com/teams/spain/real-oviedo/2336/statistics/"));
		teams.add(new Team("Valladolid", "Valladolid", "Valladolid", "Valladolid", championship, "http://www.tablesleague.com/teams/valladolid1/", "http://br.soccerway.com/teams/spain/real-valladolid-club-de-futbol/2031/statistics/"));
		teams.add(new Team("Getafe", "Getafe", "Getafe", "Getafe", championship, "http://www.tablesleague.com/teams/getafe1/", "http://br.soccerway.com/teams/spain/getafe-club-de-futbol/2039/statistics/"));
		teams.add(new Team("Numancia", "Numancia", "Numancia", "Numancia", championship, "http://www.tablesleague.com/teams/numancia1/", "http://br.soccerway.com/teams/spain/club-deportivo-numancia/2035/statistics/"));
		teams.add(new Team("Zaragoza", "Zaragoza", "Zaragoza", "Zaragoza", championship, "http://www.tablesleague.com/teams/zaragoza1/", "http://br.soccerway.com/teams/spain/real-zaragoza/2030/statistics/"));
		teams.add(new Team("Rayo Vallecano", "Rayo Vallecano", "Vallecano", "Rayo Vallecano", championship, "http://www.tablesleague.com/teams/rayo_vallecano1/", "http://br.soccerway.com/teams/spain/rayo-vallecano/2054/statistics/"));
		teams.add(new Team("Murcia", "Murcia", "UCAM", "UCAM Murcia CF", championship, "http://www.tablesleague.com/teams/murcia1/", "http://br.soccerway.com/teams/spain/costa-calida-cf/13920/statistics/"));
		teams.add(new Team("Tenerife", "Tenerife", "Tenerife", "Tenerife", championship, "http://www.tablesleague.com/teams/tenerife1/", "http://br.soccerway.com/teams/spain/club-deportivo-tenerife/2048/statistics/"));
		teams.add(new Team("Mallorca", "Mallorca", "Mallorca", "Mallorca", championship, "http://www.tablesleague.com/teams/mallorca1/", "http://br.soccerway.com/teams/spain/real-club-deportivo-mallorca/2027/statistics/"));
		teams.add(new Team("Cadiz", "Cadiz", "Cádiz", "Cádiz", championship, "http://www.tablesleague.com/teams/cadiz1/", "http://br.soccerway.com/teams/spain/cadiz-club-de-futbol/2041/statistics/"));
		teams.add(new Team("Alcorcon", "Alcorcon", "Alcorcón", "AD Alcorcón", championship, "http://www.tablesleague.com/teams/alcorcon/", "http://br.soccerway.com/teams/spain/agrupacion-deportiva-alcorcon/2085/statistics/"));
		teams.add(new Team("Almeria", "Almeria", "Almería", "Almería", championship, "http://www.tablesleague.com/teams/almeria1/", "http://br.soccerway.com/teams/spain/union-deportiva-almeria/2049/statistics/"));
		teams.add(new Team("Gimnastic Tarragona", "Gimnastic", "Tarragona", "Nàstic", championship, "http://www.tablesleague.com/teams/gimnastic1/", "http://br.soccerway.com/teams/spain/club-gimnastic-de-tarragona/2099/statistics/"));
		teams.add(new Team("Girona", "Girona", "Girona", "Girona", championship, "http://www.tablesleague.com/teams/girona1/", "http://int.soccerway.com/teams/spain/girona-fc/2101/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
