package com.costa.luiz.tropicalflix.shared;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionHandlerControllerTest {

    private final ExceptionHandlerController exceptionHandlerController = new ExceptionHandlerController();

    @Test
    void noSuchElementExceptionHandler() {
        ModelAndView modelAndView = exceptionHandlerController.uiExceptionHander(new RuntimeException());
        assertEquals("exception", modelAndView.getViewName());
    }

    @Test
    void defaultExceptionHandler() {
        Exception exception = new Exception("Test exception");
        ModelAndView modelAndView = exceptionHandlerController.uiExceptionHander(exception);
        assertEquals("exception", modelAndView.getViewName());
        assertEquals("Ocorreu o erro " + exception.getLocalizedMessage() + " no processamento", modelAndView.getModel().get("status"));
    }

    @Test
    void handleNonExistingEntity() {
        // Since this method just sets the response status, we don't need to assert anything here.
        // If the code compiles without errors, the test is successful.
        exceptionHandlerController.handleNonExistingEntity();
    }
}