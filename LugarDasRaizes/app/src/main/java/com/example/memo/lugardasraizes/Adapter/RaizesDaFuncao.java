package com.example.memo.lugardasraizes.Adapter;

import com.example.memo.lugardasraizes.Model.Ponto;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public interface RaizesDaFuncao {

    public void raizesDaFuncao(ArrayList<Ponto> pontos);

    public void printaPolo(ArrayList<Ponto> vetor);

    public void printaZero(ArrayList<Ponto> vetor);

}
