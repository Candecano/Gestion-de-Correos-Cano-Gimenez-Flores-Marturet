package com.example.gestordecorreo;
import java.util.ArrayList;
//email manager es el encargado de gestionar los correos creados
public class EmailManager {
    
    //se envia y se recibe el email
    public void enviarEmail(Email email) {

    //se obtiene la bandeja de enviados del remitente y se agrega el email
    email.getRemitente().bandeja.getBandejaEnviados().add(email);
    
    //para cada destinatario se obtiene la bandeja de entrada y se agrega el email
        for (Contacto destinatario : email.getDestinatarios()) {
            destinatario.bandeja.getBandejaEntrada().add(email);
        }
    }
    
    public void borrarEmail(ArrayList<Email> bandeja, Email email ){
        bandeja.remove(email);

    }




}
