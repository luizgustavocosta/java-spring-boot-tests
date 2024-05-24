package com.costa.luiz.tropicalflix.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementExceptionHandler() {
        return new ModelAndView("exception")
                .addObject("status", HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(Exception exception) {
        return new ModelAndView("exception")
                .addObject("status",
                        "Ocorreu o erro " + exception.getLocalizedMessage() + " no processamento");
    }
    @ExceptionHandler(NonExistingEntity.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNonExistingEntity() {
    }

}
