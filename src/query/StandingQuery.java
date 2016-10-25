package query;

import javax.persistence.TypedQuery;

import model.Championship;
import model.Standing;
import model.Team;
import util.EM;

public class StandingQuery {
	
	public Standing getStandingBy(Team team, Championship championship, Integer round) {
		
		String hql = " select standing from Standing standing "
				+ " join standing.team team "
				+ " join standing.championship championship "
				+ " where championship = :championship "
				+ " and team = :team "
				+ " and standing.round = :round ";
		
		TypedQuery<Standing> query = EM.getInstance().createQuery(hql, Standing.class);
		query.setParameter("championship", championship);
		query.setParameter("team", team);
		query.setParameter("round", round);
		return query.getSingleResult();
	}
	
	public Integer getLastStandingRound(Championship championship) {
		
		String hql = " select max(standing.round) from Standing standing "
				+ " join standing.championship championship "
				+ " where championship = :championship ";
		
		TypedQuery<Integer> query = EM.getInstance().createQuery(hql, Integer.class);
		query.setParameter("championship", championship);
		return query.getSingleResult();
	}

}
