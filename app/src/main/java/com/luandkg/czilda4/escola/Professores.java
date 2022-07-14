package com.luandkg.czilda4.escola;

import com.luandkg.czilda4.escola.organizacao.Professor;
import com.luandkg.czilda4.escola.professores.Luan;

public class Professores {

    public static Professor getProfessorCorrente(){

        String QUEM= "LUAN";

        Professor mProfessor = null;

        if (QUEM.contentEquals("LUAN")) {
            mProfessor = Luan.getLuan();
        } else if (QUEM.contentEquals("GG")) {
            mProfessor = Luan.getLuan();
        } else if (QUEM.contentEquals("FREITAS")) {
            mProfessor = Luan.getLuan();
        } else if (QUEM.contentEquals("DANTAS")) {
            mProfessor = Luan.getLuan();
        }

       // testes(mProfessor);

        return mProfessor;
    }

}
