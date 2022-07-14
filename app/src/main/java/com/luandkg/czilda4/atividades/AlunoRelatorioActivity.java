package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.listas.Lista_SemanaAvaliacao;
import com.luandkg.czilda4.utils.Texto;

public class AlunoRelatorioActivity extends AppCompatActivity {

    private TextView TV_Aluno;
    private ImageView IV_Aluno;
    private ListView LV_Semanas;
    private TextView TV_AlunoNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_relatorio);


        TV_Aluno = (TextView) findViewById(R.id.relatorio_nome);
        TV_AlunoNota = (TextView) findViewById(R.id.relatorio_nota);
        IV_Aluno = (ImageView) findViewById(R.id.relatorio_fluxo);
        LV_Semanas = (ListView) findViewById(R.id.relatorio_semanas);

        Intent it = getIntent();
        String aluno_id = it.getStringExtra("AlunoID");


        Bimestre eBimestre = BimestreCorrente.GET();

        AlunoContinuo perfil = HiperCacheDeAvaliacao.getPerfil(aluno_id, eBimestre.getSemanas());


        TV_Aluno.setText(perfil.getNome());
        TV_AlunoNota.setText(Texto.doubleNumC2(perfil.getNotaComRecuperacao()));

        IV_Aluno.setImageBitmap(FluxoFormativoContinuado.criarFluxoDeEntregaDoAluno(eBimestre, perfil.getSemanas()));

        LV_Semanas.setAdapter(new Lista_SemanaAvaliacao(getBaseContext(), perfil.getSemanas()));

    }


}