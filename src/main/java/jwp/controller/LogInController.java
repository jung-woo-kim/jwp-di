package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import jwp.mvc_container.JspView;
import jwp.mvc_container.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

public class LogInController implements Controller {

    Logger logger = Logger.getLogger(LogInController.class.getName());

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        User user = userDao.findByUserId(userId);
        if (user != null && user.matchPassword(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return new JspView("redirect:/");
        }

        request.setAttribute("loginFailed", true);
        return new JspView("/user/login.jsp");
    }
}
