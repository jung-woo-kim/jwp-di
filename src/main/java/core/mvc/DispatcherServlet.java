package core.mvc;

import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
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

    LegacyHandlerMapping lhm;
    AnnotationHandlerMapping ahm;


    @Override
    public void init() {
        lhm = new LegacyHandlerMapping();
        ahm = new AnnotationHandlerMapping();
        ahm.initialize();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Controller controller = lhm.getController(req.getRequestURI());
            if (controller != null) {
                ModelAndView view = controller.execute(req, resp);
                view.render(req,resp);
                return;
            }
            HandlerExecution handler = ahm.getHandler(req);
            ModelAndView view = handler.handle(req, resp);
            view.render(req, resp);
        } catch (Exception e) {
            logger.log(Level.WARNING,e.getMessage());
        }

    }

}
