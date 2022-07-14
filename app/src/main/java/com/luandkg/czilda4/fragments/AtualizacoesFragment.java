package com.luandkg.czilda4.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luandkg.czilda4.atividades.RealizarAvaliacao;
import com.luandkg.czilda4.atividades.RealizarChamada;
import com.luandkg.czilda4.escola.atualizador.Atualizacao;
import com.luandkg.czilda4.escola.atualizador.Atualizador;
import com.luandkg.czilda4.escola.utils.Emblemador;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.listas.Itenizador;
import com.luandkg.czilda4.listas.ListaGenerica;
import com.luandkg.czilda4.utils.Acao;
import com.luandkg.czilda4.utils.tempo.Calendario;
import com.luandkg.czilda4.utils.Widget;
import com.luandkg.czilda4.databinding.FragmentAtualizacoesBinding;

import java.util.ArrayList;

public class AtualizacoesFragment extends Fragment {

    private FragmentAtualizacoesBinding mInterface;

    private ListView LISTA_ATUALIZACOES;
    private ArrayList<Atualizacao> mAtualizacoes;

    public static Acao RECARREGAR_LISTA;
    private Context mContexto;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInterface = FragmentAtualizacoesBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        LISTA_ATUALIZACOES = mInterface.listaDeAtualizacoes;

        mContexto = this.getContext();

        RECARREGAR_LISTA = getRecarregar();

        mAtualizacoes = Atualizador.getAtualizacoes();

        LISTA_ATUALIZACOES.setAdapter(new ListaGenerica(mContexto, mAtualizacoes.size(), onItem(mAtualizacoes)));


        return root;
    }

    public Acao getRecarregar() {
        return new Acao() {
            @Override
            public void fazer() {
                mAtualizacoes = Atualizador.getAtualizacoes();
                LISTA_ATUALIZACOES.setAdapter(new ListaGenerica(mContexto, mAtualizacoes.size(), onItem(mAtualizacoes)));
            }
        };
    }


    public Itenizador onItem(ArrayList<Atualizacao> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                Atualizacao eAtualizacao = eLista.get(position);

                Widget mWidget = new Widget(R.layout.item_atualizacao, inflater, parent);

                ImageView imagem = mWidget.getImageView(R.id.atualizacao_imagem);
                TextView nome = mWidget.getTextView(R.id.atualizacao_nome);
                TextView novidades = mWidget.getTextView(R.id.atualizacao_contador);
                TextView evento = mWidget.getTextView(R.id.atualizacao_status);
                TextView tempo = mWidget.getTextView(R.id.atualizacao_tempo);


                nome.setText(eAtualizacao.getNome());
                evento.setText(eAtualizacao.getEvento());

                String faixa_de_tempo_data = eAtualizacao.getData();
                String faixa_de_tempo_hora = eAtualizacao.getHora();

                if (faixa_de_tempo_data.length() == 10) {
                    tempo.setText(Calendario.formate_dia_mes(faixa_de_tempo_data));
                } else {
                    tempo.setText("");
                }

                if (faixa_de_tempo_data.contentEquals(Calendario.getADMComBarras())) {

                    if (faixa_de_tempo_hora.length() == 5) {
                        tempo.setText(Calendario.formate_hora_min(faixa_de_tempo_hora));
                    }

                }


                if (eAtualizacao.getNome().contains("Chamada")) {
                    imagem.setImageBitmap(Emblemador.criarLogo("CHAMADA", eAtualizacao.getTurma()));
                } else if (eAtualizacao.getNome().contains("Avaliação")) {
                    imagem.setImageBitmap(Emblemador.criarLogo("AVALIACAO", eAtualizacao.getTurma()));
                }

                if (eAtualizacao.getNovidades() == 0) {
                    novidades.setBackgroundResource(android.R.color.transparent);
                    novidades.setText("");
                } else {
                    novidades.setBackgroundResource(R.drawable.circulo);
                    novidades.setText(String.valueOf(eAtualizacao.getNovidades()));
                }


                mWidget.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (eAtualizacao.getNome().contains("Chamada")) {

                            Intent intent = new Intent(v.getContext(), RealizarChamada.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            intent.putExtra("Turma", eAtualizacao.getTurma());


                            v.getContext().startActivity(intent);


                        } else if (eAtualizacao.getNome().contains("Avaliação")) {

                            Intent intent = new Intent(v.getContext(), RealizarAvaliacao.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            intent.putExtra("Turma", eAtualizacao.getTurma());

                            v.getContext().startActivity(intent);

                        }

                    }
                });


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mInterface = null;
    }
}