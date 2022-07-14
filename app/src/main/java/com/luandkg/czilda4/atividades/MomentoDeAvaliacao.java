package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.avaliacao.Mensoes;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinuaCarregada;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanasDeAtividades;
import com.luandkg.czilda4.escola.avaliacao_continua.SuperCache;
import com.luandkg.czilda4.listas.Itenizador;
import com.luandkg.czilda4.listas.ListaGenerica;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.PaletaDeCores;
import com.luandkg.czilda4.utils.tempo.Calendario;
import com.luandkg.czilda4.utils.Widget;
import com.luandkg.czilda4.utils.tempo.Data;

import java.util.ArrayList;

public class MomentoDeAvaliacao extends AppCompatActivity {

    private String mSemana;

    private TextView mTitulo;
    private TextView mDatas;

    private ListView LISTA;
    private ImageView IMG_VISUALIZADOR;

    private TextView TX_ZETA;
    private TextView TX_DELTA;
    private TextView TX_ALFA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_momento_de_avaliacao);

        mTitulo = (TextView) findViewById(R.id.momento_avaliacao_titulo);
        mDatas = (TextView) findViewById(R.id.momento_avaliacao_data);

        LISTA = (ListView) findViewById(R.id.momento_avaliacao_lista);
        IMG_VISUALIZADOR = (ImageView) findViewById(R.id.momento_avaliacao_grafico);

        TX_ZETA = (TextView) findViewById(R.id.momento_avaliacao_zeta);
        TX_DELTA = (TextView) findViewById(R.id.momento_avaliacao_delta);
        TX_ALFA = (TextView) findViewById(R.id.momento_avaliacao_alfa);

        mSemana = getIntent().getStringExtra("Semana");

        mTitulo.setText(mSemana);

        Bimestre eBimestre = BimestreCorrente.GET();

        ArrayList<Aluno> avaliados = new ArrayList<Aluno>();

        if (mSemana.contentEquals("RECUPERACAO_BIMESTRAL")) {

            mTitulo.setText("RECUPERAÇÃO BIMESTRAL");

            SuperCache eSuperCache = new SuperCache(Local.LOCAL_CACHE + "/" + Local.ARQUIVO_NOTAS);

            avaliados = eSuperCache.getAlunosDeRecuperacao(eBimestre.getID());


        } else {


            int index = 0;

            if (mSemana.length() > 0) {
                index = Integer.parseInt(mSemana);
            }

            if (eBimestre.getSemanas().size() >= index && eBimestre.getSemanas().size() >= index) {

                mTitulo.setText("SEMANA DE ATIVIDADES " + eBimestre.getSemanas().get(index).getNome());
                mDatas.setText(eBimestre.getSemanas().get(index).getStatus());

                SuperCache eSuperCache = new SuperCache(Local.LOCAL_CACHE + "/" + Local.ARQUIVO_NOTAS);

                avaliados = eSuperCache.getAlunosDaSemanaToda(eBimestre.getDatas(), eBimestre.getSemanas().get(index).getDatas());

            }

        }


        IMG_VISUALIZADOR.setImageBitmap(FluxoFormativoContinuado.onSemanaFluxo(avaliados));

        Mensoes mensoes = Atividade.contarMensoes(avaliados);


        TX_ZETA.setText(String.valueOf(mensoes.zeta()));
        TX_DELTA.setText(String.valueOf(mensoes.delta()));
        TX_ALFA.setText(String.valueOf(mensoes.alfa()));


        LISTA.setAdapter(new ListaGenerica(getBaseContext(), avaliados.size(), onItem(avaliados)));


    }


    public Itenizador onItem(ArrayList<Aluno> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                Aluno eAlunoChamada = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_momento_avaliativo, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.item_momento_avaliativo_aluno);
                TextView data = mWidget.getTextView(R.id.item_momento_avaliativo_data);
                TextView nota = mWidget.getTextView(R.id.item_momento_avaliativo_status);


                nome.setText(eAlunoChamada.getNome());


                data.setText(Calendario.inverter_mes_dia(String.valueOf(eAlunoChamada.getTurma())));
                nota.setText(String.valueOf(eAlunoChamada.getStatus()));

                data.setBackgroundColor(PaletaDeCores.VERDE);

                if (eAlunoChamada.getStatus().contentEquals("0")) {
                    nota.setBackgroundColor(PaletaDeCores.CINZA);
                } else if (eAlunoChamada.getStatus().contentEquals("1")) {
                    nota.setBackgroundColor(PaletaDeCores.VERMELHO);
                } else if (eAlunoChamada.getStatus().contentEquals("2")) {
                    nota.setBackgroundColor(PaletaDeCores.AMARELO);
                } else if (eAlunoChamada.getStatus().contentEquals("3")) {
                    nota.setBackgroundColor(PaletaDeCores.VERDE);
                }


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}