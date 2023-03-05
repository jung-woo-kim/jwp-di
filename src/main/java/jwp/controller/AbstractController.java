package jwp.controller;

import jwp.mvc_container.JsonView;
import jwp.mvc_container.JspView;
import jwp.mvc_container.ModelAndView;

public abstract class AbstractController implements Controller{
    protected ModelAndView jspView(String url) {
        return new ModelAndView(new JspView(url));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
