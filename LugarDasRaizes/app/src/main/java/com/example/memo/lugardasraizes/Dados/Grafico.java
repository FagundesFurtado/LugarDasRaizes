package com.example.memo.lugardasraizes.Dados;

import android.os.AsyncTask;

import com.example.memo.lugardasraizes.Fragment.GraficoFragment;
import com.example.memo.lugardasraizes.Model.Ponto;

import java.util.ArrayList;

public class Grafico {
    private static final Grafico ourInstance = new Grafico();
    protected String numerador = "1";
    protected String denominador = "S(S+1)(S+2)";
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
