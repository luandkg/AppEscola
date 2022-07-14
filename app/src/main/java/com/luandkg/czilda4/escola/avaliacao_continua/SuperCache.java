package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.tempo.Data;

import java.util.ArrayList;

public class SuperCache {

    private DKGObjeto eCONTAGEM;

    public SuperCache(String eArquivo) {

        DKG fluxo_entrega = new DKG();

        if (FS.arquivoExiste(eArquivo)) {
            fluxo_entrega.abrir(FS.getArquivoLocal(eArquivo));
        }

        eCONTAGEM = fluxo_entrega.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA");


    }


    public  int contagem(ArrayList<AlunoContinuo> alunos, String qual_turma) {

        int contando = 0;


        for (DKGObjeto aluno_objeto : eCONTAGEM.getObjetos()) {

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


                for (DKGObjeto proc : eCONTAGEM.getObjetos()) {


                    for (DKGObjeto em_data : proc.getObjetos()) {

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

    public  ArrayList<Aluno> getAtividadesDaSemana(ArrayList<Data> quais_datas , ArrayList<Data> atividade_datas) {

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


                for (DKGObjeto proc : eCONTAGEM.getObjetos()) {


                    for (DKGObjeto em_data : proc.getObjetos()) {

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


    public ArrayList<Aluno> getAlunosDaSemanaToda(ArrayList<Data> quais_datas , ArrayList<Data> atividade_datas) {

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


                for (DKGObjeto proc : eCONTAGEM.getObjetos()) {


                    for (DKGObjeto em_data : proc.getObjetos()) {

                        if (em_data.identifique("Data").getValor().contentEquals(data.getTempo())) {

                            String id = proc.identifique("ID").getValor();
                            String nome = proc.identifique("Nome").getValor();

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

    public ArrayList<Aluno> getAlunosDeRecuperacao(int eBimestreID){

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
