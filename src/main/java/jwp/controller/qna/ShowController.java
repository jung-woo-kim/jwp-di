package jwp.controller.qna;

import jwp.controller.Controller;
import jwp.dao.AnswerDAO;
import jwp.dao.QuestionDAO;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();

        String questionId = request.getParameter("questionId");
        Question question = questionDAO.findByQuestionId(questionId);
        request.setAttribute("question", question);

        List<Answer> answers = answerDAO.findAllByQuestionId(questionId);
        request.setAttribute("answers",answers);

        return "/qna/show.jsp";
    }
}
