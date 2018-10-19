package br.com.mkacunha.aspectjdemo.application;

import br.com.mkacunha.aspectjdemo.domain.pessoa.Pessoa;
import br.com.mkacunha.aspectjdemo.domain.pessoa.PessoaService;
import br.com.mkacunha.aspectjdemo.infrastructure.jdbc.transaction.aspect.jmx.TransactionMonitoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;


    @Autowired
    private TransactionMonitoring transactionMonitoring;

    @PostMapping
    public Pessoa post(@RequestBody Pessoa pessoa) {
        service.save(pessoa);
        return pessoa;
    }
}
