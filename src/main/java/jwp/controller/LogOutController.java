package jwp.controller;

import jwp.mvc_container.JspView;
import jwp.mvc_container.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogOutController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return new JspView("redirect:/");
    }
}
