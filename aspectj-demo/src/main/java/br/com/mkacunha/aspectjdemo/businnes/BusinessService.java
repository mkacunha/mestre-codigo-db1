package br.com.mkacunha.aspectjdemo.businnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repository;

    public String businessRuleImplementation() {
        final String rule = "Regra de negócio criada";
        return repository.save(rule);
    }

}
