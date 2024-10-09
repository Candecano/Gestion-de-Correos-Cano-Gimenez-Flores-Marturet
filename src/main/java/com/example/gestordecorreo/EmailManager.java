package com.example.gestordecorreo;
import java.util.ArrayList;
//email manager es el encargado de gestionar los correos creados
public class EmailManager {
    

    
    //se envia y se recibe el email
    public void enviarEmail(Email email) {
    Email emailClonado = email.clonarEmail();

    emailClonado.getRemitente().bandeja.getBandejaEnviados().add(emailClonado);

        for (Contacto destinatario : email.getDestinatarios()) {
             destinatario.bandeja.getBandejaEntrada().add(emailClonado);
        }
    }

    public void borrarEmail(ArrayList<Email> bandeja, Email email ){
        bandeja.remove(email);
    }

  



}
