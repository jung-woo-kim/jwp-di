package jwp.controller.qna;

import jwp.controller.AbstractController;
import jwp.dao.AnswerDAO;
import jwp.dao.QuestionDAO;
import jwp.mvc_container.ModelAndView;
import jwp.service.QnaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteQuestionController extends AbstractController {
    QuestionDAO questionDAO = QuestionDAO.getInstance();
    AnswerDAO answerDAO = AnswerDAO.getInstance();
    QnaService qnaService = QnaService.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");
        qnaService.deleteQuestion(questionId);

        return jspView("redirect:/");
    }
}
