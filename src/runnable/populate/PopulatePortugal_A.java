package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import query.ChampionshipQuery;
import query.MatchQuery;
import util.EM;
import util.Parser;

public class PopulatePortugal_A {
	
	public static void main(String[] args) {
		
		try {
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Portugal A", 
					"https://www.academiadasapostasbrasil.com/stats/match/portugal-stats/2285031",
					"Primeira Liga",
					34,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98399&fase=94302");
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
		
		teams.add(new Team("Sporting", "Sporting CP", "Sporting CP" , "Sporting", championship, "http://www.tablesleague.com/teams/sporting_cp1/", "http://br.soccerway.com/teams/portugal/sporting-clube-de-portugal/1680/statistics/"));
		teams.add(new Team("Braga", "Braga", "Braga" , "Braga", championship, "http://www.tablesleague.com/teams/braga1/", "http://br.soccerway.com/teams/portugal/sporting-braga/1682/statistics/"));
		teams.add(new Team("Benfica", "Benfica", "Benfica" , "Benfica", championship, "http://www.tablesleague.com/teams/benfica1/", "http://br.soccerway.com/teams/portugal/benfica/1679/statistics/"));
		teams.add(new Team("Porto", "FC Porto", "Porto", "FC Porto", championship, "http://www.tablesleague.com/teams/fc_porto1/", "http://br.soccerway.com/teams/portugal/futebol-clube-do-porto/1678/statistics/"));
		teams.add(new Team("Setubal", "Setubal", "Setúbal" , "V. Setúbal", championship, "http://www.tablesleague.com/teams/setubal1/", "http://br.soccerway.com/teams/portugal/vitoria-setubal/1696/statistics/"));
		teams.add(new Team("Belenenses", "Belenenses", "Belenenses" , "Belenenses", championship, "http://www.tablesleague.com/teams/belenenses1/", "http://br.soccerway.com/teams/portugal/clube-futebol--os-belenenses/1692/statistics/"));
		teams.add(new Team("Guimaraes", "Guimaraes", "Guimarães" , "V. Guimarães", championship, "http://www.tablesleague.com/teams/guimaraes1/", "http://br.soccerway.com/teams/portugal/vitoria-guimaraes/1689/statistics/"));
		teams.add(new Team("Rio Ave", "Rio Ave", "Rio Ave" , "Rio Ave", championship, "http://www.tablesleague.com/teams/rio_ave1/", "http://br.soccerway.com/teams/portugal/rio-ave-fc/1683/statistics/"));
		teams.add(new Team("Chaves", "Chaves", "Chaves" , "Chaves", championship, "http://www.tablesleague.com/teams/chaves1/", "http://br.soccerway.com/teams/portugal/gd-chaves/1704/statistics/"));
		teams.add(new Team("Feirense", "Feirense", "Feirense" , "Feirense", championship, "http://www.tablesleague.com/teams/feirense1/", "http://br.soccerway.com/teams/portugal/cd-feirense/1705/statistics/"));
		teams.add(new Team("P.Ferreira", "P.Ferreira", "Paços Ferreira" , "Paços Ferreira", championship, "http://www.tablesleague.com/teams/pferreira/", "http://br.soccerway.com/teams/portugal/pacos-ferreira/1693/statistics/"));
		teams.add(new Team("Boavista", "Boavista", "Boavista" , "Boavista", championship, "http://www.tablesleague.com/teams/boavista12/", "http://br.soccerway.com/teams/portugal/boavista/1685/statistics/"));
		teams.add(new Team("Moreirense", "Moreirense", "Moreirense" , "Moreirense", championship, "http://www.tablesleague.com/teams/moreirense1/", "http://br.soccerway.com/teams/portugal/moreirense-fc/1687/statistics/"));
		teams.add(new Team("Estoril", "Estoril", "Estoril" , "Estoril Praia", championship, "http://www.tablesleague.com/teams/estoril1/", "http://br.soccerway.com/teams/portugal/gd-estoril-praia/1695/statistics/"));
		teams.add(new Team("Arouca", "Arouca", "Arouca" , "Arouca", championship, "http://www.tablesleague.com/teams/arouca/", "http://br.soccerway.com/teams/portugal/fc-arouca/11611/statistics/"));
		teams.add(new Team("Nacional", "Nacional", "Nacional" , "Nacional", championship, "http://www.tablesleague.com/teams/nacional1_football_2/", "http://br.soccerway.com/teams/portugal/clube-desportivo-nacional/1681/statistics/"));
		teams.add(new Team("Maritimo", "Maritimo", "Marítimo" , "Marítimo", championship, "http://www.tablesleague.com/teams/maritimo1/", "http://br.soccerway.com/teams/portugal/maritimo-funchal/1684/statistics/"));
		teams.add(new Team("Tondela", "Tondela", "Tondela" , "Tondela", championship, "http://www.tablesleague.com/teams/tondela/", "http://br.soccerway.com/teams/portugal/cd-tondela/11833/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
