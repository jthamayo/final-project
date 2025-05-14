package io.github.jthamayo.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException(String message) {
	super(message);
    }

    public InvalidOperationException(String message, Throwable cause) {
	super(message, cause);
    }

}
