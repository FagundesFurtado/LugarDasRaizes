package com.example.memo.lugardasraizes;

public class Ponto {

    private double x, y;
    private boolean polo;


    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
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

}
