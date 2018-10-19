package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.PoolConnection;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionException;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionRollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component
@Scope(value = SCOPE_SINGLETON)
public class Transaction {

	public static final String THERE_IS_NOT_TRANSACTION_OPENED = "Não existe transação aberta para a thread atual.";

	public static final String ALREADY_TRANSACTION_OPENED = "Já existe uma transação aberta para a thread atual.";

	private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

	private PoolConnection poolConnection;

	private Map<Long, Connection> connectionsOpened = new HashMap<>();

	@Autowired
	public Transaction(PoolConnection poolConnection) {
		this.poolConnection = poolConnection;
	}

	public void begin() throws TransactionException {
		long currentThread = getCurrentThread();
		if (connectionsOpened.containsKey(currentThread)) {
			throw new TransactionException(ALREADY_TRANSACTION_OPENED);
		}
		Connection connection = this.poolConnection.getConnection();
		connectionsOpened.put(currentThread, connection);
	}

	public void commit() throws TransactionException {
		try {
			getCurrentConnection().commit();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionException(e);
		} finally {
			leaveCurrentConnection();
		}
	}

	public void finnaly() {
		leaveCurrentConnection();
	}

	public void rollback() {
		try {
			getCurrentConnection().rollback();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new TransactionRollbackException(e);
		} finally {
			leaveCurrentConnection();
		}
	}

	private long getCurrentThread() {
		return Thread.currentThread().getId();
	}

	public Connection getCurrentConnection() throws TransactionException {
		long currentThread = getCurrentThread();

		if (!connectionsOpened.containsKey(currentThread)) {
			throw new TransactionException(THERE_IS_NOT_TRANSACTION_OPENED);
		}

		return connectionsOpened.get(currentThread);
	}

	private void leaveCurrentConnection() {
		try {
			long currentThread = getCurrentThread();
			if (connectionsOpened.containsKey(currentThread)) {
				Connection connection = connectionsOpened.get(currentThread);
				connection.close();
				connectionsOpened.remove(currentThread);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
