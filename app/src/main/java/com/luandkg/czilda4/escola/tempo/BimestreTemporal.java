package com.luandkg.czilda4.escola.tempo;

import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;

public class BimestreTemporal {


    public static boolean temBimestre(String hoje, CalendarioEscolar calendario) {
        boolean ret = false;

        System.out.println (" Primeiro :: " + calendario.PRIMEIRO_BIMESTRE().getDatas().size());

        if (BimestreTemporal.estouNesse(hoje, calendario.PRIMEIRO_BIMESTRE().getDatas())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, calendario.SEGUNDO_BIMESTRE().getDatas())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, calendario.TERCEIRO_BIMESTRE().getDatas())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, calendario.QUARTO_BIMESTRE().getDatas())) {
            ret = true;
        }

        return ret;
    }

    public static String getBimestreNome(String hoje ,CalendarioEscolar calendario) {
        String ret = "0";

        if (BimestreTemporal.estouNesse(hoje, calendario.PRIMEIRO_BIMESTRE().getDatas())) {
            ret = "1";
        } else if (BimestreTemporal.estouNesse(hoje, calendario.SEGUNDO_BIMESTRE().getDatas())) {
            ret = "2";
        } else if (BimestreTemporal.estouNesse(hoje, calendario.TERCEIRO_BIMESTRE().getDatas())) {
            ret = "3";
        } else if (BimestreTemporal.estouNesse(hoje, calendario.QUARTO_BIMESTRE().getDatas())) {
            ret = "4";
        }

        return ret;
    }

    public static ArrayList<Data> getBimestre(String hoje,CalendarioEscolar calendario ) {
        ArrayList<Data> ret = new ArrayList<Data>();

        if (BimestreTemporal.estouNesse(hoje, calendario.PRIMEIRO_BIMESTRE().getDatas())) {
            ret = calendario.PRIMEIRO_BIMESTRE().getDatas();
        } else if (BimestreTemporal.estouNesse(hoje, calendario.SEGUNDO_BIMESTRE().getDatas())) {
            ret = calendario.SEGUNDO_BIMESTRE().getDatas();
        } else if (BimestreTemporal.estouNesse(hoje, calendario.TERCEIRO_BIMESTRE().getDatas())) {
            ret = calendario.TERCEIRO_BIMESTRE().getDatas();
        } else if (BimestreTemporal.estouNesse(hoje, calendario.QUARTO_BIMESTRE().getDatas())) {
            ret = calendario.QUARTO_BIMESTRE().getDatas();
        }

        return ret;
    }

    public static boolean estouNesse(String hoje, ArrayList<Data> datas) {
        boolean r = false;
        for (Data data : datas) {
        //    System.out.println ("Comparando :: " + hoje + " -->> " + data.getTempo());

            if (data.getTempo().contentEquals(hoje)) {
                r = true;
                break;
            }
        }
        return r;
    }

    public static int getID(String hoje, ArrayList<Data> datas) {
        int v = 0;
        for (Data data : datas) {
            if (data.getTempo().contentEquals(hoje)) {
                break;
            }
            v += 1;
        }
        return v;
    }


    public static boolean isDataValida(int ePosicao, ArrayList<Data> datas) {
        boolean resultado = false;

        if (ePosicao >= 0 && ePosicao < datas.size()) {
            resultado = true;
        }

        return resultado;
    }

    public static int getDiasParaAcabar(String data_corrente, ArrayList<Data> datas) {

        int v = getID(data_corrente, datas);

        int acabar = datas.size() - v;

        return acabar;
    }

    public static int getPorcentagem(String data_corrente, ArrayList<Data> datas) {

        int v = getID(data_corrente, datas);

        double prop = (double) v / (double) datas.size();
        int real = (int) (prop * 100.0F);

        return real;
    }

}
