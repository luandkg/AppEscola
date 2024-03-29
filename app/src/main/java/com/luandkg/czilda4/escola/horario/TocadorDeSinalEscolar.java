package com.luandkg.czilda4.escola.horario;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.escola.organizacao.Professor;
import com.luandkg.czilda4.escola.organizacao.TurmaItem;
import com.luandkg.czilda4.escola.tempo.Temporizador;
import com.luandkg.czilda4.utils.AndroidTheme;
import com.luandkg.czilda4.utils.Threader;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TocadorDeSinalEscolar {

    private TextView mFazendo;

    private ImageView mProgressante;

    private Professor mProfessor;

    final Handler myHandler = new Handler();
    private Timer temporizador;
    private Context mContexto;
    private boolean sextou = false;
    private Temporizador mTemporizador;
    private boolean isDark = false;

    public TocadorDeSinalEscolar(Context eContext, Temporizador eTemporizador, TextView eFazendo, ImageView eProgressante, Professor eProfessor) {
        mContexto = eContext;
        mTemporizador = eTemporizador;
        mFazendo = eFazendo;
        mProgressante = eProgressante;
        mProfessor = eProfessor;

          isDark = AndroidTheme.isDark(eContext);

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


            // ---------------- LIMAR TUDO -----------------------

            mFazendo.setText("Estou de boa....");

            mTemporizador.setProgressGrande(0);
            mTemporizador.setProgressPequeno(0);

            mTemporizador.setText("");
            mTemporizador.setTema(isDark);

            // ----------------------------------------------------


            String HOJE_DIA = Calendario.getDiaAtual();
            int HOJE_TEMPO = Calendario.getTempoDoDia();

            String HOJE_DATA = Calendario.getADMComBarras();


            if (mProfessor.estou_em_ferias(Data.toData(Calendario.getADMComTracoInferior()))) {

                mFazendo.setText("Estou em recesso !");
                mTemporizador.setText("RECESSO");

                mTemporizador.set(Calendario.getDiaAtual(), Calendario.getTempoDoDia(), true, mProfessor);
                mTemporizador.setFerias(mProfessor.ferias_passou(Data.toData(HOJE_DATA)), mProfessor.getFerias().size());


            } else {

                if (mProfessor.temReposicao(HOJE_DATA)) {

                    Reposicao eReposicao = mProfessor.getReposicao(HOJE_DATA);
                    HOJE_DIA = eReposicao.getReferente();

                }


                boolean isSexta = false;
                int ultima_aula_avisar_antes = 0;
                int ultima_aula = 0;

                ArrayList<AtividadeEspecial> atividades = AtividadeEspecial.filtrar(HOJE_DIA, mProfessor.getAtividades());

                mTemporizador.set(HOJE_DIA, HOJE_TEMPO, false, mProfessor);

                if (mProfessor.existeCargaDeTrabalho(Calendario.SEXTA)) {

                    // CODIGO ESPECIAL PARA SEXTAR

                    if (Calendario.isDiferente(HOJE_DIA, Calendario.SEXTA)) {
                        sextou = false;
                    }

                    if (Calendario.isIgual(HOJE_DIA, Calendario.SEXTA)) {

                        ArrayList<TurmaItem> na_sexta = TurmaItem.filtrarTurmas(Calendario.SEXTA, mProfessor.getTurmas());

                        isSexta = true;
                        if (na_sexta.size() > 0) {
                            ultima_aula_avisar_antes = na_sexta.get(na_sexta.size() - 1).getFim() - (30 * 60);
                            ultima_aula = na_sexta.get(na_sexta.size() - 1).getFim();
                        }
                    }

                }


                if (Calendario.isDiaDaSemana(HOJE_DIA)) {

                    if (mProfessor.existeCargaDeTrabalho(HOJE_DIA)) {
                        CargaDeTrabalho carga = mProfessor.getCargaDeTrabalho(HOJE_DIA);
                        progressoDaEscola(carga.getInicio().getValor(), carga.getFim().getValor());
                    }

                    for (AtividadeEspecial atividade : atividades) {

                        if (atividade.isMostrarIndo()) {
                            if (atividade.isDentroIndo(HOJE_TEMPO)) {

                                //     Informante.notificarIndo(mFazendo.getContext(), atividade, mProfessor);

                                mFazendo.setText(atividade.getEstouIndo());
                                mTemporizador.setText("IR");

                                progressoDaCoordenacao(atividade.getIndo_Inicio(), atividade.getIndo_Fim());

                            }
                        }

                        if (atividade.isDentro(HOJE_TEMPO)) {

                            mFazendo.setText(atividade.getTipo());
                            mTemporizador.setText(atividade.getSigla());

                            progressoDaCoordenacao(atividade.getInicio(), atividade.getFim());

                        }

                    }


                    // ALMOCO
                    if (mProfessor.estouAlmocando(HOJE_TEMPO)) {

                        mFazendo.setText("Estou almoçando !");
                        mTemporizador.setText("A");

                        progressoDaCoordenacao(mProfessor.getAlmocoInicio().getValor(), mProfessor.getAlmocoFim().getValor());

                    }

                    if (mProfessor.estaEmRegencia(HOJE_DIA, HOJE_TEMPO)) {

                        ArrayList<TurmaItem> hoje = TurmaItem.filtrarTurmas(HOJE_DIA, mProfessor.getTurmas());

                        boolean temAula = false;


                        for (TurmaItem horario_da_turma : hoje) {
                            if (HOJE_TEMPO >= horario_da_turma.getInicio() && HOJE_TEMPO < horario_da_turma.getFim()) {


                                mTemporizador.setText(horario_da_turma.getNome());
                                progressoDaAula(horario_da_turma.getInicio(), horario_da_turma.getFim());

                                if (horario_da_turma.getTipo().contentEquals("IN")) {
                                    mFazendo.setText("Estou no intervalo !");
                                } else {
                                    mFazendo.setText("Estou em regência ....");

                                    if (isSexta) {
                                        sextar(HOJE_TEMPO, ultima_aula_avisar_antes, horario_da_turma);
                                    }

                                }

                                temAula = true;
                                break;
                            }
                        }


                    }

                    if (mProfessor.existeCargaDeTrabalho(HOJE_DIA)) {


                        // INDO PARA CASA
                        if (mProfessor.estouIndoParaCasa(HOJE_DIA, HOJE_TEMPO)) {

                            mFazendo.setText("Estou indo para casa, amém !");
                            mTemporizador.setText("CASA");

                            progressoDaCoordenacao(mProfessor.getIndoParaCasa_Inicio(HOJE_DIA), mProfessor.getIndoParaCasa_Fim(HOJE_DIA));

                            if (sextou) {
                                mFazendo.setText("Indo para casa - SEXTOUUUUUUUUUUUU......");
                                mTemporizador.setText("AMÉM");
                            }


                        }

                        if (mProfessor.passouDaHoraDeIrEmbora(HOJE_DIA, HOJE_TEMPO) && sextou) {

                            if (sextou) {
                                mFazendo.setText("SEXTOUUUUUUUUUUUU......");
                                mTemporizador.setText("AMÉM");
                            }

                        }

                    }


                } else {

                    progressoFimDeSemana(HOJE_DIA, HOJE_TEMPO);

                }

            }

            Threader.atualizar_imagem(mProgressante, mTemporizador.criar());
        }


    };


    public void sextar(int HOJE_TEMPO, int ultima_aula_avisar_antes, TurmaItem horario_da_turma) {

        if (HOJE_TEMPO >= ultima_aula_avisar_antes) {
            int falta_v = falta(HOJE_TEMPO, horario_da_turma.getFim());

            if (falta_v > 60) {
                int min = falta_v / 60;
                mFazendo.setText("Estou em regência - QUASE SEXTANDO \n FALTA " + min + " minutos !");
            } else if (falta_v <= 60 && falta_v > 10) {
                mFazendo.setText("Estou em regência - VAI SEXTAR \n FALTA " + falta_v + " segundos !");
            } else {
                mFazendo.setText("Estou em regência - VAI SEXTAR \n CHEGANDO " + falta_v + " segundos !");

                if (!sextou) {
                    sextou = true;
                }


            }

        }

    }

    public int falta(int agora, int ate) {
        return ate - agora;
    }


    public void progressoDaAula(int eInicio, int eFim) {

        int eAgora = Calendario.getTempoDoDia();

        progressarPequeno(eAgora, eInicio, eFim);

    }

    public void progressoDaCoordenacao(int eInicio, int eFim) {

        int eAgora = Calendario.getTempoDoDia();

        progressarPequeno(eAgora, eInicio, eFim);

    }

    public void progressoDaEscola(int eInicio, int eFim) {

        int eAgora = Calendario.getTempoDoDia();

        progressar(eAgora, eInicio, eFim);

    }


    public void mostrarProgressoInterno(int inicio, int fim) {

        int total = fim - inicio;

        int eAgora = Calendario.getTempoDoDia();

        if (eAgora >= inicio && eAgora < fim) {

            double restante = (((double) eAgora - (double) inicio) / (double) total);
            double prop = restante * 100.0;
            int iprop = (int) prop;

            mTemporizador.setText("" + iprop);

        }

    }

    public void progressar(int eAgora, int eInicio, int eFim) {

        if (eAgora >= eInicio && eAgora < eFim) {

            double agora = (double) eAgora;

            double inicio = (double) eInicio;
            double fim = (double) eFim;

            double total = fim - inicio;

            double restante = (((double) agora - inicio) / total);
            double prop = restante * 100.0;

            mTemporizador.setProgressGrande((int) prop);
            //  mContador.setText(""+prop );

        }

    }

    public void progressarPequeno(int eAgora, int eInicio, int eFim) {

        if (eAgora >= eInicio && eAgora < eFim) {

            double agora = (double) eAgora;

            double inicio = (double) eInicio;
            double fim = (double) eFim;

            double total = fim - inicio;

            double restante = (((double) agora - inicio) / total);
            double prop = restante * 100.0;

            mTemporizador.setProgressPequeno((int) prop);
            //  mContador.setText(""+prop );

        }

    }

    public void progressoFimDeSemana(String eDia, int eAgora) {

        int metade = ((24 * 60 * 60));
        int total = ((24 * 60 * 60) * 2);

        if (eDia.contentEquals(Calendario.SABADO)) {

            double agora = ((double) eAgora);

            double d_total = (double) total;

            double restante = (((double) agora) / d_total);
            double prop = restante * 100.0;

            mTemporizador.setProgressGrande((int) prop);
            mTemporizador.setText("SÁB");

        } else {
            double agora = ((double) eAgora) + (double) metade;

            double d_total = (double) total;

            double restante = (((double) agora) / d_total);
            double prop = restante * 100.0;

            mTemporizador.setProgressGrande((int) prop);
            mTemporizador.setText("DOM");

            //System.out.println("FDS :: " + prop);
        }

        mFazendo.setText("Estou curtindo o final de semana ...");

    }
}
