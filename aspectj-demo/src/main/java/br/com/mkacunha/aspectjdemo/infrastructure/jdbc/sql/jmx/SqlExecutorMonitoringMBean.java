package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.sql.jmx;

public interface SqlExecutorMonitoringMBean {

    void setShowSql(boolean show);

    boolean isShowSql();
}
