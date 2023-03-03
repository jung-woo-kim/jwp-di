package jwp.controller;

import jwp.dao.QuestionDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        QuestionDAO questionDao = new QuestionDAO();
        request.setAttribute("questions", questionDao.findAll());
        return "/home.jsp";
    }
}
