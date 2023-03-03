package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.util.HttpSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ListUserController implements Controller {
    private static Logger logger = Logger.getLogger(ListUserController.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (HttpSessionUtils.isLogined(session)) {
            UserDao userDao = new UserDao();
            try {
                request.setAttribute("users",userDao.findAll());
            } catch (SQLException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
            return "/user/list.jsp";
        }
        return "redirect:/users/loginForm";
    }
}
