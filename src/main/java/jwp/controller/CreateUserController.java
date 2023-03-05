package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import jwp.mvc_container.JspView;
import jwp.mvc_container.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


public class CreateUserController implements Controller {

    Logger logger = Logger.getLogger(CreateUserController.class.getName());
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        UserDao userDao = new UserDao();
        userDao.insert(user);

        return new JspView("redirect:/user/list");
    }
}
