package jwp.dao;

import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();
    public void insert(User user) throws SQLException {
        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)", pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public void update(User user) throws SQLException {

        jdbcTemplate.update("update users set password=?, name=?, email=? where userId=?", pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        });
    }


    public List<User> findAll() throws SQLException {
        // TODO 구현 필요함.
        return jdbcTemplate.query("SELECT * FROM USERS", rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                rs.getString("email")));
    }

    public User findByUserId(String userId) throws SQLException {

        User user = jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
                pstmt -> pstmt.setString(1, userId),
                rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email")));
        return user;
    }
}
