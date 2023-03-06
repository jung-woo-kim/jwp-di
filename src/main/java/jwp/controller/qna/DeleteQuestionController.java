package jwp.controller.qna;

import jwp.controller.AbstractController;
import core.mvc.ModelAndView;
import jwp.service.QnaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteQuestionController extends AbstractController {
    QnaService qnaService = QnaService.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");
        qnaService.deleteQuestion(questionId);

        return jspView("redirect:/");
    }
}
