package com.example.memo.lugardasraizes.Dados;

import android.os.AsyncTask;
import android.util.Log;

import com.example.memo.lugardasraizes.Adapter.RespostaGrafico;
import com.example.memo.lugardasraizes.Model.Ponto;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;

public class ResolveRaizes extends AsyncTask {

    private final String TAG = "Grafico";
    private RespostaGrafico r;
    public ResolveRaizes(RespostaGrafico respostaGrafico){
        r = respostaGrafico;
        Log.i(TAG, "Iniciando solucao do problema");

        this.execute();

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        double[] equacao = new double[]{0,2,3,1};


        PolynomialFunction polynomial = new PolynomialFunction(equacao);

        LaguerreSolver laguerreSolver = new LaguerreSolver();
        Complex[] raizes = laguerreSolver.solveAllComplex(equacao, 0);

        ArrayList<Ponto> pontos = new ArrayList<>();

        for(Complex c : raizes){
            pontos.add(new Ponto(c.getReal(), c.getImaginary()));
        }


        r.grafico(pontos);

        Log.i(TAG, "Finalizou AsynkTask");
        return null;
    }
}
