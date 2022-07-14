package com.luandkg.czilda4.escola.avaliacao;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.tempo.Data;

import java.util.ArrayList;

public class Recuperacao {

    public static int getRecuperacaoContagem() {

        Local.organizarPastas();

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));
        Atividade.organizarNota(FS.getArquivoLocal(Local.ARQUIVO_RECUPERACAO(BimestreCorrente.GET().getID())), "RECUPERACAO", mAlunos);

        int presente = 0;

        for (AlunoComNota eAluno : mAlunos) {

            String v = eAluno.getNota("RECUPERACAO");

            if (v.contentEquals("1") || v.contentEquals("2") || v.contentEquals("3")) {
                presente += 1;
            }

        }

        return presente;
    }

    public static String getRecuperacaoStatus() {

        Local.organizarPastas();

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));
        Atividade.organizarNota(FS.getArquivoLocal(Local.ARQUIVO_RECUPERACAO(BimestreCorrente.GET().getID())), "RECUPERACAO", mAlunos);

        boolean isPrimeiro = true;

        String menor = "";
        String maior = "";

        String status = "";

        for (AlunoComNota eAluno : mAlunos) {

            String v = eAluno.getNota("RECUPERACAO");

            if (v.contentEquals("1") || v.contentEquals("2") || v.contentEquals("3")) {

                if (isPrimeiro) {
                    isPrimeiro = false;
                    menor = eAluno.getData("RECUPERACAO");
                    maior = eAluno.getData("RECUPERACAO");
                } else {
                    String corrente = eAluno.getData("RECUPERACAO");
                    if (Data.toData(corrente).isMenor(Data.toData(menor))) {
                        menor = corrente;
                    }
                    if (Data.toData(corrente).isMaior(Data.toData(maior))) {
                        maior = corrente;
                    }
                }

            }

        }

        if (menor.length() > 0 && maior.length() > 0) {
            status = Data.toData(menor).getTempoLegivel() + " - " + Data.toData(maior).getTempoLegivel();
        }


        return status;
    }


    public static int getRecuperacaoPorcentagem() {

        Local.organizarPastas();

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));
        Atividade.organizarNota(FS.getArquivoLocal(Local.ARQUIVO_RECUPERACAO(BimestreCorrente.GET().getID())), "RECUPERACAO", mAlunos);

        int todos = 0;
        int presente = 0;

        for (AlunoComNota eAluno : mAlunos) {

            String v = eAluno.getNota("RECUPERACAO");

            if (v.contentEquals("1") || v.contentEquals("2") || v.contentEquals("3")) {
                presente += 1;
            }

            todos += 1;

        }

        return (int) (((float) presente / (float) todos) * 100.0F);
    }

}
