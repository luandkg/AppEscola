package com.luandkg.czilda4.escola.avaliacao_continua;

public class NotaString {

    public static String FINAL(double nota) {

        String sValor = String.valueOf(nota);
        if (sValor.length() >= 3) {
            sValor = sValor.substring(0, 3);
        }

        if (sValor.endsWith(".")) {
            sValor += ".0";
        }


        if (sValor.contains("..")) {
            sValor = sValor.replace("..", ".");
        }

        return sValor;
    }

    public static String PARTE_A(double nota) {

        double valor = 0;

        if (nota >= 5) {
            valor = 5;
        } else {
            valor = nota;
        }

        String sValor = String.valueOf(valor);
        if (sValor.length() >= 3) {
            sValor = sValor.substring(0, 3);
        }

        if (sValor.endsWith(".")) {
            sValor += ".0";
        }

        return sValor;
    }

    public static String PARTE_B(double nota) {

        double valor = 0;

        if (nota >= 5) {
            valor = nota - 5;
        }

        String sValor = String.valueOf(valor);
        if (sValor.length() >= 3) {
            sValor = sValor.substring(0, 3);
        }

        if (sValor.endsWith(".")) {
            sValor += ".0";
        }

        return sValor;
    }

}
