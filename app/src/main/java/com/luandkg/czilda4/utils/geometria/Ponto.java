package com.luandkg.czilda4.utils.geometria;

public class Ponto {

    private int mX;
    private int mY;

    public Ponto(int eX, int eY) {
        mX = eX;
        mY = eY;
    }

    public int x() {
        return mX;
    }

    public int y() {
        return mY;
    }

    public void set(int eX, int eY) {
        mX = eX;
        mY = eY;
    }

}
