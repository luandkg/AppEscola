package com.luandkg.czilda4.escola.chamadas;

import com.luandkg.czilda4.escola.alunos.AlunoChamadas;
import com.luandkg.czilda4.escola.organizacao.AulaTurmaDia;

import java.util.ArrayList;

public class TurmaChamadas {

    private ArrayList<AlunoChamadas> mAlunos;
    private String mTurma;
    private ArrayList<AulaTurmaDia> dias;

    public TurmaChamadas(String eTurma) {
        mTurma = eTurma;
        mAlunos = new ArrayList<AlunoChamadas>();
        dias = new ArrayList<AulaTurmaDia>();
    }

    public String getTurma() {
        return mTurma;
    }

    public ArrayList<AlunoChamadas> getAlunos() {
        return mAlunos;
    }

    public void registrar(String eID, String eNome) {
        mAlunos.add(new AlunoChamadas(eID, mTurma, eNome));
    }

    public boolean existeAlunoComID(String eID) {
        boolean ret = false;

        for (AlunoChamadas aluno : mAlunos) {
            if (aluno.getID().contentEquals(eID)) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    public AlunoChamadas getAluno(String eID) {
        AlunoChamadas ret = null;

        for (AlunoChamadas aluno : mAlunos) {
            if (aluno.getID().contentEquals(eID)) {
                ret = aluno;
                break;
            }
        }

        return ret;
    }


    public int getAulasRealizadas() {
        int a = 0;

        for (AlunoChamadas aluno : mAlunos) {
            if ((aluno.getFalta() + aluno.getPresente()) > a) {
                a = aluno.getFalta() + aluno.getPresente();
            }
        }

        return a;
    }


    public void marcarDia(AulaTurmaDia dia) {
        dias.add(dia);
    }

    public boolean temDia(String dia) {

        boolean tem = false;
        for (AulaTurmaDia a : dias) {
            if (a.getDia().contentEquals(dia)) {
                tem = true;
                break;
            }
        }

        return tem;
    }


    public AulaTurmaDia getDia(String dia) {

        AulaTurmaDia tem = null;
        for (AulaTurmaDia a : dias) {
            if (a.getDia().contentEquals(dia)) {
                tem = a;
                break;
            }
        }

        return tem;
    }
}
