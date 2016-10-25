package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import util.EM;
import util.Parser;

public class PopulatePortugal_B {
	
	public static void main(String[] args) {
		
		try {
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Portugal B", 
					"https://www.academiadasapostasbrasil.com/stats/match/portugal-stats/2284725",
					"Liga Orangina",
					46,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98400&fase=94303");
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
		
		teams.add(new Team("Portimonense", "Portimonense", "Portimonense" , "Portimonense", championship, "http://www.tablesleague.com/teams/portimonense1/", "http://br.soccerway.com/teams/portugal/portimonense-sc/1710/statistics/"));
		teams.add(new Team("Santa Clara", "Santa Clara", "Santa Clara" , "Santa Clara", championship, "http://www.tablesleague.com/teams/santa_clara1/", "http://br.soccerway.com/teams/portugal/cd-santa-clara/1706/statistics/"));
		teams.add(new Team("Benfica B", "Benfica B", "Benfica II" , "Benfica B", championship, "http://www.tablesleague.com/teams/benfica_b/", "http://br.soccerway.com/teams/portugal/benfica-b/2891/statistics/"));
		teams.add(new Team("Penafiel", "Penafiel", "Penafiel" , "Penafiel", championship, "http://www.tablesleague.com/teams/penafiel1/", "http://br.soccerway.com/teams/portugal/futebol-clube-de-penafiel/1697/statistics/"));
		teams.add(new Team("Vizela", "Vizela", "Vizela" , "Vizela", championship, "http://www.tablesleague.com/teams/vizela1/", "http://br.soccerway.com/teams/portugal/fc-vizela/1755/statistics/"));
		teams.add(new Team("Cova da Piedade", "Cova da Piedade", "Cova Piedade" , "Cova da Piedade", championship, "http://www.tablesleague.com/teams/cova_da_piedade/", "http://br.soccerway.com/teams/portugal/cd-cova-piedade/10123/statistics/"));
		teams.add(new Team("Porto B", "FC Porto B", "Porto II", "FC Porto B", championship, "http://www.tablesleague.com/teams/fc_porto_b/", "http://br.soccerway.com/teams/portugal/porto-b/1756/statistics/"));
		teams.add(new Team("Aves", "Aves", "Aves" , "Desp. Aves", championship, "http://www.tablesleague.com/teams/aves1/", "http://br.soccerway.com/teams/portugal/desportivo-aves/1702/statistics/"));
		teams.add(new Team("U. Madeira", "U. Madeira", "União Madeira" , "U. Madeira", championship, "http://www.tablesleague.com/teams/u_madeira/", "http://br.soccerway.com/teams/portugal/cf-uniao-madeira/1711/statistics/"));
		teams.add(new Team("Braga B", "Braga B", "Braga II" , "Braga B", championship, "http://www.tablesleague.com/teams/braga__b/", "http://br.soccerway.com/teams/portugal/sporting-braga-b/1758/statistics/"));
		teams.add(new Team("Academica", "Academica", "Académica" , "Académica", championship, "http://www.tablesleague.com/teams/academica1/", "http://br.soccerway.com/teams/portugal/associacao-academica-de-coimbra/1690/statistics/"));
		teams.add(new Team("Varzim", "Varzim", "Varzim" , "Varzim", championship, "http://www.tablesleague.com/teams/varzim1/", "http://br.soccerway.com/teams/portugal/varzim-sc/1698/statistics/"));
		teams.add(new Team("AD Fafe", "AD Fafe", "Fafe" , "Fafe", championship, "http://www.tablesleague.com/teams/ad_fafe/", "http://br.soccerway.com/teams/portugal/ad-fafe/1765/statistics/"));
		teams.add(new Team("Guimaraes B", "Vitoria de Guimaraes B", "Guimarães II" , "V. Guimarães B", championship, "http://www.tablesleague.com/teams/vitoria_de_guimaraes_b/", "http://br.soccerway.com/teams/portugal/vitoria-guimaraes-ii/21775/statistics/"));
		teams.add(new Team("Gil Vicente", "Gil Vicente", "Gil Vicente" , "Gil Vicente", championship, "http://www.tablesleague.com/teams/gil_vicente1/", "http://br.soccerway.com/teams/portugal/gil-vicente-fc/2572/statistics/"));
		teams.add(new Team("Famalicao", "Famalicao", "Famalicão" , "Famalicão", championship, "http://www.tablesleague.com/teams/famalicao1/", "http://br.soccerway.com/teams/portugal/fc-famalicao/2752/statistics/"));
		teams.add(new Team("Leixoes", "Leixoes", "Leixões" , "Leixões", championship, "http://www.tablesleague.com/teams/leixoes1/", "http://br.soccerway.com/teams/portugal/leixoes/1707/statistics/"));
		teams.add(new Team("Freamunde", "Freamunde", "Freamunde" , "Freamunde", championship, "http://www.tablesleague.com/teams/freamunde1/", "http://br.soccerway.com/teams/portugal/sc-freamunde/1761/statistics/"));
		teams.add(new Team("Viseu", "Viseu", "Viseu" , "Ac. Viseu", championship, "http://www.tablesleague.com/teams/viseu1/", "http://br.soccerway.com/teams/portugal/academico-viseu-fc/1737/statistics/"));
		teams.add(new Team("Sporting CP B", "Sporting CP B", "Sporting CP II" , "Sporting B", championship, "http://www.tablesleague.com/teams/sporting_cp_b/", "http://br.soccerway.com/teams/portugal/sporting-b/1728/statistics/"));
		teams.add(new Team("Covilha", "Sp.Covilha", "Covilhã" , "Sp. Covilhã", championship, "http://www.tablesleague.com/teams/spcovilha/", "http://br.soccerway.com/teams/portugal/sc-covilha/1712/statistics/"));
		teams.add(new Team("Olhanense", "Olhanense", "Olhanense" , "Olhanense", championship, "http://www.tablesleague.com/teams/olhanense1/", "http://br.soccerway.com/teams/portugal/sc-olhanense/1713/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
