package com.luandkg.czilda4.escola.alunos;

public class Aluno {

    private String mID;
    private String mTurma;
    private String mNome;
    private String mStatus;
    private String mVisibilidade;
    private String mVivencia;
    private String mVivenciaOrigem = "";

    public Aluno(String eID, String eTurma, String eNome, String eStatus, String eVisibilidade) {
        mID = eID;

        mTurma = eTurma;
        mNome = eNome;
        mStatus = eStatus;
        mVisibilidade = eVisibilidade;
        mVivencia = "NAO";
        mVivenciaOrigem = "";
    }

    public int getIDInt(){return Integer.parseInt(mID);}

    public String getID() {
        return mID;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getNome() {
        return mNome;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getVisibilidade() {
        return mVisibilidade;
    }

    public String getVivencia() {
        return mVivencia;
    }

    public String getVivenciaOrigem() {
        return mVivenciaOrigem;
    }

    public void mudarStatus() {

        if (mStatus.contentEquals("PRESENTE")) {
            mStatus = "AUSENTE";
        } else if (mStatus.contentEquals("AUSENTE")) {
            mStatus = "PRESENTE";
        }

    }


    public void setStatus(String e) {
        mStatus = e;
    }

    public void mudarVisibilidade() {

        if (mVisibilidade.contentEquals("SIM")) {
            mVisibilidade = "NAO";
        } else if (mVisibilidade.contentEquals("NAO")) {
            mVisibilidade = "SIM";
        }

    }

    public void setVivencia(String eOrigem) {
        mVivencia = "SIM";
        mVivenciaOrigem = eOrigem;
    }
}
