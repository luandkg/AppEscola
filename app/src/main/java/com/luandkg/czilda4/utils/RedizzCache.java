package com.luandkg.czilda4.utils;

import com.luandkg.czilda4.dkg.DKG;

public class RedizzCache {

    public static void guardar(String eArquivo, String eNome, String eValor) {

        DKG documento = DKG.GET(eArquivo);

        documento.unicoObjeto("REDIZZ").identifique(eNome).setValor(eValor);

        documento.salvar(eArquivo);
    }


    public static String obter(String eArquivo, String eNome) {

        DKG documento = DKG.GET(eArquivo);

        return documento.unicoObjeto("REDIZZ").identifique(eNome).getValor();

    }


    // ESPECIFICOS

    public static void guardar(String eArquivo, String eNome, int eValor) {

        DKG documento = DKG.GET(eArquivo);

        documento.unicoObjeto("REDIZZ").identifique(eNome).setValor(LLCripto.to(String.valueOf(eValor)));

        documento.salvar(eArquivo);
    }

    public static void guardar(String eArquivo, String eNome, boolean eValor) {

        DKG documento = DKG.GET(eArquivo);

        documento.unicoObjeto("REDIZZ").identifique(eNome).setValor(LLCripto.to(String.valueOf(eValor)));

        documento.salvar(eArquivo);
    }

    public static void guardar(String eArquivo, String eNome, double eValor) {

        DKG documento = DKG.GET(eArquivo);

        documento.unicoObjeto("REDIZZ").identifique(eNome).setValor(LLCripto.to(String.valueOf(eValor)));

        documento.salvar(eArquivo);
    }

    public static void guardar(String eArquivo, String eNome, float eValor) {

        DKG documento = DKG.GET(eArquivo);

        documento.unicoObjeto("REDIZZ").identifique(eNome).setValor(LLCripto.to(String.valueOf(eValor)));

        documento.salvar(eArquivo);
    }

    public static int obter_int(String eArquivo, String eNome) {

        DKG documento = DKG.GET(eArquivo);

        String s = LLCripto.from(documento.unicoObjeto("REDIZZ").identifique(eNome).getValor());

        int ret = 0;
        if (s.length() > 0) {
            ret = Integer.parseInt(s);
        }


        return ret;

    }

    public static boolean obter_bool(String eArquivo, String eNome) {

        DKG documento = DKG.GET(eArquivo);

        String s = LLCripto.from(documento.unicoObjeto("REDIZZ").identifique(eNome).getValor());

        boolean ret = false;
        if (s.length() > 0) {
            ret = Boolean.parseBoolean(s);
        }


        return ret;

    }

    public static double obter_double(String eArquivo, String eNome) {

        DKG documento = DKG.GET(eArquivo);

        String s = LLCripto.from(documento.unicoObjeto("REDIZZ").identifique(eNome).getValor());

        double ret = 0.0;
        if (s.length() > 0) {
            ret = Double.parseDouble(s);
        }


        return ret;

    }


    public static float obter_float(String eArquivo, String eNome) {

        DKG documento = DKG.GET(eArquivo);

        String s = LLCripto.from(documento.unicoObjeto("REDIZZ").identifique(eNome).getValor());

        float ret = 0.0f;
        if (s.length() > 0) {
            ret = Float.parseFloat(s);
        }


        return ret;

    }
}
