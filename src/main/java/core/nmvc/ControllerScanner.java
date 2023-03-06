package core.nmvc;

import core.annotation.Controller;
import org.reflections.Reflections;
import sun.reflect.Reflection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerScanner {
    private static final Logger logger = Logger.getLogger(ControllerScanner.class.getName());

    Reflections reflections;

    public ControllerScanner(Object... basePackage) {
        this.reflections = new Reflections(basePackage);
    }

    public Map<Class<?>, Object> getControllers() {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
        return instantiateControllers(annotated);
    }


    private Map<Class<?>, Object> instantiateControllers(Set<Class<?>> annotated ) {
        Map<Class<?>, Object> controllers = new HashMap<>();
        try {
            for (Class<?> clazz : annotated) {
                controllers.put(clazz,clazz.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return controllers;
    }
}
