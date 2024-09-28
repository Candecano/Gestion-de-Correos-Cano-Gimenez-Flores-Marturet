package com.example.gestordecorreo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BandejaTest {
    
    @Test
    void se_manda_correo_y_se_guarda_en_bandeja_de_enviados_test(){
        Email e1 = new Email();
        EmailManager em1 = new EmailManager();
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com");


        em1.enviarEmail(e1, persona1);
        

    }


}
