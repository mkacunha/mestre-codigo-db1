package br.com.mkacunha.aspectjdemo.application;

import br.com.mkacunha.aspectjdemo.domain.pessoa.Pessoa;
import br.com.mkacunha.aspectjdemo.domain.pessoa.PessoaService;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.sql.SqlExecutor;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService service;

    @MockBean
    private Transaction transaction;

    @MockBean
    private SqlExecutor executor;

    private static final String JSON_PESSOA = "{\n" +
            "\t\"nome\": \"Nome Pessoa\",\n" +
            "    \"dataNascimento\": \"1990-01-01\",\n" +
            "    \"idade\": 30\n" +
            "}";

    @Test
    public void deve_receber_pessoa_como_parametro() throws Exception {
        when(service.save(any(Pessoa.class))).thenReturn(pessoa());

        ArgumentCaptor<Pessoa> pessoaArgumentCaptor = ArgumentCaptor.forClass(Pessoa.class);

        mockMvc.perform(post("/pessoas").content(JSON_PESSOA).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).save(pessoaArgumentCaptor.capture());
        Pessoa pessoa = pessoaArgumentCaptor.getValue();

        assertNull(pessoa.getId());
        assertEquals("Nome Pessoa", pessoa.getNome());
        assertEquals("1990-01-01", pessoa.getDataNascimento());
        assertSame(30, pessoa.getIdade());
    }

    @Test
    public void deve_retornar_pessoa_cadastrada() throws Exception {
        when(service.save(any(Pessoa.class))).thenReturn(pessoa());
        mockMvc.perform(post("/pessoas").content(JSON_PESSOA).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("nome").value("Nome Pessoa"))
                .andExpect(jsonPath("dataNascimento").value("1990-01-01"))
                .andExpect(jsonPath("idade").value("30"));

        verify(service, times(1)).save(any(Pessoa.class));
    }

    private Pessoa pessoa() {
        return new Pessoa(1, "Nome Pessoa", "1990-01-01", 30);
    }
}