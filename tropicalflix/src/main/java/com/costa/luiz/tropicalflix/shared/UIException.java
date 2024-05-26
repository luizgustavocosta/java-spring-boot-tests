package com.costa.luiz.tropicalflix.shared;

public class UIException extends RuntimeException {
    public UIException(Exception exception) {
        super(exception);
    }
}
