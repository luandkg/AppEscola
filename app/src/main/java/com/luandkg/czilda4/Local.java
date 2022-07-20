package com.luandkg.czilda4;

import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Calendario;

public class Local {

    public static final String LOCAL = "Escola";
    public static final String LOCAL_RELATORIOS = "Escola/Relatorios";
    public static final String LOCAL_NOTAS = "Escola/Notas";
    public static final String LOCAL_CACHE = "Escola/Cache";
    public static final String LOCAL_AVALIANDO = "Escola/Avaliando";
    public static final String LOCAL_REALIZAR_CHAMADA = "Escola/Chamadas";
    public static final String LOCAL_RECUPERACOES = "Escola/Recuperacoes";


    public final static String COLECAO_ESCOLA = "@ESCOLA::ESCOLA";

    public final static String COLECAO_ALUNOS = "@ESCOLA::ALUNOS";
    public final static String COLECAO_AVISOS = "@ESCOLA::AVISOS";
    public final static String COLECAO_TURMAS = "@ESCOLA::TURMAS";

    public final static String COLECAO_NOTAS = "@ESCOLA->CACHE::NOTAS";
    public final static String COLECAO_FLUXO = "@ESCOLA->CACHE::FLUXO";

    public final static String COLECAO_DESEMPENHOS = "@ESCOLA->CACHE::DESEMPENHOS";
    public final static String COLECAO_DESEMPENHANDO = "@ESCOLA->CACHE::DESEMPENHANDO";


    public final static String COLECAO_CHAMADAS = "@ESCOLA->CACHE::CHAMADAS";
    public final static String COLECAO_SEMANAS = "@ESCOLA->CACHE::SEMANAS";
    public final static String COLECAO_FREQUENCIAS = "@ESCOLA->CACHE::FREQUENCIAS";
    public final static String COLECAO_ESTATISTICAS = "@ESCOLA->CACHE::ESTATISTICAS";

    public final static String COLECAO_TUDO = "@ESCOLA->CACHE::TUDO";


    public static void organizarPastas() {

        FS.dirCriar(Local.LOCAL);
        FS.dirCriar(Local.LOCAL_REALIZAR_CHAMADA);
        FS.dirCriar(Local.LOCAL_RELATORIOS);
        FS.dirCriar(Local.LOCAL_NOTAS);
        FS.dirCriar(Local.LOCAL_AVALIANDO);
        FS.dirCriar(Local.LOCAL_RECUPERACOES);


    }


    public static String ARQUIVO_AVALIACAO_CONTINUA_HOJE() {
        return Local.LOCAL_AVALIANDO + "/" + Calendario.getADMComTracoInferior() + ".dkg";
    }

    public static String ARQUIVO_RECUPERACAO(int eBimestre) {
        return LOCAL_RECUPERACOES + "/RECUPERACAO_0" + eBimestre + ".dkg";
    }

    public static String CACHE_ARQUIVO(String eArquivo) {
        return Local.LOCAL_CACHE + "/" + eArquivo;
    }

}
