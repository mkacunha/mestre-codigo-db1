package br.com.mkacunha.aspectjdemo.businnes.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAccessAspect {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Before("execution(* br.com.mkacunha.aspectjdemo.businnes.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("Verificações em {}", joinPoint.toLongString());
        logger.info("Verificar se possui usuário logado");
        logger.info(("Verificar se usuário logado possui acesso"));
        System.out.println(joinPoint);
    }

}
