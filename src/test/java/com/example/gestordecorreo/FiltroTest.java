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














}

