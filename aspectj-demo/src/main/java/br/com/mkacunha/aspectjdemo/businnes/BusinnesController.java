package br.com.mkacunha.aspectjdemo.businnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("business")
public class BusinnesController {

    @Autowired
    private BusinessService service;

    @GetMapping("rule")
    public String app() {
        return service.businessRuleImplementation();
    }

}
