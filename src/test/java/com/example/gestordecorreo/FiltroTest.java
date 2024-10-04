package com.example.gestordecorreo;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FiltroTest {

    @Test
    void se_comprueba_la_descripcion_test() {
        String descripcion = "Filtro por asunto importante";
        Predicate<Email> predicado = email -> email.getAsunto().contains("importante");
        Filtro filtro = new Filtro(descripcion, predicado);

        assertEquals(descripcion, filtro.getDescripcion());
    }

    @Test
    void se_comprueba_el_predicado_test() {
        Bandeja b1 = new Bandeja();
        EmailManager em1 = new EmailManager();
        Contacto persona1 = new Contacto("Joaco", "joaco@gmail.com");
        Contacto persona2 = new Contacto("Joaco", "joaco@gmail.com");
        Predicate<Email> predicado = email -> email.getAsunto().contains("importante");
        Filtro filtro = new Filtro("Filtro por asunto importante", predicado);

        Email e1 = new Email();
        e1.setAsunto("Subir las notas porfavor es importante");
        em1.enviarEmail(e1, persona2);

        Email e2 = new Email();
        e2.setAsunto("Esto no pinta nada bien");

        assertTrue(filtro.getPredicado().test(e1));
        assertFalse(filtro.getPredicado().test(e2));
    }

    @Test
    void filtros_complejos_test_entrada(){
        Email e1 = new Email();
        Email e2 = new Email();
        EmailManager em1 = new EmailManager();
        Contacto persona1 = new Contacto("Joaco", "joaco@gmail.com");
        Contacto persona2 = new Contacto("Cande", "cande@gmail.com");
        
        // Definir los predicados y filtros
        Predicate<Email> predicado = email -> email.getAsunto().contains("Trabajo");
        Predicate<Email> predicado2 = email -> email.getRemitente().equals(persona1);
        Filtro f1 = new Filtro("Correos que contengan asunto Trabajo", predicado);
        Filtro f2 = new Filtro("Correos de persona1", predicado2);

        Predicate<Email> predicadoFinal = f1.getPredicado().and(f2.getPredicado());

        // Primer mail de persona 1 a persona 2
        e1.setAsunto("Trabajo");
        e1.setContenido("Le envio mi Trabajo Práctico");
        e1.setRemitente(persona1);
        e1.agregarDestinatario(persona2);

        em1.enviarEmail(e1, persona1);
        em1.recibirEmail(e1, persona2);

        // Segundo mail de persona 1 a persona 2
        e2.setAsunto("Universidad");
        e2.setContenido("holaaa todo bien");
        e2.setRemitente(persona1);
        e2.agregarDestinatario(persona2);

        em1.enviarEmail(e2, persona1);
        em1.recibirEmail(e2, persona2);

        ArrayList<Email> correosEnBandeja = em1.getBandejaEntrada(persona2);

        ArrayList<Email> correosFiltrados = correosEnBandeja.stream()
                                                    .filter(predicadoFinal)
                                                    .collect(Collectors
                                                    .toCollection(ArrayList::new));


        assertEquals(1, correosFiltrados.size());
        assertTrue(correosFiltrados.contains(e1));
        assertFalse(correosFiltrados.contains(e2));
    }

    @Test
    void filtro_compuesto_bandeja_enviados(){
        Email e1 = new Email();
        Email e2 = new Email();
        EmailManager em1 = new EmailManager();
        Contacto persona1 = new Contacto("Candela Cano ", "cande@gmail.com");
        Contacto persona2 = new Contacto("Jose Fernandez", "jose@gmail.com");
        Contacto persona3= new Contacto("Gilda Romero", "gromero@gmail.com");
      

      //primer email de persona 1 a destinatario 2
        e1.setAsunto("Trabajo Practico 2");
        e1.setContenido("Le envio mi Trabajo Práctico número 2");
        e1.setRemitente(persona1);
        e1.agregarDestinatario(persona2);

        em1.enviarEmail(e1, persona1);
        em1.recibirEmail(e1, persona2);

        // Email de persona 1 a destinatario 3
        e2.setAsunto("Cambio fecha");
        e2.setContenido("Podemos cambiar la fecha del parcial?");
        e2.setRemitente(persona1);
        e2.agregarDestinatario(persona3);

        em1.enviarEmail(e2, persona1);
        em1.recibirEmail(e2, persona3);

        // Definir los predicados y filtros
        
        Predicate<Email> predicado = email -> email.getAsunto().contains("Trabajo Practico 2");
        Predicate<Email> predicado2 = email -> email.getDestinatarios().contains(persona2);
        Filtro f1 = new Filtro("Correos que contengan asunto Trabajo Practico 2", predicado);
        Filtro f2 = new Filtro("Correos para la  persona2", predicado2);

        Predicate<Email> predicadoFinal = f1.getPredicado().and(f2.getPredicado());



        ArrayList<Email> correosEnBandeja = em1.getBandejaEnviados(persona1);


        ArrayList<Email> correosFiltrados = correosEnBandeja.stream()
                                                    .filter(predicadoFinal)
                                                    .collect(Collectors
                                                    .toCollection(ArrayList::new));

        assertEquals(1, correosFiltrados.size());
        assertTrue(correosFiltrados.contains(e1));
        assertFalse(correosFiltrados.contains(e2));

    }

    @Test 
    void filtro_por_asunto_simple(){
		//primer correo
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


        Predicate<Email> predicado = email -> email.getAsunto().contains("Universidad");
        Filtro f1 = new Filtro("Correos que contengan asunto Universidad", predicado);
      
        ArrayList<Email> correosEnBandeja = em1.getBandejaEntrada(persona2);
 
 
        ArrayList<Email> correosFiltrados = correosEnBandeja.stream()
                                                     .filter(f1.getPredicado())
                                                     .collect(Collectors
                                                     .toCollection(ArrayList::new));
 
         assertEquals(3, correosFiltrados.size());

         //se verifica la existencia de los correos en correosfiltrados
         assertTrue(correosFiltrados.contains(e2));
         assertTrue(correosFiltrados.contains(e3));
         assertTrue(correosFiltrados.contains(e4));  
	
	}

    @Test 
    void filtro_por_remitente(){
		//primer correo
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

        Predicate<Email> predicado = email -> email.getRemitente().equals(persona4);
         Filtro f1 = new Filtro("Correos que contengan el remitente de persona 4, o sea taylor", predicado);
      
        
 
        ArrayList<Email> correosEnBandeja = em1.getBandejaEntrada(persona2);
 
 
        ArrayList<Email> correosFiltrados = correosEnBandeja.stream()
                                                     .filter(f1.getPredicado())
                                                     .collect(Collectors
                                                     .toCollection(ArrayList::new));
 
         assertEquals(2, correosFiltrados.size());

         //se verifica la existencia de los correos en correosfiltrados

         assertTrue(correosFiltrados.contains(e3));
         assertTrue(correosFiltrados.contains(e4));  
	
	}
    @Test 
    void filtro_por_contenido(){
		//primer correo
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
        e1.setContenido("Subir notas, Por favor");
        e1.setRemitente(persona1);
        e1.agregarDestinatario(persona2);

        em1.enviarEmail(e1, persona1);
        assertTrue(em1.getBandejaEnviados(persona1).size() == 1);

        em1.recibirEmail(e1,persona2);
        assertTrue(em1.getBandejaEntrada(persona2).size() == 1);

		//primer mail persona 3 a persona 2
		e2.setAsunto("Universidad");
		e2.setContenido("Subir notas, Por favor");
		e2.setRemitente(persona3);
		e2.agregarDestinatario(persona2);

		em1.enviarEmail(e2, persona3);
		assertTrue(em1.getBandejaEnviados(persona3).size() == 2);

		em1.recibirEmail(e2,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 2);

		//tercer mail
		//persona 4 a persona 2(primer mail)
		e3.setAsunto("Musica");
       	e3.setContenido("Aguante Taylor Swift");
       	e3.setRemitente(persona4);
       	e3.agregarDestinatario(persona2);

		em1.enviarEmail(e3, persona4);
		assertTrue(em1.getBandejaEnviados(persona4).size() == 3);

		em1.recibirEmail(e3,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 3);
	   
		//cuarto email
		//persona 4 a persona 2(segundo mail)
		e4.setAsunto("Amigos");
		e4.setContenido("holaaa");
		e4.setRemitente(persona4);
		e4.agregarDestinatario(persona2);

		em1.enviarEmail(e4, persona4);
		assertTrue(em1.getBandejaEnviados(persona4).size() == 4);

		em1.recibirEmail(e4 ,persona2);
		assertTrue(em1.getBandejaEntrada(persona2).size() == 4);

        Predicate<Email> predicado = email -> email.getContenido().contains("Subir notas, Por favor");
         Filtro f1 = new Filtro("Correos que contengan como como contenido el mensaje 'Subir notas, Por favor'", predicado);
      
         Predicate<Email> predicadoFinal = f1.getPredicado();
 
        ArrayList<Email> correosEnBandeja = em1.getBandejaEntrada(persona2);
 
 
         ArrayList<Email> correosFiltrados = correosEnBandeja.stream()
                                                     .filter(predicadoFinal)
                                                     .collect(Collectors
                                                     .toCollection(ArrayList::new));
 
         assertEquals(2, correosFiltrados.size());

         //se verifica la existencia de los correos en correosfiltrados
         assertTrue(correosFiltrados.contains(e1));
         assertTrue(correosFiltrados.contains(e2));  
	
	}


}

