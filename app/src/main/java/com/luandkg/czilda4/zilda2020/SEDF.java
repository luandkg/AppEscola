package com.luandkg.czilda4.zilda2020;

import java.util.ArrayList;

import com.luandkg.czilda4.utils.tempo.Calendario;
import com.luandkg.czilda4.utils.tempo.Data;
import com.luandkg.czilda4.utils.tempo.DiaSemanal;
import com.luandkg.czilda4.utils.tempo.HorarioTurma;


public class SEDF {


    public static ArrayList<HorarioTurma> getHorariosDasTurmas() {

        ArrayList<HorarioTurma> mHorarios = new ArrayList<HorarioTurma>();


        HorarioTurma eTurma_A = criarHorario2CienciasE1PD("A", DiaSemanal.Segunda, DiaSemanal.Sexta, DiaSemanal.Quarta);
        HorarioTurma eTurma_B = criarHorario2CienciasE1PD("B", DiaSemanal.Segunda, DiaSemanal.Terca, DiaSemanal.Sexta);
        HorarioTurma eTurma_C = criarHorario2CienciasE1PD("C", DiaSemanal.Quarta, DiaSemanal.Quinta, DiaSemanal.Terca);
        HorarioTurma eTurma_D = criarHorario2CienciasE1PD("D", DiaSemanal.Quinta, DiaSemanal.Sexta, DiaSemanal.Sexta);
        HorarioTurma eTurma_E = criarHorario2CienciasE1PD("E", DiaSemanal.Quarta, DiaSemanal.Quinta, DiaSemanal.Quarta);
        HorarioTurma eTurma_F = criarHorario2CienciasE1PD("F", DiaSemanal.Segunda, DiaSemanal.Terca, DiaSemanal.Terca);


        mHorarios.add(eTurma_A);
        mHorarios.add(eTurma_B);
        mHorarios.add(eTurma_C);
        mHorarios.add(eTurma_D);
        mHorarios.add(eTurma_E);
        mHorarios.add(eTurma_F);

        return mHorarios;
    }

    public static HorarioTurma criarHorario2CienciasE1PD(String eTurma, DiaSemanal cn1, DiaSemanal cn2, DiaSemanal ePD) {
        HorarioTurma eTurma_A = new HorarioTurma(eTurma, cn1, cn2);
        eTurma_A.setPD(ePD);
        return eTurma_A;
    }

    public static ArrayList<Data> get3Bimestre() {

        ArrayList<Data> mDatas = new ArrayList<Data>();

        inserirDatas(mDatas, 2021, 8, 16, 31, DiaSemanal.Segunda);
        inserirDatas(mDatas, 2021, 9, 1, 30, DiaSemanal.Quarta);
        inserirDatas(mDatas, 2021, 10, 1, 8, DiaSemanal.Sexta);

        return mDatas;
    }

    public static ArrayList<Data> get4Bimestre() {

        ArrayList<Data> mDatas = new ArrayList<Data>();

        inserirDatas(mDatas, 2021, 10, 14, 31, DiaSemanal.Quinta);
        inserirDatas(mDatas, 2021, 11, 1, 30, DiaSemanal.Segunda);
        inserirDatas(mDatas, 2021, 12, 1, 22, DiaSemanal.Quarta);

        return mDatas;
    }

    public static void inserirDatas(ArrayList<Data> eDatas, int eAno, int eMes, int eDiaInicio, int eDiaFinal, DiaSemanal eDiaSemanal) {

        for (int eDia = eDiaInicio; eDia <= eDiaFinal; eDia++) {
            eDatas.add(new Data(eAno, eMes, eDia, eDiaSemanal));
            eDiaSemanal = Calendario.proximoDia(eDiaSemanal);
        }

    }

