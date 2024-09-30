package com.example.gestordecorreo;
import java.util.ArrayList;

public class EmailManager {

    private Bandeja bandeja;

    public EmailManager(){
        this.bandeja = new Bandeja();
    }

    public void enviarEmail(Email email, Contacto persona){
        bandeja.getBandejaEnviados(persona).add(email);
    }

    public void recibirEmail(Email email, Contacto persona){
        bandeja.getBandejaEntrada(persona).add(email);
    }

    public ArrayList<Email> getBandejaEnviados(Contacto persona){
        return bandeja.getBandejaEnviados(persona);
    }

    public ArrayList<Email> getBandejaEntrada(Contacto persona){
        return bandeja.getBandejaEntrada(persona);
    }
    
}
