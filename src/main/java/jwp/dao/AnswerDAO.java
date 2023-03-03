package jwp.dao;

import jwp.model.Question;
import jwp.support.jdbc.JdbcTemplate;

public class AnswerDAO {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

}
