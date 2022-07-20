package com.luandkg.czilda4.escola.atualizador;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.escola.avaliacao.AtividadeContador;
import com.luandkg.czilda4.utils.Armazem;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.utils.FS;

import java.util.ArrayList;

public class Atualizador {

    public static String ARQUIVO_ATUALIZACOES() {
        return FS.getArquivoLocal(Local.LOCAL_CACHE, Local.ArquivoAtualizacoes);
    }

    public static boolean ARQUIVO_ATUALIZACOES_EXISTE() {

        boolean existe = false;

        if (FS.arquivoExiste(Local.LOCAL_CACHE, Local.ArquivoAtualizacoes)) {
            existe = true;
        }

        return existe;
    }

    public static void avaliacao(String mTurma, int quantidade) {

        DKG on = new DKG();

        if (ARQUIVO_ATUALIZACOES_EXISTE()) {
            on.abrir(ARQUIVO_ATUALIZACOES());
        }

        DKGObjeto eAtualizacoes = on.unicoObjeto("On");
        DKGObjeto pAvaliador = eAtualizacoes.unicoObjeto("Avaliador");

        DKGObjeto eTurmaObjeto = pAvaliador.unicoObjeto(mTurma);

        Armazem armazenar = new Armazem("Escola");

        String ARQUIVO_NOTAS_01 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_01.dkg");
        String ARQUIVO_NOTAS_02 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_02.dkg");
        String ARQUIVO_NOTAS_03 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_03.dkg");
        String ARQUIVO_NOTAS_04 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_04.dkg");
        String ARQUIVO_NOTAS_05 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_05.dkg");

        String ARQUIVO_RECUPERACAO = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_RECUPERACAO.dkg");


        int q1 = AtividadeContador.quantosFizeram(FS.getArquivoLocal(ARQUIVO_NOTAS_01), "Nota01", mTurma);
        int q2 = AtividadeContador.quantosFizeram(FS.getArquivoLocal(ARQUIVO_NOTAS_02), "Nota02", mTurma);
        int q3 = AtividadeContador.quantosFizeram(FS.getArquivoLocal(ARQUIVO_NOTAS_03), "Nota03", mTurma);
        int q4 = AtividadeContador.quantosFizeram(FS.getArquivoLocal(ARQUIVO_NOTAS_04), "Nota04", mTurma);
        int q5 = AtividadeContador.quantosFizeram(FS.getArquivoLocal(ARQUIVO_NOTAS_05), "Nota05", mTurma);

        String evento = "";

        int atividade = 5;
        int contagem = q5;


        if (atividade == 5 && contagem == 0) {
            atividade = 4;
            contagem = q4;
        }

        if (atividade == 4 && contagem == 0) {
            atividade = 3;
            contagem = q3;
        }

        if (atividade == 3 && contagem == 0) {
            atividade = 2;
            contagem = q2;
        }

        if (atividade == 2 && contagem == 0) {
            atividade = 1;
            contagem = q1;
        }

        if (contagem == 0) {
            evento = "Nenhum aluno realizou a atividade " + atividade;
        } else if (contagem == 1) {
            evento = "Apenas 1 aluno realizou a atividade " + atividade + " !";
        } else {
            evento = contagem + " alunos realizaram a atividade " + atividade + " !";
        }


        int contador = eAtualizacoes.identifique("Contagem").getInteiro(0);
        contador += 1;
        eAtualizacoes.identifique("Contagem").setInteiro(contador);

        eTurmaObjeto.identifique("Evento").setValor(evento);
        eTurmaObjeto.identifique("Quantidade").setInteiro(quantidade);
        eTurmaObjeto.identifique("Data").setValor(Calendario.getADMComBarras());
        eTurmaObjeto.identifique("Hora").setValor(Calendario.getHora());
        eTurmaObjeto.identifique("Fluxo").setInteiro(contador);


        on.salvar(ARQUIVO_ATUALIZACOES());

    }

