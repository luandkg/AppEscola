package com.luandkg.czilda4.escola.alunos;

import com.luandkg.czilda4.escola.chamadas.Presenca;

import java.util.ArrayList;

public class AlunoFrequencia {

    private String mTurma;

    private String mNome;
    private ArrayList<Presenca> mPresencas;

    public AlunoFrequencia(String eTurma , String eNome) {
        mTurma = eTurma;
        mNome = eNome;
        mPresencas = new ArrayList<Presenca>();
    }

    public void marcar(String eData, String eStatus) {

        boolean existe = false;

        for (Presenca ePresenca : mPresencas) {
            if (ePresenca.getData().contentEquals(eData)) {
                ePresenca.setStatus(eStatus);
                existe = true;
                break;
            }
        }
        if (!existe) {
            mPresencas.add(new Presenca(eData, eStatus));
        }

    }

    public String getTurma() {
        return mTurma;
    }

    public String getNome() {
        return mNome;
    }

    public boolean isPresente(String eData) {
        boolean ret = false;

        for (Presenca ePresenca : mPresencas) {
            if (ePresenca.getData().contentEquals(eData)) {
                if (ePresenca.getStatus().contentEquals("PRESENTE") || ePresenca.getStatus().contentEquals("CASA")) {
                    ret = true;
                }
                break;
            }
        }

        return ret;
    }
}
