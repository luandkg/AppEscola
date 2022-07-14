package com.luandkg.czilda4.escola.alunos;

import com.luandkg.czilda4.escola.avaliacao_formativa.Formativa;
import com.luandkg.czilda4.escola.avaliacao_formativa.AvaliadorFormativo;
import com.luandkg.czilda4.escola.avaliacao_quantitativa.M3;
import com.luandkg.czilda4.escola.avaliacao_formativa.MomentoFormativo;

import java.util.ArrayList;

public class AlunoComNota {

    private String mID;
    private String mTurma;
    private String mNome;
    private String mVisibilidade;

    private String mNota1;
    private String mNota2;
    private String mNota3;
    private String mNota4;
    private String mNota5;

    private String mData1;
    private String mData2;
    private String mData3;
    private String mData4;
    private String mData5;
    private boolean mISPresente;

    private String mRecuperacao;
    private String mDataRecuperacao;

    private String mAvaliar;
    private String mDataAvaliar;
    private ArrayList<MomentoFormativo> mMomentos;

    public AlunoComNota(String eID, String eTurma, String eNome, String eVisibilidade) {

        mID = eID;
        mTurma = eTurma;
        mNome = eNome;
        mVisibilidade = eVisibilidade;

        mNota1 = "0";
        mNota2 = "0";
        mNota3 = "0";
        mNota4 = "0";
        mNota5 = "0";

        mData1 = "";
        mData2 = "";
        mData3 = "";
        mData4 = "";
        mData5 = "";
        mISPresente = false;

        mRecuperacao = "";
        mDataRecuperacao = "";

        mAvaliar = "";
        mDataAvaliar = "";

        mMomentos = new ArrayList<MomentoFormativo>();
    }

    public String getID() {
        return mID;
    }

    public String getVisibilidade() {
        return mVisibilidade;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getNome() {
        return mNome;
    }

    public String getNota1() {
        return mNota1;
    }

    public String getNota2() {
        return mNota2;
    }

    public String getNota3() {
        return mNota3;
    }

    public String getNota4() {
        return mNota4;
    }

    public String getNota5() {
        return mNota5;
    }

    public String getData1() {
        return mData1;
    }

    public String getData2() {
        return mData2;
    }

    public String getData3() {
        return mData3;
    }

    public String getData4() {
        return mData4;
    }

    public String getData5() {
        return mData5;
    }

    public String getRecuperacao() {
        return mRecuperacao;
    }

    public String getDataRecuperacao() {
        return mDataRecuperacao;
    }

    public void setNota1(String e, String data) {
        mNota1 = e;
        mData1 = data;
    }

    public void setNota2(String e, String data) {
        mNota2 = e;
        mData2 = data;
    }

    public void setNota3(String e, String data) {
        mNota3 = e;
        mData3 = data;
    }

    public void setNota4(String e, String data) {
        mNota4 = e;
        mData4 = data;
    }

    public void setNota5(String e, String data) {
        mNota5 = e;
        mData5 = data;
    }

    public void setNota(String eCampo, String e, String data) {

        if (eCampo.contentEquals("Nota01")) {
            mNota1 = e;
            mData1 = data;
        } else if (eCampo.contentEquals("Nota02")) {
            mNota2 = e;
            mData2 = data;
        } else if (eCampo.contentEquals("Nota03")) {
            mNota3 = e;
            mData3 = data;
        } else if (eCampo.contentEquals("Nota04")) {
            mNota4 = e;
            mData4 = data;
        } else if (eCampo.contentEquals("Nota05")) {
            mNota5 = e;
            mData5 = data;
        } else if (eCampo.contentEquals("RECUPERACAO")) {
            mRecuperacao = e;
            mDataRecuperacao = data;
        } else if (eCampo.contentEquals("AVALIAR")) {
            mAvaliar = e;
            mDataAvaliar = data;
        }

    }

    public String getNota(String eCampo) {

        String ret = "";

        if (eCampo.contentEquals("Nota01")) {
            ret = mNota1;
        } else if (eCampo.contentEquals("Nota02")) {
            ret = mNota2;
        } else if (eCampo.contentEquals("Nota03")) {
            ret = mNota3;
        } else if (eCampo.contentEquals("Nota04")) {
            ret = mNota4;
        } else if (eCampo.contentEquals("Nota05")) {
            ret = mNota5;
        } else if (eCampo.contentEquals("RECUPERACAO")) {
            ret = mRecuperacao;
        } else if (eCampo.contentEquals("AVALIAR")) {
            ret = mAvaliar;
        }

        return ret;
    }

    public String getData(String eCampo) {

        String ret = "";

        if (eCampo.contentEquals("Nota01")) {
            ret = mData1;
        } else if (eCampo.contentEquals("Nota02")) {
            ret = mData2;
        } else if (eCampo.contentEquals("Nota03")) {
            ret = mData3;
        } else if (eCampo.contentEquals("Nota04")) {
            ret = mData4;
        } else if (eCampo.contentEquals("Nota05")) {
            ret = mData5;
        } else if (eCampo.contentEquals("RECUPERACAO")) {
            ret = mDataRecuperacao;
        } else if (eCampo.contentEquals("AVALIAR")) {
            ret = mDataAvaliar;
        }

        return ret;
    }


    public String getStringNotas() {

        return mNota1 + " " + mNota2 + " " + mNota3 + " >> " + getNotaFinal();
    }

    public int getNota1Validada() {

        int i1 = 0;

        if (mNota1.length() > 0) {
            if (M3.isNumero(mNota1)) {
                i1 = Integer.parseInt(mNota1);
            }
        }

        return i1;
    }

    public int getNota2Validada() {

        int i2 = 0;

        if (mNota2.length() > 0) {
            if (M3.isNumero(mNota2)) {
                i2 = Integer.parseInt(mNota2);
            }
        }

        return i2;
    }

    public int getNota3Validada() {

        int i3 = 0;

        if (mNota3.length() > 0) {
            if (M3.isNumero(mNota3)) {
                i3 = Integer.parseInt(mNota3);
            }
        }

        return i3;
    }

    public int getNota4Validada() {

        int i3 = 0;

        if (mNota4.length() > 0) {
            if (M3.isNumero(mNota4)) {
                i3 = Integer.parseInt(mNota4);
            }
        }

        return i3;
    }

    public int getNota5Validada() {

        int i3 = 0;

        if (mNota5.length() > 0) {
            if (M3.isNumero(mNota5)) {
                i3 = Integer.parseInt(mNota5);
            }
        }

        return i3;
    }

    public void marcarPresente() {
        mISPresente = true;
    }

    public int getNotaFinal() {
        return Formativa.continua(mNota1, mNota2, mNota3, mNota4, mNota5, mRecuperacao, mISPresente);
    }

    public int getNotaPreFinal() {
        return Formativa.continua(mNota1, mNota2, mNota3, mNota4, mNota5, "0", mISPresente);
    }

    public ArrayList<MomentoFormativo> getMomentos() {
        return mMomentos;
    }

    public void fazerAtividade(String data, String valor) {

        int iValor = 0;

        if (valor.length() > 0) {
            iValor = Integer.parseInt(valor);
        }

        mMomentos.add(new MomentoFormativo(data, iValor));
    }

    public int getNotaPreFormativa() {
        return AvaliadorFormativo.calcularFormativa(mMomentos, "0", mISPresente);
    }

    public int getNotaFormativa() {
        return AvaliadorFormativo.calcularFormativa(mMomentos, mRecuperacao, mISPresente);
    }
}
