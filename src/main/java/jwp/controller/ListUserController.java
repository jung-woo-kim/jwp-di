package jwp.controller;

import core.db.MemoryUserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(value = "/user/list")
public class ListUserController extends HttpServlet {
    private static Logger logger = Logger.getLogger(ListUserController.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sddssdsd");
        logger.log(Level.INFO,"ds;knmfldanfldn alnm");
        MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
        System.out.println("sddssdsd");
        req.setAttribute("users",userRepository.findAll());
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dsdadas");
    }
}
