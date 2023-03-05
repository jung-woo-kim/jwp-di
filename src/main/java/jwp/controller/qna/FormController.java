package jwp.controller.qna;

import jwp.controller.AbstractController;
import jwp.dao.QuestionDAO;
import jwp.model.Question;
import jwp.mvc_container.ModelAndView;
import jwp.util.HttpSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (HttpSessionUtils.isLogined(session)) {
            return jspView("/qna/form.jsp");
        }
        return jspView("redirect:/");
    }
}
