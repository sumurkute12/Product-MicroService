package com.cognizant.microservice.exception;

public class RatingGreaterThan5Exception extends Exception 
{
	private static final long serialVersionUID = 1L;

	public RatingGreaterThan5Exception(String msg)
	{
		super(msg);
	}
}
