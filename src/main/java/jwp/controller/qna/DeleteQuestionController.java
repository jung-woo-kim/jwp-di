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
import java.util.stream.Collectors;

public class DeleteQuestionController extends AbstractController {
    QuestionDAO questionDAO = QuestionDAO.getInstance();
    AnswerDAO answerDAO = AnswerDAO.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");
        List<Answer> answers = answerDAO.findAllByQuestionId(Integer.parseInt(questionId));
        Question question = questionDAO.findByQuestionId(Integer.parseInt(questionId));

        List<Answer> notWriterAnswers = answers.stream().filter(answer -> !answer.getWriter().equals(question.getWriter())).collect(Collectors.toList());
        if (notWriterAnswers.size() > 0) {
            throw new IllegalArgumentException();
        }

        answerDAO.delete(question.getQuestionId());
        questionDAO.delete(question.getQuestionId());

        return jspView("redirect:/");
    }
}
