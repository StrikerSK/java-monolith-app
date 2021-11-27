package com.application.main.exceptions;

import com.application.exceptions.InvalidVariableException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidVariableHandler {

	@ExceptionHandler(InvalidVariableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleInvalidVariableException(InvalidVariableException ex) {
		return new ErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleTypeMismatchException(TypeMismatchException ex) {
		return new ErrorResponse(String.format("Cannot convert parameter to required type %s", ex.getRequiredType()));
	}
}
