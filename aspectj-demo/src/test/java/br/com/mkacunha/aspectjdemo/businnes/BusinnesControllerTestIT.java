package br.com.mkacunha.aspectjdemo.businnes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinnesControllerTestIT {

    @Autowired
    private BusinnesController controller;

    @Test
    public void should_create_and_save_business_rule() {
        assertEquals("Regra de negócio criada e persistida no banco de dados", controller.app());
    }

}