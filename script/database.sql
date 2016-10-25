-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2015-10-07 16:55:13.49




-- tables
-- Table Championship
CREATE TABLE tb_championship (
    id int  NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    classification_table_url varchar(255),
    code_tables_league varchar(255),
    total_rounds smallint,
    o_gol_classification_url varchar(255),
    CONSTRAINT tb_championship_pk PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Table `Match`
CREATE TABLE `tb_match` (
    id int  NOT NULL AUTO_INCREMENT,
    championship_id int  NOT NULL,
    home_team_id int  NOT NULL,
    away_team_id int  NOT NULL,
    home_team_score int,
    away_team_score int,
    home_team_ht_score int,
    away_team_ht_score int,
    url_match_stats varchar(255),
	time_home_team_first_goal smallint,
	time_away_team_first_goal smallint,
    data date NOT NULL,
    championship_round smallint,
    CONSTRAINT tb_match_pk PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Table Team
CREATE TABLE tb_team (
    id int  NOT NULL AUTO_INCREMENT,
    name varchar(255)  NOT NULL,
    name_tables_league varchar(255),
    name_academia_das_aspostas varchar(255),
    name_o_gol varchar(255),
    championship_id int  NOT NULL,
    url_tables_league varchar(255),
	url_soccer_way varchar(255),
	position smallint,
	home_position smallint,
	away_position smallint,
	avg_goals_scored_home decimal(5,2),
	avg_goals_scored_away decimal(5,2),
	avg_goals_conceded_home decimal(5,2),
	avg_goals_conceded_away decimal(5,2),
	avg_time_first_goal_scored_home smallint,
	avg_time_first_goal_conceded_home smallint,
	avg_time_first_goal_scored_away smallint,
	avg_time_first_goal_conceded_away smallint,
	under_15_home decimal(5,2),
	under_15_away decimal(5,2),
	over_15_home decimal(5,2),
	over_15_away decimal(5,2),
	under_25_home decimal(5,2),
	under_25_away decimal(5,2),
	over_25_home decimal(5,2),
	over_25_away decimal(5,2),
	under_35_home decimal(5,2),
	under_35_away decimal(5,2),
	over_35_home decimal(5,2),
	over_35_away decimal(5,2),
	under_05_first_half_home decimal(5,2),
	under_05_first_half_away decimal(5,2),
	over_05_first_half_home decimal(5,2),
	over_05_first_half_away decimal(5,2),
	under_05_second_half_home decimal(5,2),
	under_05_second_half_away decimal(5,2),
	over_05_second_half_home decimal(5,2),
	over_05_second_half_away decimal(5,2),
	under_15_first_half_home decimal(5,2),
	under_15_first_half_away decimal(5,2),
	over_15_first_half_home decimal(5,2),
	over_15_first_half_away decimal(5,2),
	under_15_second_half_home decimal(5,2),
	under_15_second_half_away decimal(5,2),
	over_15_second_half_home decimal(5,2),
	over_15_second_half_away decimal(5,2),
    CONSTRAINT tb_team_pk PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Table Standing
CREATE TABLE tb_standing (
    id int  NOT NULL AUTO_INCREMENT,
    championship_id int NOT NULL,
    team_id int NOT NULL,
    round smallint NOT NULL,
    team_position smallint NOT NULL,
    CONSTRAINT tb_standing_pk PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- foreign keys

ALTER TABLE tb_standing ADD CONSTRAINT tb_standing_championship_fk FOREIGN KEY (championship_id)
    REFERENCES tb_championship (id);

    
ALTER TABLE tb_standing ADD CONSTRAINT tb_standing_team_fk FOREIGN KEY (team_id)
    REFERENCES tb_team (id);

    
ALTER TABLE tb_team ADD CONSTRAINT tb_team_championship_fk FOREIGN KEY (championship_id)
    REFERENCES tb_championship (id);


ALTER TABLE tb_match ADD CONSTRAINT tb_match_championship_fk FOREIGN KEY (championship_id)
    REFERENCES tb_championship (id);


ALTER TABLE tb_match ADD CONSTRAINT tb_match_away_team_fk FOREIGN KEY (away_team_id)
    REFERENCES tb_team (id);


ALTER TABLE tb_match ADD CONSTRAINT tb_match_home_team_fk FOREIGN KEY (home_team_id)
    REFERENCES tb_team (id);
