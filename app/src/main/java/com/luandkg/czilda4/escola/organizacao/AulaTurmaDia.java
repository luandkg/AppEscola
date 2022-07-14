package com.luandkg.czilda4.escola.organizacao;

public class AulaTurmaDia {

    private String mDia;
    private String mTurma;

    private int mQuantidade;

    public AulaTurmaDia(String eDia, String eTurma, int eQuantidade) {
        mDia=eDia;
        mTurma=eTurma;
        mQuantidade=eQuantidade;
    }

    public String getDia(){return mDia;}
    public int getQuantidade(){return mQuantidade;}
    public String getTurma(){return mTurma;}

}
