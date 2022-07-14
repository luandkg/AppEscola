package com.luandkg.czilda4.escola.tempo;

import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.utils.tempo.Data;

import java.util.ArrayList;

public class Bimestre {

    private int mID;
    private ArrayList<Data> mDatas;
    private ArrayList<SemanaContinua> mSemanas;

    public Bimestre(int eID,ArrayList<Data> eDatas, ArrayList<SemanaContinua> eSemanas) {
        mID=eID;
        mDatas = eDatas;
        mSemanas = eSemanas;
    }

    public int getID(){return mID;}

    public ArrayList<Data> getDatas() {
        return mDatas;
    }

    public ArrayList<SemanaContinua> getSemanas() {
        return mSemanas;
    }

}
