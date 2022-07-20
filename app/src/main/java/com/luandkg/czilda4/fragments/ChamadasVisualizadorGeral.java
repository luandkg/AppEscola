package com.luandkg.czilda4.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.atividades.VisualizarChamadasActivity;
import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.chamadas.FluxoDeChamadas;
import com.luandkg.czilda4.escola.tempo.BimestreTemporal;
import com.luandkg.czilda4.utils.ActivityStarter;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.databinding.FragmentChamadasVisualizadorGeralBinding;
import com.luandkg.czilda4.zilda2020.exportadores.FluxoDeAtividades;

import java.util.ArrayList;


public class ChamadasVisualizadorGeral extends Fragment {

    private FragmentChamadasVisualizadorGeralBinding mInterface;

    private ImageView IV_CONTAGEM_TEMPO;
    private TextView TV_CONTAGEM_TEXTO;

    private TextView TV_BIMESTRE_INICIO;
    private TextView TV_BIMESTRE_FIM;

    private Button BTN_AULAS;
    private ImageView IV_VISUALIZADOR;

    private ArrayList<Data> SEGUNDO_BIMESTRE;

    private Button BTN_SEG;
    private Button BTN_TER;
    private Button BTN_QUA;
    private Button BTN_QUI;
    private Button BTN_SEX;

    private Button LAB_SEG;
    private Button LAB_TER;
    private Button LAB_QUA;
    private Button LAB_QUI;
    private Button LAB_SEX;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInterface = FragmentChamadasVisualizadorGeralBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        IV_CONTAGEM_TEMPO = mInterface.chamadasContagemTempo;
        TV_CONTAGEM_TEXTO = mInterface.chamadasContagemLabel;
        TV_BIMESTRE_INICIO = mInterface.chamadasTvBimestreInicio;
        TV_BIMESTRE_FIM = mInterface.chamadasTvBimestreFim;
        IV_VISUALIZADOR = mInterface.chamadasIvBimestreAtividades;
        BTN_AULAS = mInterface.chamadasBimestreBtnAulas;

        BTN_SEG = mInterface.chamadasBimestreBtnSeg;
        BTN_TER = mInterface.chamadasBimestreBtnTer;
        BTN_QUA = mInterface.chamadasBimestreBtnQua;
        BTN_QUI = mInterface.chamadasBimestreBtnQui;
        BTN_SEX = mInterface.chamadasBimestreBtnSex;

        LAB_SEG = mInterface.chamadasBimestreLabSeg;
        LAB_TER = mInterface.chamadasBimestreLabTer;
        LAB_QUA = mInterface.chamadasBimestreLabQua;
        LAB_QUI = mInterface.chamadasBimestreLabQui;
        LAB_SEX = mInterface.chamadasBimestreLabSex;


        CED1_Calendario ESCOLA_CALENDARIO = new CED1_Calendario();

        String HOJE_DATA = Calendario.getADMComTracoInferior();

        System.out.println("HOJE DATA :: " + HOJE_DATA);

        TV_CONTAGEM_TEXTO.setText(HOJE_DATA);

        Bimestre eBimestre = BimestreCorrente.GET();

        SEGUNDO_BIMESTRE = eBimestre.getDatas();

        if (BimestreTemporal.temBimestre(HOJE_DATA, ESCOLA_CALENDARIO)) {

            String bimestre = BimestreTemporal.getBimestreNome(HOJE_DATA);
            ArrayList<Data> datas = BimestreTemporal.getBimestre(HOJE_DATA);

            mostrar(HOJE_DATA, Calendario.getData() + " - " + bimestre + "º BIMESTRE", datas);

        } else if (CED1_Calendario.isRecesso(Calendario.getDataHoje())) {

            TV_CONTAGEM_TEXTO.setText(Calendario.getData() + " - RECESSO !");

            TV_BIMESTRE_INICIO.setText(Calendario.filtrar_primeira(CED1_Calendario.getRecesso()).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Calendario.filtrar_ultima(CED1_Calendario.getRecesso()).getTempoLegivel());

            double acabar = (double) CED1_Calendario.recesso_passou(Calendario.getDataHoje()) / (double) CED1_Calendario.getRecesso().size();


            int para_acabar = CED1_Calendario.getRecesso().size() - CED1_Calendario.recesso_passou(Calendario.getDataHoje());

            IV_CONTAGEM_TEMPO.setImageBitmap(FluxoDeAtividades.onBimestre((int) (acabar * 100.0f), para_acabar));

        }

        int alunos_quantidade =  Escola.getAlunosVisiveis().size();

        IV_VISUALIZADOR.setImageBitmap(FluxoDeChamadas.criarFluxoDePresenca(alunos_quantidade, eBimestre, Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS));

        ActivityStarter.iniciar(this.getContext(), BTN_AULAS, VisualizarChamadasActivity.class);


