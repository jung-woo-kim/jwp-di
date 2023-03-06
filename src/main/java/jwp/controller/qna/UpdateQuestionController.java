package jwp.controller.qna;

import jwp.controller.AbstractController;
import jwp.dao.QuestionDAO;
import jwp.model.Question;
import core.mvc.ModelAndView;
import jwp.util.UserSessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class UpdateQuestionController extends AbstractController {
    QuestionDAO questionDAO = QuestionDAO.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (!UserSessionUtils.isLogined(session)) {
            return jspView("redirect:/users/loginForm");
        }

        String questionId = request.getParameter("questionId");
        Question question = questionDAO.findByQuestionId(Integer.parseInt(questionId));

        if (!question.isSameUser(Objects.requireNonNull(UserSessionUtils.getUserFromSession(session)))) {
            throw new IllegalArgumentException();
        }

        question.updateTitleAndContents(request.getParameter("title"),request.getParameter("contents"));
        questionDAO.update(question);

        return jspView("redirect:/");
    }
}
