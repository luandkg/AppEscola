package com.luandkg.czilda4.escola.chamadas;

public class DataChamada {

    private String mData;
    private boolean mStatus;

    public DataChamada(String eData, boolean eStatus) {
        mData = eData;
        mStatus = eStatus;
    }


    public String getData() {
        return mData;
    }

    public String getDataSemDia() {
        String d = mData;

        d = d.replace(" :: Segunda", "");
        d = d.replace(" :: Terca", "");
        d = d.replace(" :: Quarta", "");
        d = d.replace(" :: Quinta", "");
        d = d.replace(" :: Sexta", "");

        return d;
    }

    public boolean getStatus() {
        return mStatus;
    }

}
