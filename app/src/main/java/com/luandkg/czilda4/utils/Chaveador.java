package com.luandkg.czilda4.utils;

import java.util.Random;

public class Chaveador {

    public static String criarchave() {
        Random sorte = new Random();

        String chave = "";

        String ALFABETO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int p = 0; p < 5; p++) {
            chave += String.valueOf(ALFABETO.charAt(sorte.nextInt(ALFABETO.length())));
        }

        chave += ".";

        for (int p = 0; p < 8; p++) {
            chave += String.valueOf(ALFABETO.charAt(sorte.nextInt(ALFABETO.length())));
        }

        chave += ".";

        for (int p = 0; p < 3; p++) {
            chave += String.valueOf(ALFABETO.charAt(sorte.nextInt(ALFABETO.length())));
        }

        chave += "/";

        chave += String.valueOf(ALFABETO.charAt(getAlfa(chave)));
        chave += String.valueOf(ALFABETO.charAt(getBeta(chave)));
        chave += String.valueOf(ALFABETO.charAt(getGama(chave)));


        return chave;
    }

    public static int getAlfa(String valor) {

        int a = qualValor(String.valueOf(valor.charAt(0)));
        int b = qualValor(String.valueOf(valor.charAt(1)));
        int c = qualValor(String.valueOf(valor.charAt(2)));

        int d = qualValor(String.valueOf(valor.charAt(3)));

        return reduzir((a * b) + (c * d));
    }

    public static int getBeta(String valor) {

        int a = qualValor(String.valueOf(valor.charAt(5)));
        int b = qualValor(String.valueOf(valor.charAt(6)));
        int c = qualValor(String.valueOf(valor.charAt(7)));

        int d = qualValor(String.valueOf(valor.charAt(0)));

        return reduzir((a + b) + (c - d));
    }

    public static int getGama(String valor) {

        int a = qualValor(String.valueOf(valor.charAt(9)));
        int b = qualValor(String.valueOf(valor.charAt(2)));
        int c = qualValor(String.valueOf(valor.charAt(3)));

        int d = qualValor(String.valueOf(valor.charAt(6)));

        return reduzir((a * b) - (c * d));
    }

    public static int reduzir(int qual_valor) {
        String ALFABETO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int r = 0;

        if (qual_valor > 0) {
            r = qual_valor % ALFABETO.length();
        } else if (qual_valor < 0) {
            qual_valor = qual_valor * (-1);
            r = qual_valor % ALFABETO.length();
        }

        return r;
    }

    public static int qualValor(String letra) {

        String ALFABETO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int i = 0;
        int o = ALFABETO.length();


        while (i < o) {
            String proc = String.valueOf(ALFABETO.charAt(i));
            if (proc.contentEquals(letra)) {
                break;
            }
            i += 1;
        }

        return i;
    }
}
