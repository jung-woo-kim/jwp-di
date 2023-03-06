package jwp.controller;

import core.mvc.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardController extends AbstractController{
    String url;

    public ForwardController(String url) {
        this.url = url;
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return jspView(url);
    }
}
