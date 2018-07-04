package com.example.memo.lugardasraizes.Model;

public class Configuracao {
    private static final Configuracao ourInstance = new Configuracao();

    public static Configuracao getInstance() {
        return ourInstance;
    }
    private double intervalo;

    public static Configuracao getOurInstance() {
        return ourInstance;
    }

    public double getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(double intervalo) {
        this.intervalo = intervalo;
    }

    public double getPasso() {
        return passo;
    }

    public void setPasso(double passo) {
        this.passo = passo;
    }

    private double passo;
    private Configuracao() {
        this.intervalo = 15;
        this.passo  = 0.1;
    }

}
