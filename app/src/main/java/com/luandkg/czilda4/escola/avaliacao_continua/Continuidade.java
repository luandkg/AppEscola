package com.luandkg.czilda4.escola.avaliacao_continua;

public class Continuidade {

    private String mSemana;
    private double mValor;

    public Continuidade(String eSemana, double eValor) {
        mSemana = eSemana;
        mValor = eValor;
    }


    public void setValor(double eValor){
        mValor=eValor;
    }

    public double getValor(){return mValor;}
}
