package com.example.gestordecorreo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactoTest {
    @Test
    void crear_contacto_test() {
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com"); 

        assert(persona1 != null);
        
    }

}
