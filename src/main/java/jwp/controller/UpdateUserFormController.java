package jwp.controller;

import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            if (user.getUserId().equals(request.getParameter("userId"))){
                request.setAttribute("user",user);
                return "/user/updateForm.jsp";
            }
            return "redirect:/user/list";
        }
        return "redirect:/";
    }
}
