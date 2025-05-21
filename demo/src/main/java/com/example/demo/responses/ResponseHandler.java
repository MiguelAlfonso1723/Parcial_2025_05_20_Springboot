package com.example.demo.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object response){
		Map<String, Object> body = new HashMap<>();
		body.put("message", message);
		body.put("status", status);
		body.put("response", response);
		
		return new ResponseEntity<>(body, status);
	}

}
