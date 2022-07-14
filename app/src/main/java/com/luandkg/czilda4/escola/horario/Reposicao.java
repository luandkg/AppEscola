package com.luandkg.czilda4.escola.horario;

public class Reposicao {

    private String mData;
    private String mReferente;

    public Reposicao(String eData, String eReferente) {
        mData = eData;
        mReferente = eReferente;
    }

    public String getData(){return mData;}
    public String getReferente(){return mReferente;}

}
