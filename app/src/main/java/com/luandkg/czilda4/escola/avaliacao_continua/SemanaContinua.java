package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.utils.tempo.Data;
import com.luandkg.czilda4.utils.tempo.DiaSemanal;

import java.util.ArrayList;

public class SemanaContinua {

    private ArrayList<Data> mDatas;
    private String mNome;
    private int mNumero;

    public SemanaContinua(int eNumero, String eNome, ArrayList<Data> eDatas) {
        mNumero = eNumero;
        mNome = eNome;
        mDatas = eDatas;
    }


    public ArrayList<Data> getDatas() {
        return mDatas;
    }

    public String getNome() {
        return mNome;
    }

    public int getNumero() {
        return mNumero;
    }

    public String getStatus() {

        //  System.out.println("QTD :: " + mDatas.size());
        if (mDatas.size() > 0) {
            //   System.out.println(mDatas.get(0).getTempoLegivel());
            // System.out.println(mDatas.get(mDatas.size() - 1).getTempoLegivel());

            return mDatas.get(0).getTempoLegivel() + " - " + mDatas.get(mDatas.size() - 1).getTempoLegivel();
        } else {
            return "";
        }

    }

    public Data getPrimeiraData() {
        Data ret = new Data(0, 0, 0, DiaSemanal.Domingo);
        if (mDatas.size() > 0) {
            ret = mDatas.get(0);
        }

        return ret;
    }

    public String getUltimaData() {
        String ret = "";
        if (mDatas.size() > 0) {
            ret = mDatas.get(mDatas.size() - 1).getTempoLegivel();
        }

        return ret;
    }

    public boolean temData(String eData) {
        boolean ret = false;

        for (Data data : mDatas) {
            if (data.isIgual(eData)) {
                ret = true;
                break;
            }
        }


        return ret;
    }
}
