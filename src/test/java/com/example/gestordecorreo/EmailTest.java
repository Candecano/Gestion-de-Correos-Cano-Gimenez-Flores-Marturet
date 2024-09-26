package com.example.gestordecorreo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {

    @Test
    void crear_email_test(){
        Email e1 = new Email();
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com");


        e1.setAsunto("Universidad");
        e1.setContenido("holaaa");
        e1.setRemitente(persona1);


    }

    @Test
    void crear_email_con_varias_personas_test(){
        Email e1 = new Email();
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com");
        Contacto persona2 = new Contacto("Joaquin Flores", "joaco@gmail.com"); 

        e1.setAsunto("Universidad");
        e1.setContenido("holaaa");
        e1.setRemitente(persona1);
        e1.agregarDestinatario(persona2);

        assertTrue(e1.getDestinatarios().size() == 1);
        
        
    }

    @Test
    void enviar_email_test(){
        Email e1 = new Email();
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com");
        Usuario usuario1 = new Usuario();
        
        e1.setAsunto("Universidad");
        e1.setContenido("holaaa");
        e1.setRemitente(persona1);

        usuario1.enviarEmail(e1);
        assertTrue(usuario1.getBandejaEnviados().size() == 1);

    }
    
    

    @Test
    void usuario1_manda_mail_y_usuario2_recibe(){
        Email e1 = new Email();
        Usuario usuario2 = new Usuario();
        Usuario usuario1 = new Usuario();

        e1.setAsunto("Universidad");
        e1.setContenido("holaaa");
        
        usuario1.enviarEmail(e1);
        assertTrue(usuario1.getBandejaEnviados().size() == 1);

        usuario2.recibirEmail(e1);
        assertTrue(usuario2.getBandejaEntrada().size() == 1);
    }

    
    

    
}
