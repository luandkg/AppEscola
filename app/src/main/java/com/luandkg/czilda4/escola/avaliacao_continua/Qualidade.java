package com.luandkg.czilda4.escola.avaliacao_continua;

public class Qualidade {

    public static String getExpressaoFeminina(int v) {
        String ret = "";
        if (v == 2) {
            ret = "Excelente !";
        } else if (v == 1) {
            ret = "Muito Boa !";
        } else {
            ret = "Precisa Melhorar !";
        }
        return ret;
    }

    public static String getExpressaoMasculina(int v) {
        String ret = "";
        if (v == 2) {
            ret = "Excelente !";
        } else if (v == 1) {
            ret = "Muito Bom !";
        } else {
            ret = "Precisa Melhorar !";
        }
        return ret;
    }

}
