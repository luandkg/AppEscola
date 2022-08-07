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
import com.luandkg.czilda4.atividades.AlunoDesempenhoActivity;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_RealizarChamada extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<Aluno> eLista;

    private static LayoutInflater inflater = null;

    public Lista_RealizarChamada(Context eContext, ArrayList<Aluno> lista) {
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


        Aluno eAluno = eLista.get(position);


        Widget mWidget = new Widget(R.layout.item_aluno_chamada, inflater, parent);

        TextView nome = mWidget.getTextView(R.id.tvNome);
        Button clicar = mWidget.getButton(R.id.clicar);


        if (eAluno.getStatus().contentEquals("PRESENTE")) {
            clicar.setBackgroundColor(Color.parseColor("#8bc34a"));
        } else if (eAluno.getStatus().contentEquals("AUSENTE")) {
            clicar.setBackgroundColor(Color.parseColor("#ff5722"));
        }


        nome.setText(eAluno.getNome());


        clicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eAluno.mudarStatus();

                if (eAluno.getStatus().contentEquals("PRESENTE")) {
                    clicar.setBackgroundColor(Color.parseColor("#8bc34a"));
                } else if (eAluno.getStatus().contentEquals("AUSENTE")) {
                    clicar.setBackgroundColor(Color.parseColor("#ff5722"));
                }

            }
        });

        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = new Intent(mContext, AlunoDesempenhoActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // intent.putExtra("AlunoID", String.valueOf(eAluno.getID()));
              //  mContext.startActivity(intent);

            }
        });

        mWidget.setAuto();


        return mWidget.getView();
    }


}