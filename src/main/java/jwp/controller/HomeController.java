package jwp.controller;

import jwp.dao.QuestionDAO;
import jwp.mvc_container.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        QuestionDAO questionDao = new QuestionDAO();
        return jspView("/home.jsp").addObject("questions", questionDao.findAll());
    }
}
