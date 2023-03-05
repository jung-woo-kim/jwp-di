package jwp.controller;

import jwp.mvc_container.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
