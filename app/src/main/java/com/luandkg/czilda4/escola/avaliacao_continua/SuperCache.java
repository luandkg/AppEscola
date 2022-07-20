package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;

public class SuperCache {

    private DKGObjeto mRaizAvaliacaoContinuaFormativa;

    public SuperCache(String colecao_notas) {

        DKG fluxo_entrega = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(colecao_notas);


        mRaizAvaliacaoContinuaFormativa = fluxo_entrega.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA");


    }


    public int contagem(ArrayList<AlunoContinuo> alunos, String qual_turma) {

        int contando = 0;


        for (DKGObjeto aluno_objeto : mRaizAvaliacaoContinuaFormativa.getObjetos()) {

            int i_id = aluno_objeto.identifique("ID").getInteiro(0);

            for (AlunoContinuo a : alunos) {
                if (a.getID() == i_id) {
                    if (a.getTurma().contentEquals(qual_turma)) {
                        if (aluno_objeto.identifique("NotaComRecuperacao").getDouble(0.0) >= 5) {
                            contando += 1;
                        }
                    }
                }

            }
        }

        return contando;
    }


    public ArrayList<Aluno> getAlunosDaSemana(ArrayList<Data> quais_datas, ArrayList<Data> atividade_datas) {

        ArrayList<Aluno> ret = new ArrayList<Aluno>();


        // System.out.println(fluxo_entrega.toString());

        //int fez = 0;

        ArrayList<String> fez = new ArrayList<String>();

        for (Data data : quais_datas) {

            boolean esta = false;
            for (Data qData : atividade_datas) {
                if (qData.getTempo().contentEquals(data.getTempo())) {
                    esta = true;
                    break;
                }
            }

            if (esta) {


                for (DKGObjeto proc : mRaizAvaliacaoContinuaFormativa.getObjetos()) {


                    for (DKGObjeto em_data : proc.unicoObjeto("Momentos").getObjetos()) {

                        if (em_data.identifique("Data").getValor().contentEquals(data.getTempo())) {

                            String id = proc.identifique("ID").getValor();
                            String nome = proc.identifique("Nome").getValor();

                            int atividades = em_data.identifique("Valor").getInteiro(0);
                            if (!fez.contains(String.valueOf(id))) {
                                fez.add(String.valueOf(id));
                                //  System.out.println("\tPROC ID " + proc.listar());
                                ret.add(new Aluno(id, em_data.identifique("Data").getValor().replace("2022_", ""), nome, em_data.identifique("Valor").getValor(), ""));
                            }
                            // fez += atividades;


                        }

                    }


                }
            }
        }


        //System.out.println("TEM AVALIACAO :: " + data.getTempo() + " INDO -->> " + fez.size());


        return ret;

    }

    public ArrayList<Aluno> getAtividadesDaSemana(ArrayList<Data> quais_datas, ArrayList<Data> atividade_datas) {

        ArrayList<Aluno> ret = new ArrayList<Aluno>();


        for (Data data : quais_datas) {

            boolean esta = false;
            for (Data qData : atividade_datas) {
                if (qData.getTempo().contentEquals(data.getTempo())) {
                    esta = true;
                    break;
                }
            }

            if (esta) {


                for (DKGObjeto proc : mRaizAvaliacaoContinuaFormativa.getObjetos()) {


                    for (DKGObjeto em_data : proc.unicoObjeto("Momentos").getObjetos()) {

                        if (em_data.identifique("Data").getValor().contentEquals(data.getTempo())) {

                            String id = proc.identifique("ID").getValor();
                            String nome = proc.identifique("Nome").getValor();

                            String atividade = em_data.identifique("Valor").getValor();

                            if (MetodoContinuo.isValorValido(atividade)) {
                                // System.out.println("\tPROC ID " + proc.listar());
                                ret.add(new Aluno(id, em_data.identifique("Data").getValor().replace("2022_", ""), nome, em_data.identifique("Valor").getValor(), ""));
                            }

                            // fez += atividades;


                        }

                    }


                }
            }
        }


        return ret;

    }


    public ArrayList<Aluno> getAlunosDaSemanaToda(ArrayList<Data> quais_datas, ArrayList<Data> atividade_datas) {

        ArrayList<Aluno> ret = new ArrayList<Aluno>();


        // System.out.println(fluxo_entrega.toString());

        //int fez = 0;

        ArrayList<String> fez = new ArrayList<String>();

        for (Data data : quais_datas) {

            boolean esta = false;
            for (Data qData : atividade_datas) {
                if (qData.getTempo().contentEquals(data.getTempo())) {
                    esta = true;
                    break;
                }
            }

            if (esta) {


                for (DKGObjeto aluno_passando : mRaizAvaliacaoContinuaFormativa.getObjetos()) {

                    String id = aluno_passando.identifique("ID").getValor();
                    String nome = aluno_passando.identifique("Nome").getValor();

                    for (DKGObjeto em_data : aluno_passando.unicoObjeto("Momentos").getObjetos()) {

                        if (em_data.identifique("Data").getValor().contentEquals(data.getTempo())) {


                            String atividades = em_data.identifique("Valor").getValor();

                            if (MetodoContinuo.isValorValido(atividades)) {
                                fez.add(String.valueOf(id));
                                //  System.out.println("\tPROC ID " + proc.listar());
                                ret.add(new Aluno(id, Data.toData(em_data.identifique("Data").getValor()).getFluxoSemAno(), nome, em_data.identifique("Valor").getValor(), ""));
                            }

                            // fez += atividades;


                        }

                    }


                }
            }
        }


        //System.out.println("TEM AVALIACAO :: " + data.getTempo() + " INDO -->> " + fez.size());


        return ret;

    }

    public ArrayList<Aluno> getAlunosDaRecuperacao() {

        ArrayList<Aluno> ret = new ArrayList<Aluno>();

        for (DKGObjeto aluno_passando : mRaizAvaliacaoContinuaFormativa.getObjetos()) {

            String id = aluno_passando.identifique("ID").getValor();
            String nome = aluno_passando.identifique("Nome").getValor();

            if (aluno_passando.identifique("RecuperacaoRealizada").isValor("SIM")) {

                String rec_valor = aluno_passando.identifique("RecuperacaoValor").getValor();
                String rec_data = aluno_passando.identifique("RecuperacaoData").getValor();

                ret.add(new Aluno(id, Data.toData(rec_data).getFluxoSemAno(),nome, rec_valor, ""));

            }

        }


        return ret;

    }



    public ArrayList<Aluno> getAlunosDeRecuperacaoAntiga(int eBimestreID) {

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));
        Atividade.organizarNota(FS.getArquivoLocal(Local.ARQUIVO_RECUPERACAO(eBimestreID)), "RECUPERACAO", mAlunos);

        ArrayList<Aluno> avaliados = new ArrayList<Aluno>();

        for (AlunoComNota eAluno : mAlunos) {

            String v = eAluno.getNota("RECUPERACAO");

            if (MetodoContinuo.isValorValido(v)) {

                System.out.println("DATA -->> " + eAluno.getDataRecuperacao());

                avaliados.add(new Aluno(eAluno.getID(), Data.toData(eAluno.getDataRecuperacao()).getFluxoSemAno(), eAluno.getNome(), v, ""));
            }

        }

        return avaliados;
    }

}
