package com.example.memo.lugardasraizes.Dados;

import android.os.AsyncTask;

import com.example.memo.lugardasraizes.Fragment.GraficoFragment;
import com.example.memo.lugardasraizes.Model.Ponto;

import java.util.ArrayList;

public class Grafico {
    private static final Grafico ourInstance = new Grafico();
    protected String numerador= "1x^1+1";
    protected String denominador= "1x^3+5x^2+6x^1+0";
    private GraficoFragment graficoFragment;


    private ArrayList<Ponto> pontos = new ArrayList<>();


    public static Grafico getInstance() {
        return ourInstance;
    }

    private Grafico() {
    }



    public void setEquacao(String numerador, String denominador){
        this.numerador = numerador;

        this.denominador = denominador;


    }





}
