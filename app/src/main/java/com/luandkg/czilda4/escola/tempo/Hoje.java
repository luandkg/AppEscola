package com.luandkg.czilda4.escola.tempo;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Professores;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.organizacao.TurmaItem;
import com.luandkg.czilda4.escola.organizacao.TurmaComHorario;
import com.luandkg.czilda4.utils.FS;

import java.util.ArrayList;

public class Hoje {


    public static ArrayList<String> getTurmas(String eHoje) {

        ArrayList<String> turmas_hoje = new ArrayList<String>();


        for (TurmaItem turma : Professores.getProfessorCorrente().getTurmas()) {
            if (turma.getDiaDaSemana().contentEquals(eHoje)) {
                if (!turma.getTipo().contentEquals("IN")) {
                    if (!turmas_hoje.contains(turma.getNome())) {
                        turmas_hoje.add(turma.getNome());
                    }
                }
            }
        }

        return turmas_hoje;

    }

    public static ArrayList<String> getTurmasRealizadas(String eHoje) {

        ArrayList<String> turmas_realizadas = new ArrayList<String>();

        String chamada_arquivo = Local.LOCAL_REALIZAR_CHAMADA + "/" + eHoje + ".dkg";

        DKG documento_chamadas = new DKG();

        //    System.out.println(chamada_arquivo);

        if (FS.arquivoExiste(chamada_arquivo)) {
            documento_chamadas.abrir(FS.getArquivoLocal(chamada_arquivo));

            DKGObjeto eChamada = documento_chamadas.unicoObjeto("Chamada");

            //   System.out.println(documento_chamadas.toString());

            for (DKGObjeto chamada : eChamada.getObjetos()) {

                String turma = chamada.identifique("Turma").getValor();

                if (!turmas_realizadas.contains(turma)) {
                    turmas_realizadas.add(turma);
                }

            }


        }

        return turmas_realizadas;
    }

    public static ArrayList<TurmaComHorario> getTurmasRealizadasComHorario(String eHoje) {

        ArrayList<String> turmas_realizadas = new ArrayList<String>();
        ArrayList<TurmaComHorario> turmas_realizadas_com_horario = new ArrayList<TurmaComHorario>();

        String chamada_arquivo = Local.LOCAL_REALIZAR_CHAMADA + "/" + eHoje + ".dkg";

        DKG documento_chamadas = new DKG();

        //    System.out.println(chamada_arquivo);

        if (FS.arquivoExiste(chamada_arquivo)) {
            documento_chamadas.abrir(FS.getArquivoLocal(chamada_arquivo));

            DKGObjeto eChamada = documento_chamadas.unicoObjeto("Chamada");

            //   System.out.println(documento_chamadas.toString());

            for (DKGObjeto chamada : eChamada.getObjetos()) {

                String turma = chamada.identifique("Turma").getValor();
                String data = chamada.identifique("Horario").getValor();

                if (!turmas_realizadas.contains(turma)) {
                    turmas_realizadas.add(turma);
                    turmas_realizadas_com_horario.add(new TurmaComHorario(turma, data));
                }

            }


        }

        return turmas_realizadas_com_horario;
    }

    public static int getQuantidadeDeAlunosPresente(String eHoje, ArrayList<String> turmas_realizadas) {

        int alunos_presente = 0;

        String chamada_arquivo = Local.LOCAL_REALIZAR_CHAMADA + "/" + eHoje + ".dkg";

        DKG documento_chamadas = new DKG();

        if (FS.arquivoExiste(chamada_arquivo)) {
            documento_chamadas.abrir(FS.getArquivoLocal(chamada_arquivo));

            DKGObjeto eChamada = documento_chamadas.unicoObjeto("Chamada");


            for (DKGObjeto chamada : eChamada.getObjetos()) {

                String turma = chamada.identifique("Turma").getValor();

                if (turmas_realizadas.contains(turma)) {

                    String chamada_status = chamada.identifique("Status").getValor();
                    if (chamada_status.contentEquals("PRESENTE")) {
                        alunos_presente += 1;
                    }

                }


            }


        }

        return alunos_presente;
    }


    public static int getQuantidadeDeAtividadesRealizadas(String eHoje, ArrayList<String> turmas) {

        String arquivo = Local.LOCAL_AVALIANDO + "/" + eHoje + ".dkg";

        int atividades_total = 0;

        ArrayList<String> alunos_id_realizaram = new ArrayList<String>();

        if (FS.arquivoExiste(arquivo)) {

            DKG documento = DKG.GET(FS.getArquivoLocal(arquivo));

            //System.out.println(documento.toString());

            DKGObjeto eNotas = documento.unicoObjeto("Notas");
            for (DKGObjeto atv : eNotas.getObjetos()) {

                String atv_nota = atv.identifique("AVALIAR").getValor();
                String atv_data = atv.identifique("Data").getValor();
                String atv_turma = atv.identifique("Turma").getValor();
                String atv_aluno_id = atv.identifique("ID").getValor();

               System.out.println("AlunoID :: " + atv_aluno_id + " -->> " + atv_data);

                if (turmas.contains(atv_turma)) {
                    if (atv_data.contentEquals(eHoje)) {
                        if (MetodoContinuo.isValorValido(atv_nota)) {
                            //atividades_realizadas += 1;
                            if (!alunos_id_realizaram.contains(atv_aluno_id)) {
                                alunos_id_realizaram.add(atv_aluno_id);
                            }
                        }
                    }
                }


            }

        }


        // RECUPERACAO

        String eArquivoRecuperacao = Local.ARQUIVO_RECUPERACAO(BimestreCorrente.GET().getID());

        if (FS.arquivoExiste(eArquivoRecuperacao)) {

            DKG fluxo_recuperacao = DKG.GET(FS.getArquivoLocal(eArquivoRecuperacao));

            DKGObjeto recuperacao_raiz = fluxo_recuperacao.unicoObjeto("Notas");

            // System.out.println("-->> Iniciar Recuperacao :: " + eArquivoRecuperacao);

            // System.out.println("Avaliando -->> " + aluno.identifique("Nome").getValor());

            for (DKGObjeto ePacote : recuperacao_raiz.getObjetos()) {

                String recuperacao_valor = ePacote.identifique("RECUPERACAO").getValor();
                String recuperacao_data = ePacote.identifique("Data").getValor();
                String recuperacao_aluno_id = ePacote.identifique("ID").getValor();

                if (recuperacao_data.contentEquals(eHoje)) {
                    if (MetodoContinuo.isValorValido(recuperacao_valor)) {
                        if (!alunos_id_realizaram.contains(recuperacao_aluno_id)) {
                            alunos_id_realizaram.add(recuperacao_aluno_id);
                        }
                    }
                }




            }


        }


        int atividades_realizadas = alunos_id_realizaram.size();

        System.out.println("HOJE :: " + eHoje + " --:>> " + atividades_realizadas);

        return atividades_realizadas;
    }


}
