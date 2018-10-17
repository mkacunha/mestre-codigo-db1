package br.com.mkacunha.aspectjdemo.infrastructure.jdbc;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.ConnectionException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class PoolConnection {

	public static final String DATABASE_CONNECT_ERROR = "Não foi possível connectar ao banco de dados. Entre em contato com o administrador.";

	public static final String DRIVER_CLASS = "org.h2.Driver";

	public static final String JDBC_URL = "jdbc:h2:mem:testdb";

	public static final String USER = "sa";

	public static final String PASSWORD = "";

	public static final int MIN_POOL_SIZE = 1;

	public static final int ACQUIRE_INCREMENT = 2;

	public static final int MAX_POOL_SIZE = 5;

	private static final Logger LOGGER = LoggerFactory.getLogger(PoolConnection.class);

	private final ComboPooledDataSource connectionPoolDatasource;

	public PoolConnection() throws PropertyVetoException {
		connectionPoolDatasource = new ComboPooledDataSource();
		connectionPoolDatasource.setDriverClass(DRIVER_CLASS);
		connectionPoolDatasource.setJdbcUrl(JDBC_URL);
		connectionPoolDatasource.setUser(USER);
		connectionPoolDatasource.setPassword(PASSWORD);
		connectionPoolDatasource.setMinPoolSize(MIN_POOL_SIZE);
		connectionPoolDatasource.setAcquireIncrement(ACQUIRE_INCREMENT);
		connectionPoolDatasource.setMaxPoolSize(MAX_POOL_SIZE);
	}

	public Connection getConnection() {
		try {
			Connection connection = connectionPoolDatasource.getConnection();
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ConnectionException(DATABASE_CONNECT_ERROR);
		}
	}
}