        return root;
    }

    public void mostrar(String hoje, String eFrase, ArrayList<Data> datas) {

        TV_CONTAGEM_TEXTO.setText(eFrase);

        if (datas.size() > 0) {
            TV_BIMESTRE_INICIO.setText(Calendario.filtrar_primeira(datas).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Calendario.filtrar_ultima(datas).getTempoLegivel());
        }


        int acabar = BimestreTemporal.getDiasParaAcabar(hoje, datas);
        int progresso = BimestreTemporal.getPorcentagem(hoje, datas);

        IV_CONTAGEM_TEMPO.setImageBitmap(FluxoDeAtividades.onBimestre(progresso, acabar));

        BTN_SEG.setText("0");
        BTN_TER.setText("0");
        BTN_QUA.setText("0");
        BTN_QUI.setText("0");
        BTN_SEX.setText("0");

        BTN_SEG.setBackgroundColor(Color.parseColor("#F44336"));
        BTN_TER.setBackgroundColor(Color.parseColor("#F44336"));
        BTN_QUA.setBackgroundColor(Color.parseColor("#F44336"));
        BTN_QUI.setBackgroundColor(Color.parseColor("#F44336"));
        BTN_SEX.setBackgroundColor(Color.parseColor("#F44336"));

        LAB_SEG.setBackgroundColor(Color.parseColor("#F44336"));
        LAB_TER.setBackgroundColor(Color.parseColor("#F44336"));
        LAB_QUA.setBackgroundColor(Color.parseColor("#F44336"));
        LAB_QUI.setBackgroundColor(Color.parseColor("#F44336"));
        LAB_SEX.setBackgroundColor(Color.parseColor("#F44336"));


        String dia_hoje = Calendario.getDiaAtual();

        int vSeg = -1;
        int vTer = -1;
        int vQua = -1;
        int vQui = -1;
        int vSex = -1;


        if (Calendario.isIgual(dia_hoje, Calendario.SEGUNDA)) {
            BTN_SEG.setBackgroundColor(Color.parseColor("#4CAF50"));
            LAB_SEG.setBackgroundColor(Color.parseColor("#4CAF50"));

            int v = BimestreTemporal.getID(hoje, datas);

            if (BimestreTemporal.isDataValida(v, datas)) {
                vSeg = v;
            }

        } else if (Calendario.isIgual(dia_hoje, Calendario.TERCA)) {
            BTN_TER.setBackgroundColor(Color.parseColor("#4CAF50"));
            LAB_TER.setBackgroundColor(Color.parseColor("#4CAF50"));


            int v = BimestreTemporal.getID(hoje, datas);

            if (BimestreTemporal.isDataValida(v, datas)) {
                vSeg = v - 1;
                vTer = v;
            }

        } else if (Calendario.isIgual(dia_hoje, Calendario.QUARTA)) {
            BTN_QUA.setBackgroundColor(Color.parseColor("#4CAF50"));
            LAB_QUA.setBackgroundColor(Color.parseColor("#4CAF50"));

            int v = BimestreTemporal.getID(hoje, datas);

            if (BimestreTemporal.isDataValida(v, datas)) {
                vSeg = v - 2;
                vTer = v - 1;
                vQua = v;
            }

        } else if (Calendario.isIgual(dia_hoje, Calendario.QUINTA)) {
            BTN_QUI.setBackgroundColor(Color.parseColor("#4CAF50"));
            LAB_QUI.setBackgroundColor(Color.parseColor("#4CAF50"));

            int v = BimestreTemporal.getID(hoje, datas);

            if (BimestreTemporal.isDataValida(v, datas)) {
                vSeg = v - 3;
                vTer = v - 2;
                vQua = v - 1;
                vQui = v;
            }

        } else if (Calendario.isIgual(dia_hoje, Calendario.SEXTA)) {
            BTN_SEX.setBackgroundColor(Color.parseColor("#4CAF50"));
            LAB_SEX.setBackgroundColor(Color.parseColor("#4CAF50"));

            int v = BimestreTemporal.getID(hoje, datas);

            if (BimestreTemporal.isDataValida(v, datas)) {
                vSeg = v - 4;
                vTer = v - 3;
                vQua = v - 2;
                vQui = v - 1;
                vSex = v;
            }

        } else if (Calendario.isIgual(dia_hoje, Calendario.SABADO)) {

            int v = BimestreTemporal.getID(hoje, datas);

            if (BimestreTemporal.isDataValida(v, datas)) {
                vSeg = v - 5;
                vTer = v - 4;
                vQua = v - 3;
                vQui = v - 2;
                vSex = v - 1;
            }

        } else if (Calendario.isIgual(dia_hoje, Calendario.DOMINGO)) {

            int v = BimestreTemporal.getID(hoje, datas);

            if (BimestreTemporal.isDataValida(v, datas)) {
                vSeg = v - 6;
                vTer = v - 5;
                vQua = v - 4;
                vQui = v - 3;
                vSex = v - 2;
            }

        }

        if (BimestreTemporal.isDataValida(vSeg, datas)) {
            BTN_SEG.setText(String.valueOf(FluxoDeChamadas.getFluxoDePresenca(datas.get(vSeg).getTempo(), Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS)));
        }

        if (BimestreTemporal.isDataValida(vTer, datas)) {

            System.out.println("TERCA :; " + datas.get(vTer).getTempo());
            System.out.println("TERCA :: " + FluxoDeChamadas.getFluxoDePresenca(datas.get(vTer).getTempo(), Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS));

            BTN_TER.setText(String.valueOf(FluxoDeChamadas.getFluxoDePresenca(datas.get(vTer).getTempo(), Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS)));
        }

        if (BimestreTemporal.isDataValida(vQua, datas)) {
            BTN_QUA.setText(String.valueOf(FluxoDeChamadas.getFluxoDePresenca(datas.get(vQua).getTempo(), Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS)));
        }

        if (BimestreTemporal.isDataValida(vQui, datas)) {
            BTN_QUI.setText(String.valueOf(FluxoDeChamadas.getFluxoDePresenca(datas.get(vQui).getTempo(), Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS)));
        }

        if (BimestreTemporal.isDataValida(vSex, datas)) {
            BTN_SEX.setText(String.valueOf(FluxoDeChamadas.getFluxoDePresenca(datas.get(vSex).getTempo(), Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS)));
        }


    }


}