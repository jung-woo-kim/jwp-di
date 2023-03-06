package jwp.controller.qna;

import jwp.controller.AbstractController;
import jwp.dao.AnswerDAO;
import jwp.dao.QuestionDAO;
import jwp.model.Answer;
import jwp.model.Question;
import jwp.mvc_container.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowController extends AbstractController {
    AnswerDAO answerDAO = AnswerDAO.getInstance();
    QuestionDAO questionDAO = QuestionDAO.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String questionId = request.getParameter("questionId");
        Question question = questionDAO.findByQuestionId(Integer.parseInt(questionId));
        List<Answer> answers = answerDAO.findAllByQuestionId(Integer.parseInt(questionId));

        return jspView("/qna/show.jsp").addObject("question", question).addObject("answers",answers);
    }
}
