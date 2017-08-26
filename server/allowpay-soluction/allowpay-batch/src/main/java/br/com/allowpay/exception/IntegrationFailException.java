package br.com.allowpay.exception;

import org.springframework.http.HttpStatus;

public class IntegrationFailException extends Exception {

	private static final long serialVersionUID = 5470293932831132036L;

	private final String operationName;
	private final String operationMethod;

	private final HttpStatus statusCode;

	public IntegrationFailException(final String operationName, final String operationMethod,
			final HttpStatus statusCode) {
		this.operationName = operationName;
		this.operationMethod = operationMethod;
		this.statusCode = statusCode;
	}

	public String getOperationName() {
		return operationName;
	}

	public String getOperationMethod() {
		return operationMethod;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}
}
