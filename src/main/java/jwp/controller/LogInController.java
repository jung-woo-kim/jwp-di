package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogInController implements Controller {

    Logger logger = Logger.getLogger(LogInController.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        try {
            User user = userDao.findByUserId(userId);
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                return "redirect:/";
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage());
        }



        request.setAttribute("loginFailed",true);
        return "/user/login.jsp";
    }
}
