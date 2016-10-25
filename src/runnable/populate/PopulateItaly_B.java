package runnable.populate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Championship;
import model.Team;
import util.EM;
import util.Parser;

public class PopulateItaly_B {
	
	public static void main(String[] args) {
		
		try {
			
			EM.getInstance().getTransaction().begin();
			
			Parser parser = new Parser();
			
			Championship championship = new Championship("Itália B", 
					"https://www.academiadasapostasbrasil.com/stats/match/italia-stats/2327911",
					"Serie B",
					42,
					"http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=98704&fase=94818");
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
		
		teams.add(new Team("Cittadella", "Cittadella", "Cittadella", "Cittadella", championship, "http://www.tablesleague.com/teams/cittadella1/", "http://br.soccerway.com/teams/italy/as-cittadella/1291/statistics/"));
		teams.add(new Team("Verona", "Verona", "Verona", "Hellas Verona", championship, "http://www.tablesleague.com/teams/verona1/", "http://br.soccerway.com/teams/italy/hellas-verona-fc/1277/statistics/"));
		teams.add(new Team("Benevento", "Benevento", "Benevento", "Benevento", championship, "http://www.tablesleague.com/teams/benevento1/", "http://br.soccerway.com/teams/italy/benevento-calcio/1302/statistics/"));
		teams.add(new Team("Pisa", "Pisa", "Pisa", "Pisa", championship, "http://www.tablesleague.com/teams/pisa1/", "http://br.soccerway.com/teams/italy/pisa-calcio/1290/statistics/"));
		teams.add(new Team("Spezia", "Spezia", "Spezia", "Spezia", championship, "http://www.tablesleague.com/teams/spezia1/", "http://br.soccerway.com/teams/italy/asd-spezia-calcio-2008/1288/statistics/"));
		teams.add(new Team("Brescia", "Brescia", "Brescia", "Brescia", championship, "http://www.tablesleague.com/teams/brescia1/", "http://br.soccerway.com/teams/italy/brescia-calcio/1250/statistics/"));
		teams.add(new Team("Carpi", "Carpi", "Carpi", "Carpi", championship, "http://www.tablesleague.com/teams/carpi/", "http://br.soccerway.com/teams/italy/carpi-fc-1909/12140/statistics/"));
		teams.add(new Team("Entella", "Entella", "Virtus", "Virtus Entella", championship, "http://www.tablesleague.com/teams/entella/", "http://br.soccerway.com/teams/italy/acd-virtus-entella/12109/statistics/"));
		teams.add(new Team("Frosinone", "Frosinone", "Frosinone", "Frosinone", championship, "http://www.tablesleague.com/teams/frosinone1/", "http://br.soccerway.com/teams/italy/frosinone-calcio/2981/statistics/"));
		teams.add(new Team("Bari", "Bari", "Bari 1908", "Bari", championship, "http://www.tablesleague.com/teams/bari1_football_2/", "http://br.soccerway.com/teams/italy/as-bari/1301/statistics/"));
		teams.add(new Team("Ternana", "Ternana", "Ternana", "Ternana", championship, "http://www.tablesleague.com/teams/ternana1/", "http://br.soccerway.com/teams/italy/ternana-calcio/1265/statistics/"));
		teams.add(new Team("Ascoli", "Ascoli", "Ascoli", "Ascoli", championship, "http://www.tablesleague.com/teams/ascoli1/", "http://br.soccerway.com/teams/italy/ascoli-calcio/1271/statistics/"));
		teams.add(new Team("Salernitana", "Salernitana", "Salernitana", "Salernitana", championship, "http://www.tablesleague.com/teams/salernitana1/", "http://br.soccerway.com/teams/italy/salernitana-calcio-1919/1275/statistics/"));
		teams.add(new Team("Perugia", "Perugia", "Perugia", "Perugia", championship, "http://www.tablesleague.com/teams/perugia1/", "http://br.soccerway.com/teams/italy/perugia-calcio/1262/statistics/"));
		teams.add(new Team("Pro Vercelli", "Pro Vercelli", "Pro Vercelli", "Pro Vercelli", championship, "http://www.tablesleague.com/teams/pro_vercelli/", "http://br.soccerway.com/teams/italy/us-pro-vercelli-calcio/5675/statistics/"));
		teams.add(new Team("Cesena", "Cesena", "Cesena", "Cesena", championship, "http://www.tablesleague.com/teams/cesena1/", "http://br.soccerway.com/teams/italy/ac-cesena/1279/statistics/"));
		teams.add(new Team("Spal", "Spal", "SPAL", "SPAL 2013", championship, "http://www.tablesleague.com/teams/spal1/", "http://br.soccerway.com/teams/italy/spal-1907/1287/statistics/"));
		teams.add(new Team("Trapani", "Trapani", "Trapani", "Trapani", championship, "http://www.tablesleague.com/teams/trapani/", "http://br.soccerway.com/teams/italy/asd-trapani-calcio/12229/statistics/"));
		teams.add(new Team("Novara", "Novara", "Novara", "Novara", championship, "http://www.tablesleague.com/teams/novara1/", "http://br.soccerway.com/teams/italy/novara-calcio/1293/statistics/"));
		teams.add(new Team("Vicenza", "Vicenza", "Vicenza", "Vicenza", championship, "http://www.tablesleague.com/teams/vicenza1/", "http://br.soccerway.com/teams/italy/vicenza-calcio/1269/statistics/"));
		teams.add(new Team("Latina", "Latina", "Latina", "Latina", championship, "http://www.tablesleague.com/teams/latina_football_2/", "http://br.soccerway.com/teams/italy/as-latina/5665/statistics/"));		
		teams.add(new Team("Avellino", "Avellino", "Avellino", "Avellino", championship, "http://www.tablesleague.com/teams/avellino1/", "http://br.soccerway.com/teams/italy/us-avellino/1300/statistics/"));
		
		for (Team team : teams) {
			EM.getInstance().persist(team);
		}
		
		return teams;
	}
}
