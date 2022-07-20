package com.luandkg.czilda4.libs.profile;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.Texto;
import com.luandkg.czilda4.libs.tempo.Calendario;

import java.util.ArrayList;

public class KhronosDebug {

    private static ArrayList<String> KHRONOS_DEBUGS = new ArrayList<String>();

    public static void DEBUG(String texto) {
        System.out.println(texto);
        KHRONOS_DEBUGS.add(texto);
    }

    public static void TIME_DEBUG(String texto) {

        String s_completo = Calendario.getTempoCompleto() + " -->> " + texto;

        System.out.println(s_completo);
        KHRONOS_DEBUGS.add(s_completo);
    }


    public static void LIMPAR() {
        KHRONOS_DEBUGS.clear();
    }

    public static void SALVAR() {
        String documento = "";

        for (String linha : KHRONOS_DEBUGS) {
            documento += linha + "\n";
        }

        Texto.escrever(FS.getArquivoLocal(Local.CACHE_ARQUIVO("Debug.txt")), documento);
    }

}
