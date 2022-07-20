package com.luandkg.czilda4.listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.atividades.AlunoDesempenhoActivity;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.widgets.Organizadores;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_RealizarAvaliacao extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<AlunoComNota> eLista;

    private static LayoutInflater inflater = null;
    private String eCampo;

    public Lista_RealizarAvaliacao(Context eContext, String campo, ArrayList<AlunoComNota> lista) {
        mContext = eContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        eLista = lista;
        mQuantidade = lista.size();
        eCampo = campo;
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


        AlunoComNota eAluno = eLista.get(position);

        Widget mWidget = new Widget(R.layout.item_aluno_nota, inflater, parent);

        TextView nome = mWidget.getTextView(R.id.item_aluno_nome);

        Button zero = mWidget.getButton(R.id.item_aluno_zero);
        Button um = mWidget.getButton(R.id.item_aluno_um);
        Button dois = mWidget.getButton(R.id.item_aluno_dois);
        Button tres = mWidget.getButton(R.id.item_aluno_tres);


        zero.setText("");
        um.setText("");
        dois.setText("");
        tres.setText("");

        nome.setText(eAluno.getNome());

        Organizadores.limpar(zero, um, dois, tres);
        Organizadores.onNivel(eAluno.getNota(eCampo), zero, um, dois, tres);


        clicar(zero, eAluno, eCampo, "0", zero, um, dois, tres);
        clicar(um, eAluno, eCampo, "1", zero, um, dois, tres);
        clicar(dois, eAluno, eCampo, "2", zero, um, dois, tres);
        clicar(tres, eAluno, eCampo, "3", zero, um, dois, tres);

        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AlunoDesempenhoActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("AlunoID", String.valueOf(eAluno.getID()));

                mContext.startActivity(intent);

            }
        });


        mWidget.setAuto();

        return mWidget.getView();
    }

    public void clicar(Button eBotao, AlunoComNota eAluno, String eCampo, String eStatus, Button b1, Button b2, Button b3, Button b4) {

        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!eAluno.getNota(eCampo).contentEquals(eStatus)) {
                    eAluno.setNota(eCampo, eStatus, Calendario.getADMComTracoInferior());
                }

                Organizadores.limpar(b1, b2, b3, b4);
                Organizadores.onNivel(eAluno.getNota(eCampo), b1, b2, b3, b4);

                System.out.println("Mudar :: " + eCampo + " -->> " + eAluno.getNota(eCampo));

            }
        });


    }


}