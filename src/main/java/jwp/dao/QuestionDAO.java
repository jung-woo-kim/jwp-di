package jwp.dao;

import jwp.model.Question;
import jwp.support.jdbc.JdbcTemplate;
import jwp.support.jdbc.KeyHolder;

import java.util.List;

public class QuestionDAO {
    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    private static QuestionDAO questionDAO;

    private QuestionDAO() {
    }

    public static QuestionDAO getInstance() {
        if (questionDAO == null) {
            questionDAO = new QuestionDAO();
        }
        return questionDAO;
    }

    public Question insert(Question question) {
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update("INSERT INTO QUESTIONS " +
                "(writer, title, contents, createdDate) " +
                " VALUES (?, ?, ?, ?)",
                pstmt -> {
                    pstmt.setString(1, question.getWriter());
                    pstmt.setString(2,question.getTitle());
                    pstmt.setString(3,question.getContents());
                    pstmt.setObject(4,question.getCreatedDate());
                },
                keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM QUESTIONS WHERE questionId=?",
                pstmt -> pstmt.setObject(1,id));
    }

    public List<Question> findAll() {
        return jdbcTemplate.query("SELECT * FROM QUESTIONS order by questionId", rs ->
                new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

    public Question findByQuestionId(int questionId) {
        return jdbcTemplate.queryForObject("SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?",
                pstmt -> pstmt.setObject(1, questionId),
                rs -> new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

    public void update(Question question) {
        String sql = "UPDATE QUESTIONS set title = ?, contents = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, question.getTitle(), question.getContents(), question.getQuestionId());
    }

    public void updateCountOfAnswer(Question question) {

        jdbcTemplate.update("update QUESTIONS set countOfAnswer=? where questionId=?", pstmt -> {
            pstmt.setObject(1, question.getCountOfAnswer());
            pstmt.setObject(2, question.getQuestionId());
        });
    }
}
