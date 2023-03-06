package jwp.controller.qna.api;

import jwp.controller.AbstractController;
import jwp.dao.AnswerDAO;
import jwp.dao.QuestionDAO;
import jwp.model.Answer;
import jwp.model.Question;
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
    AnswerDAO answerDAO = AnswerDAO.getInstance();
    QuestionDAO questionDAO = QuestionDAO.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int answerId = Integer.parseInt(request.getParameter("answerId"));


        Answer answer = answerDAO.findById(answerId);
        Question question = questionDAO.findByQuestionId(answer.getQuestionId());
        question.decreaseCountOfAnswer();
        questionDAO.updateCountOfAnswer(question);

        answerDAO.delete(answerId);

        return jsonView().addObject("result",Result.ok());
    }
}
