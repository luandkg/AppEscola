package com.luandkg.czilda4.escola.chamadas;

import com.luandkg.czilda4.escola.alunos.AlunoChamadas;
import com.luandkg.czilda4.escola.chamadas.ArquivarFrequencia;
import com.luandkg.czilda4.escola.chamadas.TurmaChamadas;
import com.luandkg.czilda4.escola.organizacao.Professor;

import java.util.ArrayList;

public class CarregadorDeFrequencia {

    private static ArrayList<TurmaChamadas> turmas;
    private static boolean carregado = false;

    public static TurmaChamadas getTurma(String qual_turma) {
        TurmaChamadas ret = null;

        for (TurmaChamadas proc : turmas) {
            if (proc.getTurma().contentEquals(qual_turma)) {
                ret = proc;
                break;
            }
        }

        return ret;
    }

    public static ArrayList<TurmaChamadas> carregar(Professor eProfessor) {

        if (!carregado) {
            turmas = ArquivarFrequencia.carregar(eProfessor);
            carregado = true;
        }


        return turmas;
    }


    public static AlunoChamadas getAluno(String eID) {
        AlunoChamadas ret = null;
        boolean enc = false;

        for (TurmaChamadas proc : turmas) {

            for (AlunoChamadas aluno : proc.getAlunos()) {
                if (aluno.getID().contentEquals(eID)) {
                    ret = aluno;
                    enc = true;
                    break;
                }
            }
            if (enc) {
                break;
            }
        }


        return ret;
    }



}