    public static void chamada(String mTurma, int presente, int ausente, int quantidade) {

        DKG eDKG = new DKG();

        if (ARQUIVO_ATUALIZACOES_EXISTE()) {
            eDKG.abrir(ARQUIVO_ATUALIZACOES());
        }

        DKGObjeto eAtualizacoes = eDKG.unicoObjeto("On");
        DKGObjeto eChamadas = eAtualizacoes.unicoObjeto("Chamadas");

        DKGObjeto eTurmaObjeto = eChamadas.unicoObjeto(mTurma);


        String evento = "";

        if (presente > 0) {

            if (presente == 0) {
                evento = "Todos os alunos faltaram !";
            } else if (presente == 1) {
                evento = "Apenas um aluno está presente !";
            } else {
                evento = presente + " alunos estão presentes !";
            }

        } else {

            if (ausente == 0) {
                evento = "Todos os alunos estão presentes !";
            } else if (ausente == 1) {
                evento = "Apenas um aluno faltou !";
            } else {
                evento = ausente + " alunos faltaram !";
            }

        }


        int contador = eAtualizacoes.identifique("Contagem").getInteiro(0);
        contador += 1;
        eAtualizacoes.identifique("Contagem").setInteiro(contador);

        eTurmaObjeto.identifique("Evento").setValor(evento);
        eTurmaObjeto.identifique("Quantidade").setInteiro(presente);
        eTurmaObjeto.identifique("Data").setValor(Calendario.getADMComBarras());
        eTurmaObjeto.identifique("Hora").setValor(Calendario.getHora());
        eTurmaObjeto.identifique("Fluxo").setInteiro(contador);


        eDKG.salvar(ARQUIVO_ATUALIZACOES());

    }

    public static ArrayList<Atualizacao> getAtualizacoes() {

        ArrayList<Atualizacao> mAtualizacoes = new ArrayList<Atualizacao>();


        DKG eOn = new DKG();
        eOn.abrir(ARQUIVO_ATUALIZACOES());

        DKGObjeto eOnAtualizacoes = eOn.unicoObjeto("On");
        DKGObjeto eOnChamadas = eOnAtualizacoes.unicoObjeto("Chamadas");
        DKGObjeto eOnAvaliador = eOnAtualizacoes.unicoObjeto("Avaliador");


        DKG eDKG = new DKG();


        if (FS.arquivoExiste(Local.LOCAL, Local.ArquivoTurmas)) {
            eDKG.abrir(FS.getArquivoLocal(Local.LOCAL, Local.ArquivoTurmas));


            DKGObjeto eTurmas = eDKG.unicoObjeto("Turmas");

            for (DKGObjeto eTurma : eTurmas.getObjetos()) {

                String eLetra = eTurma.identifique("Nome").getValor();

                DKGObjeto eTurmaObjeto = eOnChamadas.unicoObjeto(eLetra);

                String evento = eTurmaObjeto.identifique("Evento").getValor();
                int novidades = eTurmaObjeto.identifique("Quantidade").getInteiro(0);
                int fluxo = eTurmaObjeto.identifique("Fluxo").getInteiro(0);
                String data = eTurmaObjeto.identifique("Data").getValor();
                String hora = eTurmaObjeto.identifique("Hora").getValor();

                Atualizacao eAtualizacao = new Atualizacao(eLetra, "Chamada da Turma " + eLetra);
                eAtualizacao.setEvento(evento);
                eAtualizacao.setNovidades(novidades);
                eAtualizacao.setFluxo(fluxo);
                eAtualizacao.setData(data);
                eAtualizacao.setHora(hora);

                mAtualizacoes.add(eAtualizacao);

            }

            for (DKGObjeto eTurma : eTurmas.getObjetos()) {

                String eLetra = eTurma.identifique("Nome").getValor();

                DKGObjeto eTurmaObjeto = eOnAvaliador.unicoObjeto(eLetra);

                String evento = eTurmaObjeto.identifique("Evento").getValor();
                int novidades = eTurmaObjeto.identifique("Quantidade").getInteiro(0);
                int fluxo = eTurmaObjeto.identifique("Fluxo").getInteiro(0);
                String data = eTurmaObjeto.identifique("Data").getValor();
                String hora = eTurmaObjeto.identifique("Hora").getValor();

                Atualizacao eAtualizacao = new Atualizacao(eLetra, "Avaliação da Turma " + eLetra);
                eAtualizacao.setEvento(evento);
                eAtualizacao.setNovidades(novidades);
                eAtualizacao.setFluxo(fluxo);
                eAtualizacao.setData(data);
                eAtualizacao.setHora(hora);

                //  mAtualizacoes.add(eAtualizacao);

            }
        }


        ordenar(mAtualizacoes);

        return mAtualizacoes;
    }

