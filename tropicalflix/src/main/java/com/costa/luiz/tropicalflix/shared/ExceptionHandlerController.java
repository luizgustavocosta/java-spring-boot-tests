package com.costa.luiz.tropicalflix.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UIException.class)
    public ModelAndView uiExceptionHander(Exception exception) {
        return new ModelAndView("exception")
                .addObject("status",
                        "Ocorreu o erro " + exception.getMessage() + " no processamento");
    }

    @ExceptionHandler(NonExistingEntity.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNonExistingEntity() {
    }

}
