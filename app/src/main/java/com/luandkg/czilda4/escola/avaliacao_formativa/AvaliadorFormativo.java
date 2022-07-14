package com.luandkg.czilda4.escola.avaliacao_formativa;

import java.util.ArrayList;

public class AvaliadorFormativo {


    public static int calcularFormativa(ArrayList<MomentoFormativo> mMomentos, String mRecuperacao, boolean isPresente) {

        int somatorio = 0;
        int iRecuperacao = 0;

        if (mRecuperacao.contentEquals("1")) {
            iRecuperacao = 1;
        } else if (mRecuperacao.contentEquals("2")) {
            iRecuperacao = 2;
        } else if (mRecuperacao.contentEquals("3")) {
            iRecuperacao = 3;
        }


        int SEMANA_01 = statusDaSemana(mMomentos, "2022_04_26", "2022_04_30"); // 1 PONTO
        int SEMANA_02 = statusDaSemana(mMomentos, "2022_05_01", "2022_05_07");

        somatorio += SEMANA_01;
        somatorio += SEMANA_02;

      //  somatorio = somatorio / (3 * 2);


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

        return somatorio;
    }

    public static int statusDaSemana(ArrayList<MomentoFormativo> mMomentos, String comecar, String terminar) {

        int zero = 0;
        int ruim = 0;
        int medio = 0;
        int excelente = 0;

        boolean comecou = false;
        boolean terminou = false;

        for (MomentoFormativo momento : mMomentos) {
            if (!comecou) {
                if (momento.getData().contentEquals(comecar)) {
                    comecou = true;

                    if (momento.isZero()) {
                        zero += 1;
                    } else if (momento.isMedio()) {
                        ruim += 1;
                    } else if (momento.isMedio()) {
                        medio += 1;
                    } else if (momento.isExcelente()) {
                        excelente += 1;
                    }

                }
            } else {
                if (!terminou) {
                    if (momento.isZero()) {
                        zero += 1;
                    } else if (momento.isMedio()) {
                        ruim += 1;
                    } else if (momento.isMedio()) {
                        medio += 1;
                    } else if (momento.isExcelente()) {
                        excelente += 1;
                    }
                }
                if (momento.getData().contentEquals(terminar)) {
                    terminou = true;
                }
            }

        }

        int nota = 0;

        if (ruim > 0) {
            nota = 1;
        }

        if (medio > 0) {
            nota = 2;
        }

        if (excelente > 0) {
            nota = 3;
        }

        return nota;
    }

}
