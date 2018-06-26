package com.example.memo.lugardasraizes.Adapter;

import com.example.memo.lugardasraizes.Model.Ponto;
import com.example.memo.lugardasraizes.Model.Vetor;

import java.util.ArrayList;

public interface RespostaGrafico {

    public void grafico(ArrayList<Ponto> polos, ArrayList<Ponto> zeros,ArrayList<Vetor> vetor);
}
