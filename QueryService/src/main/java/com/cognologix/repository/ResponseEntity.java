package com.cognologix.repository;

import org.springframework.http.HttpStatus;

public class ResponseEntity {

	private Object ouput;
	private HttpStatus status;
	
	public ResponseEntity() {
		// TODO Auto-generated constructor stub
	}

	public ResponseEntity(String ouput, HttpStatus status) {
		super();
		this.ouput = ouput;
		this.status = status;
	}

	public Object getOuput() {
		return ouput;
	}

	public void setOuput(Object ouput) {
		this.ouput = ouput;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
