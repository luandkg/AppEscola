package com.luandkg.czilda4.escola.tempo;

import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;

public class Semanador {

    public static boolean isSemanaValida(String eData, ArrayList<SemanaContinua> semanas) {

        boolean esta_no_bimestre = false;
        for (SemanaContinua eSemana : semanas) {
            if (eSemana.temData(eData)) {
                esta_no_bimestre = true;
                break;
            }
        }

        return esta_no_bimestre;
    }

    public static int getSemanaID(String eData, ArrayList<SemanaContinua> semanas) {

        int semana_atual = 0;

        for (SemanaContinua eSemana : semanas) {
            if (eSemana.temData(eData)) {
                break;
            }
            semana_atual += 1;
        }

        return semana_atual;
    }

    public static String getSemanaDataCorrente(String eData, ArrayList<SemanaContinua> semanas) {

        String semana_atual_data = "";

        for (SemanaContinua eSemana : semanas) {
            if (eSemana.temData(eData)) {
                semana_atual_data = eSemana.getUltimaData();
                break;
            }
        }

        return semana_atual_data;
    }

    public static String getSemanaDataAnterior(String eData, ArrayList<SemanaContinua> semanas) {

        String semana_anterior_data = "";

        for (SemanaContinua eSemana : semanas) {
            if (eSemana.temData(eData)) {
                break;
            } else {
                semana_anterior_data = eSemana.getUltimaData();
            }
        }

        return semana_anterior_data;
    }

    public static String getSemanaNome(String eData, ArrayList<SemanaContinua> semanas) {

        eData = Data.organizarData(eData);

        String ret = "";
        //System.out.println("Procurando :: " + eData);


        for (SemanaContinua semana : semanas) {
            if (semana.temData(eData)) {
                ret = semana.getNome();
                break;
            }
        }

        //  System.out.println("Encontrado :: " + eData + " -->> " + ret);

        return ret;
    }

}
