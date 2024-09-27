package com.example.gestordecorreo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FiltroTest {
    @Test 
    void filtro_por_asunto(){
		//primer correo
        Email e1 = new Email();
		Email e2 = new Email();
		Email e3 = new Email();
		Email e4 = new Email();
		Filtro f1 = new Filtro();
        EmailManager em1 = new EmailManager();
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com");
        Contacto persona2 = new Contacto("Candela", "joaco@gmail.com"); 
		//primer mail
        e1.setAsunto("Trabajo");
        e1.setContenido("holaaa");
        e1.setRemitente(persona1);
        e1.agregarDestinatario(persona2);

        em1.enviarEmail(e1, persona1);
        assertTrue(em1.getBandejaEnviados(persona1).size() == 1);

        em1.recibirEmail(e1,persona2);
        assertTrue(em1.getBandejaEntrada(persona2).size() == 1);

		//segundo mail
		e2.setAsunto("Universidad");
		e2.setContenido("holaaa");
		e2.setRemitente(persona1);
		e2.agregarDestinatario(persona2);

		em1.enviarEmail(e1, persona1);
		assertTrue(em1.getBandejaEnviados(persona1).size() == 2);

		em1.recibirEmail(e1,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 2);

		//tercer mail
		e3.setAsunto("Universidad");
       	e3.setContenido("holaaa");
       	e3.setRemitente(persona1);
       	e3.agregarDestinatario(persona2);

		em1.enviarEmail(e1, persona1);
		assertTrue(em1.getBandejaEnviados(persona1).size() == 3);

		em1.recibirEmail(e1,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 3);
	   
		//cuarto email
		e4.setAsunto("Universidad");
		e4.setContenido("holaaa");
		e4.setRemitente(persona1);
		e4.agregarDestinatario(persona2);

		em1.enviarEmail(e1, persona1);
		assertTrue(em1.getBandejaEnviados(persona1).size() == 4);

		em1.recibirEmail(e1,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 4);


       	f1.getBandejaEntrada(persona2).filtrarPorAsunto("Universidad");


	}


    @Test
	void filtrar_por_asunto() {

	}


}
