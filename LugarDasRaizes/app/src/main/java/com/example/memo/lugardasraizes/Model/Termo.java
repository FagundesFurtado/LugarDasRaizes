package com.example.memo.lugardasraizes.Model;

public class Termo {
    private Integer coeficiente;
    private Integer grau;

    public Termo(Integer coeficiente, Integer grau)
    {
        this.coeficiente = coeficiente;
        this.grau = grau;
    }
    public Integer getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Integer coeficiente) {
        this.coeficiente = coeficiente;
    }

    public Integer getGrau() {
        return grau;
    }

    public void setGrau(Integer grau) {
        this.grau = grau;
    }
    public Termo derivada()
    {
        return new Termo(this.coeficiente*this.grau, this.grau-1);
    }
    public String toString()
    {
        return this.getCoeficiente()+"x^"+this.getGrau();
    }
}
