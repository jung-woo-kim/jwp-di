package jwp.mvc_container;

import jwp.controller.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    Logger logger = Logger.getLogger(DispatcherServlet.class.getName());

    RequestMapping requestMapping;

    @Override
    public void init() {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = requestMapping.getController(req.getRequestURI());
        try {
            View view = controller.execute(req, resp);
            view.render(req,resp);
        } catch (Exception e) {
            logger.log(Level.WARNING,e.getMessage());
        }

    }

}