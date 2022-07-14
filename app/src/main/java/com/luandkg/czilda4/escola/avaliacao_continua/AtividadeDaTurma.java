package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.utils.tempo.Data;

public class AtividadeDaTurma {

    private String mArquivo;
    private String mTurma;
    private String mSemana;
    private String mStatus;

    private int mFizeram;
    private int mTotal;

    public AtividadeDaTurma(String eArquivo, String eTurma, String eSemana, String eStatus, int eFizeram, int eTotal) {
        mArquivo = eArquivo;
        mTurma = eTurma;
        mSemana = eSemana;
        mStatus = eStatus;
        mFizeram = eFizeram;
        mTotal = eTotal;
    }

    public String getArquivo() {
        return mArquivo;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getSemana() {
        return mSemana;
    }

    public String getStatus() {
        return mStatus;
    }

    public int getFizeram() {
        return mFizeram;
    }

    public int getTotal() {
        return mTotal;
    }

    public String getNome() {

        String nomeCompleto = "";
        String arquivo_simples = getArquivo().replace(".dkg", "");

        if (arquivo_simples.length() == 10) {
            arquivo_simples = Data.toData(arquivo_simples).getFluxo();
            nomeCompleto = getSemana().replace("SEMANA DE ATIVIDADES", "SEMANA ") + " :: " + arquivo_simples;
        } else {
            nomeCompleto = getSemana();
        }

        return nomeCompleto;

    }

    public String getData() {
        return getArquivo().replace(".dkg", "");
    }
}
