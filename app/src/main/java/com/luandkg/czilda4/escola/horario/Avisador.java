package com.luandkg.czilda4.escola.horario;

import android.content.Context;
import android.os.Handler;

import com.luandkg.czilda4.escola.organizacao.Professor;
import com.luandkg.czilda4.escola.organizacao.TurmaItem;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Avisador {

    private Professor mProfessor;

    private final Handler myHandler = new Handler();
    private Timer temporizador;
    private Context mContexto;

    public Avisador(Context eContext, Professor eProfessor) {
        mContexto = eContext;
        mProfessor = eProfessor;
    }


    public void run() {
        temporizador = new Timer();
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        }, 0, 500);
    }

    public Runnable myRunnable = new Runnable() {
        public void run() {


            String HOJE_DIA = Calendario.getDiaAtual();
            int HOJE_TEMPO = Calendario.getTempoDoDia();
            String HOJE_DATA = Calendario.getADMComBarras();

            if (mProfessor.estou_em_ferias(Data.toData(Calendario.getADMComTracoInferior()))) {

            } else {

                if (mProfessor.temReposicao(HOJE_DATA)) {

                    Reposicao eReposicao = mProfessor.getReposicao(HOJE_DATA);
                    HOJE_DIA = eReposicao.getReferente();

                }


                for (TurmaItem ti : mProfessor.getTurmas()) {
                    if (!ti.getDiaDaSemana().contentEquals(HOJE_DIA)) {
                        ti.desavisar();
                    }
                }

                if (Calendario.isDiaDaSemana(HOJE_DIA)) {

                    if (mProfessor.estaEmRegencia(HOJE_DIA, HOJE_TEMPO)) {

                        ArrayList<TurmaItem> hoje = TurmaItem.filtrarTurmas(HOJE_DIA, mProfessor.getTurmas());


                        for (TurmaItem horario_da_turma : hoje) {

                            if (horario_da_turma.isQuantosMinutosAntes(HOJE_TEMPO, 3)) {
                                Informante.notificar(mContexto, horario_da_turma, mProfessor);
                            }

                            if (horario_da_turma.isDentro(HOJE_TEMPO)) {

                                if (!horario_da_turma.foiAvisado()) {
                                    horario_da_turma.avisar();
                                    Informante.notificar(mContexto, horario_da_turma, mProfessor);
                                }

                                break;
                            }
                        }


                    }


                }


            }



        }
    };


}
