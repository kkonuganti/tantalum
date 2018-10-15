package com.tantalum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateIDController {
	
	@Value("${property.prefix}")
	private Object prefix;
	@Value("${property.suffix}")
	private Object suffix;
	
	@ExceptionHandler
	public ResponseEntity<Object> handleException(Exception ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/message-uuid",produces=MediaType.TEXT_PLAIN_VALUE)
	public String generateID() {
		return new IDModel(prefix,suffix).getId();
	}
	


}
