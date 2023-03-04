package jwp.dao;

import jwp.model.Answer;
import jwp.model.Question;
import jwp.support.jdbc.JdbcTemplate;

import java.util.List;

public class AnswerDAO {
    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public List<Answer> findAllByQuestionId(String questionId) {
        return jdbcTemplate.query("SELECT * FROM ANSWERS WHERE questionId=?",
                pstmt -> pstmt.setString(1, questionId),
                rs -> new Answer(rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate")));
    }
}
