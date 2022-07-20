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


    public final static String ArquivoAvisos = "avisos.dkg";
    public final static String ArquivoAlunos = "alunos.dkg";
    public final static String ArquivoTurmas = "turmas.dkg";
    public final static String ArquivoAtualizacoes = "escola.dkg";


    public final static String COLECAO_ALUNOS = "@ESCOLA::ALUNOS";


    public static final String ARQUIVO_CHAMADAS = "chamadas.dkg";

    public static final String ARQUIVO_AVISOS = "Escola/avisos.dkg";

    public static final String ARQUIVO_TUDO = "tudo.dkg";
    public static final String ARQUIVO_DESEMPENHO = "desempenho.dkg";

    public static final String ARQUIVO_NOTAS = "notas.dkg";
    public static final String ARQUIVO_DESEMPENHO_NOTAS = "desempenho_notas.dkg";

    public static final String ARQUIVO_FLUXO = "fluxo.dkg";

    public static final String ARQUIVO_SEMANAS = "semanas.dkg";
    public final static String ARQUIVO_CACHE_FREQUENCIA = "Escola/Cache/frequencia.dkg";
    public final static String ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS = "Escola/Cache/frequencia_estatisticas.dkg";

    public static void organizarPastas() {

        FS.dirCriar(Local.LOCAL);
        FS.dirCriar(Local.LOCAL_REALIZAR_CHAMADA);
        FS.dirCriar(Local.LOCAL_RELATORIOS);
        FS.dirCriar(Local.LOCAL_NOTAS);
        FS.dirCriar(Local.LOCAL_AVALIANDO);

    }


    public static String ARQUIVO_AVALIACAO_CONTINUA_HOJE() {
        return Local.LOCAL_AVALIANDO + "/" + Calendario.getADMComTracoInferior() + ".dkg";
    }

    public static String ARQUIVO_RECUPERACAO(int eBimestre) {
        return "Escola/Notas/notas_RECUPERACAO_0" + eBimestre + ".dkg";
    }

    public static String CACHE_ARQUIVO(String eArquivo) {
        return Local.LOCAL_CACHE + "/" + eArquivo;
    }

}
