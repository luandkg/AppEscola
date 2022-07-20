package com.luandkg.czilda4.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.atividades.AprovadorActivity;
import com.luandkg.czilda4.atividades.NotasActivity;
import com.luandkg.czilda4.databinding.FragmentResultadorBinding;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.avaliacao.Mensoes;
import com.luandkg.czilda4.escola.avaliacao_continua.Perfilometro;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.desempenhador.DesempenhoIO;
import com.luandkg.czilda4.escola.desempenhador.DesempenhoReferencia;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.escola.avaliacao.Mensionador;
import com.luandkg.czilda4.escola.avaliacao_continua.DesempenhoGrafico;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.tempo.Semanador;
import com.luandkg.czilda4.utils.ActivityStarter;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Calendario;

import java.util.ArrayList;

public class DesempenhadorFragment extends Fragment {


    private FragmentResultadorBinding mInterface;
    private ImageView IV_PRE;
    private ImageView IV_POS;
    private Button BTN_NOTAS;

    private Button BTN_OMEGA;
    private Button BTN_ZETA;
    private Button BTN_DELTA;
    private Button BTN_ALFA;

    private Button BTN_APROVADOS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mInterface = FragmentResultadorBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();


        IV_PRE = mInterface.ivPre;
        IV_POS = mInterface.ivPos;
        BTN_NOTAS = mInterface.resultadorBtnNotas;

        BTN_OMEGA = mInterface.resultadorNotasIi;
        BTN_ZETA = mInterface.resultadorNotasMi;
        BTN_DELTA = mInterface.resultadorNotasMs;
        BTN_ALFA = mInterface.resultadorNotasSs;
        BTN_APROVADOS = mInterface.resultadorAprovados;


        Bimestre eBimestre = BimestreCorrente.GET();

        ArrayList<DKGObjeto> perfils = HiperCacheDeAvaliacao.getPerfis();

        ArrayList<AlunoContinuo> alunos_continuos = Perfilometro.getPerfis(perfils, eBimestre.getSemanas());

        ArrayList<SemanaContinua> SEGUNDO_SEMANAS = eBimestre.getSemanas();

        String HOJE_DATA = Calendario.getData();

        boolean esta_no_bimestre = Semanador.isSemanaValida(HOJE_DATA, SEGUNDO_SEMANAS);
        int semana_atual = Semanador.getSemanaID(HOJE_DATA, SEGUNDO_SEMANAS);


        String semana_atual_data = Semanador.getSemanaDataCorrente(HOJE_DATA, SEGUNDO_SEMANAS);

        String semana_anterior_data = Semanador.getSemanaDataAnterior(HOJE_DATA, SEGUNDO_SEMANAS);


        System.out.println("ESTA NO BIMESTRE :: " + esta_no_bimestre);
        System.out.println("SEMANA :: " + semana_atual);
        System.out.println("SEMANA ATUAL DATA :: " + semana_atual_data);
        System.out.println("SEMANA ANTERIOR DATA :: " + semana_anterior_data);

        if (esta_no_bimestre) {
            DesempenhoIO.guardar(Local.COLECAO_DESEMPENHOS, semana_atual_data, alunos_continuos);
        }

        DesempenhoReferencia mSEM_Antes = DesempenhoIO.getDesempenho_Sem(Local.COLECAO_DESEMPENHOS, semana_anterior_data);
        DesempenhoReferencia mSEM_Agora = DesempenhoIO.getDesempenho_Sem(Local.COLECAO_DESEMPENHOS, semana_atual_data);


        DesempenhoReferencia mCOM_Antes = DesempenhoIO.getDesempenho_Com(Local.COLECAO_DESEMPENHOS, semana_anterior_data);
        DesempenhoReferencia mCOM_Agora = DesempenhoIO.getDesempenho_Com(Local.COLECAO_DESEMPENHOS, semana_atual_data);



        IV_PRE.setImageBitmap(DesempenhoGrafico.onMedias(alunos_continuos, mSEM_Antes, mSEM_Agora, false));
        IV_POS.setImageBitmap(DesempenhoGrafico.onMedias(alunos_continuos, mCOM_Antes, mCOM_Agora, true));

        Mensoes mensoes = Mensionador.contarMensoes(alunos_continuos);


        BTN_OMEGA.setText(String.valueOf(mensoes.omega()));
        BTN_ZETA.setText(String.valueOf(mensoes.zeta()));
        BTN_DELTA.setText(String.valueOf(mensoes.delta()));
        BTN_ALFA.setText(String.valueOf(mensoes.alfa()));

        BTN_OMEGA.setBackgroundColor(Color.parseColor(Mensionador.COR_OMEGA));
        BTN_ZETA.setBackgroundColor(Color.parseColor(Mensionador.COR_ZETA));
        BTN_DELTA.setBackgroundColor(Color.parseColor(Mensionador.COR_DELTA));
        BTN_ALFA.setBackgroundColor(Color.parseColor(Mensionador.COR_ALFA));


        BTN_APROVADOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AprovadorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });


        ActivityStarter.iniciar_com_argumento(this.getContext(), BTN_NOTAS, NotasActivity.class, "MENSAO", "");

        ActivityStarter.iniciar_com_argumento(this.getContext(), BTN_OMEGA, NotasActivity.class, "MENSAO", Mensionador.OMEGA);
        ActivityStarter.iniciar_com_argumento(this.getContext(), BTN_ZETA, NotasActivity.class, "MENSAO", Mensionador.ZETA);
        ActivityStarter.iniciar_com_argumento(this.getContext(), BTN_DELTA, NotasActivity.class, "MENSAO", Mensionador.DELTA);
        ActivityStarter.iniciar_com_argumento(this.getContext(), BTN_ALFA, NotasActivity.class, "MENSAO", Mensionador.ALFA);

        return root;

    }


}