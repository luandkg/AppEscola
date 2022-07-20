package com.luandkg.czilda4.libs.profile;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.libs.tempo.Calendario;

import java.util.ArrayList;

public class KhronosProfiler {

    private static DKG arquivando = new DKG();
    private static boolean is_raiz = true;

    private static ArrayList<DKGObjeto> STACKS = new ArrayList<DKGObjeto>();
    private static DKGObjeto CORRENTE = null;

    public static void entrar() {
        is_raiz = false;
        STACKS.add(CORRENTE);
    }

    public static void sair() {

        if (STACKS.size() > 0) {
            STACKS.remove(STACKS.get(STACKS.size() - 1));
        }

        if (STACKS.size() == 0) {
            is_raiz = true;
        } else {
            is_raiz = false;
        }


    }

    public static DKGObjeto GET() {
        if (is_raiz) {
            return arquivando.unicoObjeto("KHRONOS");
        } else {
            return STACKS.get(STACKS.size() - 1);
        }
    }


    public static ProfileStamp profile(String funcao_nome, String execucao) {

        DKGObjeto objeto = GET().criarObjeto("Funcao");
        objeto.identifique("Funcao", funcao_nome);
        objeto.identifique("Khronos", System.nanoTime());
        objeto.identifique("Execucao", execucao);

        objeto.identifique("TCIniciar", Calendario.getTempoCompleto());
        objeto.identifique("Iniciar", System.nanoTime());


        CORRENTE = objeto;

        return new ProfileStamp(objeto);
    }

    public static ProfileStamp profile_started(String funcao_nome) {
        return profile(funcao_nome, "FUNCTION");
    }


    public static void salvar(String eArquivo) {
        arquivando.salvar(eArquivo);
    }

}
