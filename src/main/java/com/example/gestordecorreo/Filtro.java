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















}

