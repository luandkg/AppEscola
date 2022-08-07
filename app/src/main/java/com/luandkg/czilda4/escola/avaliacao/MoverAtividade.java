package com.luandkg.czilda4.escola.avaliacao;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;

import java.util.ArrayList;

public class MoverAtividade {


    public static void recuperacaoToAtividade(String eRecuperacao, String eAtividade, String eData) {

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));

        DKG recuperacao = new DKG();
        if (FS.arquivoExiste(eRecuperacao)) {
            recuperacao.abrir(FS.getArquivoLocal(eRecuperacao));

            DKGObjeto recuperacoes = recuperacao.unicoObjeto("Notas");

            for (DKGObjeto aluno : recuperacoes.getObjetos()) {

                for (AlunoComNota aluno_avaliar : mAlunos) {
                    if (aluno_avaliar.getID().contentEquals(aluno.identifique("ID").getValor())) {

                        aluno_avaliar.setNota("AVALIAR", aluno.identifique("RECUPERACAO").getValor(), eData);

                        break;
                    }
                }

            }

        }


        System.out.println(recuperacao.toString());


        Atividade.salvarNota("AVALIAR", mAlunos, FS.getArquivoLocal(eAtividade));

        DKG atv = new DKG();
        atv.abrir(FS.getArquivoLocal(eAtividade));

        System.out.println(atv.toString());

    }



}
