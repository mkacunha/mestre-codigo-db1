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
public class SqlExecutorTestIT {

    @Autowired
    private SqlExecutor executor;

    @Autowired
    private Transaction transaction;

    @Test
    public void deve_executar_comando_sql() throws TransactionException {

        String sql = "CREATE TABLE   PESSOA " +
                "(id INTEGER NOT NULL auto_increment, " +
                " nome VARCHAR(255), " +
                " data_nascimento DATE, " +
                " idade INTEGER, " +
                " PRIMARY KEY ( id ))";

        transaction.begin();
        executor.execute(sql);
        executor.execute("insert into PESSOA(nome, data_nascimento, idade) values('JOÃO DA SILVA', '1990-02-01', 28);");
        transaction.commit();

    }


}