package com.luandkg.czilda4.escola;

import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.libs.tempo.DiaSemanal;

import java.util.ArrayList;

public class CED1_Calendario {

    public static ArrayList<Data> getAno() {
        ArrayList<Data> mDatas = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        return mDatas;
    }

    public static ArrayList<Data> getPrimeiro() {
        ArrayList<Data> mDatas = getAno();
        return Calendario.filtrar(mDatas, Calendario.parse("14/02/2022"), Calendario.parse("29/04/2022"));
    }

    public static ArrayList<Data> getSegundo() {
        ArrayList<Data> mDatas = getAno();
        return Calendario.filtrar(mDatas, Calendario.parse("02/05/2022"), Calendario.parse("11/07/2022"));
    }

    public static ArrayList<Data> getTerceiro() {
        ArrayList<Data> mDatas = getAno();
        return Calendario.filtrar(mDatas, Calendario.parse("29/07/2022"), Calendario.parse("07/10/2022"));
    }

    public static ArrayList<Data> getQuarto() {
        ArrayList<Data> mDatas = getAno();
        return Calendario.filtrar(mDatas, Calendario.parse("10/10/2022"), Calendario.parse("22/12/2022"));
    }

    public static ArrayList<Data> getRecesso() {
        ArrayList<Data> mDatas = getAno();
        return Calendario.filtrar(mDatas, Calendario.parse("12/7/2022"), Calendario.parse("29/7/2022"));
    }

    public static boolean isRecesso(Data eData) {
        boolean ret = false;

        for (Data dt : getRecesso()) {
            if (dt.isIgual(eData)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public static int recesso_passou(Data eData) {
        int ret = 0;

        for (Data dt : getRecesso()) {
            if (dt.isIgual(eData)) {
                break;
            }
            ret += 1;
        }

        return ret;
    }

    public static Bimestre SEGUNDO_BIMESTRE() {

        ArrayList<Data> TODAS_DATAS = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        ArrayList<Data> SEGUNDO_BIMESTRE_DATAS = Calendario.filtrar(TODAS_DATAS, Calendario.parse("25/04/2022"), Calendario.parse("11/07/2022"));


        ArrayList<SemanaContinua> SEGUNDO_BIMESTRE_SEMANAS = new ArrayList<SemanaContinua>();

        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(1, "1", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("25/04/2022"), Calendario.parse("30/04/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(2, "2", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("01/05/2022"), Calendario.parse("07/05/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(3, "3", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("08/05/2022"), Calendario.parse("14/05/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(4, "4", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("15/05/2022"), Calendario.parse("21/05/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(5, "5", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("22/05/2022"), Calendario.parse("28/05/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(6, "6", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("29/05/2022"), Calendario.parse("04/06/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(7, "7", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("05/06/2022"), Calendario.parse("11/06/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(8, "8", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("12/06/2022"), Calendario.parse("18/06/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(9, "9", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("19/06/2022"), Calendario.parse("25/06/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(10, "10", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("26/06/2022"), Calendario.parse("02/07/2022"))));
        SEGUNDO_BIMESTRE_SEMANAS.add(new SemanaContinua(11, "11", Calendario.filtrar(SEGUNDO_BIMESTRE_DATAS, Calendario.parse("03/07/2022"), Calendario.parse("07/07/2022"))));

        return new Bimestre(2, SEGUNDO_BIMESTRE_DATAS, SEGUNDO_BIMESTRE_SEMANAS);

    }

    public static Bimestre TERCEIRO_BIMESTRE() {


        ArrayList<Data> TODAS_DATAS = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        ArrayList<Data> TERCEIRO_BIMESTRE_DATAS = Calendario.filtrar(TODAS_DATAS, Calendario.parse("29/07/2022"), Calendario.parse("07/10/2022"));


        ArrayList<SemanaContinua> TERCEIRO_BIMESTRE_SEMANAS = new ArrayList<SemanaContinua>();

        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(1, "1", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("29/07/2022"), Calendario.parse("06/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(2, "2", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("07/08/2022"), Calendario.parse("13/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(3, "3", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("14/08/2022"), Calendario.parse("20/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(4, "4", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("21/08/2022"), Calendario.parse("27/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(5, "5", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("28/08/2022"), Calendario.parse("03/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(6, "6", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("04/08/2022"), Calendario.parse("10/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(7, "7", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("11/09/2022"), Calendario.parse("17/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(8, "8", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("18/09/2022"), Calendario.parse("24/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(9, "9", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("25/09/2022"), Calendario.parse("01/10/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(10, "10", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("02/10/2022"), Calendario.parse("07/10/2022"))));

        return new Bimestre(3, TERCEIRO_BIMESTRE_DATAS, TERCEIRO_BIMESTRE_SEMANAS);


    }

    public static Bimestre QUARTO_BIMESTRE() {


        ArrayList<Data> TODAS_DATAS = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        ArrayList<Data> QUARTO_BIMESTRE_DATAS = Calendario.filtrar(TODAS_DATAS, Calendario.parse("10/10/2022"), Calendario.parse("22/10/2022"));


        ArrayList<SemanaContinua> QUARTO_BIMESTRE_SEMANAS = new ArrayList<SemanaContinua>();

        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(1, "1", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("10/10/2022"), Calendario.parse("15/10/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(2, "2", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("16/10/2022"), Calendario.parse("22/10/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(3, "3", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("23/10/2022"), Calendario.parse("29/10/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(4, "4", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("30/10/2022"), Calendario.parse("05/11/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(5, "5", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("06/11/2022"), Calendario.parse("12/11/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(6, "6", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("13/11/2022"), Calendario.parse("19/11/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(7, "7", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("20/11/2022"), Calendario.parse("26/11/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(8, "8", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("27/11/2022"), Calendario.parse("03/12/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(9, "9", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("04/12/2022"), Calendario.parse("10/12/2022"))));
        QUARTO_BIMESTRE_SEMANAS.add(new SemanaContinua(10, "10", Calendario.filtrar(QUARTO_BIMESTRE_DATAS, Calendario.parse("11/12/2022"), Calendario.parse("17/12/2022"))));

        return new Bimestre(4, QUARTO_BIMESTRE_DATAS, QUARTO_BIMESTRE_SEMANAS);


    }

}
