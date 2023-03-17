package core.mvc;

import core.nmvc.HandlerMapping;
import jwp.controller.*;
import jwp.controller.qna.*;
import jwp.controller.qna.api.AddAnswerController;
import jwp.controller.qna.api.DeleteAnswerController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LegacyHandlerMapping implements HandlerMapping {
    private final Map<String, Controller> controllers = new HashMap<>();

    public LegacyHandlerMapping() {
    }

    public void initControllers() {

        controllers.put("/users/create",new CreateUserController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/login", new LogInController());
        controllers.put("/users/logout", new LogOutController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/updateForm", new UpdateUserFormController());

        controllers.put("/qna/show", new ShowController());
        controllers.put("/qna/form", new FormController());
        controllers.put("/qna/create", new AddQuestionController());


        controllers.put("/api/qna/addAnswer", new AddAnswerController());
        controllers.put("/api/qna/deleteAnswer", new DeleteAnswerController());
        controllers.put("/qna/updateForm", new UpdateQuestionFormController());
        controllers.put("/qna/update", new UpdateQuestionController());
        controllers.put("/qna/delete", new DeleteQuestionController());

    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        return controllers.get(request.getRequestURI());
    }
}
