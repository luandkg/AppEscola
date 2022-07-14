package com.luandkg.czilda4.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.Versionador;
import com.luandkg.czilda4.atividades.AprovadorActivity;
import com.luandkg.czilda4.atividades.VivenciaActivity;
import com.luandkg.czilda4.databinding.FragmentHojeBinding;
import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.escola.utils.EmblemadorHoje;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.tempo.Hoje;
import com.luandkg.czilda4.escola.organizacao.TurmaComHorario;
import com.luandkg.czilda4.utils.tempo.Calendario;
import com.luandkg.czilda4.zilda2020.exportadores.FluxoDeAtividades;

import java.util.ArrayList;


public class HojeFragment extends Fragment {

    private FragmentHojeBinding mInterface;
    private TextView TV_TEXTO;
    private ImageView IV_HOJE;

    private TextView TV_CHAMADAS;
    private ImageView IV_CHAMADAS;
    private TextView TV_CHAMADAS_MAIS;
    private TextView TV_VERSAO;


    private TextView TV_ATIVIDADES;
    private TextView TV_ATIVIDADES_MAIS;
    private ImageView IV_ATIVIDADES;

    private Button BTN_VIVENCIA;

    private String HOJE = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mInterface = FragmentHojeBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        IV_HOJE = (ImageView) mInterface.hojeImagem;
        TV_TEXTO = (TextView) mInterface.hojeTexto;

        TV_CHAMADAS = (TextView) mInterface.hojeChamadasTexto;
        TV_CHAMADAS_MAIS = (TextView) mInterface.hojeChamadasTextoMais;
        IV_CHAMADAS = (ImageView) mInterface.hojeChamadasImagem;
        TV_VERSAO = (TextView) mInterface.hojeVersao;
        BTN_VIVENCIA = (Button) mInterface.hojeVivencia;


        TV_ATIVIDADES = (TextView) mInterface.hojeAtividadesTexto;
        TV_ATIVIDADES_MAIS = (TextView) mInterface.hojeAtividadesTextoMais;


        TV_CHAMADAS_MAIS.setTextColor(Color.parseColor("#4CAF50"));
        TV_ATIVIDADES_MAIS.setTextColor(Color.parseColor("#4CAF50"));


        IV_ATIVIDADES = (ImageView) mInterface.hojeAtividadesImagem;

        Versionador v = new Versionador();


        TV_TEXTO.setText(Calendario.getData());
        TV_VERSAO.setText(v.getVersao() + " - Luan Freitas ( luandkg@gmail.com )");

        HOJE = Calendario.getADMComTracoInferior();


        String HOJE_DIA = Calendario.getDiaAtual();


        BTN_VIVENCIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), VivenciaActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });


        ArrayList<String> turmas_hoje = Hoje.getTurmas(HOJE_DIA);
        ArrayList<String> turmas_realizadas = Hoje.getTurmasRealizadas(HOJE);
        ArrayList<TurmaComHorario> turmas_realizadas_com_horario = Hoje.getTurmasRealizadasComHorario(HOJE);


        int alunos_total = Escola.getAlunosVisiveisEOrdenadosDasTurmas(turmas_realizadas).size();

        int alunos_presente = Hoje.getQuantidadeDeAlunosPresente(HOJE, turmas_realizadas);

        int atividades_realizadas = Hoje.getQuantidadeDeAtividadesRealizadas(HOJE, turmas_realizadas);


        if (turmas_hoje.size() > 0) {

            if (CED1_Calendario.isRecesso(Calendario.getDataHoje())) {

                TV_TEXTO.setText(Calendario.getData() + " - RECESSO !");

                double acabar = (double) CED1_Calendario.recesso_passou(Calendario.getDataHoje()) / (double) CED1_Calendario.getRecesso().size();

                int para_acabar = CED1_Calendario.getRecesso().size() - CED1_Calendario.recesso_passou(Calendario.getDataHoje());

                IV_HOJE.setImageBitmap(FluxoDeAtividades.onBimestre((int) (acabar * 100.0f), para_acabar));


            }else{
                IV_HOJE.setImageBitmap(EmblemadorHoje.criar(turmas_realizadas_com_horario, turmas_realizadas.size(), turmas_hoje.size()));
            }

        }


        if (alunos_presente == 0) {
            TV_CHAMADAS.setText("Nenhum aluno presente !");
        } else if (alunos_presente == 1) {
            TV_CHAMADAS.setText(alunos_presente + " aluno presente !");
        } else {
            TV_CHAMADAS.setText(alunos_presente + " alunos presentes !");
        }

        IV_CHAMADAS.setImageBitmap(EmblemadorHoje.criarSimples(alunos_presente, alunos_total));


        if (atividades_realizadas == 0) {
            TV_ATIVIDADES.setText("Nenhuma atividade realizada !");
        } else if (atividades_realizadas == 1) {
            TV_ATIVIDADES.setText(atividades_realizadas + " atividade realizada !");
        } else {
            TV_ATIVIDADES.setText(atividades_realizadas + " atividades realizadas !");
        }

        IV_ATIVIDADES.setImageBitmap(EmblemadorHoje.criarSimples(atividades_realizadas, alunos_total));

        System.out.println("Turmas :: " + turmas_hoje.size());
        System.out.println("Turmas ja :: " + turmas_realizadas.size());
        System.out.println("Alunos :: " + alunos_presente);


        if (turmas_realizadas.size() > 1) {

            int mais_alunos = 0;
            int mais_atividades = 0;

            ArrayList<String> turmas_realizadas_ate = new ArrayList<String>();

            int index = 0;
            int ultimo = turmas_realizadas.size() - 1;
            while (index < ultimo) {

                String turma = turmas_realizadas.get(index);
                turmas_realizadas_ate.add(turma);

                int alunos_presente_ate = Hoje.getQuantidadeDeAlunosPresente(HOJE, turmas_realizadas_ate);
                int atividades_ate = Hoje.getQuantidadeDeAtividadesRealizadas(HOJE, turmas_realizadas_ate);

                mais_alunos = alunos_presente - alunos_presente_ate;
                mais_atividades = atividades_realizadas - atividades_ate;

                System.out.println("Aumentou :: " + turma + " -->> " + mais_alunos + " :: " + mais_atividades);

                index += 1;
            }

            System.out.println("Aumentou :: " + mais_alunos + "::" + mais_atividades);
            if (mais_alunos > 0) {
                TV_CHAMADAS_MAIS.setText("+ " + mais_alunos);
            }

            if (mais_atividades > 0) {
                TV_ATIVIDADES_MAIS.setText("+ " + mais_atividades);
            }
        }

        return root;
    }
}