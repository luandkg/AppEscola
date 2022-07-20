package com.luandkg.czilda4.escola.avaliacao;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.utils.ContadorSN;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;

import java.io.File;
import java.util.ArrayList;

public class AtividadeContador {


    public static int contar(ArrayList<AlunoComNota> eAlunos, String data) {
        int c = 0;

        for (AlunoComNota aluno : eAlunos) {

            if (aluno.getData1().contentEquals(data)) {
                if (Atividade.isNotaValida(aluno.getNota1())) {
                    c += 1;
                }
            }

            if (aluno.getData2().contentEquals(data)) {
                if (Atividade.isNotaValida(aluno.getNota2())) {
                    c += 1;
                }
            }

            if (aluno.getData3().contentEquals(data)) {
                if (Atividade.isNotaValida(aluno.getNota3())) {
                    c += 1;
                }
            }

            if (aluno.getData4().contentEquals(data)) {
                if (Atividade.isNotaValida(aluno.getNota4())) {
                    c += 1;
                }
            }

            if (aluno.getData5().contentEquals(data)) {
                if (Atividade.isNotaValida(aluno.getNota5())) {
                    c += 1;
                }
            }

            if (aluno.getDataRecuperacao().contentEquals(data)) {
                if (Atividade.isNotaValida(aluno.getRecuperacao())) {
                    c += 1;
                }
            }
        }

        return c;

    }




    public static ContadorSN contarAlunosNotas(ArrayList<AlunoComNota> mAlunos) {

        int sim = 0;
        int nao = 0;

        for (AlunoComNota aluno : mAlunos) {
            int v = aluno.getNotaFinal();
            if (v >= 5) {
                sim += 1;
            } else {
                nao += 1;
            }
        }

        return new ContadorSN(sim,nao);
    }

    public static ContadorSN contarAlunosContinuosNotas(ArrayList<AlunoContinuo> mAlunos) {

        int sim = 0;
        int nao = 0;

        for (AlunoContinuo aluno : mAlunos) {
            double v = aluno.getAcumuladoContinuidadeComRecuperacao();
            if (v >= 5) {
                sim += 1;
            } else {
                nao += 1;
            }
        }

        return new ContadorSN(sim,nao);
    }

    public static int getSim(ArrayList<AlunoComNota> mAlunos, String eCampo) {

        int c = 0;

        for (AlunoComNota aluno : mAlunos) {
            String v = aluno.getNota(eCampo);
            int i = 0;
            if (v.length() > 0) {
                i = Integer.parseInt(v);
            }
            if (i > 0) {
                c += 1;
            }
        }

        return c;
    }

    public static int getNao(ArrayList<AlunoComNota> mAlunos, String eCampo) {

        int c = 0;

        for (AlunoComNota aluno : mAlunos) {
            String v = aluno.getNota(eCampo);
            int i = 0;
            if (v.length() > 0) {
                i = Integer.parseInt(v);
            }
            if (i == 0) {
                c += 1;
            }
        }

        return c;
    }

    public static int getContagem(ArrayList<AlunoComNota> mAlunos, String campo, int valor) {

        int v = 0;

        for (AlunoComNota aluno : mAlunos) {
            String vs = aluno.getNota(campo);
            if (vs.length() > 0) {
                int vi = Integer.parseInt(vs);
                if (vi == valor) {
                    v += 1;
                }
            }
        }

        return v;
    }

    public static int quantosFizeram(String eArquivoLocal, String eCampo, String qualTurma) {

        int contando = 0;

        Local.organizarPastas();


        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();

        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);

            DKGObjeto eNotas = eDocumento.unicoObjeto("Notas");

            for (DKGObjeto ePacote : eNotas.getObjetos()) {
                if (ePacote.identifique("Turma").getValor().contentEquals(qualTurma)) {

                    String valor = ePacote.identifique(eCampo).getValor();

                    if (valor.contentEquals("1") || valor.contentEquals("2") || valor.contentEquals("3")) {
                        contando += 1;
                    }

                }
            }


        }

        return contando;
    }

}
