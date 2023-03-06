package jwp.controller;

import jwp.dao.UserDao;
import jwp.mvc_container.ModelAndView;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;


public class ListUserController extends AbstractController {
    private static Logger logger = Logger.getLogger(ListUserController.class.getName());

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLogined(session)) {
            UserDao userDao = new UserDao();
            return jspView("/user/list.jsp").addObject("users", userDao.findAll());
        }
        return jspView("redirect:/users/loginForm");
    }
}
