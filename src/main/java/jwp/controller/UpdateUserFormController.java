package jwp.controller;

import jwp.model.User;
import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            if (user.getUserId().equals(request.getParameter("userId"))){
                return jspView("/user/updateForm.jsp").addObject("user",user);
            }
            return jspView("redirect:/user/list");
        }
        return jspView("redirect:/");
    }
}
