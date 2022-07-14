package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.avaliacao_continua.HistoricoDeAtividades;
import com.luandkg.czilda4.escola.avaliacao_continua.AtividadeDaTurma;
import com.luandkg.czilda4.listas.Lista_AtividadesDaTurma;

import java.util.ArrayList;

public class ListadorDeAtividadesActivity extends AppCompatActivity {

    private String mTurma;
    private TextView TV_Nome;
    private ListView LV_Lista;

    private Lista_AtividadesDaTurma mLista_AtividadesDaTurma;
    private ImageView mListador_fluxoDeAtividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listador_de_atividades);

        Intent i = this.getIntent();
        mTurma = i.getStringExtra("Turma");

        TV_Nome = (TextView) findViewById(R.id.listador_atividades_nome);
        LV_Lista = (ListView) findViewById(R.id.listador_atividades_lista);
        mListador_fluxoDeAtividades = (ImageView) findViewById(R.id.listador_fluxoDeAtividades);

        TV_Nome.setText(mTurma);

        ArrayList<AtividadeDaTurma> atividades = HistoricoDeAtividades.getAtividadesDaTurma(mTurma, BimestreCorrente.GET().getDatas(), BimestreCorrente.GET().getSemanas());

        System.out.println("Atividades :: " + atividades.size());

        int alunos_total = Escola.getAlunosComNotasVisiveisEOrdenadosDaTurma(mTurma).size();

        Bitmap imagem = FluxoFormativoContinuado.criarFluxoDeEntregaDaTurma(alunos_total, BimestreCorrente.GET(), atividades);


        mListador_fluxoDeAtividades.setImageBitmap(imagem);

        mLista_AtividadesDaTurma = new Lista_AtividadesDaTurma(getBaseContext(), atividades, mTurma);
        LV_Lista.setAdapter(mLista_AtividadesDaTurma);

    }


}