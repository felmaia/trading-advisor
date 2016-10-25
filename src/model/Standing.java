package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_standing")
public class Standing {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="round")
	private Integer round;
	
	@Column(name="team_position")
	private Integer teamPosition;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="team_id")
	private Team team;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="championship_id")
	private Championship championship;
	
	public Standing(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public Integer getTeamPosition() {
		return teamPosition;
	}

	public void setTeamPosition(Integer teamPosition) {
		this.teamPosition = teamPosition;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}

	@Override
	public String toString() {
		return "Standing [round=" + round + ", teamPosition=" + teamPosition
				+ ", team=" + team + ", championship=" + championship + "]";
	};
	
}
