package com.luandkg.czilda4.escola.horario;

import android.content.Context;
import android.graphics.Bitmap;

import com.luandkg.czilda4.escola.utils.Emblemador;
import com.luandkg.czilda4.escola.organizacao.Professor;
import com.luandkg.czilda4.escola.organizacao.TurmaItem;
import com.luandkg.czilda4.utils.Notificar;

import java.util.Random;

public class Informante {


    public static void limpar(Professor mProfessor, String HOJE_DIA) {

        for (TurmaItem ti : mProfessor.getTurmas()) {
            if (!ti.getDiaDaSemana().contentEquals(HOJE_DIA)) {
                ti.desavisar();
            }
        }

    }

    public static void notificar(Context mContexto, TurmaItem ti, Professor mProfessor) {

        if (!ti.foiAvisado()) {

            ti.avisar();

            String eTitulo = "";
            String eAviso = "";
            String eModo = "";

            if (ti.getNome().contentEquals("I")) {

                eModo = "INTERVALO";
                eTitulo = "Intervalo";

                Random sorte = new Random();
                int numero = sorte.nextInt(100);

                if (numero >= 0 && numero < 25) {
                    eAviso = "Vamos descansar um pouco....";
                } else if (numero >= 25 && numero < 50) {
                    eAviso = "Vamos relaxar um pouquinho....";
                } else {
                    eAviso = "Hora de ficar em paz....";
                }


            } else {

                eModo = "AULA";

                eTitulo = "Trocar de Turma";
                eAviso = "Ir para a turma " + ti.getNome();


            }

            Bitmap meu_icone = Emblemador.criarAulaSimbolo(eModo, ti.getNome());

            Notificar.notifique(mContexto, "AVISOS", eTitulo, eAviso, meu_icone);

        }


    }
}
