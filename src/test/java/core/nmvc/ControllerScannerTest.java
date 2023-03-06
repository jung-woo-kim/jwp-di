package core.nmvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerScannerTest {
    private static final Logger logger = Logger.getLogger(ControllerScannerTest.class.getName());

    private ControllerScanner cf;

    @BeforeEach
    public void setup() {
        cf = new ControllerScanner("core.nmvc");
    }

    @Test
    public void getControllers() {
        Map<Class<?>, Object> controllers = cf.getControllers();
        for (Class<?> controller : controllers.keySet()) {
            logger.log(Level.INFO,"controller : " + controller.getName());
        }
    }
}