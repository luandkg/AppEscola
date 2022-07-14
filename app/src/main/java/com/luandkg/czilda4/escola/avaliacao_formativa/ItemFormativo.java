package com.luandkg.czilda4.escola.avaliacao_formativa;

public class ItemFormativo {

    private String mValor;
    private String mData;

    public ItemFormativo(String valor, String data) {
        mValor = valor;
        mData = data;
    }

    public void set(String valor, String data) {
        mValor = valor;
        mData = data;
    }

    public String getValor() {
        return mValor;
    }

    public String getData() {
        return mData;
    }

    public int getNivel() {

        int v = 0;

        if (mValor.contentEquals("1")) {
            v = 1;
        } else if (mValor.contentEquals("2")) {
            v = 2;
        } else if (mValor.contentEquals("3")) {
            v = 3;
        } else if (mValor.contentEquals("0")) {
            v = 0;
        }


        return v;
    }
}
