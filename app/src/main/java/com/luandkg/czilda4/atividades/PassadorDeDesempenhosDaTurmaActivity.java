package com.luandkg.czilda4.atividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.DesempenhoGrafico;
import com.luandkg.czilda4.escola.avaliacao_continua.Perfilometro;
import com.luandkg.czilda4.utils.Texto;

import java.util.ArrayList;

public class PassadorDeDesempenhosDaTurmaActivity extends AppCompatActivity {

    private TextView TX_NOME;
    private ImageView IV_FLUXO;
    private TextView TX_NOTA;

    private Button BTN_PROXIMO;
    private Button BTN_ANTERIOR;
    private ArrayList<Aluno> mAlunos;

    private int mIndex;
    private int mQuantidade;

    private ImageView IV_PREVIEW1;
    private ImageView IV_PREVIEW2;
    private ImageView IV_PREVIEW3;
    private ImageView IV_PREVIEW4;

    private ArrayList<DKGObjeto> mPerfis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passador_de_desempenhos_da_turma);

        TX_NOME = (TextView) findViewById(R.id.passador_aluno_desempenho_nome);
        TX_NOTA = (TextView) findViewById(R.id.passador_aluno_desempenho_nota);

        IV_FLUXO = (ImageView) findViewById(R.id.passador_aluno_desempenho_fluxo);

        BTN_PROXIMO = (Button) findViewById(R.id.passador_proximo);
        BTN_ANTERIOR = (Button) findViewById(R.id.passador_anterior);

        IV_PREVIEW1 = (ImageView) findViewById(R.id.passador_aluno_desempenho_preview1);
        IV_PREVIEW2 = (ImageView) findViewById(R.id.passador_aluno_desempenho_preview2);
        IV_PREVIEW3 = (ImageView) findViewById(R.id.passador_aluno_desempenho_preview3);
        IV_PREVIEW4 = (ImageView) findViewById(R.id.passador_aluno_desempenho_preview4);

        Intent it = getIntent();
        String mTurma = it.getStringExtra("Turma");


        mAlunos = OrdenarAlunos.ordendar(Escola.filtrar_visiveis_da_turma(mTurma));

        mIndex = 0;
        mQuantidade = mAlunos.size();

        mPerfis = Perfilometro.getPerfilRaiz();

        atualizar();


        BTN_PROXIMO.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                mIndex += 1;
                if (mIndex >= mQuantidade) {
                    mIndex = 0;
                }

                atualizar();

            }
        });


        BTN_ANTERIOR.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                mIndex -= 1;

                if (mIndex < 0) {
                    mIndex = mQuantidade - 1;
                }

                atualizar();

            }
        });


    }

    public void atualizar() {

        if (mIndex < mQuantidade) {

            AlunoContinuo perfil = Perfilometro.getPerfilDeSemSemanas(mAlunos.get(mIndex).getID(), mPerfis);


            TX_NOME.setText(perfil.getNome());
            TX_NOTA.setText(Texto.doubleNumC2(perfil.getNotaComRecuperacao()));

            // TX_NOTA.setText(perfil.getNome() + " :: " + Texto.doubleNumC2(perfil.getNota()));

            IV_FLUXO.setImageBitmap(DesempenhoGrafico.onDesempenho(perfil.getParticipacao(), perfil.getCompromisso(), perfil.getDedicacao(), perfil.getFrequencia(), perfil.getQualificacao()));


        }

        int mIndex_Menos2 = mIndex - 2;
        if (mIndex_Menos2 < 0) {
            mIndex_Menos2 = 0;
        }
        if (mIndex_Menos2 < mQuantidade) {
            AlunoContinuo perfil2 = Perfilometro.getPerfilDeSemSemanas(mAlunos.get(mIndex_Menos2).getID(), mPerfis);
            IV_PREVIEW1.setImageBitmap(DesempenhoGrafico.onDesempenho(perfil2.getParticipacao(), perfil2.getCompromisso(), perfil2.getDedicacao(), perfil2.getFrequencia(), perfil2.getQualificacao()));
        }

        int mIndex_Menos1 = mIndex - 1;
        if (mIndex_Menos1 < 0) {
            mIndex_Menos1 = 0;
        }
        if (mIndex_Menos1 < mQuantidade) {
            AlunoContinuo perfil2 = Perfilometro.getPerfilDeSemSemanas(mAlunos.get(mIndex_Menos1).getID(), mPerfis);
            IV_PREVIEW2.setImageBitmap(DesempenhoGrafico.onDesempenho(perfil2.getParticipacao(), perfil2.getCompromisso(), perfil2.getDedicacao(), perfil2.getFrequencia(), perfil2.getQualificacao()));
        }

        int mIndex_Mais1 = mIndex + 1;
        if (mIndex_Mais1 >= mQuantidade) {
            mIndex_Mais1 = mQuantidade - 1;
        }
        if (mIndex_Mais1 >= 0) {
            AlunoContinuo perfil2 = Perfilometro.getPerfilDeSemSemanas(mAlunos.get(mIndex_Mais1).getID(), mPerfis);
            IV_PREVIEW3.setImageBitmap(DesempenhoGrafico.onDesempenho(perfil2.getParticipacao(), perfil2.getCompromisso(), perfil2.getDedicacao(), perfil2.getFrequencia(), perfil2.getQualificacao()));
        }

        int mIndex_Mais2 = mIndex + 2;
        if (mIndex_Mais2 >= mQuantidade) {
            mIndex_Mais2 = mQuantidade - 1;
        }
        if (mIndex_Mais2 >= 0) {
            AlunoContinuo perfil2 = Perfilometro.getPerfilDeSemSemanas(mAlunos.get(mIndex_Mais2).getID(), mPerfis);
            IV_PREVIEW4.setImageBitmap(DesempenhoGrafico.onDesempenho(perfil2.getParticipacao(), perfil2.getCompromisso(), perfil2.getDedicacao(), perfil2.getFrequencia(), perfil2.getQualificacao()));
        }
    }
}