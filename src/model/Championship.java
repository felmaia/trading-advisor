package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_championship")
public class Championship {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@OneToMany(mappedBy="championship")
	private List<Match> matches;
	
	@OneToMany(mappedBy="championship")
	private List<Team> teams;
	
	@Column(name="classification_table_url")
	private String classificationTableURL;
	
	@Column(name="o_gol_classification_url")
	private String oGolClassificationURL;
	
	@Column(name="total_rounds")
	private Integer totalRounds;

	@Column(name="code_tables_league")
	private String codeTablesLeague;
	
	public Championship(){};
	
	public Championship(
			String name, 
			String classificationTableURL,
			String code,
			Integer totalRounds,
			String oGolClassificationURL) {
		this.setName(name);
		this.setClassificationTableURL(classificationTableURL);
		this.setCodeTablesLeague(code);
		this.setTotalRounds(totalRounds);
		this.setoGolClassificationURL(oGolClassificationURL);
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
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public String getClassificationTableURL() {
		return classificationTableURL;
	}
	public void setClassificationTableURL(String classificationTableURL) {
		this.classificationTableURL = classificationTableURL;
	}
	public String getCodeTablesLeague() {
		return codeTablesLeague;
	}
	public void setCodeTablesLeague(String codeTablesLeague) {
		this.codeTablesLeague = codeTablesLeague;
	}
	public String getoGolClassificationURL() {
		return oGolClassificationURL;
	}
	public void setoGolClassificationURL(String oGolClassificationURL) {
		this.oGolClassificationURL = oGolClassificationURL;
	}
	public Integer getTotalRounds() {
		return totalRounds;
	}
	public void setTotalRounds(Integer totalRounds) {
		this.totalRounds = totalRounds;
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
		Championship other = (Championship) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Championship [id=" + id + ", name=" + name + "]";
	}
	
}
