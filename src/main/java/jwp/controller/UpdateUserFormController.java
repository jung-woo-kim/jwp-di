package jwp.controller;

import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/users/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            if (user.getUserId().equals(req.getParameter("userId"))){
                RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
                req.setAttribute("user",user);
                rd.forward(req,resp);
                return;
            }
            resp.sendRedirect("/user/list");
            return;
        }
        resp.sendRedirect("/");
    }
}
