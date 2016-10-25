package query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Championship;
import model.Match;
import model.Team;
import util.EM;

public class MatchQuery {
	
	public Match getMatchById(Integer id) {
		
		String hql = " select match from Match match "
				+ " where match.id = :id ";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public Match getMatchByTeamsAndChampionship(Team homeTeam, Team awayTeam, Championship championship) {
		
		String hql = " select match from Match match "
				+ " where match.homeTeam = :homeTeam "
				+ " and match.awayTeam = :awayTeam "
				+ " and match.championship = :championship ";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("homeTeam", homeTeam);
		query.setParameter("awayTeam", awayTeam);
		query.setParameter("championship", championship);
		return query.getSingleResult();
	}
	
	public List<Match> getMatchsByTeamAndChampionship(Team team, Championship championship) {
		
		String hql = " select match from Match match "
				+ " where (match.homeTeam = :team or match.awayTeam = :team) "
				+ " and match.championship = :championship "
				+ " order by match.date ";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("team", team);
		query.setParameter("championship", championship);
		return query.getResultList();
	}
	
	public List<Match> getLastXMatchesFrom(Team team, int numberOfMatches) {
		
		String hql = " select match from Match match "
				+ " join match.homeTeam homeTeam "
				+ " join match.awayTeam awayTeam "
				+ " where (homeTeam = :team or awayTeam = :team) "
				+ " and match.homeTeamScore is not null "
				+ " order by match.date desc";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("team", team);
		query.setMaxResults(numberOfMatches);
		return query.getResultList();
	}
	
	public List<Match> getLastXHomeMatchesFrom(Team team, int numberOfMatches, Date dateLimit) {
		
		String hql = " select match from Match match "
				+ " join match.homeTeam homeTeam "
				+ " where homeTeam = :team "
				+ " and match.homeTeamScore is not null "
				+ " and match.date < :dateLimit "
				+ " order by match.date desc";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		query.setMaxResults(numberOfMatches);
		return query.getResultList();
	}
	
	public List<Match> getLastXAwayMatchesFrom(Team team, int numberOfMatches, Date dateLimit) {
		
		String hql = " select match from Match match "
				+ " join match.awayTeam awayTeam "
				+ " where awayTeam = :team "
				+ " and match.homeTeamScore is not null "
				+ " and match.date < :dateLimit "
				+ " order by match.date desc";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("team", team);
		query.setParameter("dateLimit", dateLimit);
		query.setMaxResults(numberOfMatches);
		return query.getResultList();
	}
	
	public List<Match> getOutdatedMatches(Championship championship) {
		
		String hql = " select match from Match match "
				+ " join match.championship championship "
				+ " where championship = :championship "
				+ " and match.homeTeamScore is null "
				+ " and match.date <= :today "
				+ " order by match.date asc";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("championship", championship);
		query.setParameter("today", new Date());
		return query.getResultList();
	}
	
	public List<Match> getAllMatchesFrom(Championship championship) {
		
		String hql = " select match from Match match "
				+ " join match.championship championship "
				+ " where championship = :championship "
				+ " order by match.date asc";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("championship", championship);
		return new ArrayList<Match>(new LinkedHashSet<Match>(query.getResultList()));
	}
	
	public List<Match> getPlayedMatchesFrom(Championship championship) {
		
		String hql = " select match from Match match "
				+ " join match.championship championship "
				+ " where championship = :championship "
				+ " and match.homeTeamScore is not null "
				+ " order by match.date asc";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("championship", championship);
		return new ArrayList<Match>(new LinkedHashSet<Match>(query.getResultList()));
	}
	
	public List<Match> getMatchesFrom(Championship championship, int round) {
		
		String hql = " select match from Match match "
				+ " join match.championship championship "
				+ " where championship = :championship "
				+ " and match.championshipRound = :round ";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("championship", championship);
		query.setParameter("round", round);
		return query.getResultList();
	}
	
	public List<Match> getMatchesFrom(Championship championship, Date date) {
		
		String hql = " select match from Match match "
				+ " join match.championship championship "
				+ " where championship = :championship "
				+ " and match.homeTeamScore is null "
				+ " and match.date = :date ";
		
		TypedQuery<Match> query = EM.getInstance().createQuery(hql, Match.class);
		query.setParameter("championship", championship);
		query.setParameter("date", date);
		return query.getResultList();
	}

}