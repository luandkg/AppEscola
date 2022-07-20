package com.luandkg.czilda4.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luandkg.czilda4.escola.organizacao.TurmaItemStruct;
import com.luandkg.czilda4.escola.horario.Reposicao;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.tempo.Temporizador;
import com.luandkg.czilda4.utils.Itenizador;
import com.luandkg.czilda4.utils.ListaGenerica;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.utils.Widget;
import com.luandkg.czilda4.utils.PaletaDeCores;
import com.luandkg.czilda4.escola.organizacao.Professor;
import com.luandkg.czilda4.escola.Professores;
import com.luandkg.czilda4.escola.horario.TocadorDeSinalEscolar;
import com.luandkg.czilda4.escola.organizacao.TurmaItem;
import com.luandkg.czilda4.databinding.FragmentTurmaBinding;

import java.util.ArrayList;

public class HorarioFragment extends Fragment {

    private FragmentTurmaBinding binding;

    private ImageView mProgresoEscola;

    private Button mHoje;
    private Button mSegunda;
    private Button mTerca;
    private Button mQuarta;
    private Button mQuinta;
    private Button mSexta;
    private ListView mLista;
    private TextView mFazendo;
    private TextView mRodape;

    private TocadorDeSinalEscolar mTocadorDeSinalEscolar;
    private String mSelecionado = "";
    private Professor mProfessor;
    private Temporizador mTemporizador;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTurmaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mProgresoEscola = (ImageView) binding.progressoGeral;

        mHoje = (Button) binding.hoje;
        mSegunda = (Button) binding.segunda;
        mTerca = (Button) binding.terca;
        mQuarta = (Button) binding.quarta;
        mQuinta = (Button) binding.quinta;
        mSexta = (Button) binding.sexta;
        mFazendo = (TextView) binding.fazendo;
        mRodape = (TextView) binding.rodape;

        mLista = (ListView) binding.lista;



        mProgresoEscola.setBackgroundColor(Color.TRANSPARENT);

        mSelecionado = "HOJE";


        mProfessor = Professores.getProfessorCorrente();

        mRodape.setText("Professor " + mProfessor.getNome());


        menu();


        mHoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = "HOJE";
                menu();
            }
        });

        mSegunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.SEGUNDA;
                menu();
            }
        });

        mTerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.TERCA;
                menu();
            }
        });

        mQuarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.QUARTA;
                menu();
            }
        });

        mQuinta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.QUINTA;
                menu();
            }
        });

        mSexta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.SEXTA;
                menu();
            }
        });


        mTemporizador = new Temporizador();
        mTemporizador.setProgressGrande(0);

        mTemporizador.set(Calendario.getDiaAtual(), 0,false, mProfessor);
        mTemporizador.podeDesenhar();

        mTocadorDeSinalEscolar = new TocadorDeSinalEscolar(this.getContext(), mTemporizador, mFazendo, mProgresoEscola, mProfessor);

        mTocadorDeSinalEscolar.run();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void menu() {

        int VERDE = PaletaDeCores.VERDE;
        int VERMELHO = PaletaDeCores.VERMELHO;
        int AMARELO = PaletaDeCores.AMARELO;

        mHoje.setBackgroundColor(VERMELHO);
        mSegunda.setBackgroundColor(VERMELHO);
        mTerca.setBackgroundColor(VERMELHO);
        mQuarta.setBackgroundColor(VERMELHO);
        mQuinta.setBackgroundColor(VERMELHO);
        mSexta.setBackgroundColor(VERMELHO);


        if (mSelecionado.contentEquals("HOJE")) {

            mHoje.setBackgroundColor(VERDE);

            String eHoje = Calendario.getDiaAtual();

            String HOJE_DATA = Calendario.getADMComBarras();

            if (mProfessor.temReposicao(HOJE_DATA)) {

                Reposicao eReposicao = mProfessor.getReposicao(HOJE_DATA);
                eHoje = eReposicao.getReferente();

            }


            if (Calendario.isIgual(Calendario.SEGUNDA, eHoje)) {
                mSegunda.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.TERCA, eHoje)) {
                mTerca.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.QUARTA, eHoje)) {
                mQuarta.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.QUINTA, eHoje)) {
                mQuinta.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.SEXTA, eHoje)) {
                mSexta.setBackgroundColor(AMARELO);
            }


            ArrayList<TurmaItem> horarios = TurmaItem.filtrarTurmas(Calendario.getDiaAtual(), mProfessor.getTurmas());

            mLista.setAdapter(new ListaGenerica(getContext(), horarios.size(), onItem(horarios, mProfessor)));


        } else if (Calendario.isIgual(Calendario.SEGUNDA, mSelecionado)) {

            organizar(mHoje, mSegunda, Calendario.SEGUNDA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.TERCA, mSelecionado)) {

            organizar(mHoje, mTerca, Calendario.TERCA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.QUARTA, mSelecionado)) {

            organizar(mHoje, mQuarta, Calendario.QUARTA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.QUINTA, mSelecionado)) {

            organizar(mHoje, mQuinta, Calendario.QUINTA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.SEXTA, mSelecionado)) {

            organizar(mHoje, mSexta, Calendario.SEXTA, VERDE, AMARELO);

        }


    }

    public void organizar(Button eHoje, Button eButton, String eDia, int VERDE, int AMARELO) {

        eButton.setBackgroundColor(VERDE);

        ArrayList<TurmaItem> horarios = TurmaItem.filtrarTurmas(eDia, mProfessor.getTurmas());

        mLista.setAdapter(new ListaGenerica(getContext(), horarios.size(), onItem(horarios, mProfessor)));

        if (Calendario.getDiaAtual().contentEquals(eDia)) {
            eHoje.setBackgroundColor(VERDE);
            eButton.setBackgroundColor(AMARELO);
        }

    }


    public Itenizador onItem(ArrayList<TurmaItem> eLista, Professor eProfesssor) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                TurmaItem eTurmaItem = eLista.get(position);


                TurmaItemStruct mTurmaItemStruct = new TurmaItemStruct();

                Widget mWidget = null;


                if (eProfesssor.getSigla().contentEquals("GG")) {
                    mWidget = new Widget(R.layout.item_turma_gg, inflater, parent);

                    mTurmaItemStruct.nome = mWidget.getTextView(R.id.gg_turma_nome);
                    mTurmaItemStruct.status = mWidget.getButton(R.id.gg_turma_status);
                    mTurmaItemStruct.duplo = mWidget.getButton(R.id.gg_turma_duplo);
                    mTurmaItemStruct.sala = mWidget.getButton(R.id.gg_sala);


                } else {

                    mWidget = new Widget(R.layout.item_turma, inflater, parent);

                    mTurmaItemStruct.nome = mWidget.getTextView(R.id.turma_nome);
                    mTurmaItemStruct.status = mWidget.getButton(R.id.turma_status);
                    mTurmaItemStruct.duplo = mWidget.getButton(R.id.turma_duplo);


                }


                mTurmaItemStruct.duplo.setVisibility(View.INVISIBLE);

                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#37474f"));

                if (eTurmaItem.getNome().contentEquals("IN")) {

                } else {
                    mTurmaItemStruct.status.setText(eTurmaItem.getNome());
                }

                mTurmaItemStruct.nome.setText(eTurmaItem.getTempo());

                if (eTurmaItem.isDupla()) {
                    mTurmaItemStruct.duplo.setVisibility(View.VISIBLE);
                    mTurmaItemStruct.duplo.setBackgroundColor(Color.parseColor("#039be5"));
                }


                if (eProfesssor.getSigla().contentEquals("LUAN")) {

                    if (eTurmaItem.getNome().contains("9")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else if (eTurmaItem.getNome().contains("8")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
                    } else if (eTurmaItem.getNome().contains("7")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#FDD835"));
                    }

                } else if (eProfesssor.getSigla().contentEquals("GG")) {

                    if (eTurmaItem.getTipo().contains("CN")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else if (eTurmaItem.getTipo().contains("PD")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    mTurmaItemStruct.sala.setText(eTurmaItem.getSala());
                    mTurmaItemStruct.sala.setBackgroundColor(Color.parseColor("#ff8a65"));

                    if (eTurmaItem.getNome().contentEquals("I")) {
                        mTurmaItemStruct.sala.setVisibility(View.INVISIBLE);
                    }

                } else if (eProfesssor.getSigla().contentEquals("FREITAS")) {

                    if (eTurmaItem.getTipo().contains("CN")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else if (eTurmaItem.getTipo().contains("PD")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
                    }

                } else if (eProfesssor.getSigla().contentEquals("DANTAS")) {

                    if (eTurmaItem.getTipo().contains("CN")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else if (eTurmaItem.getTipo().contains("PD")) {
                        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
                    }

                }


                mWidget.setAuto();


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}