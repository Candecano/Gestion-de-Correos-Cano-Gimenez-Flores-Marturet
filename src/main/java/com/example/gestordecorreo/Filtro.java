package com.example.gestordecorreo;
import java.util.stream.*;

// por asunto
// por remitente
// spam
// LEIDOS PUEDE SER   
// contactos fav
// 
import java.util.function.Predicate;
import java.util.ArrayList;


public class Filtro {

    private ArrayList<Email> emails;

    public Filtro(){
        emails = new ArrayList<Email>();
    }
    
    public ArrayList<Email> filtrarPorAsunto(String asuntoParaFiltrar) {
       
        Predicate<Email> filtroPorAsunto = e -> e.getAsunto().contains(asuntoParaFiltrar);
        var filtrado = this.emails.stream()
                        .filter(filtroPorAsunto)
                        .collect(Collectors
                        .toCollection(ArrayList::new));
        return filtrado;
    }
    
    


}