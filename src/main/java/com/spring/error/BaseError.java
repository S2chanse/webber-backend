package com.spring.error;

public class BaseError extends RuntimeException {
	private static final long serialVersionUID=1L;
	private String message;

	public BaseError(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
