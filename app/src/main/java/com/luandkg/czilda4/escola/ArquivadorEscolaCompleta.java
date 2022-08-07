package com.luandkg.czilda4.escola;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Calendario;

import java.io.File;

public class ArquivadorEscolaCompleta {

    public static void salvar(String arquivo_tudo) {


        DKG documento = new DKG();

        DKGObjeto mEscola = documento.unicoObjeto("ESCOLA");
        DKGObjeto mEscolaChamadas = mEscola.unicoObjeto("CHAMADAS");
        DKGObjeto mEscolaAvaliacoes = mEscola.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA");
        DKGObjeto mEscolaDesempenhos = mEscola.unicoObjeto("DESEMPENHOS");

        mEscola.identifique("DDC", Calendario.getHoraCompleta());

        // GUARDAR CHAMADAS

        for (File arquivo_chamada : FS.listarArquivos(Local.LOCAL_REALIZAR_CHAMADA)) {

            DKG eDocumento = DKG.GET(arquivo_chamada.getAbsolutePath());

            DKGObjeto chamada_dia = mEscolaChamadas.criarObjeto("CHAMADA");

            chamada_dia.identifique("Data", arquivo_chamada.getName().replace(".dkg", ""));
            chamada_dia.getObjetos().addAll(eDocumento.unicoObjeto("Chamada").getObjetos());

        }


        DKG documento_notas = SigmaCollection.INIT_COLLECTION(Local.COLECAO_NOTAS);

        for (DKGObjeto aluno : documento_notas.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA").getObjetos()) {
            mEscolaAvaliacoes.getObjetos().add(aluno);
        }


        DKG documento_desempenhos = SigmaCollection.INIT_COLLECTION(Local.COLECAO_DESEMPENHOS);

        for (DKGObjeto aluno : documento_desempenhos.unicoObjeto("DESEMPENHO").getObjetos()) {
            mEscolaDesempenhos.getObjetos().add(aluno);
        }


        mEscola.identifique("DDM", Calendario.getHoraCompleta());

        documento.salvar(arquivo_tudo);

    }


}
