package com.example.memo.lugardasraizes.Dados;

import android.os.AsyncTask;
import android.util.Log;

import com.example.memo.lugardasraizes.Adapter.RespostaGrafico;
import com.example.memo.lugardasraizes.Model.Ponto;
import com.example.memo.lugardasraizes.Model.Termo;
import com.example.memo.lugardasraizes.Model.Vetor;

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
            Log.i(TAG, "Ponto " + d);
        }
        if (equacao.length != 1) {
            for(double d : equacao)
            {
                Log.i("Raizes","Funcao -> "+d);
            }
            Complex[] raizes = laguerreSolver.solveAllComplex(equacao, 0);
            for (Complex c : raizes) {

                Log.i("Raizes",c.toString());
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

        /*Verifica pontos a direita*/
        intervalos(polosVetor,zerosVetor);

        /*Assintotas*/
        assintotas(polosVetor,zerosVetor);
        r.grafico(polosVetor, zerosVetor);

        Log.i(TAG, "Finalizou AsynkTask");
        return null;
    }

    private void assintotas(ArrayList<Ponto> polos, ArrayList<Ponto> zeros)
    {
        double somatoriaRaizesPolo = somatoriaVetor(polos);
        double somatoriaRaizesZero = somatoriaVetor(zeros);
        double quantidadeDeAssintotas = polos.size()-zeros.size();
        double pontoIrradiacao = (somatoriaRaizesPolo-somatoriaRaizesZero)/(quantidadeDeAssintotas);
        double direcaoDasAssintotas = 180/quantidadeDeAssintotas;
       // ArrayList<ArrayList<Ponto>> assintotas = new ArrayList<>();
        //Desenha assintota
        for(int i =0; i<quantidadeDeAssintotas;i++)
        {
            //Coeficiente angular
            double coeficienteAngular = Math.atan((360*i-180)/quantidadeDeAssintotas);
            double b = 0;
            desenhaReta(coeficienteAngular,b,pontoIrradiacao,9999);

        }

    }

    private void desenhaReta(double a, double b, double inicio, double fim)
    {

    }
    private double somatoriaVetor(ArrayList<Ponto> vetor)
    {
        //Considerando apenas o X
        double contador=0;
        for(Ponto p : vetor)
        {
         contador+=p.getX();
        }
        return contador;
    }
    private void intervalos(ArrayList<Ponto> polos, ArrayList<Ponto> zeros) {
        ArrayList<Ponto> todos = (ArrayList<Ponto>) polos.clone();
        ArrayList<Vetor> vetor = new ArrayList<>();
        todos.addAll(zeros);
        Ponto anterior = new Ponto(-999,0);
        Collections.sort(todos);
        for (Ponto i : todos) {
            Log.i("Todos", i.getX() + "\t" + i.getY());
            if(verificaPolosEZeroAdireita(todos,i.getX())){
                try{
                    Vetor v = new Vetor(anterior, i);
                    vetor.add(v);
                    /*Desenha para esquerda*/
                    desenhaReta(0,0,i.getX(),anterior.getX());
                    anterior = i;
                }catch (Exception e )
                {
                    e.printStackTrace();
                }
            }

        }
    }
    private boolean verificaPolosEZeroAdireita(ArrayList<Ponto> todos, double ponto)
    {

        if(contaADireita(todos,ponto)%2==0)
            return false;
        return true;
    }
    private int contaADireita(ArrayList<Ponto> vetor, double valor)
    {
        int contador=0;
        for(Ponto v : vetor)
        {
            if(v.getX()>valor)
            {
                contador = contador+1;
            }
        }
        return contador;
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