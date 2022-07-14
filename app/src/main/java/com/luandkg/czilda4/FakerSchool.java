package com.luandkg.czilda4;

import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.chamadas.Chamada;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.tempo.Calendario;

import java.util.ArrayList;
import java.util.Random;

public class FakerSchool {


    public static void init() {

        DKG eDKGAlunos = new DKG();
        DKGObjeto eAlunos = eDKGAlunos.unicoObjeto("Alunos");

        criarAluno(eAlunos, "1001", "Luan Alves Freitas", "9A");
        criarAluno(eAlunos, "1002", "Ana Alves Freitas", "9A");
        criarAluno(eAlunos, "1003", "Carlos Alves Freitas", "9A");
        criarAluno(eAlunos, "1004", "Hudofrito de Ovos", "9A");
        criarAluno(eAlunos, "1005", "Rodolfinho Camargo", "9A");
        criarAluno(eAlunos, "1006", "Alexon de Souza", "9A");
        criarAluno(eAlunos, "1007", "Ricardinho Fuleiro", "9A");
        criarAlunoVivencia(eAlunos, "1009", "Pauleiro Korzz", "9A", "8C");
        criarAlunoVivencia(eAlunos, "1010", "Vulqueto Mion", "9A", "8A");
        criarAluno(eAlunos, "1011", "Flaukal Aargos", "9A");

        criarAluno(eAlunos, "2001", "Amanda Fuleiro", "9B");
        criarAluno(eAlunos, "2002", "Lucas Gritador", "9B");
        criarAluno(eAlunos, "2003", "Ricardinho Xato", "9B");
        criarAluno(eAlunos, "2004", "Grosso Marcos", "9B");
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

        criarAluno(eAlunos, "9001", "Vinn Samuca", "9G");
        criarAluno(eAlunos, "9002", "Xuzz Samuca", "9G");
        criarAluno(eAlunos, "9003", "Algom Lerdus", "9G");

        eDKGAlunos.salvar(FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAlunos));

        criarAtividade("2022_04_22");
        criarAtividade("2022_04_24");
        criarAtividade("2022_04_26");
        criarAtividade("2022_04_28");
        criarAtividade("2022_04_30");

        criarAtividade("2022_05_05");
        criarAtividade("2022_05_12");
        criarAtividade("2022_05_20");
        criarAtividade("2022_05_22");

        criarAtividade("2022_05_24");
        criarAtividade("2022_05_26");
        criarAtividade("2022_05_28");
        criarAtividade("2022_05_30");

        criarAtividade("2022_06_12");
        criarAtividade("2022_06_14");
        criarAtividade("2022_06_16");
        criarAtividade("2022_06_18");

        criarAtividade("2022_06_20");
        criarAtividade("2022_06_21");
        criarAtividade("2022_06_22");
        criarAtividade("2022_06_23");
        criarAtividade("2022_06_24");

        criarAtividade(Calendario.getADMComTracoInferior());

        criarRecuperacao("2", "2022_06_25");

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

    public static void criarRecuperacao(String eBimestre, String eAtividade) {

        String AVALIAR = "RECUPERACAO";

        String mArquivoCorrente = "Escola/Notas/notas_RECUPERACAO_0" + eBimestre + ".dkg";

        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));

        Random eSorte = new Random();

        for (AlunoComNota aluno : mAlunos) {
            int v = eSorte.nextInt(100);

            aluno.setNota(AVALIAR, "0", Calendario.getADMComBarras());

            if (v >= 75) {
                aluno.setNota(AVALIAR, "1", Calendario.getADMComBarras());
            }

            if (v >= 85) {
                aluno.setNota(AVALIAR, "2", Calendario.getADMComBarras());
            }

            if (v >= 95) {
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

}
