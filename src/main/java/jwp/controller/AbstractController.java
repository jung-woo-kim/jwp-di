package jwp.controller;

import core.mvc.JsonView;
import core.mvc.JspView;
import core.mvc.ModelAndView;

public abstract class AbstractController implements Controller{
    protected ModelAndView jspView(String url) {
        return new ModelAndView(new JspView(url));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
