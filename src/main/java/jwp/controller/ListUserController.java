package jwp.controller;

import jwp.dao.UserDao;
import jwp.mvc_container.JspView;
import jwp.mvc_container.View;
import jwp.util.HttpSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;


public class ListUserController implements Controller {
    private static Logger logger = Logger.getLogger(ListUserController.class.getName());

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (HttpSessionUtils.isLogined(session)) {
            UserDao userDao = new UserDao();
            request.setAttribute("users",userDao.findAll());

            return new JspView("/user/list.jsp");
        }
        return new JspView("redirect:/users/loginForm");
    }
}
