package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.aspect.jmx;

import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
@ManagedResource(objectName="bean:name=TransactionMonitoring")
public class TransactionMonitoring implements TransactionMonitoringMBean {

    private int errors;

    private int success;

    private int transactions;

    @Override
    public int getErrors() {
        return errors;
    }

    @Override
    public int getSuccess() {
        return success;
    }

    @Override
    public int getTransactions() {
        return transactions;
    }

    public void newError() {
        this.errors++;
    }

    public void newSuccess() {
        this.success++;
    }

    public void newTransaction() {
        this.transactions++;
    }
}
