package jwp.controller.api;

import jwp.controller.AbstractController;
import jwp.controller.Controller;
import jwp.dao.AnswerDAO;
import jwp.model.Result;
import jwp.mvc_container.JsonView;
import jwp.mvc_container.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class DeleteAnswerController implements Controller {
    Logger logger = Logger.getLogger(AbstractController.class.getName());

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int answerId = Integer.parseInt(request.getParameter("answerId"));

        AnswerDAO answerDAO = new AnswerDAO();
        answerDAO.delete(answerId);
        request.setAttribute("result",Result.ok());
        return new JsonView();
    }
}
