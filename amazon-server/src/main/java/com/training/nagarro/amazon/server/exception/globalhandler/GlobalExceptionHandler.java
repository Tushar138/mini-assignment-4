package com.training.nagarro.amazon.server.exception.globalhandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.training.nagarro.amazon.server.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<String,Object>> badRequestExceptionHandler(NotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.getErrorResponse(exception.getMessage(),404));
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String,Object>> noResourceFoundExceptionHandler(NoResourceFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.getErrorResponse(exception.getMessage(),404));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> genericExceptionHandler(Exception exception){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.getErrorResponse(exception.getMessage(),500));
	}
	
	
	private Map<String,Object> getErrorResponse(String message,int code){
		Map<String, Object> errorResponse=new HashMap<>();
		errorResponse.put("message", message);
		errorResponse.put("code", code);
		errorResponse.put("timestamp", this.formattedDateTime());
		return errorResponse;
	}
	
	private String formattedDateTime() {
		LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
	    return formattedDateTime;
	}
}
