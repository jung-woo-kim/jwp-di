package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.Answer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import static org.junit.jupiter.api.Assertions.*;

class AnswerDAOTest {
    static AnswerDAO answerDAO;
    @BeforeAll
    public static void setup() {
        answerDAO = AnswerDAO.getInstance();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void addAnswer() throws Exception {
        int questionId = 1;
        Answer expected = new Answer(questionId,"javajigi", "answer contents");
        Answer actual = answerDAO.insert(expected);
        assertEquals(expected,actual);
    }
}