package com.luandkg.czilda4.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.atividades.RealizarAvaliacaoContinua;
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
import com.luandkg.czilda4.databinding.FragmentSegundoBimestreBinding;

import java.util.ArrayList;


public class SegundoBimestreFragment extends Fragment {

    private FragmentSegundoBimestreBinding mInterface;

    private ListView LISTA_ATUALIZACOES;
    private ArrayList<Atualizacao> mAtualizacoes;

    public static Acao RECARREGAR_LISTA;
    public Context mContexto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInterface = FragmentSegundoBimestreBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        LISTA_ATUALIZACOES = mInterface.segundobimestreLista;

        mContexto = this.getContext();

        RECARREGAR_LISTA = getRecarregar();

        mAtualizacoes = Atualizador.getAtualizacoesAvaliacoes();

        Atualizador.ordenar(mAtualizacoes);

        LISTA_ATUALIZACOES.setAdapter(new ListaGenerica(mContexto, mAtualizacoes.size(), onItem(mAtualizacoes)));


        return root;

    }

    public Acao getRecarregar() {
        return new Acao() {
            @Override
            public void fazer() {

                mAtualizacoes = Atualizador.getAtualizacoesAvaliacoes();

                Atualizador.ordenar(mAtualizacoes);

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

                String eTempo = eAtualizacao.getData();

                if (eTempo.length() == 10) {

                    String dia = String.valueOf(eTempo.charAt(8)) + String.valueOf(eTempo.charAt(9));
                    String mes = String.valueOf(eTempo.charAt(5)) + String.valueOf(eTempo.charAt(6));

                    String sMes = Calendario.getMesPrefixo(mes);

                    tempo.setText(dia + " de " + sMes);

                } else {
                    tempo.setText("");
                }

                if (eTempo.contentEquals(Calendario.getADMComBarras())) {

                    String eHora = eAtualizacao.getHora();
                    if (eHora.length() == 5) {

                        String hora = String.valueOf(eHora.charAt(0)) + String.valueOf(eHora.charAt(1));
                        String min = String.valueOf(eHora.charAt(3)) + String.valueOf(eHora.charAt(4));

                        tempo.setText(hora + ":" + min);

                    }

                }


                if (eAtualizacao.getNome().contains("Chamada")) {
                    //    holder.imagem.setBackgroundResource(R.drawable.cc);

                    imagem.setImageBitmap(Emblemador.criarLogo("CHAMADA", eAtualizacao.getTurma()));

                } else if (eAtualizacao.getNome().contains("Avaliação")) {
                    //  holder.imagem.setBackgroundResource(R.drawable.ca);

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

                            Intent intent = new Intent(v.getContext(), RealizarAvaliacaoContinua.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            intent.putExtra("Turma", eAtualizacao.getTurma());
                            intent.putExtra("Antiga", "NAO");
                            intent.putExtra("AntigaArquivo", "");

                            v.getContext().startActivity(intent);

                        }

                    }
                });


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}