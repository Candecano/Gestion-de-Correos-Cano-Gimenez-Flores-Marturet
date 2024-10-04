package com.example.gestordecorreo;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class Filtro {
    private String descripcion;
    private Predicate<Email> predicado;

    public Filtro(String descripcion, Predicate<Email> predicado) {
        this.descripcion = descripcion;
        this.predicado = predicado;
    }

    public Predicate<Email> getPredicado() {
        return predicado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void agregarFiltro(Filtro filtro) {
        
    }

    
    //general
public ArrayList<Email> filtros (ArrayList<Email> email,  Predicate<Email> filtro){
            
    return email.stream()
                .filter(filtro)
                .collect(Collectors
                .toCollection(ArrayList::new));
}

//fitro asunto
public Predicate<Email> filtroPorAsunto(String asuntoparaFiltrar){
    Predicate<Email> filtro= e -> e.getAsunto().contains(asuntoparaFiltrar);
    return filtro; 
}


//filtro remitente
public Predicate<Email> filtroPorRemitente(Contacto contactoAFiltrar){
    Predicate<Email> filtro= e -> e.getRemitente().equals(contactoAFiltrar);
    return filtro; 
}

public Predicate<Email> filtroContenido(String filtradoporContenido){
    Predicate<Email> filtro= e -> e.getContenido().contains(filtradoporContenido);
    return filtro; 
}





}

