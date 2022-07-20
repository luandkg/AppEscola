package com.luandkg.czilda4.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.FechadorBimestral;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.tempo.BimestreTemporal;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.zilda2020.exportadores.FluxoDeAtividades;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.databinding.FragmentBimestreBinding;

import java.util.ArrayList;

public class BimestreFragment extends Fragment {

    private FragmentBimestreBinding binding;

    private ImageView IV_CONTAGEM_TEMPO;
    private TextView TV_CONTAGEM_TEXTO;

    private TextView TV_BIMESTRE_INICIO;
    private TextView TV_BIMESTRE_FIM;

    private Button BTN_SINCRONIZAR;
    private ImageView IV_VISUALIZADOR;
    private ArrayList<Data> SEGUNDO_BIMESTRE;

    private FechadorBimestral mFechadorBimestral;
    private boolean mExecutando = false;
    private Thread thread;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBimestreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        IV_CONTAGEM_TEMPO = binding.contagemTempo;
        TV_CONTAGEM_TEXTO = binding.contagemLabel;
        TV_BIMESTRE_INICIO = binding.tvBimestreInicio;
        TV_BIMESTRE_FIM = binding.tvBimestreFim;
        BTN_SINCRONIZAR = binding.bimestreBtnSincronizar;
        IV_VISUALIZADOR = binding.bimestreFluxoDeAtividades;


        String HOJE_DATA = Calendario.getADMComTracoInferior();


        TV_CONTAGEM_TEXTO.setText(HOJE_DATA);


        CED1_Calendario ESCOLA_CALENDARIO = new CED1_Calendario();
        Bimestre eBimestre = BimestreCorrente.GET();

        SEGUNDO_BIMESTRE = eBimestre.getDatas();

        if (BimestreTemporal.temBimestre(HOJE_DATA, ESCOLA_CALENDARIO)) {

            String bimestre = BimestreTemporal.getBimestreNome(HOJE_DATA);
            ArrayList<Data> datas = BimestreTemporal.getBimestre(HOJE_DATA);

            mostrar(HOJE_DATA, bimestre + "º BIMESTRE", datas);

        } else if (CED1_Calendario.isRecesso(Calendario.getDataHoje())) {

            TV_CONTAGEM_TEXTO.setText(Calendario.getData() + " - RECESSO !");

            TV_BIMESTRE_INICIO.setText(Calendario.filtrar_primeira(CED1_Calendario.getRecesso()).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Calendario.filtrar_ultima(CED1_Calendario.getRecesso()).getTempoLegivel());

            double acabar = (double) CED1_Calendario.recesso_passou(Calendario.getDataHoje()) / (double) CED1_Calendario.getRecesso().size();


            int para_acabar = CED1_Calendario.getRecesso().size() - CED1_Calendario.recesso_passou(Calendario.getDataHoje());

            IV_CONTAGEM_TEMPO.setImageBitmap(FluxoDeAtividades.onBimestre((int) (acabar * 100.0f), para_acabar));

        }

        int alunos_total = Escola.getAlunosVisiveisEOrdenadosDaEscola().size();

        Bitmap imagem = FluxoFormativoContinuado.criarFluxoDeEntrega(alunos_total, eBimestre, Local.COLECAO_FLUXO);

        IV_VISUALIZADOR.setImageBitmap(imagem);


        BTN_SINCRONIZAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sincronizar();
            }
        });


        return root;
    }

    public void mostrar(String HOJE_DATA, String eFrase, ArrayList<Data> datas) {

        TV_CONTAGEM_TEXTO.setText(Calendario.getData() + " - " + eFrase);

        if (datas.size() > 0) {
            TV_BIMESTRE_INICIO.setText(Calendario.filtrar_primeira(datas).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Calendario.filtrar_ultima(datas).getTempoLegivel());
        }


        int para_acabar = BimestreTemporal.getDiasParaAcabar(HOJE_DATA, datas);
        int em_progresso = BimestreTemporal.getPorcentagem(HOJE_DATA, datas);

        IV_CONTAGEM_TEMPO.setImageBitmap(FluxoDeAtividades.onBimestre(em_progresso, para_acabar));


    }

    public void sincronizar() {

        if (!mExecutando) {
            mExecutando = true;

            mFechadorBimestral = new FechadorBimestral(getContext(), BimestreCorrente.GET(), TV_CONTAGEM_TEXTO, IV_CONTAGEM_TEMPO, IV_VISUALIZADOR);

            thread = new Thread(mFechadorBimestral);
            thread.start();

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}