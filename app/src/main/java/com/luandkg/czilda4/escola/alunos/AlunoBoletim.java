package com.luandkg.czilda4.escola.alunos;

public class AlunoBoletim {

    private String mTurma;
    private String mNome;
    private String mStatus;
    private String mAssinou;

    public AlunoBoletim(String eTurma, String eNome, String eStatus,String eAssinou) {
        mTurma = eTurma;
        mNome = eNome;
        mStatus = eStatus;
        mAssinou = eAssinou;
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


    public void mudarStatus() {

        if (mStatus.contentEquals("NAO")) {
            mStatus = "SIM";
        } else if (mStatus.contentEquals("SIM")) {
            mStatus = "NAO";
        }

    }


    public void setStatus(String e) {
        mStatus = e;
    }

    public void mudarAssinatura() {

        if (mAssinou.contentEquals("NAO")) {
            mAssinou = "SIM";
        } else if (mAssinou.contentEquals("SIM")) {
            mAssinou = "NAO";
        }

    }

    public void setAssinou(String e) {
        mAssinou = e;
    }

    public String getAssinou() {
        return mAssinou;
    }


}
