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
    QuestionDAO questionDAO;

    @BeforeEach
    public void setup() {
        questionDAO = QuestionDAO.getInstance();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void addQuestion() {
        Question expected = new Question("writer", "title", "contents",0);
        Question actual = questionDAO.insert(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void findAll() {
        assertEquals(8, questionDAO.findAll().size());
    }
}