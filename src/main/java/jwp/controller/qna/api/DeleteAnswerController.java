package jwp.controller.qna.api;

import jwp.controller.AbstractController;
import jwp.dao.AnswerDAO;
import jwp.model.Result;
import jwp.mvc_container.JsonView;
import jwp.mvc_container.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class DeleteAnswerController extends AbstractController {
    Logger logger = Logger.getLogger(AbstractController.class.getName());

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int answerId = Integer.parseInt(request.getParameter("answerId"));

        AnswerDAO answerDAO = new AnswerDAO();
        answerDAO.delete(answerId);
        return jsonView().addObject("result",Result.ok());
    }
}
