package core.nmvc;

import core.mvc.ModelAndView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationHandlerMappingTest {
    private AnnotationHandlerMapping handlerMapping;
    private MockHttpServletResponse response;
    private MockHttpServletRequest request;
    @BeforeEach
    void setUp() {
        handlerMapping = new AnnotationHandlerMapping("core.nmvc");
        handlerMapping.initialize();

        response = new MockHttpServletResponse();
    }

    @Test
    public void list() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/users");
        HandlerExecution execution = handlerMapping.getHandler(request);
        ModelAndView mav = execution.handle(request, response);
        mav.render(request, response);
        assertEquals("/users/list.jsp", response.getForwardedUrl());
    }

    @Test
    public void show() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/users/show");
        HandlerExecution execution = handlerMapping.getHandler(request);
        ModelAndView mav = execution.handle(request, response);
        mav.render(request, response);
        assertEquals("/users/show.jsp", response.getForwardedUrl());
    }

    @Test
    public void create() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/users");
        HandlerExecution execution = handlerMapping.getHandler(request);
        ModelAndView mav = execution.handle(request, response);
        mav.render(request, response);
        assertEquals("/users", response.getRedirectedUrl());
    }
}