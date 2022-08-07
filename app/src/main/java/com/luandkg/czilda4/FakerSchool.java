package com.luandkg.czilda4;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.chamadas.Chamada;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Calendario;

import java.util.ArrayList;
import java.util.Random;

public class FakerSchool {


    public static void init() {

        DKG eDKGAlunos = new DKG();
        DKGObjeto eAlunos = eDKGAlunos.unicoObjeto("Alunos");

        criarAluno(eAlunos, "1001", "Eduardo Xiz Connor", "9A");
        criarAluno(eAlunos, "1002", "Sarah Connor Lincor", "9A");
        criarAluno(eAlunos, "1003", "Ruevaldo Sariel Abandonado", "9A");
        criarAluno(eAlunos, "1004", "Hudofrito de Ovos", "9A");
        criarAluno(eAlunos, "1005", "Rodolfinho Camargo", "9A");
        criarAluno(eAlunos, "1006", "Alexon de Souza", "9A");
        criarAluno(eAlunos, "1007", "Ricardinho Fuleiro", "9A");
        criarAlunoVivencia(eAlunos, "1009", "Pauleiro Korzz", "9A", "8C");
        criarAlunoVivencia(eAlunos, "1010", "Vulqueto Mion", "9A", "8A");
        criarAluno(eAlunos, "1011", "Flakkal Aargos", "9A");

        criarAluno(eAlunos, "2001", "Amanda Fuleiro", "9B");
        criarAluno(eAlunos, "2002", "Lucas Gritador", "9B");
        criarAluno(eAlunos, "2003", "Ricardinho Xato", "9B");
        criarAluno(eAlunos, "2004", "Gordinho Marcos", "9B");
        criarAlunoVivencia(eAlunos, "2005", "Marquinhos Shaun", "9B", "8E");
        criarAlunoVivencia(eAlunos, "2006", "Lukkinhas Monz", "9B", "8G");

        criarAluno(eAlunos, "3001", "Xaman Evange", "9C");
        criarAluno(eAlunos, "3002", "Brutao Xonnas", "9C");
        criarAluno(eAlunos, "3003", "Italo Samuca", "9C");
        criarAluno(eAlunos, "3004", "Marquinhos Vin", "9C");
        criarAluno(eAlunos, "3005", "Bruninho Ollarere", "9C");
        criarAluno(eAlunos, "3006", "Xandao Nacz", "9C");

        criarAluno(eAlunos, "4001", "Maikao Brunn", "9D");
        criarAluno(eAlunos, "4002", "Geonkz Forgz", "9D");
        criarAluno(eAlunos, "4003", "Zallor Aun", "9D");
        criarAluno(eAlunos, "4004", "Cabritinho Quin", "9D");
        criarAluno(eAlunos, "4005", "Bernardo Xorgs", "9D");

        criarAluno(eAlunos, "5001", "Pedrinho Briengo", "9E");
        criarAluno(eAlunos, "5002", "Alfredinho Caue", "9E");

        criarAluno(eAlunos, "9001", "Sabrina Samuca", "9G");
        criarAluno(eAlunos, "9002", "Wesley Samuca", "9G");
        criarAluno(eAlunos, "9003", "Alfredo Lerdus", "9G");

        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_ALUNOS, eDKGAlunos);
        //  eDKGAlunos.salvar(FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAlunos));

        criarAtividade("2022_07_22");
        criarAtividade("2022_07_24");
        criarAtividade("2022_07_26");
        criarAtividade("2022_07_28");
        criarAtividade("2022_07_30");

        criarAtividade("2022_08_05");
        criarAtividade("2022_08_12");
        criarAtividade("2022_08_20");
        criarAtividade("2022_08_22");

        criarAtividade("2022_08_24");
        criarAtividade("2022_08_26");
        criarAtividade("2022_08_28");
        criarAtividade("2022_08_30");

        criarAtividade("2022_09_03");
        criarAtividade("2022_09_05");
        criarAtividade("2022_09_08");
        criarAtividade("2022_09_10");

        criarAtividade("2022_09_12");
        criarAtividade("2022_09_14");
        criarAtividade("2022_09_16");
        criarAtividade("2022_09_18");

        criarAtividade("2022_09_20");
        criarAtividade("2022_09_21");
        criarAtividade("2022_09_22");
        criarAtividade("2022_09_23");
        criarAtividade("2022_09_24");

