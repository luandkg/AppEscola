package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.avaliacao_continua.Perfilometro;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.avaliacao.Mensionador;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.listas.Lista_AlunosNotaFinal;

import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {

    private TextView TX_CONTADOR;

    private Button BTN_OMEGA;
    private Button BTN_ZETA;
    private Button BTN_DELTA;
    private Button BTN_ALFA;

    private ListView LISTA;
    private ImageView IV_DESEMPENHO;

    private String mNota;
    private ArrayList<AlunoContinuo> alunos_continuos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        BTN_OMEGA = (Button) findViewById(R.id.notas_ii);
        BTN_ZETA = (Button) findViewById(R.id.notas_mi);
        BTN_DELTA = (Button) findViewById(R.id.notas_ms);
        BTN_ALFA = (Button) findViewById(R.id.notas_ss);

        LISTA = (ListView) findViewById(R.id.notas_lista);
        TX_CONTADOR = (TextView) findViewById(R.id.notas_contador);
        IV_DESEMPENHO = (ImageView) findViewById(R.id.notas_desempenho);

        BTN_OMEGA.setBackgroundColor(Color.parseColor("#78909C"));
        BTN_ZETA.setBackgroundColor(Color.parseColor("#78909C"));
        BTN_DELTA.setBackgroundColor(Color.parseColor("#78909C"));
        BTN_ALFA.setBackgroundColor(Color.parseColor("#78909C"));

        TX_CONTADOR.setText("0");


        alunos_continuos = Perfilometro.getPerfis(HiperCacheDeAvaliacao.getPerfis(), BimestreCorrente.GET().getSemanas());


        Intent i = this.getIntent();
        mNota = i.getStringExtra("MENSAO");


        if (mNota.contentEquals("")) {
            mudar(Mensionador.OMEGA);
        } else {
            mudar(mNota);
        }


        BTN_OMEGA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudar(Mensionador.OMEGA);
            }
        });

        BTN_ZETA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudar(Mensionador.ZETA);
            }
        });

        BTN_DELTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudar(Mensionador.DELTA);
            }
        });

        BTN_ALFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudar(Mensionador.ALFA);
            }
        });
    }

    public void mudar(String e) {
        mNota = e;

        BTN_OMEGA.setBackgroundColor(Color.parseColor("#78909C"));
        BTN_ZETA.setBackgroundColor(Color.parseColor("#78909C"));
        BTN_DELTA.setBackgroundColor(Color.parseColor("#78909C"));
        BTN_ALFA.setBackgroundColor(Color.parseColor("#78909C"));


        if (mNota.contentEquals(Mensionador.OMEGA)) {
            BTN_OMEGA.setBackgroundColor(Color.parseColor(Mensionador.COR_OMEGA));
        } else if (mNota.contentEquals(Mensionador.ZETA)) {
            BTN_ZETA.setBackgroundColor(Color.parseColor(Mensionador.COR_ZETA));
        } else if (mNota.contentEquals(Mensionador.DELTA)) {
            BTN_DELTA.setBackgroundColor(Color.parseColor(Mensionador.COR_DELTA));
        } else if (mNota.contentEquals(Mensionador.ALFA)) {
            BTN_ALFA.setBackgroundColor(Color.parseColor(Mensionador.COR_ALFA));
        }


        ArrayList<AlunoContinuo> alunos_continuos_grupo = Mensionador.listar(mNota, alunos_continuos);


        TX_CONTADOR.setText(String.valueOf(alunos_continuos_grupo.size()));

        IV_DESEMPENHO.setImageBitmap(FluxoFormativoContinuado.criarDesempenho(alunos_continuos, mNota));


        LISTA.setAdapter(new Lista_AlunosNotaFinal(this.getBaseContext(), alunos_continuos_grupo));


    }


}