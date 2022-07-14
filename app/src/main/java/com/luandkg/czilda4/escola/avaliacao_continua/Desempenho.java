package com.luandkg.czilda4.escola.avaliacao_continua;

public class Desempenho {

    private String mNome;
    private String mValor;
    private int mQuantidade;

    public Desempenho(String eNome,String eValor,int eQuantidade){
        mNome=eNome;
                mValor=eValor;
                        mQuantidade=        eQuantidade;
    }

    public String getNome(){return mNome;}
    public String getValor(){return mValor;}
    public int getQuantidade(){return mQuantidade;}

}
