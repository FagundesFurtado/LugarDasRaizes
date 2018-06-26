package com.example.memo.lugardasraizes.Adapter;

import com.example.memo.lugardasraizes.Model.Ponto;
import com.example.memo.lugardasraizes.Model.Vetor;

import java.util.ArrayList;

public interface ComunicacaoGrafico {

    public void zeraGrafico();

    public void printaPolo(ArrayList<Ponto> vetor);

    public void printaZero(ArrayList<Ponto> vetor);

    public void printaIntersecao(ArrayList<Vetor> vetor);

    public void printaAssintotas(ArrayList<Vetor> vetor);

    public void printaCurva(ArrayList<Vetor> vetor);



}
