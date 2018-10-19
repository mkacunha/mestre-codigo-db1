package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.aspect;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transaction;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionException;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.aspect.jmx.TransactionMonitoring;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TransactionAspect {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private Transaction transaction;

    @Autowired
    private TransactionMonitoring monitoring;

    @Before("@annotation(br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transactional)")
    public void before(JoinPoint joinPoint) throws TransactionException {
        logger.info("Iniciar transação {}", joinPoint);
        monitoring.newTransaction();
        transaction.begin();
    }

    @AfterReturning("@annotation(br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transactional)")
    public void after(JoinPoint joinPoint) throws TransactionException {
        logger.info("Executar commit {}", joinPoint);
        monitoring.newSuccess();
        transaction.commit();
    }

    @AfterThrowing("@annotation(br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transactional)")
    public void afterThrowing(JoinPoint joinPoint) {
        logger.info("Executar rollback {}", joinPoint);
        monitoring.newError();
        transaction.rollback();
    }

}
