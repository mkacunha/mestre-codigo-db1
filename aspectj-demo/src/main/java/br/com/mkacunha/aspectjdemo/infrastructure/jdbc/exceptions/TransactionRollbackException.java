package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions;

public class TransactionRollbackException extends RuntimeException {

	public TransactionRollbackException(Throwable cause) {
		super(cause);
	}
}
