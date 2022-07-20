package com.luandkg.czilda4.utils;

import com.luandkg.czilda4.Local;

public class Redizz {

    public static void guardar(String eNome, String eValor) {
        RedizzCache.guardar(FS.getArquivoLocal(Local.CACHE_ARQUIVO("redizz.dkg")), eNome, eValor);
    }

    public static String obter(String eNome) {
        return RedizzCache.obter(FS.getArquivoLocal(Local.CACHE_ARQUIVO("redizz.dkg")), eNome);
    }


}
