package com.luandkg.czilda4.escola.tempo;

import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;

public class CalendarioEscolar {

    private ArrayList<Data> mAno;
    private ArrayList<Data> mRecesso;

    public CalendarioEscolar() {
        mAno = new ArrayList<Data>();
        mRecesso = new ArrayList<Data>();
    }

    public void setAno(ArrayList<Data> eAno) {
        mAno = eAno;
    }

    public ArrayList<Data> getAno() {
        return mAno;
    }

    public void setRecesso(ArrayList<Data> eRecesso) {
        mRecesso = eRecesso;
    }

    public ArrayList<Data> getRecesso() {
        return mRecesso;
    }

    public boolean isRecesso(Data eData) {
        boolean ret = false;

        for (Data dt : getRecesso()) {
            if (dt.isIgual(eData)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public int recesso_passou(Data eData) {
        int ret = 0;

        for (Data dt : getRecesso()) {
            if (dt.isIgual(eData)) {
                break;
            }
            ret += 1;
        }

        return ret;
    }

    public Bimestre PRIMEIRO_BIMESTRE() {
        return new Bimestre(1, new ArrayList<Data>(), new ArrayList<SemanaContinua>());
    }

    public Bimestre SEGUNDO_BIMESTRE() {
        return new Bimestre(2, new ArrayList<Data>(), new ArrayList<SemanaContinua>());
    }

    public Bimestre TERCEIRO_BIMESTRE() {
        return new Bimestre(3, new ArrayList<Data>(), new ArrayList<SemanaContinua>());
    }

    public Bimestre QUARTO_BIMESTRE() {
        return new Bimestre(4, new ArrayList<Data>(), new ArrayList<SemanaContinua>());
    }

}
