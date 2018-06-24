package com.example.memo.lugardasraizes.Dados;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.memo.lugardasraizes.Adapter.RespostaGrafico;
import com.example.memo.lugardasraizes.Fragment.Inicio;
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
    private static ResolveRaizes singleton = new ResolveRaizes();
    private final String TAG = "Grafico";
    private RespostaGrafico r;
    private Grafico grafico = Grafico.getInstance();


    private ResolveRaizes() {
    }

    public static ResolveRaizes getInstance() {
        return singleton;
    }

    public ResolveRaizes(RespostaGrafico respostaGrafico) {
        r = respostaGrafico;
        Log.i(TAG, "Iniciando solucao do problema");
        this.execute();

    }


    private void adicionaPontos(double[] equacao, ArrayList<Ponto> pontos, boolean polo) {

        PolynomialFunction polynomial = new PolynomialFunction(equacao);

        LaguerreSolver laguerreSolver = new LaguerreSolver();


        Log.i(TAG, "Vetor Equacao");
        for (double d : equacao) {
            Log.i(TAG, "" + d);
        }
        if (equacao.length != 1) {
            Complex[] raizes = laguerreSolver.solveAllComplex(equacao, 0);
            for (Complex c : raizes) {
                pontos.add(new Ponto(c.getReal(), c.getImaginary(), polo));
            }
        }
    }


    @Override
    protected Object doInBackground(Object[] objects) {


        double zeros[] = trataString(grafico.numerador);
        double polos[] = trataString(grafico.denominador);

        ArrayList<Ponto> polosVetor = new ArrayList<>();
        ArrayList<Ponto> zerosVetor = new ArrayList<>();
        adicionaPontos(zeros, polosVetor, false);
        adicionaPontos(polos, zerosVetor, true);
        double[] equacao = new double[]{0, 2, 3, 1};
        //trataString(objects[0].toString());


        r.grafico(polosVetor, zerosVetor);

        Log.i(TAG, "Finalizou AsynkTask");
        return null;
    }

    //trying
    private double[] trataString(String funcao) {

        String poli = funcao;
        //String poli = "1x^3+3x^2+2x^1";
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
            Log.i("TermosPronto ", "Termo " + q.toString());
        }

        Log.i("TamanhoTermos", "" + termos.size());


        double[] termosParaFuncao = new double[termos.size()];
        Log.i("vetor", String.valueOf(termos.get(0).getGrau() + 2));

        for (int j = 0; j < termosParaFuncao.length; j++) {
            termosParaFuncao[j] = Double.valueOf(0);
        }

        for (int i = 0; i < termos.size(); i++) {
            Log.i("vetorFinal", "Posicao " + termos.get(i).getGrau() + "\t" + termos.get(i).getCoeficiente());
            termosParaFuncao[i] = Double.valueOf(termos.get(i).getCoeficiente());
        }
        for (int p = 0; p < termosParaFuncao.length; p++) {
            Log.i("peteca", "Valor " + termosParaFuncao[p] + "\t posicao " + p);
        }

        for (double d : termosParaFuncao) {

            Log.i("SaidaVetor", "" + d);
        }


        return termosParaFuncao;

    }

    public void addTermo(ArrayList<Termo> lista, String termo) {
        termo = termo.replace("^", "");
        String[] funcao = termo.split("x");

        for (int l = 0; l < funcao.length; l++) {
            if (funcao[l].contains("+")) {
                char aux;
                aux = funcao[l].charAt(1);
                Log.i("Funcao ", String.valueOf(aux));
                funcao[l] = String.valueOf(aux);
            }

        }
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