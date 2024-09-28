package com.example.gestordecorreo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FiltroTest {
    @Test 
    void filtro_por_asunto(){
		//primer correo
		Bandeja b1 = new Bandeja();
        Email e1 = new Email();
		Email e2 = new Email();
		Email e3 = new Email();
		Email e4 = new Email();
		
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

		em1.enviarEmail(e2, persona1);
		assertTrue(em1.getBandejaEnviados(persona1).size() == 2);

		em1.recibirEmail(e2,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 2);

		//tercer mail
		e3.setAsunto("Universidad");
       	e3.setContenido("holaaa");
       	e3.setRemitente(persona1);
       	e3.agregarDestinatario(persona2);

		em1.enviarEmail(e3, persona1);
		assertTrue(em1.getBandejaEnviados(persona1).size() == 3);

		em1.recibirEmail(e3,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 3);
	   
		//cuarto email
		e4.setAsunto("Universidad");
		e4.setContenido("holaaa");
		e4.setRemitente(persona1);
		e4.agregarDestinatario(persona2);

		em1.enviarEmail(e4, persona1);
		assertTrue(em1.getBandejaEnviados(persona1).size() == 4);

		em1.recibirEmail(e4 ,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 4);

		String asuntoparaFiltrar = "Universidad";
       
		ArrayList<Email> emailsfiltrados = b1.filtros(em1.getBandejaEntrada(persona2), b1.filtroPorAsunto(asuntoparaFiltrar));
           
	   	assertEquals(3, emailsfiltrados.size());
	
	}

	@Test 
    void filtro_por_remitente(){
		//primer correo
		Bandeja b1 = new Bandeja();
        Email e1 = new Email();
		Email e2 = new Email();
		Email e3 = new Email();
		Email e4 = new Email();
		
        EmailManager em1 = new EmailManager();	
        Contacto persona1 = new Contacto("Joaquin Flores", "joaco@gmail.com");
        Contacto persona2 = new Contacto("Candela", "candelaria@gmail.com"); 
		Contacto persona3 = new Contacto("Carla", "carlita@gmail.com"); 
		Contacto persona4 = new Contacto("Taylor", "ts13@gmail.com"); 
		//primer mail de persona 1 a persona 2
        e1.setAsunto("Trabajo");
        e1.setContenido("holaaa");
        e1.setRemitente(persona1);
        e1.agregarDestinatario(persona2);

        em1.enviarEmail(e1, persona1);
        assertTrue(em1.getBandejaEnviados(persona1).size() == 1);

        em1.recibirEmail(e1,persona2);
        assertTrue(em1.getBandejaEntrada(persona2).size() == 1);

		//primer mail persona 3 a persona 2
		e2.setAsunto("Universidad");
		e2.setContenido("holaaa");
		e2.setRemitente(persona3);
		e2.agregarDestinatario(persona2);

		em1.enviarEmail(e2, persona3);
		assertTrue(em1.getBandejaEnviados(persona3).size() == 2);

		em1.recibirEmail(e2,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 2);

		//tercer mail
		//persona 4 a persona 2(primer mail)
		e3.setAsunto("Universidad");
       	e3.setContenido("holaaa");
       	e3.setRemitente(persona4);
       	e3.agregarDestinatario(persona2);

		em1.enviarEmail(e3, persona4);
		assertTrue(em1.getBandejaEnviados(persona4).size() == 3);

		em1.recibirEmail(e3,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 3);
	   
		//cuarto email
		//persona 4 a persona 2(segundo mail)
		e4.setAsunto("Universidad");
		e4.setContenido("holaaa");
		e4.setRemitente(persona4);
		e4.agregarDestinatario(persona2);

		em1.enviarEmail(e4, persona4);
		assertTrue(em1.getBandejaEnviados(persona4).size() == 4);

		em1.recibirEmail(e4 ,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 4);

		Contacto contactoAFiltrar = persona4;
       
		ArrayList<Email> emailsfiltrados = b1.filtros(em1.getBandejaEntrada(persona2), b1.filtroPorRemitente(contactoAFiltrar));
           
	   	assertEquals(2, emailsfiltrados.size());
	
	}














}
