package jwp;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static boolean isLogined(HttpSession session) {
        Object value = session.getAttribute("user");
        return value != null;
    }
}
