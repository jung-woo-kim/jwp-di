package jwp.dao;

import jwp.model.User;
import jwp.support.jdbc.JdbcTemplate;

import java.util.List;

public class UserDao {
    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();
    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)", pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public void update(User user) {

        jdbcTemplate.update("update users set password=?, name=?, email=? where userId=?", pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        });
    }


    public List<User> findAll() {
        // TODO 구현 필요함.
        return jdbcTemplate.query("SELECT * FROM USERS", rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                rs.getString("email")));
    }

    public User findByUserId(String userId) {
        return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
                pstmt -> pstmt.setString(1, userId),
                rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email")));
    }
}
