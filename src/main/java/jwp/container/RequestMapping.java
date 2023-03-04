package jwp.container;

import jwp.controller.*;
import jwp.controller.qna.ShowController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private final Map<String, Controller> controllers = new HashMap<>();

    public RequestMapping() {
        initControllers();
    }

    private void initControllers() {
        controllers.put("/users/create",new CreateUserController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/", new HomeController());
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/login", new LogInController());
        controllers.put("/users/logout", new LogOutController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/updateForm", new UpdateUserFormController());
        controllers.put("/qna/show", new ShowController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
