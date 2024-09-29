package com.example.gestordecorreo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactoTest {
    @Test
    void crear_contacto_test() {
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com"); 

        assert(persona1 != null);
        
    }

    

}
