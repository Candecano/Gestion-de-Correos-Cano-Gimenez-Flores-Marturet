package com.example.gestordecorreo;
import java.util.ArrayList;

public class EmailManager {

    private Bandeja bandeja;

    public EmailManager(){
        this.bandeja = new Bandeja();
    }

    public void enviarEmail(Email email) {
    bandeja.getBandejaEnviados(email.getRemitente()).add(email);
    
        for (Contacto destinatario : email.getDestinatarios()) {
            bandeja.getBandejaEntrada(destinatario).add(email);
        }
    }


    public ArrayList<Email> getBandejaEnviados(Contacto persona){
        return bandeja.getBandejaEnviados(persona);
    }

    public ArrayList<Email> getBandejaEntrada(Contacto persona){
        return bandeja.getBandejaEntrada(persona);
    }
    
}
