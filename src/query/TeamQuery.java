package query;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import model.Championship;
import model.Team;
import util.EM;

public class TeamQuery {
	
	public List<Team> getTeamsFromChampionship(Championship championship) {
		
		String hql = " select team from Team team "
				+ " join team.championship championship "
				+ " where championship = :championship ";
		
		TypedQuery<Team> query = EM.getInstance().createQuery(hql, Team.class);
		query.setParameter("championship", championship);
		return query.getResultList();
	}
	
	public Team getTeamByName(String name) {
		
		String hql = " select team from Team team "
				+ " where team.name like :name ";
		
		TypedQuery<Team> query = EM.getInstance().createQuery(hql, Team.class);
		query.setParameter("name", name+"%");
		return query.getSingleResult();
	}
	
	public Team getTeamByTablesLeagueName(String name, Championship championship) {
		
		String hql = " select team from Team team "
				+ " where team.nameTablesLeague like :name and team.championship = :championship";
		
		TypedQuery<Team> query = EM.getInstance().createQuery(hql, Team.class);
		query.setParameter("name", name+"%");
		query.setParameter("championship", championship);
		return query.getSingleResult();
	}
	
	public Team getTeamByAcademiaDasApostasName(String name, Championship championship) {
		
		String hql = " select team from Team team "
				+ " where team.nameAcademiaDasApostas like :name and team.championship = :championship";
		
		TypedQuery<Team> query = EM.getInstance().createQuery(hql, Team.class);
		query.setParameter("name", name+"%");
		query.setParameter("championship", championship);
		return query.getSingleResult();
	}
	
	public Team getTeamByOGolName(String name, Championship championship) {
		
		//System.out.println("Name: " + name + " - Championship: " + championship);
		
		String hql = " select team from Team team "
				+ " where team.nameOGol like :name and team.championship = :championship";
		
		TypedQuery<Team> query = EM.getInstance().createQuery(hql, Team.class);
		Team searchedTeam = null;
		
		try {
			query.setParameter("name", name+"%");
			query.setParameter("championship", championship);
			searchedTeam = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NonUniqueResultException ex) {
			ex.printStackTrace();
		}
		return searchedTeam;
	}
	
	public Integer getTeamPositionByRoundAndChampionship(Team team, Integer round, Championship championship) {
		
		String hql = " select standing.teamPosition from Standing standing "
				+ " where standing.championship = :championship "
				+ " and standing.round = :round "
				+ " and standing.team = :team ";
		
		TypedQuery<Integer> query = EM.getInstance().createQuery(hql, Integer.class);
		query.setParameter("championship", championship);
		query.setParameter("round", round);
		query.setParameter("team", team);
		return query.getSingleResult();
	}
	
	public long countPlayedHomeMatchesFor(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult(); 
	}
	
	public float getTeamAvgMatchesScoredHomeHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamHTScore > 0 ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public Double getTeamAvgTimeFirstGoalScoredHome(Team team, Date dateLimit) {
		
		String hql = " select avg(match.timeHomeTeamFirstGoal) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgTimeFirstGoalConcededHome(Team team, Date dateLimit) {
		
		String hql = " select avg(match.timeAwayTeamFirstGoal) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		
		return query.getSingleResult();
	}
	
