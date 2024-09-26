package com.example.gestordecorreo;
import java.util.*;

public class Usuario {
    private ArrayList<Email> bandejaEntrada = new ArrayList<>();
    private ArrayList<Email> bandejaEnviados = new ArrayList<>();

    
    
    public void enviarEmail(Email email){
        bandejaEnviados.add(email);
    }

    public ArrayList<Email> getBandejaEnviados(){
        return bandejaEnviados;
    }

    public void recibirEmail(Email email){
        bandejaEntrada.add(email);
    }

    public ArrayList<Email> getBandejaEntrada(){
        return bandejaEntrada;
    }
}
