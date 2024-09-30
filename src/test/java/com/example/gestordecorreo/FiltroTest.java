package com.example.gestordecorreo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

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









}
