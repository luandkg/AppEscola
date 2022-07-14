package com.luandkg.czilda4.escola.avaliacao_continua;

public class SemanaContinuaValores {

    private String mData;
    private String mDataRealizada;

    private String mValor;
    private String mParcela;
    private String mNomeCompleto;
    private String mStatusCompleto;

    private boolean misUltima = false;


    public SemanaContinuaValores(String eData,String eDataRealizada,String eValor ,String eParcela,String eNomeCompleto,String eStatusCompleto){
        mData=eData;
        mDataRealizada=eDataRealizada;

        mValor=eValor;
        mParcela=eParcela;
        mNomeCompleto=eNomeCompleto;
        mStatusCompleto=eStatusCompleto;
    }

    public String getData(){return mData;}
    public String getDataRealizada(){return mDataRealizada;}

    public String getValor(){return mValor;}
    public String getParcela(){return mParcela;}

    public String getNomeCompleto(){return mNomeCompleto;}
    public String getStatusCompleto(){return mStatusCompleto;}

    public void marcarUltima(){
        misUltima=true;
    }

    public boolean isUltima(){return misUltima;}
}
