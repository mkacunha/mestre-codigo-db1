package br.com.mkacunha.aspectjdemo.domain.pessoa;

import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.exceptions.TransactionException;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.sql.SqlExecutor;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class PessoaService {

    private final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    private static final String SQL_INSERT = "insert into pessoa values(%d, '%s', '%s', %d);";

    private static final String SQL_SELECT_ALL = "select * from pessoa";

    private static final String SQL_DELETE_ALL = "delete from pessoa";

    @Autowired
    private SqlExecutor sqlExecutor;

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        buildAndExecuteSql(pessoa);
        return pessoa;
    }

    @Transactional
    public void save(List<Pessoa> pessoas) {
        pessoas.forEach(this::buildAndExecuteSql);
    }

    private void buildAndExecuteSql(Pessoa pessoa) {
        try {
            logger.info("Salvar pessoa {}", pessoa);
            final String sql = String.format(SQL_INSERT, pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getIdade());
            sqlExecutor.execute(sql);
        } catch (TransactionException e) {
            logger.error(e.getMessage(), e);
            throw new PessoaSerciceExceprion("Não foi possível salvar pessoa");
        }
    }


    @Transactional
    public List<Pessoa> findAll() {
        try {
            logger.info("Buscar todas pessoas cadastradas");
            List<Pessoa> pessoas = new ArrayList<>();
            sqlExecutor.execute(SQL_SELECT_ALL, extrairPessoaList(pessoas));
            return pessoas;
        } catch (TransactionException e) {
            logger.error(e.getMessage(), e);
            throw new PessoaSerciceExceprion("Não foi possível recuperar todas as pessoas");
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            logger.info("Deletar todas pessoas cadastradas");
            sqlExecutor.execute(SQL_DELETE_ALL);
        } catch (TransactionException e) {
            logger.error(e.getMessage(), e);
            throw new PessoaSerciceExceprion("Não foi deletar todas as pessoas");
        }
    }

    private Consumer<PreparedStatement> extrairPessoaList(List<Pessoa> pessoas) {
        return stmt -> {
            try {
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    final int id = resultSet.getInt("id");
                    final String nome = resultSet.getString("nome");
                    final String dataNascimento = resultSet.getString("data_nascimento");
                    final int idade = resultSet.getInt("idade");
                    pessoas.add(new Pessoa(id, nome, dataNascimento, idade));
                }

            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        };
    }


}
