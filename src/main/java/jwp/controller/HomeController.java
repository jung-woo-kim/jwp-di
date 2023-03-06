package jwp.controller;

import jwp.dao.QuestionDAO;
import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeController extends AbstractController {
    QuestionDAO questionDAO = QuestionDAO.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        return jspView("/home.jsp").addObject("questions", questionDAO.findAll());
    }
}