        criarAtividade("2022_09_30");
        criarAtividade("2022_09_03");
        criarAtividade("2022_09_08");
        criarAtividade("2022_09_10");

        criarAtividade(Calendario.getADMComTracoInferior());

        criarRecuperacao(3, "2022_08_22", "2022_08_24", "2022_08_26");

    }

    public static void init_segundo() {

        DKG eDKGAlunos = new DKG();
        DKGObjeto eAlunos = eDKGAlunos.unicoObjeto("Alunos");

        criarAluno(eAlunos, "1001", "Eduardo Xiz Connor", "9A");
        criarAluno(eAlunos, "1002", "Sarah Connor Lincor", "9A");
        criarAluno(eAlunos, "1003", "Ruevaldo Sariel Abandonado", "9A");
        criarAluno(eAlunos, "1004", "Hudofrito de Ovos", "9A");
        criarAluno(eAlunos, "1005", "Rodolfinho Camargo", "9A");
        criarAluno(eAlunos, "1006", "Alexon de Souza", "9A");
        criarAluno(eAlunos, "1007", "Ricardinho Fuleiro", "9A");
        criarAlunoVivencia(eAlunos, "1009", "Pauleiro Korzz", "9A", "8C");
        criarAlunoVivencia(eAlunos, "1010", "Vulqueto Mion", "9A", "8A");
        criarAluno(eAlunos, "1011", "Flakkal Aargos", "9A");

        criarAluno(eAlunos, "2001", "Amanda Fuleiro", "9B");
        criarAluno(eAlunos, "2002", "Lucas Gritador", "9B");
        criarAluno(eAlunos, "2003", "Ricardinho Xato", "9B");
        criarAluno(eAlunos, "2004", "Gordinho Marcos", "9B");
        criarAlunoVivencia(eAlunos, "2005", "Marquinhos Shaun", "9B", "8E");
        criarAlunoVivencia(eAlunos, "2006", "Lukkinhas Monz", "9B", "8G");

        criarAluno(eAlunos, "3001", "Xaman Evange", "9C");
        criarAluno(eAlunos, "3002", "Brutao Xonnas", "9C");
        criarAluno(eAlunos, "3003", "Italo Samuca", "9C");
        criarAluno(eAlunos, "3004", "Marquinhos Vin", "9C");
        criarAluno(eAlunos, "3005", "Bruninho Ollarere", "9C");
        criarAluno(eAlunos, "3006", "Xandao Nacz", "9C");

        criarAluno(eAlunos, "4001", "Maikao Brunn", "9D");
        criarAluno(eAlunos, "4002", "Geonkz Forgz", "9D");
        criarAluno(eAlunos, "4003", "Zallor Aun", "9D");
        criarAluno(eAlunos, "4004", "Cabritinho Quin", "9D");
        criarAluno(eAlunos, "4005", "Bernardo Xorgs", "9D");

        criarAluno(eAlunos, "5001", "Pedrinho Briengo", "9E");
        criarAluno(eAlunos, "5002", "Alfredinho Caue", "9E");

        criarAluno(eAlunos, "9001", "Sabrina Samuca", "9G");
        criarAluno(eAlunos, "9002", "Wesley Samuca", "9G");
        criarAluno(eAlunos, "9003", "Alfredo Lerdus", "9G");

        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_ALUNOS, eDKGAlunos);
        //  eDKGAlunos.salvar(FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAlunos));

        criarAtividade("2022_05_12");
        criarAtividade("2022_05_14");
        criarAtividade("2022_05_16");
        criarAtividade("2022_05_18");
        criarAtividade("2022_05_20");

        criarAtividade("2022_05_22");
        criarAtividade("2022_05_24");
        criarAtividade("2022_05_26");
        criarAtividade("2022_05_28");
        criarAtividade("2022_05_30");

        criarAtividade("2022_06_05");
        criarAtividade("2022_06_12");
        criarAtividade("2022_06_20");
        criarAtividade("2022_06_22");

        criarAtividade("2022_06_24");
        criarAtividade("2022_06_26");
        criarAtividade("2022_06_28");
        criarAtividade("2022_06_30");

        criarAtividade("2022_07_03");
        criarAtividade("2022_07_05");
        criarAtividade("2022_07_08");
        criarAtividade("2022_07_10");


        criarAtividade(Calendario.getADMComTracoInferior());

        criarRecuperacao(2, "2022_06_22", "2022_06_24", "2022_07_02");

    }


    public static void criarAluno(DKGObjeto eAlunos, String eID, String eNome, String eTurma) {

        DKGObjeto al = eAlunos.criarObjeto("Turma");
        al.identifique("ID").setValor(eID);
        al.identifique("Nome").setValor(eNome);
        al.identifique("Turma").setValor(eTurma);
        al.identifique("Visibilidade").setValor("SIM");


    }

    public static void criarAlunoVivencia(DKGObjeto eAlunos, String eID, String eNome, String eTurma, String eOrigem) {

        DKGObjeto al = eAlunos.criarObjeto("Turma");
        al.identifique("ID").setValor(eID);
        al.identifique("Nome").setValor(eNome);
        al.identifique("Turma").setValor(eTurma);
        al.identifique("Visibilidade").setValor("SIM");
        al.identifique("Vivencia").setValor("SIM");
        al.identifique("Origem").setValor(eOrigem);


    }

    public static void criarAtividade(String eAtividade) {

        String AVALIAR = "AVALIAR";

        String mArquivoCorrente = Local.LOCAL_AVALIANDO + "/" + eAtividade + ".dkg";

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));

        Random eSorte = new Random();

        for (AlunoComNota aluno : mAlunos) {
            int v = eSorte.nextInt(100);

            aluno.setNota(AVALIAR, "0", Calendario.getADMComBarras());

            if (v >= 50) {
                aluno.setNota(AVALIAR, "1", Calendario.getADMComBarras());
            }

            if (v >= 70) {
                aluno.setNota(AVALIAR, "2", Calendario.getADMComBarras());
            }

            if (v >= 90) {
                aluno.setNota(AVALIAR, "3", Calendario.getADMComBarras());
            }
        }


        Atividade.salvarNota(AVALIAR, mAlunos, FS.getArquivoLocal(mArquivoCorrente));

        ArrayList<Aluno> chamada_alunos = Escola.getAlunosVisiveisEOrdenadosDaEscola();

        for (Aluno aluno : chamada_alunos) {
            int v = eSorte.nextInt(100);
            if (v >= 50) {
                aluno.setStatus("PRESENTE");
            } else {
                aluno.setStatus("AUSENTE");
            }
        }


        Chamada.salvarChamadaNoDia(eAtividade, chamada_alunos);

    }

    public static void criarRecuperacao(int eBimestre, String eData1, String eData2, String eData3) {

        String AVALIAR = "RECUPERACAO";

        String mArquivoCorrente = Local.ARQUIVO_RECUPERACAO(eBimestre);

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));

        Random eSorte = new Random();

        for (AlunoComNota aluno : mAlunos) {
            int v = eSorte.nextInt(100);
            int vData = eSorte.nextInt(100);

            String em_data = eData1;

            if (vData > 50) {
                em_data = eData2;
            } else if (vData > 75) {
                em_data = eData3;
            }

            v=95;


            aluno.setNota(AVALIAR, "0", em_data);

            if (v >= 75) {
                aluno.setNota(AVALIAR, "1", em_data);
            }

            if (v >= 85) {
                aluno.setNota(AVALIAR, "2", em_data);
            }

            if (v >= 95) {
                aluno.setNota(AVALIAR, "3", em_data);
            }
        }


        Atividade.salvarNota(AVALIAR, mAlunos, FS.getArquivoLocal(mArquivoCorrente));

        ArrayList<Aluno> chamada_alunos = Escola.getAlunosVisiveisEOrdenadosDaEscola();

        for (Aluno aluno : chamada_alunos) {
            int v = eSorte.nextInt(100);
            if (v >= 50) {
                aluno.setStatus("PRESENTE");
            } else {
                aluno.setStatus("AUSENTE");
            }
        }


        Chamada.salvarChamadaNoDia(eData1, chamada_alunos);
        Chamada.salvarChamadaNoDia(eData2, chamada_alunos);
        Chamada.salvarChamadaNoDia(eData3, chamada_alunos);


        System.out.println("===================================================== GUARDAR RECUOPERACAO");

        String mArquivoRecuperacao = Local.ARQUIVO_RECUPERACAO(2);
        final String RECUPERACAO = "RECUPERACAO";

        ArrayList<AlunoComNota> rAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));


        Atividade.organizarNota(FS.getArquivoLocal(mArquivoRecuperacao), RECUPERACAO, rAlunos);


    }

}
