package com.cognizant.microservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
}
