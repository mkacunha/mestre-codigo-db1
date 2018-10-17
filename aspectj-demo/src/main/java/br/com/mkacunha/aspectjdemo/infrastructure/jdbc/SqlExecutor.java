package br.com.mkacunha.aspectjdemo.infrastructure.jdbc;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class SqlExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SqlExecutor.class);

	private Transaction transaction;

	public SqlExecutor(Transaction transaction) {
		this.transaction = transaction;
	}

	public void execute(String sql) throws TransactionException {
		execute(sql, null);
	}

	public void execute(String sql, Consumer<PreparedStatement> consumer) throws TransactionException {
		try {
			PreparedStatement stmt = createAndExecutePreparedStatement(sql);
			Optional.ofNullable(consumer).ifPresent(con -> con.accept(stmt));
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(e);
		}
	}

	private PreparedStatement createAndExecutePreparedStatement(String sql) throws TransactionException, SQLException {
		Connection currentConnection = transaction.getCurrentConnection();
		PreparedStatement stmt = currentConnection.prepareStatement(sql, RETURN_GENERATED_KEYS);
		stmt.execute();
		return stmt;
	}
}
