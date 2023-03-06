package core.nmvc;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationHandlerMapping implements HandlerMapping{
    private final Object[] basePackage;

    private final Map<HandlerKey, HandlerExecution> handlerExecutions = new HashMap<>();
    private ControllerScanner controllerScanner;

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        controllerScanner = new ControllerScanner(basePackage);
        Map<Class<?>, Object> controllers = controllerScanner.getControllers();
        Set<Method> requestMappingMethods = getRequestMappingMethods(controllers.keySet());
        for (Method method : requestMappingMethods) {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            handlerExecutions.put(createHandlerKey(annotation),new HandlerExecution(controllers.get(method.getDeclaringClass()),method));
        }
    }

    private static HandlerKey createHandlerKey(RequestMapping annotation) {
        return new HandlerKey(annotation.value(), annotation.method());
    }

    @Override
    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        return handlerExecutions.get(new HandlerKey(requestUri, rm));
    }

    private Set<Method> getRequestMappingMethods(Set<Class<?>> controllers) {
        return controllers.stream()
                .flatMap(aClass -> Arrays.stream(aClass.getMethods()))
                .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                .collect(Collectors.toSet());
    }
}