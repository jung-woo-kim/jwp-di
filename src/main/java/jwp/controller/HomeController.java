package jwp.controller;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import jwp.dao.QuestionDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    QuestionDAO questionDAO = QuestionDAO.getInstance();

    @RequestMapping("/")
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(new JspView("/home.jsp"));
        return modelAndView.addObject("questions",questionDAO.findAll());
    }
}
