package com.luandkg.czilda4.escola.avaliacao;

import android.graphics.Color;

public class CoresDeAvaliacao {

    public static String RUIM = "#E53935";
    public static String BOM = "#FDD835";
    public static String EXCELENTE = "#4CAF50";

    public static String NAO_AVALIADO = "#78909C";


    public static String COR_PORCENTAGEM(float porcentagem){

        String cor = "";

        String E = "#FF3D00";
        String D = "#FF9100";
        String C = "#FFC400";
        String B = "#64DD17";
        String A = "#00B0FF";


        if (porcentagem >= 90) {
            cor=A;
        } else if (porcentagem >= 75 && porcentagem < 90) {
            cor=B;
        } else if (porcentagem >= 50 && porcentagem < 75) {
            cor=C;
        } else if (porcentagem >= 25 && porcentagem < 50) {
            cor=D;
        } else {
            cor=E;
        }

        return cor;
    }
}
