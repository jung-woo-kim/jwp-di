package jwp.controller;

import jwp.mvc_container.JspView;
import jwp.mvc_container.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardController implements Controller{
    String url;

    public ForwardController(String url) {
        this.url = url;
    }

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new JspView(url);
    }
}
