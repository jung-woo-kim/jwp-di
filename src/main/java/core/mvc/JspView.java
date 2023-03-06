package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class JspView implements View{
    String viewName;

    public JspView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String,?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        move(model, request,response);
    }

    private void move(Map<String,?> model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (viewName.startsWith("redirect")) {
            resp.sendRedirect(viewName.split(":")[1]);
            return;
        }
        Set<String> keys = model.keySet();
        for (String key: keys) {
            req.setAttribute(key,model.get(key));
        }
        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }
}
