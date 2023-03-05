package jwp.controller;

import jwp.model.User;
import jwp.mvc_container.JspView;
import jwp.mvc_container.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            if (user.getUserId().equals(request.getParameter("userId"))){
                request.setAttribute("user",user);
                return new JspView("/user/updateForm.jsp");
            }
            return new JspView("redirect:/user/list");
        }
        return new JspView("redirect:/");
    }
}
