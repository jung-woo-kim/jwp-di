package jwp.controller.qna;

import jwp.controller.AbstractController;
import jwp.dao.QuestionDAO;
import jwp.model.Question;
import jwp.mvc_container.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question = new Question(request.getParameter("writer"), request.getParameter("title"), request.getParameter("contents"), 0);
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.insert(question);


        return jspView("redirect:/");
    }
}
