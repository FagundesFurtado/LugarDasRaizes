package com.example.memo.lugardasraizes.Dados;

import android.os.AsyncTask;

import com.example.memo.lugardasraizes.Fragment.GraficoFragment;
import com.example.memo.lugardasraizes.Model.Ponto;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;

public class Grafico {
    private static final Grafico ourInstance = new Grafico();
    protected String numerador;
    protected String denominador;
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
