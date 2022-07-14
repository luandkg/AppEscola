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
import com.luandkg.czilda4.atividades.AlunoRelatorioActivity;
import com.luandkg.czilda4.atividades.RealizarAvaliacaoContinuaAtrasadaActivity;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.AtividadeDaTurma;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.utils.Texto;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class Lista_AlunosEmVivencia extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<Aluno> eLista;

    private static LayoutInflater inflater = null;

    public Lista_AlunosEmVivencia(Context eContext, ArrayList<Aluno> lista) {
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


        Aluno aluno_vivencia = eLista.get(position);


        Widget mWidget = new Widget(R.layout.item_atividade, inflater, parent);

        TextView nome = mWidget.getTextView(R.id.item_atividade_nome);
        TextView status = mWidget.getTextView(R.id.item_atividade_status);
        TextView modo = mWidget.getTextView(R.id.item_atividade_tempo);

        ImageView grafico = mWidget.getImageView(R.id.item_atividade_imagem);


        ArrayList<AlunoContinuo> alunos_continuos = Escola.getAlunosContinuosVisiveisEOrdenadosDaEscola();

        MetodoContinuo.avaliarComDocumento(HiperCacheDeAvaliacao.getDocumento(), alunos_continuos, BimestreCorrente.GET().getSemanas());

        String atividades = "";

        for (AlunoContinuo aa : alunos_continuos) {
            if (aa.getID() == aluno_vivencia.getIDInt()) {

                modo.setText(Texto.doubleNumC2(aa.getAcumuladoContinuidadeComRecuperacao()));

                grafico.setImageBitmap(FluxoFormativoContinuado.criarAvaliacao(aa.getNivel(), 10));

                if (aa.getAtividadesEntregues() == 1) {
                    atividades = aa.getAtividadesEntregues() + " atividade realizada !";
                } else if (aa.getAtividadesEntregues() > 1) {
                    atividades = aa.getAtividadesEntregues() + " atividades realizadas !";
                }

                break;
            }
        }


        nome.setText(aluno_vivencia.getNome());
        status.setText(aluno_vivencia.getVivenciaOrigem() + " ->> " + aluno_vivencia.getTurma() + " : " + atividades);


        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AlunoRelatorioActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("AlunoID", String.valueOf(aluno_vivencia.getID()));

                mContext.startActivity(intent);

            }
        });


        mWidget.setAuto();


        return mWidget.getView();
    }


}