package com.luandkg.czilda4.escola.chamadas;

public class DataStatus {

    private String mData;
    private String mStatus;

    public DataStatus(String data,String status){
        mData=data;
        mStatus=status;
    }

    public String getData(){return mData;}
    public String getStatus(){return mStatus;}

}
