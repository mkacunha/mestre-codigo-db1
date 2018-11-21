package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.sql.jmx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlExecutorMonitoringTest {

    @Autowired
    private SqlExecutorMonitoring sqlExecutorMonitoring;

    @Test
    public void deve_existir_uma_instancia_no_contexto_do_spring() {
        assertNotNull(sqlExecutorMonitoring);
    }

    @Test
    public void deve_setar_atributo_showSql_para_true() {
        sqlExecutorMonitoring.setShowSql(true);

        assertTrue(sqlExecutorMonitoring.isShowSql());
    }

    @Test
    public void deve_setar_atributo_showSql_para_false() {
        sqlExecutorMonitoring.setShowSql(false);

        assertFalse(sqlExecutorMonitoring.isShowSql());
    }
}