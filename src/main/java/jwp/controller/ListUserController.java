package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.HttpSessionUtils;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ListUserController implements Controller {
    private static Logger logger = Logger.getLogger(ListUserController.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (HttpSessionUtils.isLogined(session)) {
            MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
            request.setAttribute("users",userRepository.findAll());
            return "/user/list.jsp";
        }
        return "redirect:/users/loginForm";
    }
}
