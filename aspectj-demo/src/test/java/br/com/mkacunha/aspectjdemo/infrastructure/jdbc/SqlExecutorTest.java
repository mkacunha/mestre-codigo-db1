package br.com.mkacunha.aspectjdemo.infrastructure.jdbc;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionException;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.sql.SqlExecutor;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlExecutorTest {

    @Autowired
    private SqlExecutor executor;

    @Autowired
    private Transaction transaction;

    @Test
    public void deve_executar_comando_sql() throws TransactionException {
        transaction.begin();
        executor.execute("insert into PESSOA(id, nome, data_nascimento, idade) values(1, 'JOÃO DA SILVA', '1990-02-01', 28);");
        transaction.commit();
    }


}