package com.example.gestordecorreo;
import  java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    public boolean validarEmail(String email){
        Pattern pattern= Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();

    }
    
}