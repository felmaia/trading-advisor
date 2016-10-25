SELECT * FROM `tb_match` WHERE home_team_id=462 AND away_team_id=457

SELECT (home_team_id)FROM `tb_match`

UPDATE tb_match SET DATA='2015-11-21', home_team_score=2 , away_team_score =1 , home_team_ht_score= 1, away_team_ht_score =0, time_home_team_first_goal=35, time_away_team_first_goal=59 WHERE id= 5662

SELECT * FROM `tb_match` m
JOIN tb_team t ON t.id = m.home_team_id
WHERE m.championship_id=1

SELECT  COUNT(*) FROM tb_match m
JOIN tb_team t ON t.id = m.away_team_id
WHERE m.home_team_score IS NOT NULL
AND t.id=343

SELECT  SUM(m.away_team_ht_score) FROM tb_match m
JOIN tb_team t ON t.id = m.away_team_id
WHERE m.home_team_score IS NOT NULL
AND t.id=343

SELECT hot.name, awt.name, m.home_team_score, m.away_team_score, m.data, m.championship_round FROM `tb_match` m
JOIN tb_team hot ON hot.id = m.home_team_id
JOIN tb_team awt ON awt.id = m.away_team_id
WHERE m.championship_id=1
AND (hot.id = 10 OR awt.id=10)
ORDER BY m.data

SELECT * FROM tb_match m 
WHERE (m.championship_id  IN (23))AND((m.home_team_score > 0 && time_home_team_first_goal IS NULL) OR (m.away_team_score > 0 AND time_away_team_first_goal IS NULL))
ORDER BY m.data

SELECT * FROM tb_match m 
WHERE (m.championship_id  IN (24))AND(m.home_team_score IS NOT NULL)AND(m.away_team_id=483)
ORDER BY m.data


SELECT * FROM tb_match m 
WHERE (m.home_team_score > 0 && time_home_team_first_goal IS NULL) OR (m.away_team_score > 0 AND time_away_team_first_goal IS NULL)
ORDER BY m.data


UPDATE tb_match SET time_home_team_first_goal=48 WHERE id= 4882

UPDATE tb_championship SET matches_url_academia_das_apostas='https://www.academiadasapostasbrasil.com/stats/competition/russia-stats/121/11670/31690/0/',  rounds=30
WHERE id=26

SELECT * FROM tb_match m WHERE m.championship_id=3 AND m.home_team_score IS NOT NULL

SELECT t.position, t.name, c.name FROM `tb_team` t
JOIN tb_championship c ON c.id = t.championship_id
WHERE c.id=26
ORDER BY t.position

SELECT * FROM tb_match WHERE championship_id=1

SELECT * FROM tb_match WHERE url_match_stats_academia_das_apostas IS NULL

SELECT * FROM `tb_team`

SELECT * FROM `tb_team` WHERE championship_id=13 ORDER BY NAME

UPDATE tb_team SET name_o_gol='FC Köln' WHERE id=250

SELECT * FROM `tb_championship`

UPDATE tb_team SET NAME = 'Bourg-Peronnas' WHERE id=228
---Bourg-Peronnas 228

SELECT @@global.time_zone, @@session.time_zone;
SELECT TIMEDIFF(NOW(), UTC_TIMESTAMP);
SELECT NOW()

SET GLOBAL time_zone = '-2:00';
SET time_zone = '-2:00';
SET GLOBAL time_zone = 'America/Sao_Paulo';
SET time_zone = 'America/Sao_Paulo';



SELECT NOW(),
SELECT CURDATE(),CURTIME()
SELECT GETUTCDATE()


ALTER TABLE tb_team ADD COLUMN name_o_gol VARCHAR(255)
ALTER TABLE tb_championship ADD COLUMN o_gol_classification_url VARCHAR(255)
ALTER TABLE tb_championship ADD COLUMN total_rounds SMALLINT
ALTER TABLE tb_match ADD COLUMN  time_away_team_first_goal SMALLINT
ALTER TABLE tb_match ADD COLUMN  home_team_position SMALLINT
ALTER TABLE tb_match ADD COLUMN  championship_round SMALLINT

ALTER TABLE tb_standing DROP COLUMN teamPosition
ALTER TABLE tb_championship DROP COLUMN rounds


SELECT * FROM tb_standing ORDER BY championship_id
SELECT t.name_o_gol, t.id, s.* FROM tb_standing s
JOIN tb_team t ON t.id = s.team_id
WHERE s.championship_id=23
DELETE FROM tb_standing WHERE championship_id=2 AND ROUND=34
UPDATE tb_championship SET total_rounds=30,  o_gol_classification_url='http://www.ogol.com.br/edition.php?jornada_in=$&id_edicao=87194&fase=81899' WHERE id=26;
SELECT * FROM `tb_championship`

INSERT INTO tb_standing (championship_id, team_id, ROUND, team_position) VALUES (23, 457, 16, 21)

SELECT id, NAME, name_o_gol FROM tb_team WHERE championship_id=26


SELECT * FROM tb_standing WHERE championship_id=5 AND ROUND=9 AND team_id=92
DELETE FROM tb_standing WHERE id=6066

SELECT SYSDATE FROM DUAL