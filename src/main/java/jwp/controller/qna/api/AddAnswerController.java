package jwp.controller.qna.api;

import jwp.controller.AbstractController;
import jwp.dao.AnswerDAO;
import jwp.dao.QuestionDAO;
import jwp.model.Answer;
import jwp.model.Question;
import core.mvc.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddAnswerController extends AbstractController {
    Logger logger = Logger.getLogger(AbstractController.class.getName());
    AnswerDAO answerDAO = AnswerDAO.getInstance();
    QuestionDAO questionDAO = QuestionDAO.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Answer answer = new Answer(Integer.parseInt(request.getParameter("questionId")), request.getParameter("writer"), request.getParameter("contents"));

        logger.log(Level.INFO,answer.getContents());
        Answer savedAnswer = answerDAO.insert(answer);

        Question question = questionDAO.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDAO.updateCountOfAnswer(question);

        return jsonView().addObject("answer",savedAnswer);
    }
}
