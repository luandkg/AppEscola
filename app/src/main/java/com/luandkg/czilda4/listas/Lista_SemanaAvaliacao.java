package com.luandkg.czilda4.listas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.utils.Emblemador;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinuaValores;
import com.luandkg.czilda4.escola.avaliacao.CoresDeAvaliacao;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_SemanaAvaliacao extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<SemanaContinuaValores> eLista;

    private static LayoutInflater inflater = null;

    public Lista_SemanaAvaliacao(Context eContext, ArrayList<SemanaContinuaValores> lista) {
        mContext = eContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        eLista = lista;
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


        SemanaContinuaValores semana = eLista.get(position);


        Widget mWidget = new Widget(R.layout.item_semana_avaliacao, inflater, parent);
        ImageView imagem = mWidget.getImageView(R.id.item_semana_avaliacao_imagem);
        TextView nome = mWidget.getTextView(R.id.item_semana_avaliacao_nome);
        TextView status = mWidget.getTextView(R.id.item_semana_avaliacao_status);
        TextView tempo = mWidget.getTextView(R.id.item_semana_avaliacao_tempo);
        TextView parcela = mWidget.getTextView(R.id.item_semana_avaliacao_parcela);


        imagem.setImageBitmap(Emblemador.criarNota(semana.getValor()));

        nome.setText(semana.getNomeCompleto());
        tempo.setText(semana.getDataRealizada());
        status.setText(semana.getStatusCompleto());
        parcela.setText(semana.getParcela());

        if (semana.isUltima()) {
            parcela.setTextColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));
            parcela.setTextSize(13);
            parcela.setText("+" + semana.getParcela());
        }

        mWidget.setAuto();


        return mWidget.getView();
    }


}