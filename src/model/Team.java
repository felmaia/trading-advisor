package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_team")
public class Team {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(name="name_tables_league")
	private String nameTablesLeague;
	
	@Column(name="name_academia_das_aspostas")
	private String nameAcademiaDasApostas;
	
	@Column(name="name_o_gol")
	private String nameOGol;
	
	@ManyToOne
	@JoinColumn(name="championship_id")
	private Championship championship;
	
	@OneToMany(mappedBy="homeTeam")
	private List<Match> homeMatches;
	
	@OneToMany(mappedBy="awayTeam")
	private List<Match> awayMatches;
	
	@Column(name="url_tables_league")
	private String urlTablesLeague;
	
	@Column(name="url_soccer_way")
	private String urlSoccerWay;
	
	@Column(name="position")
	private Integer position;
	
	@Column(name="home_position")
	private Integer homePosition;
	
	@Column(name="away_position")
	private Integer awayPosition;
	
	@Column(name="avg_goals_scored_home")
	private Float avgGoalsScoredHome;
	
	@Column(name="avg_goals_scored_away")
	private Float avgGoalsScoredAway;
	
	@Column(name="avg_goals_conceded_home")
	private Float avgGoalsConcededHome;
	
	@Column(name="avg_goals_conceded_away")
	private Float avgGoalsConcededAway;
	
	@Column(name="avg_time_first_goal_scored_home")
	private Integer avgTimeFirstGoalScoredHome;
	
	@Column(name="avg_time_first_goal_conceded_home")
	private Integer avgTimeFirstGoalConcededHome;
	
	@Column(name="avg_time_first_goal_scored_away")
	private Integer avgTimeFirstGoalScoredAway;
	
	@Column(name="avg_time_first_goal_conceded_away")
	private Integer avgTimeFirstGoalConcededAway;
	
	@Column(name="under_15_home")
	private Float under15Home = null;
	
	@Column(name="under_15_away")
	private Float under15Away = null;
	
	@Column(name="over_15_home")
	private Float over15Home = null;
	
	@Column(name="over_15_away")
	private Float over15Away = null;
	
	@Column(name="under_25_home")
	private Float under25Home = null;
	
	@Column(name="under_25_away")
	private Float under25Away = null;
	
	@Column(name="over_25_home")
	private Float over25Home = null;
	
	@Column(name="over_25_away")
	private Float over25Away = null;
	
	@Column(name="under_35_home")
	private Float under35Home = null;
	
	@Column(name="under_35_away")
	private Float under35Away = null;
	
	@Column(name="over_35_home")
	private Float over35Home = null;
	
	@Column(name="over_35_away")
	private Float over35Away = null;
	
	@Column(name="under_05_first_half_home")
	private Float under05FirstHalfHome = null;
	
	@Column(name="under_05_first_half_away")
	private Float under05FirstHalfAway = null;
	
	@Column(name="under_05_second_half_home")
	private Float under05SecondHalfHome = null;
	
	@Column(name="under_05_second_half_away")
	private Float under05SecondHalfAway = null;
	
	@Column(name="over_05_first_half_home")
	private Float over05FirstHalfHome = null;
	
	@Column(name="over_05_first_half_away")
	private Float over05FirstHalfAway = null;
	
	@Column(name="over_05_second_half_home")
	private Float over05SecondHalfHome = null;
	
	@Column(name="over_05_second_half_away")
	private Float over05SecondHalfAway = null;
	
	@Column(name="under_15_first_half_home")
	private Float under15FirstHalfHome = null;
	
	@Column(name="under_15_first_half_away")
	private Float under15FirstHalfAway = null;
	
	@Column(name="under_15_second_half_home")
	private Float under15SecondHalfHome = null;
	
	@Column(name="under_15_second_half_away")
	private Float under15SecondHalfAway = null;
	
	@Column(name="over_15_first_half_home")
	private Float over15FirstHalfHome = null;
	
	@Column(name="over_15_first_half_away")
	private Float over15FirstHalfAway = null;
	
	@Column(name="over_15_second_half_home")
	private Float over15SecondHalfHome = null;
	
	@Column(name="over_15_second_half_away")
	private Float over15SecondHalfAway = null;
	

	/*public boolean isHomeTeam(Match match) {
		return this.id.equals(match.getHomeTeam().getId());
	}
	
	public boolean isAwayTeam(Match match) {
		return this.id.equals(match.getAwayTeam().getId());
	}*/
	
	public Team() {};
	
