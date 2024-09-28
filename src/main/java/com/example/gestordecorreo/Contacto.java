package com.example.gestordecorreo;

public class Contacto {
    private String nombreCompleto;
    private String email;

    public Contacto(String nombreCompleto, String email) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }

    public String getNombre() {
        return nombreCompleto;
    }

    public void setNombre(String valor) {
        nombreCompleto = valor;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String valor){
        email = valor;
    }
 
}
