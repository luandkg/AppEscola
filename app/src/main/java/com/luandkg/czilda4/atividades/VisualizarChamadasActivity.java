package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.chamadas.CarregadorDeFrequencia;
import com.luandkg.czilda4.escola.utils.Emblemador;
import com.luandkg.czilda4.escola.chamadas.TurmaChamadas;
import com.luandkg.czilda4.escola.professores.Luan;
import com.luandkg.czilda4.utils.Itenizador;
import com.luandkg.czilda4.utils.ListaGenerica;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class VisualizarChamadasActivity extends AppCompatActivity {

    private ListView LISTA_DE_CHAMADAS;
    private ArrayList<TurmaChamadas> mChamadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_chamadas);

        LISTA_DE_CHAMADAS= (ListView) findViewById(R.id.visualizar_chamadas_lista);

        mChamadas = CarregadorDeFrequencia.carregar(Luan.getLuan());

        LISTA_DE_CHAMADAS.setAdapter(new ListaGenerica(getBaseContext(), mChamadas.size(), onItem(mChamadas)));

    }


    public Itenizador onItem(ArrayList<TurmaChamadas> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                TurmaChamadas eChamada = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_chamadas_chamada, inflater, parent);

                ImageView imagem = mWidget.getImageView(R.id.item_chamadas_chamada_imagem);
                TextView nome = mWidget.getTextView(R.id.item_chamadas_chamada_nome);
                TextView descricao = mWidget.getTextView(R.id.item_chamadas_chamada_descricao);


                imagem.setImageBitmap(Emblemador.criarLogo("CHAMADA", eChamada.getTurma()));

                nome.setText("Chamada da turma " + eChamada.getTurma());

                int aulas = eChamada.getAulasRealizadas();

                if (aulas == 0) {
                    descricao.setText("Nenhuma aula realizada.");
                } else if (aulas == 1) {
                    descricao.setText("Apenas 1 aula foi realizada.");
                } else if (aulas > 0) {
                    descricao.setText(eChamada.getAulasRealizadas() + " aulas realizadas.");
                }

                mWidget.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(), ChamadasVisualizador.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("Turma", eChamada.getTurma());

                        v.getContext().startActivity(intent);


                    }
                });

                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }

}