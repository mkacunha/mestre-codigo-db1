package br.com.mkacunha.aspectjdemo.domain.pessoa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @Before
    public void setUp() throws Exception {
        pessoaService.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        pessoaService.deleteAll();
    }

    @Test
    public void deve_salvar_pessoa() {
        Pessoa pessoa = new Pessoa(1, "João Silva", "1990-01-01", 30);
        pessoaService.save(pessoa);
        Pessoa pessoaEncontrada = pessoaService.findAll().get(0);
        assertEquals(pessoa, pessoaEncontrada);
    }

    @Test
    public void deve_retonar_exception_e_executar_rollback() {
        Pessoa pessoa1 = new Pessoa(1, "João Silva", "1990-01-01", 30);
        Pessoa pessoa2 = new Pessoa(2, "João Silva 2", "1990-01-01", 30);

        pessoaService.save(pessoa1);

        try {
            pessoaService.save(Arrays.asList(pessoa2, pessoa1));
        } catch (Exception e) {
            List<Pessoa> pessoas = pessoaService.findAll();
            Pessoa pessoaEncontrada = pessoas.get(0);
            assertEquals(1, pessoas.size());
            assertEquals(pessoa1, pessoaEncontrada);
        }
    }


}