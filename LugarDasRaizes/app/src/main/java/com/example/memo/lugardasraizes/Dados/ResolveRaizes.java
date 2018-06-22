package com.example.memo.lugardasraizes.Dados;

import android.os.AsyncTask;
import android.util.Log;

import com.example.memo.lugardasraizes.Adapter.RespostaGrafico;
import com.example.memo.lugardasraizes.Model.Ponto;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
        //trataString(objects[0].toString());

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

    private void trataString(String funcao)
    {
        //3s^3+2s^2+s^1+4
        String poli = "-3x^3-(3x^2)+4+3x+2x^8";
        Pattern.compile("(?=[+-])").split(poli);

        Pattern pegapoli = Pattern.compile("(?=[+-])");
        String[] m = pegapoli.split(poli);

        for(String i : m)
            Log.i("Poli", i);
        ArrayList<Termo> termos = new ArrayList<>();
        int sinal=1;
        for(int i=0;i<funcao.length();i++)
        {
            if(funcao.charAt(i) == '-') {
                sinal = -1;
            }
            //Testando commit no pc da Renata Dias

        }


    }
}
