package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions;

import java.sql.SQLException;

public class TransactionException extends Exception {

	public TransactionException(String message) {
		super(message);
	}

	public TransactionException(SQLException e) {
		super(e);
	}
}
