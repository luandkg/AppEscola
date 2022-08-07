package com.luandkg.czilda4.escola.avaliacao;


import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;

import java.util.ArrayList;

public class Mensionador {

    public static final String OMEGA = "OMEGA";
    public static final String ZETA = "ZETA";
    public static final String DELTA = "DELTA";
    public static final String ALFA = "ALFA";

    public static final String COR_OMEGA = "#F44336";
    public static final String COR_ZETA = "#EF6C00";
    public static final String COR_DELTA = "#FDD835";
    public static final String COR_ALFA = "#4CAF50";


    public static String getMensao(double enota) {
        String ret = "";

        if (enota >= 0.0 && enota < 3.0) {
            ret = OMEGA;
        } else if (enota >= 3.0 && enota < 5.0) {
            ret = ZETA;
        } else if (enota >= 5.0 && enota < 7.0) {
            ret = DELTA;
        } else if (enota >= 7.0 && enota < 11.0) {
            ret = ALFA;
        }

        return ret;

    }

    public static String getCorDaMensao(String eMensao) {
        String ret = "";

        if (eMensao.contentEquals(OMEGA)) {
            ret = COR_OMEGA;
        } else if (eMensao.contentEquals(ZETA)) {
            ret = COR_ZETA;
        } else if (eMensao.contentEquals(DELTA)) {
            ret = COR_DELTA;
        } else if (eMensao.contentEquals(ALFA)) {
            ret = COR_ALFA;
        }

        return ret;

    }

    public static double acima(String eMensao) {

        double valor = 0;

        if (eMensao.contentEquals(OMEGA)) {
            valor = 0.0;
        } else if (eMensao.contentEquals(ZETA)) {
            valor = 3.0;
        } else if (eMensao.contentEquals(DELTA)) {
            valor = 5.0;
        } else if (eMensao.contentEquals(ALFA)) {
            valor = 7.0;
        }

        return valor;
    }

    public static double abaixo(String eMensao) {

        double valor = 0;

        if (eMensao.contentEquals(OMEGA)) {
            valor = 3.0;
        } else if (eMensao.contentEquals(ZETA)) {
            valor = 5.0;
        } else if (eMensao.contentEquals(DELTA)) {
            valor = 7.0;
        } else if (eMensao.contentEquals(ALFA)) {
            valor = 11.0;
        }

        return valor;
    }


    public static int contar_omega(ArrayList<AlunoContinuo> alunos_continuos) {
        return contar(Mensionador.OMEGA, alunos_continuos);
    }


    public static int contar_zeta(ArrayList<AlunoContinuo> alunos_continuos) {
        return contar(Mensionador.ZETA, alunos_continuos);
    }


    public static int contar_delta(ArrayList<AlunoContinuo> alunos_continuos) {
        return contar(Mensionador.DELTA, alunos_continuos);
    }


    public static int contar_alfa(ArrayList<AlunoContinuo> alunos_continuos) {
        return contar(Mensionador.ALFA, alunos_continuos);
    }


    public static int contar(String eMensao, ArrayList<AlunoContinuo> alunos_continuos) {

        double acima = Mensionador.acima(eMensao);
        double abaixo = Mensionador.abaixo(eMensao);


        ArrayList<AlunoContinuo> alunos_continuos_grupo = new ArrayList<AlunoContinuo>();


        for (AlunoContinuo aluno : alunos_continuos) {

            if (aluno.getNotaComRecuperacao() >= acima && aluno.getNotaComRecuperacao() < abaixo) {
                alunos_continuos_grupo.add(aluno);
            }

        }


        return alunos_continuos_grupo.size();

    }

    public static ArrayList<AlunoContinuo> listar(String eMensao, ArrayList<AlunoContinuo> alunos_continuos) {

        double acima = Mensionador.acima(eMensao);
        double abaixo = Mensionador.abaixo(eMensao);


        ArrayList<AlunoContinuo> alunos_continuos_grupo = new ArrayList<AlunoContinuo>();


        for (AlunoContinuo aluno : alunos_continuos) {

            if (aluno.getNotaComRecuperacao() >= acima && aluno.getNotaComRecuperacao() < abaixo) {
                alunos_continuos_grupo.add(aluno);
            }

        }


        return alunos_continuos_grupo;

    }

    public static Mensoes contarMensoes(ArrayList<AlunoContinuo> alunos) {

        int omega = 0;
        int zeta = 0;
        int delta = 0;
        int alfa = 0;

        double alfa_acima = Mensionador.acima(Mensionador.ALFA);
        double alfa_abaixo = Mensionador.abaixo(Mensionador.ALFA);

        double delta_acima = Mensionador.acima(Mensionador.DELTA);
        double delta_abaixo = Mensionador.abaixo(Mensionador.DELTA);

        double zeta_acima = Mensionador.acima(Mensionador.ZETA);
        double zeta_abaixo = Mensionador.abaixo(Mensionador.ZETA);

        double omega_acima = Mensionador.acima(Mensionador.OMEGA);
        double omega_abaixo = Mensionador.abaixo(Mensionador.OMEGA);



        for (AlunoContinuo aluno : alunos) {

            if (aluno.getNotaComRecuperacao() >= alfa_acima && aluno.getNotaComRecuperacao() < alfa_abaixo) {
                alfa += 1;
            }else    if (aluno.getNotaComRecuperacao() >= delta_acima && aluno.getNotaComRecuperacao() < delta_abaixo) {
                delta+=1;
            }else    if (aluno.getNotaComRecuperacao() >= zeta_acima && aluno.getNotaComRecuperacao() < zeta_abaixo) {
                zeta+=1;
            }else    if (aluno.getNotaComRecuperacao() >= omega_acima && aluno.getNotaComRecuperacao() < omega_abaixo) {
                omega+=1;
            }

        }




        return new Mensoes(omega, zeta, delta, alfa);

    }


}
