package com.example.memo.lugardasraizes.Dados;

import android.os.AsyncTask;
import android.util.Log;

import com.example.memo.lugardasraizes.Adapter.RespostaGrafico;
import com.example.memo.lugardasraizes.Model.Ponto;
import com.example.memo.lugardasraizes.Model.Termo;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class ResolveRaizes extends AsyncTask {

    private final String TAG = "Grafico";
    private RespostaGrafico r;
    private Grafico grafico = Grafico.getInstance();

    public ResolveRaizes(RespostaGrafico respostaGrafico) {
        r = respostaGrafico;
        Log.i(TAG, "Iniciando solucao do problema");

        this.execute();

    }

    @Override
    protected Object doInBackground(Object[] objects) {

        double polos[] = trataString(grafico.numerador);
        double zeros[] = trataString(grafico.denominador);


        double[] equacao = new double[]{0, 2, 3, 1};
        //trataString(objects[0].toString());

        PolynomialFunction polynomial = new PolynomialFunction(equacao);

        LaguerreSolver laguerreSolver = new LaguerreSolver();
        Complex[] raizes = laguerreSolver.solveAllComplex(equacao, 0);

        ArrayList<Ponto> pontos = new ArrayList<>();

        for (Complex c : raizes) {
            pontos.add(new Ponto(c.getReal(), c.getImaginary()));
        }


        r.grafico(pontos);

        Log.i(TAG, "Finalizou AsynkTask");
        return null;
    }


    private double[] trataString(String funcao) {
        //3s^3+2s^2+s^1+4
        String poli = "1x^3+3x^2+2x^1";

        Pattern.compile("(?=[+-])").split(poli);

        Pattern pegapoli = Pattern.compile("(?=[+-])");
        String[] m = pegapoli.split(poli);
        ArrayList<Termo> termos = new ArrayList<>();
        for (String i : m) {
            Log.i("Poli", i);
            addTermo(termos, i);
        }
        Collections.sort(termos, new Comparator<Termo>() {
            @Override
            public int compare(Termo o1, Termo o2) {
                return o1.getGrau().compareTo(o2.getGrau());
            }
        });


        for (Termo q : termos) {
            Log.i("Vetor termos ", "Termo " + q.toString());
        }

        Integer[] termosParaFuncao = new Integer[termos.get(0).getGrau() + 1];

        for (int j = 0; j < termosParaFuncao.length; j++) {
            termosParaFuncao[j] = 0;
        }
        for (int i = 0; i < termos.size(); i++) {
            Log.i("vetorFinal", "Posicao " + termos.get(i).getGrau() + "\t" + termos.get(i).getCoeficiente());
            termosParaFuncao[termos.get(i).getGrau()] = termos.get(i).getCoeficiente();
        }
        for (int p = 0; p < termosParaFuncao.length; p++) {
            Log.i("peteca", "Valor " + termosParaFuncao[p] + "\t posicao " + p);
        }

        return null;

    }

    public void addTermo(ArrayList<Termo> lista, String termo) {
        termo = termo.replace("^", "");
        String[] funcao = termo.split("x");

        for (String l : funcao)
            Log.i("Funcao ", l);
        if (funcao.length == 2) {
            lista.add(new Termo(Integer.parseInt(funcao[0]), Integer.parseInt(funcao[1])));
        }
        if (funcao.length == 1) {
            try {
                lista.add(new Termo(Integer.parseInt(funcao[0]), 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}