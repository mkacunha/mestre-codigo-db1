package com.mkacunha.processadorcep;

import com.mkacunha.processadorcep.infrastructure.jdbc.Transaction;
import com.mkacunha.processadorcep.infrastructure.jdbc.sql.Command;
import com.mkacunha.processadorcep.infrastructure.jdbc.sql.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private Transaction transaction;

    @Autowired
    private CommandExecutor executor;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        transaction.begin();
        executor.execute(sqlCleanDatabase());
        executor.execute(sqlCidade());
        executor.execute(sqlCep());
        executor.execute(sqlHistorico());
        executor.execute(sqlHistoricoLog());
        transaction.commit();
    }

    private Command sqlCleanDatabase() {
        return Command.init("DROP ALL OBJECTS").command();
    }

    private Command sqlCidade() {
        final String sql = "CREATE TABLE cidade(\n" +
                "  ibge varchar(100) NOT NULL,\n" +
                "  nome varchar(100) NOT NULL,\n" +
                "  uf varchar(2) NOT NULL,\n" +
                "  id int PRIMARY KEY AUTO_INCREMENT,\n" +
                ")";
        return Command.init(sql).command();
    }

    private Command sqlCep() {
        final String sql = "CREATE TABLE cep (\n" +
                "  id int PRIMARY KEY AUTO_INCREMENT,\n" +
                "  cidade_fk int,\n" +
                "  cep varchar(20),\n" +
                "  logradouro varchar(100),\n" +
                "  bairro varchar(100),\n" +
                "  complemento varchar(100),\n" +
                "  numero varchar(20),\n" +
                ")";
        return Command.init(sql).command();
    }

    private Command sqlHistorico() {
        final String sql = "CREATE TABLE historico (\n" +
                "  id int PRIMARY KEY AUTO_INCREMENT,\n" +
                "  token varchar(100),\n" +
                "  arquivo varchar(100),\n" +
                "  status varchar(100),\n" +
                "  dt_historico Date,\n" +
                "  qt_registros_novos int,\n" +
                "  qt_registros_alterados int,\n" +
                "  qt_registros_com_erros int\n" +
                ")";
        return Command.init(sql).command();
    }

    private Command sqlHistoricoLog() {
        final String sql = "CREATE TABLE historicolog (\n" +
                "  id int PRIMARY KEY AUTO_INCREMENT,\n" +
                "  cep varchar(20) NOT NULL,\n" +
                "  status varchar(100),\n" +
                "  log varchar(500),\n" +
                "  historico_fk int\n" +
                ") ";
        return Command.init(sql).command();
    }
}
