package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.escola.alunos.AlunoChamadas;
import com.luandkg.czilda4.escola.chamadas.FluxoDeChamadas;
import com.luandkg.czilda4.escola.chamadas.TurmaChamadas;
import com.luandkg.czilda4.escola.chamadas.CarregadorDeFrequencia;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.utils.Itenizador;
import com.luandkg.czilda4.utils.ListaGenerica;
import com.luandkg.czilda4.utils.PaletaDeCores;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class ChamadasVisualizador extends AppCompatActivity {

    private String mTurma;
    private TextView mTitulo;
    private ListView LISTA;
    private ImageView GRAFICO;

    private TurmaChamadas mTurmaChamadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamadas_visualizador);

        mTitulo = (TextView) findViewById(R.id.chamadas_visualizador_titulo);
        LISTA = (ListView) findViewById(R.id.chamadas_visualizador_lista);
        GRAFICO = (ImageView) findViewById(R.id.chamadas_visualizador_grafico);

        Intent it = getIntent();
        mTurma = it.getStringExtra("Turma");

        mTitulo.setText(mTurma);

        mTurmaChamadas = CarregadorDeFrequencia.getTurma(mTurma);

        ArrayList<AlunoChamadas> chamadas = AlunoChamadas.ordenar(mTurmaChamadas.getAlunos());


        GRAFICO.setImageBitmap(FluxoDeChamadas.criarFluxoDePresencaDaTurma(mTurmaChamadas,BimestreCorrente.GET()));


        LISTA.setAdapter(new ListaGenerica(getBaseContext(), chamadas.size(), onItem(chamadas)));

    }


    public Itenizador onItem(ArrayList<AlunoChamadas> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                AlunoChamadas eAlunoChamada = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_aluno_frequencia, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.item_aluno_frequencia_nome);
                Button presente = mWidget.getButton(R.id.item_aluno_frequencia_presente);
                Button falta = mWidget.getButton(R.id.item_aluno_frequencia_falta);


                nome.setText(eAlunoChamada.getNome());
                presente.setText(String.valueOf(eAlunoChamada.getPresente()));
                falta.setText(String.valueOf(eAlunoChamada.getFalta()));

                presente.setBackgroundColor(PaletaDeCores.VERDE);
                falta.setBackgroundColor(PaletaDeCores.VERMELHO);

                mWidget.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(v.getContext(), AlunoFrequenciaActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("AlunoID", eAlunoChamada.getID());

                        v.getContext().startActivity(intent);

                    }
                });

                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}