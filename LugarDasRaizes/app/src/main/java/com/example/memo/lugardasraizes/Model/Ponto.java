package com.example.memo.lugardasraizes.Model;


public class Ponto implements Comparable<Ponto> {

    private double x, y;
    private boolean polo;


    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Ponto(double x, double y, boolean polo) {
        this.x = x;
        this.y = y;
        this.polo = polo;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isPolo() {
        return polo;
    }

    public void setPolo(boolean polo) {
        this.polo = polo;
    }

    @Override
    public int compareTo(Ponto outroPonto) {
        if (this.x > outroPonto.getX()) {
            return -1;
        }
        if (this.x < outroPonto.getX()) {
            return 1;
        }
        return 0;
    }
}
