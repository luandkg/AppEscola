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
import com.luandkg.czilda4.atividades.AlunoDesempenhoActivity;
import com.luandkg.czilda4.atividades.AlunoRelatorioActivity;
import com.luandkg.czilda4.atividades.MomentoDeAvaliacao;
import com.luandkg.czilda4.atividades.RealizarAvaliacao;
import com.luandkg.czilda4.atividades.RealizarChamada;
import com.luandkg.czilda4.escola.atualizador.Atualizacao;
import com.luandkg.czilda4.escola.avaliacao.CoresDeAvaliacao;
import com.luandkg.czilda4.escola.avaliacao.Mensionador;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinuaCarregada;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.tempo.Hoje;
import com.luandkg.czilda4.escola.utils.Emblemador;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_BuscandoAlunos extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<AlunoContinuo> eLista;

    private static LayoutInflater inflater = null;

    public Lista_BuscandoAlunos(Context eContext, ArrayList<AlunoContinuo> lista) {
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


        AlunoContinuo alunoContinuo = eLista.get(position);

        Widget mWidget = new Widget(R.layout.item_atualizacao, inflater, parent);

        ImageView imagem = mWidget.getImageView(R.id.atualizacao_imagem);
        TextView nome = mWidget.getTextView(R.id.atualizacao_nome);
        TextView novidades = mWidget.getTextView(R.id.atualizacao_contador);
        TextView evento = mWidget.getTextView(R.id.atualizacao_status);
        TextView tempo = mWidget.getTextView(R.id.atualizacao_tempo);


        nome.setText(alunoContinuo.getNome());

        String entregues = "Nenhuma atividade realizada !";
        if (alunoContinuo.getAtividadesEntregues() == 1) {
            entregues = "1 atividade entregue !";
        } else if (alunoContinuo.getAtividadesEntregues() > 1) {
            entregues = alunoContinuo.getAtividadesEntregues() + " atividade entregue !";
        }

        evento.setText(entregues);


        String nao_existe = "#bdbdbd";
        String existe = "#f44336";
        String esta_hoje = "#4caf50";

        String HOJE_DIA = Calendario.getDiaAtual();
        String HOJE = Calendario.getADMComTracoInferior();


        String qual_cor = nao_existe;

        ArrayList<String> turmas_hoje = Hoje.getTurmas(HOJE_DIA);


        if (turmas_hoje.contains(String.valueOf(alunoContinuo.getTurma()))) {
            qual_cor = existe;
        }

        if (Hoje.estava_presente(HOJE, String.valueOf(alunoContinuo.getID()))) {
            qual_cor = esta_hoje;
        }

        imagem.setImageBitmap(Emblemador.criarAluno(qual_cor, alunoContinuo.getTurma()));



        String mensao = Mensionador.getMensao(alunoContinuo.getAcumuladoContinuidadeComRecuperacao());

        novidades.setBackgroundColor(Color.parseColor(Mensionador.getCorDaMensao(mensao)));
        novidades.setText(String.valueOf(mensao.charAt(0)));


        if (alunoContinuo.getUltimaAtividadeRealizada().length() == 10) {
            tempo.setText(Calendario.formate_dia_mes(Data.toData(alunoContinuo.getUltimaAtividadeRealizada()).getTempo()));
        } else {
            tempo.setText("--");
        }

        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AlunoRelatorioActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("AlunoID", String.valueOf(alunoContinuo.getID()));

                mContext.startActivity(intent);

            }
        });


        mWidget.setAuto();


        return mWidget.getView();
    }

}
