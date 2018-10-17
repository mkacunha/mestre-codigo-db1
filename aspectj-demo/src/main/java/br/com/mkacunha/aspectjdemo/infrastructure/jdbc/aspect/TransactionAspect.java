package br.com.mkacunha.aspectjdemo.infrastructure.jdbc.aspect;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.Transaction;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionException;
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

    @Before("@annotation(br.com.mkacunha.aspectjdemo.infrastructure.jdbc.Transactional)")
    public void before(JoinPoint joinPoint) throws TransactionException {
        logger.info("Iniciar transação {}", joinPoint);
        transaction.begin();
    }

    @AfterReturning("@annotation(br.com.mkacunha.aspectjdemo.infrastructure.jdbc.Transactional)")
    public void after(JoinPoint joinPoint) throws TransactionException {
        logger.info("Executar commit {}", joinPoint);
        transaction.commit();
    }

    @AfterThrowing("@annotation(br.com.mkacunha.aspectjdemo.infrastructure.jdbc.Transactional)")
    public void afterThrowing(JoinPoint joinPoint) {
        logger.info("Executar rollback {}", joinPoint);
        transaction.rollback();
    }

}
