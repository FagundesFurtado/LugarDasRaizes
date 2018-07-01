package com.example.memo.lugardasraizes.Main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.memo.lugardasraizes.Adapter.RaizesDaFuncao;
import com.example.memo.lugardasraizes.Dados.ResolveRaizes;
import com.example.memo.lugardasraizes.Model.Ponto;
import com.example.memo.lugardasraizes.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GraficoActivity extends AppCompatActivity implements RaizesDaFuncao, Comparator {

    private final String TAG = "GraficoActivity";

    private GraphView grafico;
    private ProgressDialog progresso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        ResolveRaizes resolveRaizes = new ResolveRaizes( this);

        Intent i = getIntent();

        String numerador = i.getExtras().get("Numerador").toString();
        String denominador = i.getExtras().get("Denominador").toString();





        grafico = findViewById(R.id.graph);
        grafico.getViewport().setYAxisBoundsManual(true);
        grafico.getViewport().setMinY(-3);
        grafico.getViewport().setMaxY(3);

        grafico.getViewport().setXAxisBoundsManual(true);
        grafico.getViewport().setMinX(-50);
        grafico.getViewport().setMaxX(50);

        grafico.getViewport().setScrollable(true);
        grafico.getViewport().setScrollableY(true);
        grafico.getViewport().setScalable(true);
        grafico.getViewport().setScalableY(true);


        resolveRaizes.execute(numerador, denominador);


        progresso = new ProgressDialog(this);

        progresso.setTitle("Carregando");
        progresso.setMessage("Calculando raízes da função");
        progresso.setCancelable(false);
        progresso.show();


    }

    @Override
    public void raizesDaFuncao(ArrayList<Ponto> pontos) {

        Log.i(TAG, "Printa Raizes");

        ArrayList<DataPoint> ponto = new ArrayList<>();
        for (Ponto p : pontos) {
            ponto.add(new DataPoint(p.getX(), p.getY()));
        }
        Collections.sort(ponto, this);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(ponto.toArray(new DataPoint[ponto.size()]));

        series.setSize(10);
        series.setColor(Color.BLUE);

        grafico.addSeries(series);

        progresso.dismiss();

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
        grafico.addSeries(series);
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

        series.setColor(Color.BLUE);

        grafico.addSeries(series);
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

}
