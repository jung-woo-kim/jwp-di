package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/updateForm")
public class UpdateUserFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
        User user = memoryUserRepository.findUserById(userId);

        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        req.setAttribute("user",user);
        rd.forward(req,resp);
    }
}