    public static ArrayList<Atualizacao> getAtualizacoesAvaliacoes() {

        ArrayList<Atualizacao> mAtualizacoes = new ArrayList<Atualizacao>();


        DKG eOn = new DKG();
        eOn.abrir(ARQUIVO_ATUALIZACOES());

        DKGObjeto eOnAtualizacoes = eOn.unicoObjeto("On");
        DKGObjeto eOnChamadas = eOnAtualizacoes.unicoObjeto("Chamadas");
        DKGObjeto eOnAvaliador = eOnAtualizacoes.unicoObjeto("Avaliador");


        DKG eDKG = new DKG();


        if (FS.arquivoExiste(Local.LOCAL, Local.ArquivoTurmas)) {
            eDKG.abrir(FS.getArquivoLocal(Local.LOCAL, Local.ArquivoTurmas));


            DKGObjeto eTurmas = eDKG.unicoObjeto("Turmas");

            for (DKGObjeto eTurma : eTurmas.getObjetos()) {

                String eLetra = eTurma.identifique("Nome").getValor();

                DKGObjeto eTurmaObjeto = eOnChamadas.unicoObjeto(eLetra);

                String evento = eTurmaObjeto.identifique("Evento").getValor();
                int novidades = eTurmaObjeto.identifique("Quantidade").getInteiro(0);
                int fluxo = eTurmaObjeto.identifique("Fluxo").getInteiro(0);
                String data = eTurmaObjeto.identifique("Data").getValor();
                String hora = eTurmaObjeto.identifique("Hora").getValor();

                Atualizacao eAtualizacao = new Atualizacao(eLetra, "Chamada da Turma " + eLetra);
                eAtualizacao.setEvento(evento);
                eAtualizacao.setNovidades(novidades);
                eAtualizacao.setFluxo(fluxo);
                eAtualizacao.setData(data);
                eAtualizacao.setHora(hora);

                //   mAtualizacoes.add(eAtualizacao);

            }

            for (DKGObjeto eTurma : eTurmas.getObjetos()) {

                String eLetra = eTurma.identifique("Nome").getValor();

                DKGObjeto eTurmaObjeto = eOnAvaliador.unicoObjeto(eLetra);

                String evento = eTurmaObjeto.identifique("Evento").getValor();
                int novidades = eTurmaObjeto.identifique("Quantidade").getInteiro(0);
                int fluxo = eTurmaObjeto.identifique("Fluxo").getInteiro(0);
                String data = eTurmaObjeto.identifique("Data").getValor();
                String hora = eTurmaObjeto.identifique("Hora").getValor();

                Atualizacao eAtualizacao = new Atualizacao(eLetra, "Avaliação da Turma " + eLetra);
                eAtualizacao.setEvento(evento);
                eAtualizacao.setNovidades(novidades);
                eAtualizacao.setFluxo(fluxo);
                eAtualizacao.setData(data);
                eAtualizacao.setHora(hora);

                mAtualizacoes.add(eAtualizacao);

            }
        }

        return mAtualizacoes;
    }


    public static void ordenar(ArrayList<Atualizacao> arr) {
        Atualizacao temp;
        int n = arr.size();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {
                if (arr.get(j).getFluxo() < (arr.get(i).getFluxo())) {
                    temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
    }


    public static void mudarStatusAvaliacao(String mTurma, String evento) {

        DKG on = new DKG();

        if (ARQUIVO_ATUALIZACOES_EXISTE()) {
            on.abrir(ARQUIVO_ATUALIZACOES());
        }

        DKGObjeto eAtualizacoes = on.unicoObjeto("On");
        DKGObjeto pAvaliador = eAtualizacoes.unicoObjeto("Avaliador");

        DKGObjeto eTurmaObjeto = pAvaliador.unicoObjeto(mTurma);


        int contador = eAtualizacoes.identifique("Contagem").getInteiro(0) + 1;

        eAtualizacoes.identifique("Contagem").setInteiro(contador);
        eTurmaObjeto.identifique("Evento").setValor(evento);
        eTurmaObjeto.identifique("Data").setValor(Calendario.getADMComBarras());
        eTurmaObjeto.identifique("Hora").setValor(Calendario.getHora());
        eTurmaObjeto.identifique("Fluxo").setInteiro(contador);


        on.salvar(ARQUIVO_ATUALIZACOES());

    }

    public static void mudarQuantidadeAvaliacao(String mTurma, int quantidade) {

        DKG on = new DKG();

        if (ARQUIVO_ATUALIZACOES_EXISTE()) {
            on.abrir(ARQUIVO_ATUALIZACOES());
        }

        DKGObjeto eAtualizacoes = on.unicoObjeto("On");
        DKGObjeto pAvaliador = eAtualizacoes.unicoObjeto("Avaliador");

        DKGObjeto eTurmaObjeto = pAvaliador.unicoObjeto(mTurma);


        int contador = eAtualizacoes.identifique("Contagem").getInteiro(0) + 1;


        eAtualizacoes.identifique("Contagem").setInteiro(contador);

        eTurmaObjeto.identifique("Quantidade").setInteiro(quantidade);
        eTurmaObjeto.identifique("Data").setValor(Calendario.getADMComBarras());
        eTurmaObjeto.identifique("Hora").setValor(Calendario.getHora());
        eTurmaObjeto.identifique("Fluxo").setInteiro(contador);


        on.salvar(ARQUIVO_ATUALIZACOES());

    }


}