    public static DiaSemanal proximoDia(DiaSemanal eDiaSemanal) {

        DiaSemanal eRetorno = eDiaSemanal;

        if (eDiaSemanal == DiaSemanal.Domingo) {
            eRetorno = DiaSemanal.Segunda;
        } else if (eDiaSemanal == DiaSemanal.Segunda) {
            eRetorno = DiaSemanal.Terca;
        } else if (eDiaSemanal == DiaSemanal.Terca) {
            eRetorno = DiaSemanal.Quarta;
        } else if (eDiaSemanal == DiaSemanal.Quarta) {
            eRetorno = DiaSemanal.Quinta;
        } else if (eDiaSemanal == DiaSemanal.Quinta) {
            eRetorno = DiaSemanal.Sexta;
        } else if (eDiaSemanal == DiaSemanal.Sexta) {
            eRetorno = DiaSemanal.Sabado;
        } else if (eDiaSemanal == DiaSemanal.Sabado) {
            eRetorno = DiaSemanal.Domingo;
        }

        return eRetorno;
    }


    public static String getSemanaGrupo(int eMes, int eDia) {

        String ret = "";

        if (eMes == 8) {

            if (eDia >= 16 && eDia <= 20) {
                ret = "AMARELO";
            } else if (eDia >= 23 && eDia <= 27) {
                ret = "AZUL";
            } else if (eDia >= 30 && eDia <= 31) {
                ret = "AMARELO";
            }

        } else if (eMes == 9) {

            if (eDia >= 1 && eDia <= 3) {
                ret = "AMARELO";
            } else if (eDia >= 6 && eDia <= 10) {
                ret = "AZUL";
            } else if (eDia >= 13 && eDia <= 17) {
                ret = "AMARELO";
            } else if (eDia >= 20 && eDia <= 24) {
                ret = "AZUL";
            } else if (eDia >= 27 && eDia <= 30) {
                ret = "AMARELO";
            }

        } else if (eMes == 10) {

            if (eDia == 1) {
                ret = "AMARELO";
            } else if (eDia >= 4 && eDia <= 8) {
                ret = "AZUL";
            } else if (eDia >= 11 && eDia <= 15) {
                ret = "AMARELO";
            } else if (eDia >= 18 && eDia <= 22) {
                ret = "AZUL";
            } else if (eDia >= 25 && eDia <= 30) {
                ret = "AMARELO";
            }

        } else if (eMes == 11) {


            if (eDia >= 1 && eDia <= 5) {
                ret = "AZUL";
            } else if (eDia >= 8 && eDia <= 12) {
                ret = "AMARELO";
            } else if (eDia >= 15 && eDia <= 19) {
                ret = "AZUL";
            } else if (eDia >= 22 && eDia <= 26) {
                ret = "AMARELO";
            } else if (eDia >= 29 && eDia <= 30) {
                ret = "AZUL";
            }

        } else if (eMes == 12) {


            if (eDia >= 1 && eDia <= 3) {
                ret = "AZUL";
            } else if (eDia >= 6 && eDia <= 10) {
                ret = "AMARELO";
            } else if (eDia >= 13 && eDia <= 17) {
                ret = "AZUL";
            } else if (eDia >= 20 && eDia <= 24) {
                ret = "AMARELO";

            }

        }


        return ret;

    }


    public static HorarioTurma getHorarioDaTurma(ArrayList<HorarioTurma> mTurmas, String eTurma) {

        HorarioTurma ret = null;

        for (HorarioTurma eHorarioTurma : mTurmas) {
            if (eHorarioTurma.getTurma().contentEquals(eTurma)) {
                ret = eHorarioTurma;
                break;
            }
        }

        return ret;
    }

    public static ArrayList<Data> getAulasDaTurma(ArrayList<Data> mDatas, HorarioTurma eTurma) {

        ArrayList<Data> ret = new ArrayList<Data>();

        for (Data eData : mDatas) {
            if (eTurma.temAula(eData.getDiaSemanal())) {
                ret.add(eData);
            }
        }

        return ret;
    }

    public static ArrayList<Data> getAulasDePDTurma(ArrayList<Data> mDatas, HorarioTurma eTurma) {

        ArrayList<Data> ret = new ArrayList<Data>();

        for (Data eData : mDatas) {
            if (eData.getDiaSemanal() == eTurma.getPD()) {
                ret.add(eData);
            }

        }

        return ret;
    }

}
