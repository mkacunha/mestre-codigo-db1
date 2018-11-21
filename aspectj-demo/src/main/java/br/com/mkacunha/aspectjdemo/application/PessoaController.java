package br.com.mkacunha.aspectjdemo.application;

import br.com.mkacunha.aspectjdemo.domain.pessoa.Pessoa;
import br.com.mkacunha.aspectjdemo.domain.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public Pessoa post(@RequestBody Pessoa pessoa) {
        return service.save(pessoa);
    }
}
