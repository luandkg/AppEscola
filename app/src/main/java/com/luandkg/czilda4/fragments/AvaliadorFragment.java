package com.luandkg.czilda4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.avaliacao.Recuperacao;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanasDeAtividades;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinuaCarregada;
import com.luandkg.czilda4.escola.chamadas.Chamada;
import com.luandkg.czilda4.listas.Lista_Avaliacoes;
import com.luandkg.czilda4.databinding.FragmentAvaliadorBinding;
import com.luandkg.czilda4.utils.Animattor;

import java.util.ArrayList;

public class AvaliadorFragment extends Fragment {

    private FragmentAvaliadorBinding mInterface;

    private ListView LISTA_AVALIACAO;
    private ImageView IMG_VISUALIZADOR;
    private TextView TV_ATIVIDADES;

    private ArrayList<Aluno> mAlunos;
    private ArrayList<SemanaContinuaCarregada> mSemanas;

    private Animattor mAvaliadorAnimattor;
    private Lista_Avaliacoes mLista_Avaliacoes;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInterface = FragmentAvaliadorBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        LISTA_AVALIACAO = mInterface.fragmentAvaliadorLista;
        IMG_VISUALIZADOR = (ImageView) mInterface.fluxo;
        TV_ATIVIDADES = (TextView) mInterface.avaliadorAtividades;

        mAlunos = Escola.getAlunosVisiveis();


        mSemanas = SemanasDeAtividades.carregarSemanas(Local.LOCAL_CACHE + "/" + Local.ARQUIVO_SEMANAS);

        int atividades = SemanasDeAtividades.getAtividades(Local.LOCAL_CACHE + "/" + Local.ARQUIVO_SEMANAS);
        int presentes_porcentagem = Chamada.getPresentesPorcentagem();
        int recuperacao_porcentagem = Recuperacao.getRecuperacaoPorcentagem();


        TV_ATIVIDADES.setText(String.valueOf(atividades));

        mLista_Avaliacoes = new Lista_Avaliacoes(getContext(), mSemanas);
        LISTA_AVALIACAO.setAdapter(mLista_Avaliacoes);


        IMG_VISUALIZADOR.setImageBitmap(FluxoFormativoContinuado.onFluxo(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos));

        mAvaliadorAnimattor = new Animattor(getContext(), IMG_VISUALIZADOR);

        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 10));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 20));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 40));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 60));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 80));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 100));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 120));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 140));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 160));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 180));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 200));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 220));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 240));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 260));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 280));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 300));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 320));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 340));
        mAvaliadorAnimattor.adicionar(FluxoFormativoContinuado.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 360));

        mAvaliadorAnimattor.esperarParaComecar(1*35*1000);
        mAvaliadorAnimattor.iniciar();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mInterface = null;
    }
}