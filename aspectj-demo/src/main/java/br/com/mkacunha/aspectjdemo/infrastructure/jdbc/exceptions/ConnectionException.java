package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions;

public class ConnectionException extends RuntimeException {

	public ConnectionException(String message) {
		super(message);
	}
}
