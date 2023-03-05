package jwp.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jwp.controller.AbstractController;
import jwp.controller.Controller;
import jwp.dao.AnswerDAO;
import jwp.model.Answer;
import jwp.model.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteAnswerController implements Controller {
    Logger logger = Logger.getLogger(AbstractController.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int answerId = Integer.parseInt(request.getParameter("answerId"));

        AnswerDAO answerDAO = new AnswerDAO();
        answerDAO.delete(answerId);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(Result.ok()));
        return null;
    }
}
