package jwp.controller;

import jwp.mvc_container.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController implements Controller{
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            return doGet(request, response);

        }
        return doPost(request, response);
    }
    abstract View doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    abstract View doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
