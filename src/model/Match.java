package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_match")
public class Match implements Comparable<Match> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="championship_id")
	private Championship championship;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="home_team_id")
	private Team homeTeam;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="away_team_id")
	private Team awayTeam;
	
	@Column(name="home_team_score")
	private Integer homeTeamScore;
	
	@Column(name="away_team_score")
	private Integer awayTeamScore;
	
	@Column(name="home_team_ht_score")
	private Integer homeTeamHTScore;
	
	@Column(name="away_team_ht_score")
	private Integer awayTeamHTScore;
	
	@Column(name="url_match_stats")
	private String urlMatchStats;
	
	@Column(name="time_home_team_first_goal")
	private Integer timeHomeTeamFirstGoal;
	
	@Column(name="time_away_team_first_goal")
	private Integer timeAwayTeamFirstGoal;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data")
	private Date date;
	
	@Column(name="championship_round")
	private Integer championshipRound;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Championship getChampionship() {
		return championship;
	}
	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Integer getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(Integer homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}
	public Integer getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(Integer awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getHomeTeamHTScore() {
		return homeTeamHTScore;
	}
	public void setHomeTeamHTScore(Integer homeTeamHTScore) {
		this.homeTeamHTScore = homeTeamHTScore;
	}
	public Integer getAwayTeamHTScore() {
		return awayTeamHTScore;
	}
	public void setAwayTeamHTScore(Integer awayTeamHTScore) {
		this.awayTeamHTScore = awayTeamHTScore;
	}
	public Integer getTimeHomeTeamFirstGoal() {
		return timeHomeTeamFirstGoal;
	}
	public void setTimeHomeTeamFirstGoal(Integer timeHomeTeamFirstGoal) {
		this.timeHomeTeamFirstGoal = timeHomeTeamFirstGoal;
	}
	public Integer getTimeAwayTeamFirstGoal() {
		return timeAwayTeamFirstGoal;
	}
	public void setTimeAwayTeamFirstGoal(Integer timeAwayTeamFirstGoal) {
		this.timeAwayTeamFirstGoal = timeAwayTeamFirstGoal;
	}
	public String getUrlMatchStats() {
		return urlMatchStats;
	}
	public void setUrlMatchStats(String urlMatchStats) {
		this.urlMatchStats = urlMatchStats;
	}
	public Integer getChampionshipRound() {
		return championshipRound;
	}
	public void setChampionshipRound(Integer championshipRound) {
		this.championshipRound = championshipRound;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result
				+ ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Match [id=" + id + ", championship=" + championship.getName()
				+ ", homeTeam=" + homeTeam.getName() + ", awayTeam=" + awayTeam.getName() + "]";
	}
	
	public int compareTo(Match compareMatch) {
		
		Date compareDate = ((Match) compareMatch).getDate(); 
		
		//ascending order
		return this.date.compareTo(compareDate);
		
		//descending order
		//return compareQuantity - this.quantity;
		
	}	
	
}
