package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.escola.alunos.AlunoChamadas;
import com.luandkg.czilda4.escola.chamadas.DataChamada;
import com.luandkg.czilda4.escola.chamadas.CarregadorDeFrequencia;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.listas.Itenizador;
import com.luandkg.czilda4.listas.ListaGenerica;
import com.luandkg.czilda4.utils.PaletaDeCores;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class AlunoFrequenciaActivity extends AppCompatActivity {

    private String mAlunoID;
    private TextView mTitulo;
    private ListView LISTA;

    private AlunoChamadas mAlunoChamadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_frequencia);

        mTitulo = (TextView) findViewById(R.id.aluno_frequencia_titulo);
        LISTA = (ListView) findViewById(R.id.aluno_frequencia_lista);

        Intent it = getIntent();
        mAlunoID = it.getStringExtra("AlunoID");

        mTitulo.setText(mAlunoID);


        mAlunoChamadas = CarregadorDeFrequencia.getAluno(mAlunoID);

        if (mAlunoChamadas != null) {

            mTitulo.setText(mAlunoChamadas.getNome());

            LISTA.setAdapter(new ListaGenerica(this.getBaseContext(), mAlunoChamadas.getDatas().size(), onItem(mAlunoChamadas.getDatas())));

        }


    }


    public Itenizador onItem(ArrayList<DataChamada> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                DataChamada eDataChamada = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_frequencia, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.item_frequencia_nome);
                Button presente = mWidget.getButton(R.id.item_frequencia_presente);
                Button falta = mWidget.getButton(R.id.item_frequencia_falta);


                nome.setText(eDataChamada.getData());

                presente.setBackgroundColor(PaletaDeCores.CINZA);
                falta.setBackgroundColor(PaletaDeCores.CINZA);

                if (eDataChamada.getStatus()) {
                    presente.setBackgroundColor(PaletaDeCores.VERDE);
                } else {
                    falta.setBackgroundColor(PaletaDeCores.VERMELHO);
                }


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}