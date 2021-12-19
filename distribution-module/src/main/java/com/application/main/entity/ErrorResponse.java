package com.application.main.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorResponse {

	private String message;
	private Date date;

	public ErrorResponse(String message) {
		this.message = message;
		this.date = new Date();
	}

}