	public Team (
			String teamName, 
			String tablesLeagueName,
			String academiaDasApostasName,
			String oGolName,
			Championship championship, 
			String tablesLeagueURL, 
			String soccerWayURL) {
		this.setName(teamName);
		this.setNameTablesLeague(tablesLeagueName);
		this.setNameAcademiaDasApostas(academiaDasApostasName);
		this.setNameOGol(oGolName);
		this.setChampionship(championship);
		this.setUrlTablesLeague(tablesLeagueURL);
		this.setUrlSoccerWay(soccerWayURL);
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Championship getChampionship() {
		return championship;
	}
	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getHomePosition() {
		return homePosition;
	}
	public void setHomePosition(Integer homePosition) {
		this.homePosition = homePosition;
	}
	public Integer getAwayPosition() {
		return awayPosition;
	}
	public void setAwayPosition(Integer awayPosition) {
		this.awayPosition = awayPosition;
	}
	public Float getAvgGoalsScoredHome() {
		return avgGoalsScoredHome;
	}
	public void setAvgGoalsScoredHome(Float avgGoalsScoredHome) {
		this.avgGoalsScoredHome = avgGoalsScoredHome;
	}
	public Float getAvgGoalsScoredAway() {
		return avgGoalsScoredAway;
	}
	public void setAvgGoalsScoredAway(Float avgGoalsScoredAway) {
		this.avgGoalsScoredAway = avgGoalsScoredAway;
	}
	public Float getAvgGoalsConcededHome() {
		return avgGoalsConcededHome;
	}
	public void setAvgGoalsConcededHome(Float avgGoalsConcededHome) {
		this.avgGoalsConcededHome = avgGoalsConcededHome;
	}
	public Float getAvgGoalsConcededAway() {
		return avgGoalsConcededAway;
	}
	public void setAvgGoalsConcededAway(Float avgGoalsConcededAway) {
		this.avgGoalsConcededAway = avgGoalsConcededAway;
	}
	public Integer getAvgTimeFirstGoalScoredHome() {
		return avgTimeFirstGoalScoredHome;
	}
	public void setAvgTimeFirstGoalScoredHome(Integer avgTimeFirstGoalScoredHome) {
		this.avgTimeFirstGoalScoredHome = avgTimeFirstGoalScoredHome;
	}
	public Integer getAvgTimeFirstGoalConcededHome() {
		return avgTimeFirstGoalConcededHome;
	}
	public void setAvgTimeFirstGoalConcededHome(Integer avgTimeFirstGoalConcededHome) {
		this.avgTimeFirstGoalConcededHome = avgTimeFirstGoalConcededHome;
	}
	public Integer getAvgTimeFirstGoalScoredAway() {
		return avgTimeFirstGoalScoredAway;
	}
	public void setAvgTimeFirstGoalScoredAway(Integer avgTimeFirstGoalScoredAway) {
		this.avgTimeFirstGoalScoredAway = avgTimeFirstGoalScoredAway;
	}
	public Integer getAvgTimeFirstGoalConcededAway() {
		return avgTimeFirstGoalConcededAway;
	}
	public void setAvgTimeFirstGoalConcededAway(Integer avgTimeFirstGoalConcededAway) {
		this.avgTimeFirstGoalConcededAway = avgTimeFirstGoalConcededAway;
	}
	public List<Match> getHomeMatches() {
		return homeMatches;
	}
	public void setHomeMatches(List<Match> homeMatches) {
		this.homeMatches = homeMatches;
	}
	public List<Match> getAwayMatches() {
		return awayMatches;
	}
	public void setAwayMatches(List<Match> awayMatches) {
		this.awayMatches = awayMatches;
	}
	public String getUrlTablesLeague() {
		return urlTablesLeague;
	}
	public void setUrlTablesLeague(String urlTablesLeague) {
		this.urlTablesLeague = urlTablesLeague;
	}
	public String getUrlSoccerWay() {
		return urlSoccerWay;
	}
	public void setUrlSoccerWay(String urlSoccerWay) {
		this.urlSoccerWay = urlSoccerWay;
	}

	public String getNameTablesLeague() {
		return nameTablesLeague;
	}

	public void setNameTablesLeague(String nameTablesLeague) {
		this.nameTablesLeague = nameTablesLeague;
	}

	public String getNameAcademiaDasApostas() {
		return nameAcademiaDasApostas;
	}

	public void setNameAcademiaDasApostas(String nameAcademiaDasApostas) {
		this.nameAcademiaDasApostas = nameAcademiaDasApostas;
	}

	public String getNameOGol() {
		return nameOGol;
	}

	public void setNameOGol(String nameOGol) {
		this.nameOGol = nameOGol;
	}
	
	public Float getUnder15Home() {
		return under15Home;
	}

	public void setUnder15Home(Float under15Home) {
		this.under15Home = under15Home;
	}

	public Float getUnder15Away() {
		return under15Away;
	}

	public void setUnder15Away(Float under15Away) {
		this.under15Away = under15Away;
	}

	public Float getOver15Home() {
		return over15Home;
	}

	public void setOver15Home(Float over15Home) {
		this.over15Home = over15Home;
	}

	public Float getOver15Away() {
		return over15Away;
	}

	public void setOver15Away(Float over15Away) {
		this.over15Away = over15Away;
	}

	public Float getUnder25Home() {
		return under25Home;
	}

	public void setUnder25Home(Float under25Home) {
		this.under25Home = under25Home;
	}

	public Float getUnder25Away() {
		return under25Away;
	}

	public void setUnder25Away(Float under25Away) {
		this.under25Away = under25Away;
	}

	public Float getOver25Home() {
		return over25Home;
	}

	public void setOver25Home(Float over25Home) {
		this.over25Home = over25Home;
	}

	public Float getOver25Away() {
		return over25Away;
	}

	public void setOver25Away(Float over25Away) {
		this.over25Away = over25Away;
	}

	public Float getUnder35Home() {
		return under35Home;
	}

	public void setUnder35Home(Float under35Home) {
		this.under35Home = under35Home;
	}

	public Float getUnder35Away() {
		return under35Away;
	}

	public void setUnder35Away(Float under35Away) {
		this.under35Away = under35Away;
	}

	public Float getOver35Home() {
		return over35Home;
	}

	public void setOver35Home(Float over35Home) {
		this.over35Home = over35Home;
	}

	public Float getOver35Away() {
		return over35Away;
	}

	public void setOver35Away(Float over35Away) {
		this.over35Away = over35Away;
	}
	
	public Float getUnder05FirstHalfHome() {
		return under05FirstHalfHome;
	}

	public void setUnder05FirstHalfHome(Float under05FirstHalfHome) {
		this.under05FirstHalfHome = under05FirstHalfHome;
	}

	public Float getUnder05FirstHalfAway() {
		return under05FirstHalfAway;
	}

	public void setUnder05FirstHalfAway(Float under05FirstHalfAway) {
		this.under05FirstHalfAway = under05FirstHalfAway;
	}

	public Float getUnder05SecondHalfHome() {
		return under05SecondHalfHome;
	}

	public void setUnder05SecondHalfHome(Float under05SecondHalfHome) {
		this.under05SecondHalfHome = under05SecondHalfHome;
	}

	public Float getUnder05SecondHalfAway() {
		return under05SecondHalfAway;
	}

	public void setUnder05SecondHalfAway(Float under05SecondHalfAway) {
		this.under05SecondHalfAway = under05SecondHalfAway;
	}

	public Float getOver05FirstHalfHome() {
		return over05FirstHalfHome;
	}

	public void setOver05FirstHalfHome(Float over05FirstHalfHome) {
		this.over05FirstHalfHome = over05FirstHalfHome;
	}

	public Float getOver05FirstHalfAway() {
		return over05FirstHalfAway;
	}

	public void setOver05FirstHalfAway(Float over05FirstHalfAway) {
		this.over05FirstHalfAway = over05FirstHalfAway;
	}

	public Float getOver05SecondHalfHome() {
		return over05SecondHalfHome;
	}

	public void setOver05SecondHalfHome(Float over05SecondHalfHome) {
		this.over05SecondHalfHome = over05SecondHalfHome;
	}

	public Float getOver05SecondHalfAway() {
		return over05SecondHalfAway;
	}

	public void setOver05SecondHalfAway(Float over05SecondHalfAway) {
		this.over05SecondHalfAway = over05SecondHalfAway;
	}

	public Float getUnder15FirstHalfHome() {
		return under15FirstHalfHome;
	}

	public void setUnder15FirstHalfHome(Float under15FirstHalfHome) {
		this.under15FirstHalfHome = under15FirstHalfHome;
	}

	public Float getUnder15FirstHalfAway() {
		return under15FirstHalfAway;
	}

	public void setUnder15FirstHalfAway(Float under15FirstHalfAway) {
		this.under15FirstHalfAway = under15FirstHalfAway;
	}

	public Float getUnder15SecondHalfHome() {
		return under15SecondHalfHome;
	}

	public void setUnder15SecondHalfHome(Float under15SecondHalfHome) {
		this.under15SecondHalfHome = under15SecondHalfHome;
	}

	public Float getUnder15SecondHalfAway() {
		return under15SecondHalfAway;
	}

	public void setUnder15SecondHalfAway(Float under15SecondHalfAway) {
		this.under15SecondHalfAway = under15SecondHalfAway;
	}

	public Float getOver15FirstHalfHome() {
		return over15FirstHalfHome;
	}

	public void setOver15FirstHalfHome(Float over15FirstHalfHome) {
		this.over15FirstHalfHome = over15FirstHalfHome;
	}

	public Float getOver15FirstHalfAway() {
		return over15FirstHalfAway;
	}

	public void setOver15FirstHalfAway(Float over15FirstHalfAway) {
		this.over15FirstHalfAway = over15FirstHalfAway;
	}

	public Float getOver15SecondHalfHome() {
		return over15SecondHalfHome;
	}

	public void setOver15SecondHalfHome(Float over15SecondHalfHome) {
		this.over15SecondHalfHome = over15SecondHalfHome;
	}

	public Float getOver15SecondHalfAway() {
		return over15SecondHalfAway;
	}

	public void setOver15SecondHalfAway(Float over15SecondHalfAway) {
		this.over15SecondHalfAway = over15SecondHalfAway;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Team other = (Team) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + "]";
	}
	
}
