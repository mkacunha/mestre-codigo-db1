package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.aspect.jmx;

public interface TransactionMonitoringMBean {

    int getErrors();

    int getSuccess();

    int getTransactions();

}
