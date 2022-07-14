package com.luandkg.czilda4.atividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.chamadas.Chamada;
import com.luandkg.czilda4.escola.utils.ContadorSN;
import com.luandkg.czilda4.escola.chamadas.Frequenciometro;
import com.luandkg.czilda4.fragments.AtualizacoesFragment;
import com.luandkg.czilda4.escola.atualizador.Atualizador;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.listas.Lista_RealizarChamada;

import java.util.ArrayList;

public class RealizarChamada extends AppCompatActivity {

    private TextView TXT_Titulo;


    private Button BTN_SALVAR;
    private Button BTN_DESEMPENHOS;

    private ListView lista;

    private String mTurma = "A";

    private ArrayList<Aluno> mAlunos;
    private Context mContexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_chamada);

        TXT_Titulo = (TextView) findViewById(R.id.realizarChamada_titulo);

        BTN_SALVAR = (Button) findViewById(R.id.realizarChamada_salvar);
        BTN_DESEMPENHOS = (Button) findViewById(R.id.realizarChamada_desempenhos);

        lista = (ListView) findViewById(R.id.realizarChamada_lista);

        Intent it = getIntent();
        mTurma = it.getStringExtra("Turma");


        TXT_Titulo.setText("Chamada - " + mTurma);
        mContexto = this.getBaseContext();

        listar();

        BTN_SALVAR.setBackgroundColor(Color.parseColor("#66bb6a"));


        BTN_SALVAR.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        BTN_DESEMPENHOS.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContexto, PassadorDeDesempenhosDaTurmaActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("Turma", mTurma);

                mContexto.startActivity(intent);

            }
        });

    }


    public void listar() {

        mAlunos = OrdenarAlunos.ordendar(Escola.filtrar_visiveis_da_turma(mTurma));
        Chamada.organizar(mAlunos);

        lista.setAdapter(new Lista_RealizarChamada(mContexto, mAlunos));

    }

    public void salvar() {

        Chamada.salvarChamada(mAlunos);

        ContadorSN contagem = Frequenciometro.contarDaTurma(mAlunos);

        Atualizador.chamada(mTurma, contagem.getSim(), contagem.getNao(), contagem.getTudo());

        Toast.makeText(getBaseContext(), "Salvando chamada !", Toast.LENGTH_SHORT).show();

        AtualizacoesFragment.RECARREGAR_LISTA.fazer();

    }


}