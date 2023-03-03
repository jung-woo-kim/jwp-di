package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


public class UpdateUserController implements Controller {

    Logger logger = Logger.getLogger(UpdateUserController.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));

        UserDao userDao = new UserDao();
        userDao.update(user);

        return "redirect:/user/list";
    }
}
