package br.com.mkacunha.aspectjdemo;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.sql.SqlExecutor;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AspectjDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AspectjDemoApplication.class, args);
	}


	@Autowired
	private Transaction transaction;

	@Autowired
	private SqlExecutor executor;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String sql = "CREATE TABLE   PESSOA " +
				"(id INTEGER NOT NULL, " +
				" nome VARCHAR(255), " +
				" data_nascimento DATE, " +
				" idade INTEGER, " +
				" PRIMARY KEY ( id ))";

		transaction.begin();
		executor.execute(sql);
		transaction.commit();
	}
}
