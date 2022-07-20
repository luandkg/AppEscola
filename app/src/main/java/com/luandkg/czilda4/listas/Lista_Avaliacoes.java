package com.luandkg.czilda4.listas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.atividades.MomentoDeAvaliacao;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinuaCarregada;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.avaliacao.CoresDeAvaliacao;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_Avaliacoes extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<SemanaContinuaCarregada> eLista;

    private static LayoutInflater inflater = null;

    public Lista_Avaliacoes(Context eContext, ArrayList<SemanaContinuaCarregada> lista) {
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


        SemanaContinuaCarregada eSemanaContinua = eLista.get(position);
        Widget mWidget = new Widget(R.layout.item_atividade, inflater, parent);

        TextView nome = mWidget.getTextView(R.id.item_atividade_nome);
        TextView status = mWidget.getTextView(R.id.item_atividade_status);
        TextView modo = mWidget.getTextView(R.id.item_atividade_tempo);
        TextView mais = mWidget.getTextView(R.id.item_atividade_mais);

        ImageView grafico = mWidget.getImageView(R.id.item_atividade_imagem);

        int fizeram = eSemanaContinua.getFizeram();
        int total = eSemanaContinua.getTodos();

        nome.setText(eSemanaContinua.getNome());

        status.setText(eSemanaContinua.getStatus());

        grafico.setImageBitmap(FluxoFormativoContinuado.criarAvaliacao(fizeram, total));

        modo.setText(String.valueOf(fizeram));

        if (!eSemanaContinua.getNome().contentEquals("RECUPERAÇÃO BIMESTRAL")) {
            int anterior = 0;

            SemanaContinua sc_atual = BimestreCorrente.GET().getSemanas().get(position);

            if (sc_atual.getPrimeiraData().isMenor(Calendario.getDataHoje()))

                if (position > 0 && (position - 1) >= 0) {
                    anterior = eLista.get(position - 1).getFizeram();

                    if (eSemanaContinua.getFizeram() > anterior) {

                        int diferenca = eSemanaContinua.getFizeram() - anterior;

                        mais.setText("+" + String.valueOf(diferenca));
                        mais.setTextColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));

                    } else if (eSemanaContinua.getFizeram() < anterior) {

                        int diferenca = anterior - eSemanaContinua.getFizeram();

                        mais.setText("-" + String.valueOf(diferenca));
                        mais.setTextColor(Color.parseColor(CoresDeAvaliacao.RUIM));

                    }

                }

        }


        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eSemanaContinua.getNome().contentEquals("RECUPERAÇÃO BIMESTRAL")) {

                    Intent intent = new Intent(v.getContext(), MomentoDeAvaliacao.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("Semana", "RECUPERACAO_BIMESTRAL");

                    v.getContext().startActivity(intent);

                } else {


                    Intent intent = new Intent(v.getContext(), MomentoDeAvaliacao.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("Semana", String.valueOf(eSemanaContinua.getNumero() - 1));

                    v.getContext().startActivity(intent);
                }


            }
        });


        mWidget.setAuto();


        return mWidget.getView();
    }


}