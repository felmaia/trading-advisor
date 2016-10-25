package query;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Championship;
import util.EM;


public class ChampionshipQuery {
	
	public Championship getChampionshipByName(String name) {
		
		String hql = " select championship from Championship championship "
				+ " where championship.name like :name ";
		
		TypedQuery<Championship> query = EM.getInstance().createQuery(hql, Championship.class);
		query.setParameter("name", name+"%");
		return query.getSingleResult();
	}
	
	public Championship getChampionshipById(Integer id) {
		
		String hql = " select championship from Championship championship "
				+ " where championship.id = :id ";
		
		TypedQuery<Championship> query = EM.getInstance().createQuery(hql, Championship.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public List<Championship> getAllChampionships() {
		
		String hql = " select championship from Championship championship ";
		
		TypedQuery<Championship> query = EM.getInstance().createQuery(hql, Championship.class);
		return query.getResultList();
	}
	
	public Integer getChampionshipLastRound(Championship championship) {
		
		String hql = "select match.championshipRound from Match match"
				+ " join match.championship championship "
				+ " where championship = :championship "
				+ " and match.homeTeamScore is not null "
				+ " order by match.id desc ";
		
		TypedQuery<Integer> query = EM.getInstance().createQuery(hql, Integer.class);
		query.setParameter("championship", championship);
		query.setMaxResults(1);
		return query.getSingleResult();
	}
	
	public Double getChampionshipAvgGoalsScoredHomeHT(Championship championship, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamHTScore) from Championship championship "
				+ " join championship.matches match "
				+ " where championship = :championship "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("championship", championship);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getChampionshipAvgGoalsScoredHome(Championship championship, Date dateLimit) {
		
		String hql = " select avg(match.homeTeamScore) from Championship championship "
				+ " join championship.matches match "
				+ " where championship = :championship "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("championship", championship);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getChampionshipAvgGoalsScoredAwayHT(Championship championship, Date dateLimit) {
		
		String hql = " select avg(match.awayTeamHTScore) from Championship championship "
				+ " join championship.matches match "
				+ " where championship = :championship "
				+ " and match.date < :dateLimit "
				+ " and match.awayTeamHTScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("championship", championship);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public Double getChampionshipAvgGoalsScoredAway(Championship championship, Date dateLimit) {
		
		String hql = " select avg(match.awayTeamScore) from Championship championship "
				+ " join championship.matches match "
				+ " where championship = :championship "
				+ " and match.date < :dateLimit "
				+ " and match.awayTeamHTScore is not null ";
		
		TypedQuery<Double> query = EM.getInstance().createQuery(hql, Double.class);
		query.setParameter("championship", championship);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	private long countPlayedMatchesFrom(Championship championship, Date dateLimit) {
		
		String hql = " select count(match) from Match match "
				+ " join match.championship championship "
				+ " where championship = :championship "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamScore is not null ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("championship", championship);
		query.setParameter("dateLimit", dateLimit);
		return query.getSingleResult();
	}
	
	public float getChampionshipAvgMatchesUnder35(Championship championship, Date dateLimit) {
		
		String hql = " select count(match) from Championship championship "
				+ " join championship.matches match "
				+ " where championship = :championship "
				+ " and match.date < :dateLimit "
				+ " and match.homeTeamHTScore is not null "
				+ " and (match.homeTeamScore + match.awayTeamScore < 4) ";
		
		TypedQuery<Long> query = EM.getInstance().createQuery(hql, Long.class);
		query.setParameter("championship", championship);
		query.setParameter("dateLimit", dateLimit);
		long results = query.getSingleResult();
		
		return (float)results/countPlayedMatchesFrom(championship, dateLimit);
	}

}