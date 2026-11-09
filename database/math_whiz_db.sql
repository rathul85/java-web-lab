-- PostgreSQL version
-- STEP 1: create the database (run this while connected to the default "postgres" database).
-- PostgreSQL has no "CREATE DATABASE IF NOT EXISTS", so run it only once.
CREATE DATABASE math_whiz_db;

-- STEP 2: connect to math_whiz_db (psql: \c math_whiz_db  |  pgAdmin: select math_whiz_db, open Query Tool)
-- then run the table creation below.
CREATE TABLE IF NOT EXISTS game_scores (
  id          SERIAL PRIMARY KEY,
  player_name VARCHAR(100) NOT NULL,
  score       INT          NOT NULL,
  difficulty  VARCHAR(20)  NOT NULL,
  played_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- Top 10 leaderboard query
-- SELECT * FROM game_scores ORDER BY score DESC, played_at ASC LIMIT 10;
