package com.luandkg.czilda4.escola.atualizador;

public class Atualizacao {

    private String mTurma;

    private String mNome;
    private String mEvento;

    private int mNovidades;
    private int mFluxo;
    private String mData;
    private String mHora;

    public Atualizacao(String eTurma, String eNome) {
        mTurma = eTurma;
        mNome = eNome;
        mEvento = "";
        mNovidades = 0;
        mFluxo = 0;
        mData = "";
        mHora = "";

    }

    public void setNovidades(int n) {
        mNovidades = n;
    }

    public String getNome() {
        return mNome;
    }

    public String getTurma() {
        return mTurma;
    }


    public void setEvento(String n) {
        mEvento = n;
    }

    public String getEvento() {
        return mEvento;
    }


    public int getNovidades() {
        return mNovidades;
    }


    public void setFluxo(int n) {
        mFluxo = n;
    }

    public int getFluxo() {
        return mFluxo;
    }

    public void setData(String n) {
        mData = n;
    }

    public String getData() {
        return mData;
    }


    public void setHora(String n) {
        mHora = n;
    }

    public String getHora() {
        return mHora;
    }


}
