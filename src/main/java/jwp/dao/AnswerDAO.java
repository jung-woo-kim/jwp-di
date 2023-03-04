package jwp.dao;

import jwp.model.Answer;
import jwp.support.jdbc.JdbcTemplate;
import jwp.support.jdbc.KeyHolder;

import java.util.List;

public class AnswerDAO {
    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public Answer insert(Answer answer) {
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update("INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)",
                pstmt -> {
                    pstmt.setString(1, answer.getWriter());
                    pstmt.setString(2,answer.getContents());
                    pstmt.setObject(3,answer.getCreatedDate());
                    pstmt.setObject(4,answer.getQuestionId());
                },
                keyHolder);
        return findById(keyHolder.getId());
    }

    public List<Answer> findAllByQuestionId(String questionId) {
        return jdbcTemplate.query("SELECT * FROM ANSWERS WHERE questionId=?",
                pstmt -> pstmt.setString(1, questionId),
                rs -> new Answer(rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate")));
    }

    public Answer findById(int answerId) {
        return jdbcTemplate.queryForObject("SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?",
                pstmt -> pstmt.setObject(1, answerId),
                rs -> new Answer(rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate")));
    }
}
