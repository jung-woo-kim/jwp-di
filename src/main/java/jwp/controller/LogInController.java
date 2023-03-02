package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
        User user = memoryUserRepository.findUserById(userId);

        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            return "redirect:/";
        }

        request.setAttribute("loginFailed",true);
        return "/user/login.jsp";
    }
}
