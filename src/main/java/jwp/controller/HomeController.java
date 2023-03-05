package jwp.controller;

import jwp.dao.QuestionDAO;
import jwp.mvc_container.JspView;
import jwp.mvc_container.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        QuestionDAO questionDao = new QuestionDAO();
        request.setAttribute("questions", questionDao.findAll());
        return new JspView("/home.jsp");
    }
}
