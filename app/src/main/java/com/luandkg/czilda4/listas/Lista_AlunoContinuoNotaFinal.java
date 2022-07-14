package com.luandkg.czilda4.listas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.atividades.AlunoRelatorioActivity;
import com.luandkg.czilda4.escola.avaliacao.Mensionador;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.NotaString;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_AlunoContinuoNotaFinal extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<AlunoContinuo> eLista;

    private static LayoutInflater inflater = null;

    public Lista_AlunoContinuoNotaFinal(Context eContext, ArrayList<AlunoContinuo> lista) {
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


        AlunoContinuo eAluno = eLista.get(position);

        Widget mWidget = new Widget(R.layout.item_aluno_nota_final, inflater, parent);

        TextView nome = mWidget.getTextView(R.id.notafinal_nome);

        Button nota = mWidget.getButton(R.id.notafinal_valor);
        Button parte1 = mWidget.getButton(R.id.notafinal_parte1);
        Button parte2 = mWidget.getButton(R.id.notafinal_parte2);


        nome.setText(eAluno.getNome());

        double notafinal = eAluno.getAcumuladoContinuidadeComRecuperacao();

        parte1.setText((NotaString.PARTE_A(notafinal)));
        parte2.setText((NotaString.PARTE_B(notafinal)));


        parte1.setBackgroundColor(Color.parseColor("#37474f"));
        parte2.setBackgroundColor(Color.parseColor("#37474f"));

        nota.setText((NotaString.FINAL(notafinal)));


        if (notafinal >= 0 && notafinal < 3) {
            nota.setBackgroundColor(Color.parseColor(Mensionador.COR_OMEGA));
        } else if (notafinal >= 3 && notafinal < 5) {
            nota.setBackgroundColor(Color.parseColor(Mensionador.COR_ZETA));
        } else if (notafinal >= 5 && notafinal < 7) {
            nota.setBackgroundColor(Color.parseColor(Mensionador.COR_DELTA));
        } else if (notafinal >= 7 && notafinal <= 10) {
            nota.setBackgroundColor(Color.parseColor(Mensionador.COR_ALFA));
        }


        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AlunoRelatorioActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("AlunoID", String.valueOf(eAluno.getID()));

                mContext.startActivity(intent);

            }
        });


        mWidget.setAuto();

        return mWidget.getView();

    }


}