package dao;

import model.GameScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC access to the game_scores table (Tasks 07 and 08), PostgreSQL version.
 * Change USER / PASSWORD below to match your PostgreSQL setup.
 */
public class ScoreDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/math_whiz_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";   // <-- password you set when installing PostgreSQL

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void save(GameScore g) throws SQLException {
        String sql = "INSERT INTO game_scores (player_name, score, difficulty, played_at) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, g.getPlayerName());
            ps.setInt(2, g.getScore());
            ps.setString(3, g.getDifficulty());
            ps.setTimestamp(4, g.getPlayedAt());
            ps.executeUpdate();
        }
    }

    public List<GameScore> getTop10() throws SQLException {
        String sql = "SELECT id, player_name, score, difficulty, played_at "
                   + "FROM game_scores ORDER BY score DESC, played_at ASC LIMIT 10";
        List<GameScore> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GameScore g = new GameScore();
                g.setId(rs.getInt("id"));
                g.setPlayerName(rs.getString("player_name"));
                g.setScore(rs.getInt("score"));
                g.setDifficulty(rs.getString("difficulty"));
                g.setPlayedAt(rs.getTimestamp("played_at"));
                list.add(g);
            }
        }
        return list;
    }
}
