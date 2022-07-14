package com.luandkg.czilda4.zilda2020.exportadores;

public class OnTrilha {

    boolean mTem = false;
    int mPrimeiro = 0;
    int mUltimo = 0;

    public OnTrilha() {

        mTem = false;
        mPrimeiro = 0;
        mUltimo = 0;

    }


    public void setPrimeiro(int ePrimeiro) {
        mPrimeiro = ePrimeiro;
    }

    public void setUltimo(int eUltimo) {
        mUltimo = eUltimo;
    }

    public void comecar() {
        mTem = true;
    }

    public boolean tem(){return mTem;}
    public int getPrimeiro(){return mPrimeiro;}
    public int getUltimo(){return mUltimo;}

}
