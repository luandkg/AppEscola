package com.luandkg.czilda4.listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.atividades.RealizarAvaliacaoContinuaAtrasadaActivity;
import com.luandkg.czilda4.escola.avaliacao_continua.AtividadeDaTurma;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_AtividadesDaTurma extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<AtividadeDaTurma> eLista;

    private static LayoutInflater inflater = null;
    private String mTurma;

    public Lista_AtividadesDaTurma(Context eContext, ArrayList<AtividadeDaTurma> lista, String eTurma) {
        mContext = eContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        eLista = lista;
        mTurma = eTurma;
        mQuantidade = lista.size();
    }

    @Override
    public int getCount() {
        return mQuantidade;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        AtividadeDaTurma atividade = eLista.get(position);


        Widget mWidget = new Widget(R.layout.item_atividade, inflater, parent);

        TextView nome = mWidget.getTextView(R.id.item_atividade_nome);
        TextView status = mWidget.getTextView(R.id.item_atividade_status);
        TextView modo = mWidget.getTextView(R.id.item_atividade_tempo);

        ImageView grafico = mWidget.getImageView(R.id.item_atividade_imagem);

        nome.setText(atividade.getNome());
        status.setText(atividade.getStatus());

        modo.setText("");

        grafico.setImageBitmap(FluxoFormativoContinuado.criarAvaliacao(atividade.getFizeram(), atividade.getTotal()));

        modo.setText(String.valueOf(atividade.getFizeram()));

        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), RealizarAvaliacaoContinuaAtrasadaActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Turma", mTurma);
                intent.putExtra("Antiga", "SIM");
                intent.putExtra("AntigaArquivo", Local.LOCAL_AVALIANDO + "/" + atividade.getArquivo());


                v.getContext().startActivity(intent);


            }
        });


        mWidget.setAuto();


        return mWidget.getView();
    }


}