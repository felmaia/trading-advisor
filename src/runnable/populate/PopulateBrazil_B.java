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

public class PopulateBrazil_B {
	
	public static void main(String[] args) {
		
		try {
			
			ChampionshipQuery championshipQuery = new ChampionshipQuery();
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Brasileiro B", 
					"https://www.academiadasapostasbrasil.com/stats/match/brasil-stats/2218660",
					"Serie B",
					38,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=96941&fase=91564");
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
		
		teams.add(new Team("Vasco", "Vasco da Gama", "Vasco", "Vasco", championship, "http://www.tablesleague.com/teams/vasco_da_gama1/", "http://br.soccerway.com/teams/brazil/cr-vasco-da-gama/321/statistics/"));
		teams.add(new Team("Bahia", "Bahia", "Bahia", "Bahia", championship, "http://www.tablesleague.com/teams/bahia1/", "http://br.soccerway.com/teams/brazil/esporte-clube-bahia/341/statistics/"));
		teams.add(new Team("Ceara", "Ceara", "Ceará", "Ceará", championship, "http://www.tablesleague.com/teams/ceara/", "http://br.soccerway.com/teams/brazil/ceara-sporting-club/333/statistics/"));
		teams.add(new Team("Londrina", "Londrina EC", "Londrina", "Londrina", championship, "http://www.tablesleague.com/teams/londrina_ec/", "http://br.soccerway.com/teams/brazil/londrina-esporte-clube/346/statistics/"));
		teams.add(new Team("Vila Nova", "Vila Nova", "Vila Nova", "Vila Nova", championship, "http://www.tablesleague.com/teams/vila_nova1/", "http://br.soccerway.com/teams/brazil/vila-nova-futebol-clube/329/statistics/"));
		teams.add(new Team("Avai", "Avai FC", "Avaí", "Avaí", championship, "http://www.tablesleague.com/teams/avai_fc1/", "http://br.soccerway.com/teams/brazil/avai-futebol-clube/330/statistics/"));
		teams.add(new Team("Oeste", "Oeste FC", "Oeste", "Oeste", championship, "http://www.tablesleague.com/teams/oeste_fc/", "http://br.soccerway.com/teams/brazil/oeste-futebol-club/10309/statistics/"));
		teams.add(new Team("Paysandu", "Paysandu", "Paysandu", "Paysandu", championship, "http://www.tablesleague.com/teams/paysandu1/", "http://br.soccerway.com/teams/brazil/paysandu-sport-clube/322/statistics/"));
		teams.add(new Team("Nautico", "Nautico", "Náutico", "Náutico", championship, "http://www.tablesleague.com/teams/nautico1/", "http://br.soccerway.com/teams/brazil/clube-nautico-capibaribe/326/statistics/"));
		teams.add(new Team("Tupi", "Tupi", "Tupi", "Tupi", championship, "http://www.tablesleague.com/teams/tupi/", "http://br.soccerway.com/teams/brazil/tupi-football-club/7928/statistics/"));
		teams.add(new Team("Luverdense", "Luverdense", "Luverdense", "Luverdense", championship, "http://www.tablesleague.com/teams/luverdense/", "http://br.soccerway.com/teams/brazil/luverdense-esporte-clube/5130/statistics/"));
		teams.add(new Team("Sampaio Correa", "Sampaio Correa", "Sampaio Corrêa", "Sampaio Corrêa", championship, "http://www.tablesleague.com/teams/sampaio_correa/", "http://br.soccerway.com/teams/brazil/sampaio-correa-futebol-clube/2833/statistics/"));
		teams.add(new Team("Bragantino", "Bragantino", "Bragantino", "Bragantino", championship, "http://www.tablesleague.com/teams/bragantino1/", "http://br.soccerway.com/teams/brazil/clube-atletico-bragantino/2827/statistics/"));
		teams.add(new Team("CRB", "CRB", "CRB", "CRB", championship, "http://www.tablesleague.com/teams/crb1/", "http://br.soccerway.com/teams/brazil/clube-de-regatas-brasil/344/statistics/"));
		teams.add(new Team("Atletico GO", "Atletico GO", "Atlético GO", "Atlético Goianiense", championship, "http://www.tablesleague.com/teams/atletico_go/", "http://br.soccerway.com/teams/brazil/atletico-clube-goianiense/6216/statistics/"));
		teams.add(new Team("Criciuma", "Criciuma", "Criciúma", "Criciúma", championship, "http://www.tablesleague.com/teams/criciuma1/", "http://br.soccerway.com/teams/brazil/criciuma-esporte-clube/305/statistics/"));
		teams.add(new Team("Parana", "Parana Clube", "Paraná", "Paraná", championship, "http://www.tablesleague.com/teams/parana_clube1/", "http://br.soccerway.com/teams/brazil/parana-clube/309/statistics/"));
		teams.add(new Team("Joinville", "Joinville", "Joinville", "Joinville", championship, "http://www.tablesleague.com/teams/joinville/", "http://br.soccerway.com/teams/brazil/joinville-esporte-clube/348/statistics/"));
		teams.add(new Team("Goias", "Goias", "Goiás", "Goiás", championship, "http://www.tablesleague.com/teams/goias1/", "http://br.soccerway.com/teams/brazil/goias-esporte-clube/307/statistics/"));
		teams.add(new Team("Brasil de Pelotas", "Brasil de Pelotas", "Brasil Pelotas", "Brasil de Pelotas", championship, "http://www.tablesleague.com/teams/brasil_de_pelotas/", "http://br.soccerway.com/teams/brazil/gremio-esportivo-brasil/6205/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
