package com.role.game.exception;

public class DependencyInjectionException extends RuntimeException {

	private static final long serialVersionUID = 9139928313006709190L;

	private static final String message = "Error occured while dependency injection";

	public DependencyInjectionException() {
		super(message);
	}

	public DependencyInjectionException(Throwable cause) {
		super(message, cause);
	}

}
