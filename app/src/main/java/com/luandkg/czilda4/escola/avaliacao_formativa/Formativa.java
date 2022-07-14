package com.luandkg.czilda4.escola.avaliacao_formativa;

public class Formativa {

    // METODO AVALIATIVO FORMATIVO E CONTINUO
    // SECREATIA DE EDUCACAO DO DISTRITO FEDERAL - 2022


    public static final int FREQUENCIA_MINIMA_CIENCIAS = 10;
    public static final int FREQUENCIA_MINIMA_PD3 = 5;


    public static int continua(String mNota1, String mNota2, String mNota3, String mNota4, String mNota5, String mRecuperacao, boolean isPresente) {

        int i1 = valor(mNota1);
        int i2 = valor(mNota2);
        int i3 = valor(mNota3);
        int i4 = valor(mNota4);
        int i5 = valor(mNota5);

        int iRecuperacao = valor(mRecuperacao);

        int temExcelente = contarExcelentes(i1, i2, i3, i4, i5);

        if (temExcelente > 0) {

            i1 = reduzir(i1);
            i2 = reduzir(i2);
            i3 = reduzir(i3);
            i4 = reduzir(i4);
            i5 = reduzir(i5);

        }

        int somatorio = i1 + i2 + i3 + i4 + i5;


        if (isPresente) {
            if (somatorio < 3) {
                somatorio = 3;
            }
        }

        if (somatorio < 5 && iRecuperacao == 3) {

            if (somatorio >= 3) {
                somatorio = 5;
            } else {
                somatorio = 3;
            }

        } else if (somatorio < 5 && iRecuperacao == 2) {

            if (somatorio >= 3) {
                somatorio = 4;
            } else {
                somatorio = 2;
            }

        }

        somatorio = normalizar(somatorio);

        return somatorio;

    }


    public static int normalizar(int v) {

        if (v < 0) {
            v = 0;
        }

        if (v > 10) {
            v = 10;
        }

        return v;
    }

    public static int contarExcelentes(int i1, int i2, int i3, int i4, int i5) {

        int temExcelente = 0;

        if (isExcelente(i1)) {
            temExcelente += 1;
        }
        if (isExcelente(i2)) {
            temExcelente += 1;
        }
        if (isExcelente(i3)) {
            temExcelente += 1;
        }
        if (isExcelente(i4)) {
            temExcelente += 1;
        }
        if (isExcelente(i5)) {
            temExcelente += 1;
        }

        return temExcelente;
    }

    public static int PARTE_A(int nota) {

        int valor = 0;

        if (nota >= 5) {
            valor = 5;
        } else {
            valor = nota;
        }

        return valor;
    }

    public static int PARTE_B(int nota) {

        int valor = 0;

        if (nota >= 5) {
            valor = nota - 5;
        }

        return valor;
    }


    public static int valor(String nota) {
        int valor = 0;
        if (nota.length() > 0) {
            if (isNumero(nota)) {
                valor = Integer.parseInt(nota);
            }
        }

        return valor;
    }


    public static int reduzir(int v) {

        if (v > 0) {
            v = v - 1;
        }

        return v;

    }

    public static boolean isExcelente(int v) {
        boolean excelente = false;

        if (v == 3) {
            excelente = true;
        }
        return excelente;
    }


    public static boolean isNumero(String s) {
        boolean ret = false;

        if (s.contentEquals("0")) {
            ret = true;
        } else if (s.contentEquals("1")) {
            ret = true;
        } else if (s.contentEquals("2")) {
            ret = true;
        } else if (s.contentEquals("3")) {
            ret = true;
        } else if (s.contentEquals("4")) {
            ret = true;
        } else if (s.contentEquals("5")) {
            ret = true;
        } else if (s.contentEquals("6")) {
            ret = true;
        } else if (s.contentEquals("7")) {
            ret = true;
        } else if (s.contentEquals("8")) {
            ret = true;
        } else if (s.contentEquals("9")) {
            ret = true;
        }

        return ret;
    }

}
