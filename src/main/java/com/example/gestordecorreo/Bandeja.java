package com.example.gestordecorreo;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class Bandeja  {
    private ArrayList<Email> bandejaEntrada = new ArrayList<>();
    private ArrayList<Email> bandejaEnviados = new ArrayList<>();

  
public ArrayList<Email> getBandejaEnviados(Contacto persona){
    return bandejaEnviados;
}

public ArrayList<Email> getBandejaEntrada(Contacto persona){
    return bandejaEntrada;
}

public void setBandejaEnviados(ArrayList<Email> bandejaEnviados) {
    this.bandejaEnviados = bandejaEnviados;
}

public void setBandejaEntrada(ArrayList<Email> bandejaEntrada) {
    this.bandejaEntrada = bandejaEntrada;
}



 //general
 public ArrayList<Email> filtros (ArrayList<Email> email,  Predicate<Email> filtro){
            
    return email.stream()
                .filter(filtro)
                .collect(Collectors.toCollection(ArrayList::new));
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


//filtros compuestos
//remitente y asunto
public Predicate<Email> filtroPorcorreoYasunto(Contacto remitenteAFiltrar, String asuntoparaFiltrar ){
    Predicate<Email> asunto= e -> e.getAsunto().contains(asuntoparaFiltrar);
    Predicate<Email> filtro= e -> e.getRemitente().equals(remitenteAFiltrar);   
    return filtro.and(asunto); 
}











}  
 