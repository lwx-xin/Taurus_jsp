package org.taurus.config.util.entity;

public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	private String statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public CustomException(String message, String statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	public CustomException() {
		super();
	}

	@Override
	public String toString() {
		return "CustomException [message=" + message + ", statusCode=" + statusCode + "]";
	}

}
