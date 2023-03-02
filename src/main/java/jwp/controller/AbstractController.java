package jwp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController implements Controller{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            return doGet(request, response);

        }
        return doPost(request, response);
    }
    abstract String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    abstract String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
