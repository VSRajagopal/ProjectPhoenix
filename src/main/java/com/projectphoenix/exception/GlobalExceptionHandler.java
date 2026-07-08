package com.projectphoenix.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class); 

	// ProjectNotFoundException - 404
	// MethodArgumentNotValidException - 400
	// Exception - 500

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ErrorResponse> projectNotFoundException(ProjectNotFoundException exception,
			HttpServletRequest request) {
		logger.error("ProjectNotFoundException handled");
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setPath(request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> unexpectedException(Exception exception, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setPath(request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		StringBuilder sb = new StringBuilder();
		for(FieldError fieldError : fieldErrors) {
			sb.append(fieldError.getField() + " : " + fieldError.getDefaultMessage()).append(", ");
		}
		errorResponse.setMessage(sb.toString());
		errorResponse.setPath(request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
