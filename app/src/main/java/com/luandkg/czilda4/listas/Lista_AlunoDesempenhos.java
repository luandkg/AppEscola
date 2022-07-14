package com.luandkg.czilda4.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.avaliacao_continua.Desempenho;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_AlunoDesempenhos extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<Desempenho> eLista;

    private static LayoutInflater inflater = null;

    public Lista_AlunoDesempenhos(Context eContext, ArrayList<Desempenho> lista) {
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


        Desempenho eDesempenho = eLista.get(position);
        Widget mWidget = new Widget(R.layout.item_aluno_desempenho, inflater, parent);

        TextView nome = mWidget.getTextView(R.id.item_aluno_desempenho_nome);
        TextView status = mWidget.getTextView(R.id.item_aluno_desempenho_status);
        TextView tempo = mWidget.getTextView(R.id.item_aluno_desempenho_tempo);


        ImageView grafico = mWidget.getImageView(R.id.item_aluno_desempenho_imagem);


        nome.setText(eDesempenho.getNome());

        status.setText(eDesempenho.getValor());

        grafico.setImageBitmap(FluxoFormativoContinuado.criarDesempenhoCirculo(eDesempenho.getNome(),eDesempenho.getQuantidade()));

        tempo.setText("");

        mWidget.setAuto();


        return mWidget.getView();
    }


}