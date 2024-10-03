package com.example.gestordecorreo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    void filtros_complejos_test(){
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

}

