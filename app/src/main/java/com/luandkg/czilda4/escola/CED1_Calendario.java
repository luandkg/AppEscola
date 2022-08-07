package com.luandkg.czilda4.escola;

import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.CalendarioEscolar;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.libs.tempo.DiaSemanal;

import java.util.ArrayList;

public class CED1_Calendario extends CalendarioEscolar {

    public CED1_Calendario() {
        super();

        setAno(Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
        setRecesso(Calendario.filtrar(getAno(), Calendario.parse("12/7/2022"), Calendario.parse("29/7/2022")));

    }

    @Override
    public Bimestre PRIMEIRO_BIMESTRE() {

        ArrayList<Data> PRIMEIRO_BIMESTRE_DATAS = Calendario.filtrar(getAno(), Calendario.parse("14/02/2022"), Calendario.parse("29/04/2022"));

        ArrayList<SemanaContinua> PRIMEIRO_BIMESTRE_SEMANAS = new ArrayList<SemanaContinua>();

        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(1, "1", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("14/02/2022"), Calendario.parse("19/02/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(2, "2", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("20/02/2022"), Calendario.parse("26/02/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(3, "3", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("27/02/2022"), Calendario.parse("05/03/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(4, "4", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("06/03/2022"), Calendario.parse("12/03/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(5, "5", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("13/03/2022"), Calendario.parse("19/03/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(6, "6", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("20/03/2022"), Calendario.parse("26/03/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(7, "7", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("27/03/2022"), Calendario.parse("02/04/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(8, "8", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("03/04/2022"), Calendario.parse("09/04/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(9, "9", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("10/04/2022"), Calendario.parse("16/04/2022"))));
        PRIMEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(10, "10", Calendario.filtrar(PRIMEIRO_BIMESTRE_DATAS, Calendario.parse("17/04/2022"), Calendario.parse("24/04/2022"))));



        return new Bimestre(1, PRIMEIRO_BIMESTRE_DATAS, PRIMEIRO_BIMESTRE_SEMANAS);

    }

    @Override
    public Bimestre SEGUNDO_BIMESTRE() {

        ArrayList<Data> SEGUNDO_BIMESTRE_DATAS = Calendario.filtrar(getAno(), Calendario.parse("25/04/2022"), Calendario.parse("11/07/2022"));


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

    @Override
    public Bimestre TERCEIRO_BIMESTRE() {


        ArrayList<Data> TERCEIRO_BIMESTRE_DATAS = Calendario.filtrar(getAno(), Calendario.parse("29/07/2022"), Calendario.parse("07/10/2022"));


        ArrayList<SemanaContinua> TERCEIRO_BIMESTRE_SEMANAS = new ArrayList<SemanaContinua>();

        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(1, "1", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("29/07/2022"), Calendario.parse("06/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(2, "2", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("07/08/2022"), Calendario.parse("13/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(3, "3", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("14/08/2022"), Calendario.parse("20/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(4, "4", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("21/08/2022"), Calendario.parse("27/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(5, "5", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("28/08/2022"), Calendario.parse("03/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(6, "6", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("04/09/2022"), Calendario.parse("10/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(7, "7", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("11/09/2022"), Calendario.parse("17/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(8, "8", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("18/09/2022"), Calendario.parse("24/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(9, "9", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("25/09/2022"), Calendario.parse("01/10/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(10, "10", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("02/10/2022"), Calendario.parse("07/10/2022"))));

        return new Bimestre(3, TERCEIRO_BIMESTRE_DATAS, TERCEIRO_BIMESTRE_SEMANAS);


    }

    @Override
    public Bimestre QUARTO_BIMESTRE() {


        ArrayList<Data> QUARTO_BIMESTRE_DATAS = Calendario.filtrar(getAno(), Calendario.parse("10/10/2022"), Calendario.parse("22/10/2022"));


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
