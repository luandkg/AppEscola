package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Data;

import java.io.File;
import java.util.ArrayList;

public class HistoricoDeAtividades {

    public static ArrayList<AtividadeDaTurma> getAtividadesDaTurma(String qual_turma, ArrayList<Data> quais_datas, ArrayList<SemanaContinua> semanas) {

        ArrayList<AtividadeDaTurma> quais_atividades = new ArrayList<AtividadeDaTurma>();


        for (Data eData : quais_datas) {

            String arquivo_atividade = eData.getTempo() + ".dkg";

            boolean temTurma = false;
            int fizeram = 0;
            int total = 0;

            String eArquivoCaminho = FS.getArquivoLocal(Local.LOCAL_AVALIANDO + "/" + arquivo_atividade);
            if (new File(eArquivoCaminho).exists()) {

                DKG eDocumento = DKG.GET(eArquivoCaminho);

                DKGObjeto chamada_dia = eDocumento.unicoObjeto("Notas");
                for (DKGObjeto aluno_chamada : chamada_dia.getObjetos()) {

                    String aluno_chamada_id = aluno_chamada.identifique("ID").getValor();
                    String aluno_chamada_nota = aluno_chamada.identifique("AVALIAR").getValor();
                    String aluno_chamada_turma = aluno_chamada.identifique("Turma").getValor();

                    if (aluno_chamada_turma.contentEquals(qual_turma)) {
                        temTurma = true;

                        if (MetodoContinuo.isValorValido(aluno_chamada_nota)) {
                            fizeram += 1;
                        }
                        total += 1;
                    }


                }

            }

            if (temTurma) {

                String atividade_semana = "";
                String atividade_status = "";

                String nome_organizando = arquivo_atividade.replace(".dkg", "");

                for (SemanaContinua eSemana : semanas) {
                    if (eSemana.temData(Data.organizarData(nome_organizando.replace("_", "/")))) {
                        atividade_semana = "SEMANA DE ATIVIDADES " + eSemana.getNome();
                        atividade_status = eSemana.getStatus();
                        break;
                    }
                }

                if (fizeram > 0) {
                    quais_atividades.add(new AtividadeDaTurma(arquivo_atividade, qual_turma, atividade_semana, atividade_status, fizeram, total));
                }

            }

        }

        return quais_atividades;
    }


}
