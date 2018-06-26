package com.example.memo.lugardasraizes.Model;

public class Vetor {
    private Ponto pontoInicial;
    private Ponto pontoFinal;
    public Vetor(Ponto pontoInicial, Ponto pontoFinal)
    {
        this.pontoFinal = pontoFinal;
        this.pontoInicial = pontoInicial;
    }

    public Ponto getPontoInicial() {
        return pontoInicial;
    }

    public void setPontoInicial(Ponto pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public Ponto getPontoFinal() {
        return pontoFinal;
    }

    public void setPontoFinal(Ponto pontoFinal) {
        this.pontoFinal = pontoFinal;
    }
}
