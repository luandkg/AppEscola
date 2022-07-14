package com.luandkg.czilda4.escola.chamadas;

public class Presenca {

    private String mData;
    private String mStatus;

    public Presenca(String eData,String eStatus){
        mData=eData;
        mStatus=eStatus;
    }

    public String getData(){return mData;}
    public String getStatus(){return mStatus;}

    public void setStatus(String e){
        mStatus=e;
    }
}
