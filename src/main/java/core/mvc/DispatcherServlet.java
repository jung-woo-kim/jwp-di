package core.mvc;

import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
import core.nmvc.HandlerMapping;
import jwp.controller.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(DispatcherServlet.class.getName());

    private final List<HandlerMapping> handlerMappings = new ArrayList<>();

    @Override
    public void init() {
        LegacyHandlerMapping lhm = new LegacyHandlerMapping();
        lhm.initControllers();
        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping();
        ahm.initialize();
        handlerMappings.add(lhm);
        handlerMappings.add(ahm);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object handler = getHandler(req);
            if (handler == null) {
                throw new IllegalArgumentException();
            }
            ModelAndView view = execute(handler, req, resp);
            view.render(req, resp);
        } catch (Exception e) {
            logger.log(Level.WARNING,e.getMessage());
        }

    }

    private ModelAndView execute(Object handler, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (handler instanceof Controller) {
            return ((Controller) handler).execute(req,resp);
        }
        return ((HandlerExecution) handler).handle(req,resp);
    }

    private Object getHandler(HttpServletRequest req) {
        for (HandlerMapping handlerMapping : handlerMappings) {
            Object handler = handlerMapping.getHandler(req);
            if (handler != null) {
                return handler;
            }
        }
        return null;
    }

}
