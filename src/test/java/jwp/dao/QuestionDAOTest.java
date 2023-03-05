package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionDAOTest {
    static QuestionDAO questionDAO;

    @BeforeEach
    public void setup() {
        questionDAO = new QuestionDAO();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void addQuestion() {
        Question expected = new Question("writer", "title", "contents",0);
        QuestionDAO questionDao = new QuestionDAO();
        Question actual = questionDao.insert(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void findAll() {
        QuestionDAO questionDao = new QuestionDAO();
        assertEquals(8, questionDao.findAll().size());
    }
}