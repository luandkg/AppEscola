package com.luandkg.czilda4.escola.avaliacao_formativa;

public class MomentoFormativo {

    private String mData;
    private int mValor;

    public MomentoFormativo(String eData,int eValor){
        mData=eData;
        mValor=eValor;
    }

    public String getData(){return mData;}
    public int getValor(){return mValor;}

    public boolean isZero(){return mValor==0;}
    public boolean isRuim(){return mValor==1;}
    public boolean isMedio(){return mValor==2;}
    public boolean isExcelente(){return mValor==3;}

}