	public Double getTeamAvgTimeFirstGoalScoredAway(Team team, Date dateLimit) {
		
		String hql = " select avg(match.timeAwayTeamFirstGoal) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		//System.out.println(team.getName());
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgTimeFirstGoalConcededAway(Team team, Date dateLimit) {
		
		String hql = " select avg(match.timeHomeTeamFirstGoal) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsScoredHomeHT(Team team, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamHTScore) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsScoredHome(Team team, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamScore) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsScoredOrConcededHomeHT(Team team, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamHTScore + match.awayTeamHTScore) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		//long totalGoalsScoredHomeHT = query.getSingleResult();
		
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsConcededHomeHT(Team team, Date dateLimit) {
		
		String hql = " select avg(match.awayTeamHTScore) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsConcededHome(Team team, Date dateLimit) {
		
		String hql = " select avg(match.awayTeamScore) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public float getTeamAvgMatchesScoredOrConcededHomeHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and (match.awayTeamHTScore > 0 or match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesScoredHomeBeforeXMinutes(Team team, int minutes, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.timeHomeTeamFirstGoal < :minutes ";
				//+ " and (match.awayTeamHTScore > 0 or match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		query.setParameter("minutes", minutes);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesConcededHomeBeforeXMinutes(Team team, int minutes, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.timeAwayTeamFirstGoal < :minutes ";
				//+ " and (match.awayTeamHTScore > 0 or match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		query.setParameter("minutes", minutes);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesConcededHomeHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.awayTeamHTScore > 0 ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public long countPlayedAwayMatchesFor(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult(); 
	}
	
	public Double getTeamAvgGoalsScoredAwayHT(Team team, Date dateLimit) {
		
		String hql = " select avg(match.awayTeamHTScore) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsScoredAway(Team team, Date dateLimit) {
		
		String hql = " select avg(match.awayTeamScore) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public float getTeamAvgMatchesScoredOrConcededAwayHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and (match.awayTeamHTScore > 0 or match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesScoredAwayBeforeXMinutes(Team team, int minutes, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.timeAwayTeamFirstGoal < :minutes ";
				//+ " and (match.awayTeamHTScore > 0 or match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		query.setParameter("minutes", minutes);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesConcededAwayBeforeXMinutes(Team team, int minutes, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.timeHomeTeamFirstGoal < :minutes ";
				//+ " and (match.awayTeamHTScore > 0 or match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		query.setParameter("minutes", minutes);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesScoredAwayHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.awayTeamHTScore > 0 ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long result = query.getSingleResult();
		
		return (float)result/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public Double getTeamAvgGoalsScoredOrConcededAwayHT(Team team, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamHTScore + match.awayTeamHTScore) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		//long totalGoalsConcededAwayHT = query.getSingleResult();
		
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsConcededAwayHT(Team team, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamHTScore) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getTeamAvgGoalsConcededAway(Team team, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamScore) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public float getTeamAvgMatchesConcededAwayHT(Team team,  Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamHTScore > 0 ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long result = query.getSingleResult();
		
		return (float)result/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesScoredBeforeConcededHomeHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and (match.timeHomeTeamFirstGoal < match.timeAwayTeamFirstGoal or match.awayTeamHTScore = 0) "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long result = query.getSingleResult();
		
		return (float)result/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesConcededBeforeScoredAwayHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and (match.timeHomeTeamFirstGoal < match.timeAwayTeamFirstGoal or match.awayTeamHTScore = 0) "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long result = query.getSingleResult();
		
		return (float)result/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder35Home(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamScore + match.homeTeamScore < 4) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder35Away(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamScore + match.homeTeamScore < 4) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder25Home(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamScore + match.homeTeamScore < 3) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder25Away(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamScore + match.homeTeamScore < 3) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder05HomeHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamHTScore + match.homeTeamHTScore < 1) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder05AwayHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamHTScore + match.homeTeamHTScore < 1) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesOver05HomeHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamHTScore + match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesOver05AwayHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamHTScore + match.homeTeamHTScore > 0) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder15HomeHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamHTScore + match.homeTeamHTScore < 2) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesUnder15AwayHT(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamHTScore + match.homeTeamHTScore < 2) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesOver15Home(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamScore + match.homeTeamScore > 1) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedHomeMatchesFor(team, dateLimit);
	}
	
	public float getTeamAvgMatchesOver15Away(Team team, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null "
				+ " and (match.awayTeamScore + match.homeTeamScore > 1) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedAwayMatchesFor(team, dateLimit);
	}
}
