package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.sql.jmx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
@ManagedResource(objectName = "bean:name=SqlExecutorMonitoring")
public class SqlExecutorMonitoring implements SqlExecutorMonitoringMBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlExecutorMonitoring.class);

    private boolean showSql;

    @Override
    public void setShowSql(boolean show) {
        LOGGER.info("Alteração no monitoramento de SQL -> Atributo showSql --> atual: {} - novo: {}", this.showSql, show);
        this.showSql = show;
    }

    @Autowired
    public boolean isShowSql() {
        return showSql;
    }
}
