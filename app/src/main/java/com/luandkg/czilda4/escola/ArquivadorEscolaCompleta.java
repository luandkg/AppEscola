package com.luandkg.czilda4.escola;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.AlunoChamadas;
import com.luandkg.czilda4.escola.chamadas.DataChamada;
import com.luandkg.czilda4.escola.chamadas.TurmaChamadas;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.tempo.Calendario;

import java.io.File;
import java.util.ArrayList;

public class ArquivadorEscolaCompleta {

    public static void salvar( String pasta_chamadas,String arquivo_notas, String arquivo_desempenhos, String arquivo) {


        DKG documento = new DKG();

        DKGObjeto mEscola = documento.unicoObjeto("ESCOLA");
        DKGObjeto mEscolaChamadas = mEscola.unicoObjeto("CHAMADAS");
        DKGObjeto mEscolaAvaliacoes = mEscola.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA");
        DKGObjeto mEscolaDesempenhos = mEscola.unicoObjeto("DESEMPENHOS");

        mEscola.identifique("DDC", Calendario.getHoraCompleta());

        // GUARDAR CHAMADAS

        for (File arquivo_chamada : FS.listarArquivos(pasta_chamadas)) {

            DKG eDocumento = DKG.GET(arquivo_chamada.getAbsolutePath());

            DKGObjeto chamada_dia = mEscolaChamadas.criarObjeto("CHAMADA");

            chamada_dia.identifique("Data", arquivo_chamada.getName().replace(".dkg", ""));
            chamada_dia.getObjetos().addAll(eDocumento.unicoObjeto("Chamada").getObjetos());

        }


        DKG documento_notas = DKG.GET(FS.getArquivoLocal(arquivo_notas));

        for (DKGObjeto aluno : documento_notas.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA").getObjetos()) {
            mEscolaAvaliacoes.getObjetos().add(aluno);
        }


        DKG documento_desempenhos = DKG.GET(FS.getArquivoLocal(arquivo_desempenhos));

        for (DKGObjeto aluno : documento_desempenhos.unicoObjeto("DESEMPENHO").getObjetos()) {
            mEscolaDesempenhos.getObjetos().add(aluno);
        }


        mEscola.identifique("DDM", Calendario.getHoraCompleta());

        documento.salvar(arquivo);

    }


}
