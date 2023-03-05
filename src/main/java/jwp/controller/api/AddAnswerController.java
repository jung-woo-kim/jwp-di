package jwp.controller.api;

import jwp.controller.AbstractController;
import jwp.controller.Controller;
import jwp.dao.AnswerDAO;
import jwp.model.Answer;
import jwp.mvc_container.JsonView;
import jwp.mvc_container.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddAnswerController implements Controller {
    Logger logger = Logger.getLogger(AbstractController.class.getName());

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Answer answer = new Answer(Integer.parseInt(request.getParameter("questionId")), request.getParameter("writer"), request.getParameter("contents"));

        logger.log(Level.INFO,answer.getContents());

        AnswerDAO answerDAO = new AnswerDAO();
        Answer savedAnswer = answerDAO.insert(answer);
        request.setAttribute("answer",savedAnswer);
        return new JsonView();
    }
}
