package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.PoolConnection;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionException;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionRollbackException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class TransactionTest {

    @InjectMocks
    private Transaction transaction;

    @Mock
    private PoolConnection poolConnection;

    @Mock
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(poolConnection.getConnection()).thenReturn(connection);
    }

    @Test
    public void deve_criar_uma_conexao_e_salvar_para_thread() throws TransactionException {
        transaction.begin();
        Map<Long, Connection> connectionsOpened = (Map<Long, Connection>) ReflectionTestUtils.getField(transaction, "connectionsOpened");
        assertEquals(connection, connectionsOpened.get(Thread.currentThread().getId()));
    }

    @Test(expected = TransactionException.class)
    public void deve_retornar_eception_quando_já_existe_uma_conexao_para_thread_atual() throws TransactionException {
        Map<Long, Connection> connectionsOpened = new HashMap<>();
        connectionsOpened.put(Thread.currentThread().getId(), connection);
        ReflectionTestUtils.setField(transaction, "connectionsOpened", connectionsOpened);
        transaction.begin();
    }

    @Test
    public void deve_executar_commit() throws TransactionException, SQLException {
        transaction.begin();
        transaction.commit();

        verify(connection, times(1)).commit();
    }

    @Test
    public void deve_fechar_conexao_remover_do_pool_quando_executar_commit() throws TransactionException, SQLException {
        transaction.begin();
        transaction.commit();

        Map<Long, Connection> connectionsOpened = (Map<Long, Connection>) ReflectionTestUtils.getField(transaction, "connectionsOpened");

        verify(connection, times(1)).close();
        assertFalse(connectionsOpened.containsKey(Thread.currentThread().getId()));
    }


    @Test(expected = TransactionException.class)
    public void deve_retornar_exception_quando_ocorrer_qualquer_sql_exception_no_commit() throws TransactionException, SQLException {
        doThrow(new SQLException("Exception")).when(connection).commit();
        transaction.begin();
        transaction.commit();
    }

    @Test
    public void deve_executar_rollback() throws TransactionException, SQLException {
        transaction.begin();
        transaction.rollback();

        verify(connection, times(1)).rollback();
    }

    @Test
    public void deve_fechar_conexao_remover_do_pool_quando_executar_rollback() throws TransactionException, SQLException {
        transaction.begin();
        transaction.rollback();

        Map<Long, Connection> connectionsOpened = (Map<Long, Connection>) ReflectionTestUtils.getField(transaction, "connectionsOpened");

        verify(connection, times(1)).close();
        assertFalse(connectionsOpened.containsKey(Thread.currentThread().getId()));
    }


    @Test(expected = TransactionRollbackException.class)
    public void deve_retornar_exception_quando_ocorrer_qualquer_excecao_no_rollback() throws TransactionException, SQLException {
        doThrow(new RuntimeException("Exception")).when(connection).rollback();
        transaction.begin();
        transaction.rollback();
    }


    @Test
    public void deve_retornar_connection_para_thread_atual() throws TransactionException {
        transaction.begin();
        assertEquals(connection, transaction.getCurrentConnection());
    }

    @Test(expected = TransactionException.class)
    public void deve_retornar_exception_quando_nao_existe_conexao_para_thread_atual() throws TransactionException {
        transaction.getCurrentConnection();
    }


}