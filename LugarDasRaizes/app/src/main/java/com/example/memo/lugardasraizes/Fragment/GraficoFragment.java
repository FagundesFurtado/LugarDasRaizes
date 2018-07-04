package com.example.memo.lugardasraizes.Fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memo.lugardasraizes.Adapter.ComunicacaoGrafico;
import com.example.memo.lugardasraizes.Adapter.RaizesDaFuncao;
import com.example.memo.lugardasraizes.Adapter.RespostaGrafico;
import com.example.memo.lugardasraizes.Dados.ResolveRaizes;
import com.example.memo.lugardasraizes.Model.Ponto;
import com.example.memo.lugardasraizes.Model.Vetor;
import com.example.memo.lugardasraizes.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GraficoFragment extends Fragment implements RespostaGrafico, Comparator, ComunicacaoGrafico, RaizesDaFuncao {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private GraphView graphView;
    private final String TAG = "GraficoFragmentLog";


    private OnFragmentInteractionListener mListener;

    public GraficoFragment() {
        // Required empty public constructor
    }


    public static GraficoFragment newInstance(String param1, String param2) {
        GraficoFragment fragment = new GraficoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_grafico, container, false);

        graphView = v.findViewById(R.id.graph);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(-3);
        graphView.getViewport().setMaxY(3);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(-50);
        graphView.getViewport().setMaxX(50);

        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScrollableY(true);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);


        // enable scaling and scrolling
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);

        graphView.removeAllSeries();
        ResolveRaizes r = new ResolveRaizes(this, this, this);


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        graphView.removeAllSeries();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void trataPoloOuZero(PointsGraphSeries<DataPoint> series, boolean polo) {

        if (polo) {

            series.setColor(Color.GREEN);
            series.setCustomShape(new PointsGraphSeries.CustomShape() {
                @Override
                public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                    paint.setStrokeWidth(10);
                    Log.i("Valores", x + "\t" + y);
                    Log.i("Valores", "> " + (x - 20) + "\t" + (y + 20));
                    canvas.drawLine(x - 20, y - 20, x + 20, y + 20, paint);
                    canvas.drawLine(x + 20, y - 20, x - 20, y + 20, paint);
                }
            });

        } else {
            series.setColor(Color.BLUE);
        }


    }

    private LineGraphSeries<DataPoint> desenhaReta(final Ponto pontoInicial, final Ponto pontoFinal) {

        if (pontoFinal.getX() > pontoInicial.getX()) {
            return ordenaPontos(pontoInicial, pontoFinal);
        }
        return ordenaPontos(pontoFinal, pontoInicial);
    }

    private LineGraphSeries ordenaPontos(Ponto a, Ponto b) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(a.getX(), a.getY()),
                new DataPoint(b.getX(), b.getY())});
        series.setColor(Color.YELLOW);

        return series;
    }

    private PointsGraphSeries<DataPoint> criaPontosSerie(ArrayList<Ponto> pontos) {


        ArrayList<DataPoint> ponto = new ArrayList<>();
        for (Ponto p : pontos) {
            ponto.add(new DataPoint(p.getX(), p.getY()));
        }
        Collections.sort(ponto, this);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(ponto.toArray(new DataPoint[ponto.size()]));


        if (!pontos.isEmpty()) {
            trataPoloOuZero(series, pontos.get(0).isPolo());
        }

        return series;
    }

    @Override
    public void grafico(ArrayList<Ponto> polos, ArrayList<Ponto> zeros, ArrayList<Vetor> vetor) {
        graphView.removeAllSeries();

        for (Vetor v : vetor) {
            graphView.addSeries(desenhaReta(v.getPontoInicial(), v.getPontoFinal()));
        }
        graphView.addSeries(criaPontosSerie(polos));
        graphView.addSeries(criaPontosSerie(zeros));


    }

    @Override
    public int compare(Object o1, Object o2) {
        DataPoint p1 = (DataPoint) o1;
        DataPoint p2 = (DataPoint) o2;
        if (p1.getX() < p2.getX()) {

            return -1;
        }
        if (p1.getX() > p2.getX()) {

            return 1;
        }
        return 0;
    }

    @Override
    public void zeraGrafico() {
        graphView.removeAllSeries();
    }

    @Override
    public void printaPolo(ArrayList<Ponto> vetor) {

        Log.i(TAG, "PrintaPolos");
        ArrayList<DataPoint> ponto = new ArrayList<>();
        for (Ponto p : vetor) {
            Log.i("PolosDaEquacao", p.getX() + " " + p.getY());
            ponto.add(new DataPoint(p.getX(), p.getY()));
        }
        Collections.sort(ponto, this);
        Log.i("TamhanhoPolos", "" + ponto.size());
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(ponto.toArray(new DataPoint[ponto.size()]));

        series.setColor(Color.GREEN);
        series.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(10);
                Log.i("Valores", x + "\t" + y);
                Log.i("Valores", "> " + (x - 20) + "\t" + (y + 20));
                canvas.drawLine(x - 20, y - 20, x + 20, y + 20, paint);
                canvas.drawLine(x + 20, y - 20, x - 20, y + 20, paint);
            }
        });
        graphView.addSeries(series);
    }

    public void limpaGrafico() {
        graphView.removeAllSeries();

    }

    @Override
    public void printaZero(ArrayList<Ponto> vetor) {

        Log.i(TAG, "PrintaZeros");

        ArrayList<DataPoint> ponto = new ArrayList<>();
        for (Ponto p : vetor) {
            ponto.add(new DataPoint(p.getX(), p.getY()));
        }
        Collections.sort(ponto, this);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(ponto.toArray(new DataPoint[ponto.size()]));
        series.setSize(20);
        series.setColor(Color.RED);

        graphView.addSeries(series);
    }

    @Override
    public void printaIntersecao(ArrayList<Vetor> vetor) {
        Log.i(TAG, "printaIntersecao");
        for (Vetor v : vetor) {
            graphView.addSeries(desenhaReta(v.getPontoInicial(), v.getPontoFinal()));
        }
        //  graphView.addSeries(desenhaReta(new Ponto(10,10),new Ponto(1000,1000)));


    }

    @Override
    public void printaAssintotas(ArrayList<Vetor> vetor) {
        for (Vetor v : vetor) {
            graphView.addSeries(desenhaReta(v.getPontoInicial(), v.getPontoFinal()));
        }
        Log.i(TAG, "printaAssintotas");

    }

    @Override
    public void printaCurva(ArrayList<Vetor> vetor) {
        Log.i(TAG, "printaCurva");
    }

    @Override
    public void raizesDaFuncao(ArrayList<Ponto> pontos) {

        Log.i(TAG, "Printa Raizes");

        ArrayList<DataPoint> ponto = new ArrayList<>();
        for (Ponto p : pontos) {
            ponto.add(new DataPoint(p.getX(), p.getY()));
        }

        Collections.sort(ponto, this);
        PointsGraphSeries series = new PointsGraphSeries<>(ponto.toArray(new DataPoint[ponto.size()]));

//        for(int i=1;i<pontos.size()-1;i++)
//        {
//            graphView.addSeries(desenhaReta(pontos.get(i),pontos.get(i+1)));
//        }
        series.setColor(Color.BLUE);

        graphView.addSeries(series);



    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
