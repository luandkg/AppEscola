package com.luandkg.czilda4.escola.avaliacao;


import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.utils.FS;

import java.io.File;
import java.util.ArrayList;


public class Atividade {


    public static void organizarNota(String eArquivoLocal, String eCampo, ArrayList<AlunoComNota> mAlunos) {

        Local.organizarPastas();

        File eArquivo = new File(eArquivoLocal);

        System.out.println("-->> CARREGAR ARQUIVO :: " + eArquivoLocal);

        if (eArquivo.exists()) {

            DKG eDocumento = new DKG();
            eDocumento.abrir(eArquivoLocal);

            // System.out.println(eDocumento.toString());

            DKGObjeto eChamada = eDocumento.unicoObjeto("Notas");

            for (AlunoComNota eAluno : mAlunos) {

                for (DKGObjeto ePacote : eChamada.getObjetos()) {
                    if (ePacote.identifique("ID").getValor().contentEquals(eAluno.getID())) {

                        eAluno.setNota(eCampo, ePacote.identifique(eCampo).getValor(), ePacote.identifique("Data").getValor());

                        break;
                    }
                }


            }

        }


    }


    public static void salvarNota(String eCampo, ArrayList<AlunoComNota> mAlunos, String eArquivoNota) {

        Local.organizarPastas();

        File eArquivo = new File(eArquivoNota);

        DKG eDocumento = new DKG();

        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoNota);
        }

        System.out.println("-->> SALVAR ARQUIVO :: " + eArquivoNota);

        DKGObjeto eChamada = eDocumento.unicoObjeto("Notas");

        for (AlunoComNota eAluno : mAlunos) {

            boolean existe = false;
            for (DKGObjeto ePacote : eChamada.getObjetos()) {
                if (ePacote.identifique("ID").getValor().contentEquals(eAluno.getID())) {
                    ePacote.identifique(eCampo, eAluno.getNota(eCampo));
                    ePacote.identifique("Data", eAluno.getData(eCampo));
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                DKGObjeto p = eChamada.criarObjeto("Aluno");
                p.identifique("ID", eAluno.getID());
                p.identifique("Turma", eAluno.getTurma());
                p.identifique("Nome", eAluno.getNome());
                p.identifique(eCampo, eAluno.getNota(eCampo));
                p.identifique("Data", eAluno.getData(eCampo));
            }
        }

        eDocumento.salvar(eArquivoNota);


    }


    public static int getComNotas(String mTurma) {


        ArrayList<AlunoComNota> eAlunos = Escola.carregarAlunosComNota(mTurma);


        int quantidade = 0;

        for (AlunoComNota eA : eAlunos) {
            if (eA.getVisibilidade().contentEquals("SIM")) {
                if (eA.getNotaFinal() > 0) {
                    quantidade += 1;
                }
            }

        }


        return quantidade;
    }


    public static boolean isNotaValida(String s) {

        boolean ret = false;

        if (s.length() > 0) {
            if (s.contentEquals("1")) {
                ret = true;
            } else if (s.contentEquals("2")) {
                ret = true;
            } else if (s.contentEquals("3")) {
                ret = true;
            }
        }

        return ret;

    }


    public static Mensoes contarMensoes(ArrayList<Aluno> alunos) {

        int zeta = 0;
        int delta = 0;
        int alfa = 0;


        for (Aluno eAluno : alunos) {
            if (eAluno.getStatus().contentEquals("3")) {
                alfa += 1;
            } else if (eAluno.getStatus().contentEquals("2")) {
                delta += 1;
            } else if (eAluno.getStatus().contentEquals("1")) {
                zeta += 1;
            }
        }

        return new Mensoes(0, zeta, delta, alfa);

    }
}
