package br.com.mkacunha.aspectjdemo.businnes;

import org.springframework.stereotype.Repository;

@Repository
public class BusinessRepository {

    public String save(String rule) {
        return rule + " e persistida no banco de dados";
    }

}
