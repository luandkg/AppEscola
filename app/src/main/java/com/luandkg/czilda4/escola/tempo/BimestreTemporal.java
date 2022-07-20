package com.luandkg.czilda4.escola.tempo;

import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;

public class BimestreTemporal {


    public static boolean temBimestre(String hoje, CED1_Calendario calendario) {
        boolean ret = false;

        System.out.println (" Primeiro :: " + CED1_Calendario.getPrimeiro().size());

        if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getPrimeiro())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getSegundo())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getTerceiro())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getQuarto())) {
            ret = true;
        }

        return ret;
    }

    public static String getBimestreNome(String hoje ) {
        String ret = "0";

        if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getPrimeiro())) {
            ret = "1";
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getSegundo())) {
            ret = "2";
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getTerceiro())) {
            ret = "3";
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getQuarto())) {
            ret = "4";
        }

        return ret;
    }

    public static ArrayList<Data> getBimestre(String hoje ) {
        ArrayList<Data> ret = new ArrayList<Data>();

        if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getPrimeiro())) {
            ret = CED1_Calendario.getPrimeiro();
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getSegundo())) {
            ret = CED1_Calendario.getSegundo();
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getTerceiro())) {
            ret = CED1_Calendario.getTerceiro();
        } else if (BimestreTemporal.estouNesse(hoje, CED1_Calendario.getQuarto())) {
            ret = CED1_Calendario.getQuarto();
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
