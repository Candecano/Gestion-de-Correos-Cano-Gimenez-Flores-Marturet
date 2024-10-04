package com.example.gestordecorreo;
import java.util.ArrayList;
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





}  
 