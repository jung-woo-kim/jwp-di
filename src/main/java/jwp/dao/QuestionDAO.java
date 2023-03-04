package jwp.dao;

import jwp.model.Question;
import jwp.support.jdbc.JdbcTemplate;

import java.util.List;

public class QuestionDAO {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public List<Question> findAll() {
        return jdbcTemplate.query("SELECT * FROM QUESTIONS order by questionId", rs ->
                new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

    public Question findByQuestionId(String questionId) {
        return jdbcTemplate.queryForObject("SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?",
                pstmt -> pstmt.setString(1, questionId),
                rs -> new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }
}
