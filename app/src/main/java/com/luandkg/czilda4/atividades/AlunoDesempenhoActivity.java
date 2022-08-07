package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.avaliacao_continua.Desempenho;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.escola.avaliacao_continua.DesempenhoGrafico;
import com.luandkg.czilda4.escola.avaliacao_continua.Qualidade;
import com.luandkg.czilda4.listas.Lista_AlunoDesempenhos;
import com.luandkg.czilda4.utils.Texto;

import java.util.ArrayList;

public class AlunoDesempenhoActivity extends AppCompatActivity {

    private TextView TX_NOME;
    private ImageView IV_FLUXO;
    private TextView TX_NOTA;

    private ListView LISTA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_desempenho);


        TX_NOME = (TextView) findViewById(R.id.aluno_desempenho_nome);
        TX_NOTA = (TextView) findViewById(R.id.aluno_desempenho_nota);
        IV_FLUXO = (ImageView) findViewById(R.id.aluno_desempenho_fluxo);

        LISTA = (ListView) findViewById(R.id.aluno_desempenho_lista);

        Intent it = getIntent();
        String aluno_id = it.getStringExtra("AlunoID");

        AlunoContinuo perfil = HiperCacheDeAvaliacao.getPerfil(aluno_id, BimestreCorrente.GET().getSemanas());


        TX_NOME.setText(perfil.getNome());
        TX_NOTA.setText(Texto.doubleNumC2(perfil.getNotaComRecuperacao()));


        IV_FLUXO.setImageBitmap(DesempenhoGrafico.onDesempenho(perfil.getParticipacao(), perfil.getCompromisso(), perfil.getDedicacao(), perfil.getFrequencia(), perfil.getQualificacao()));

        ArrayList<Desempenho> desempenhos = new ArrayList<Desempenho>();
      //  desempenhos.add(new Desempenho("Participação", Qualidade.getExpressaoFeminina(perfil.getParticipacao()),perfil.getParticipacao()));
      //  desempenhos.add(new Desempenho("Compromisso", Qualidade.getExpressaoMasculina(perfil.getCompromisso()),perfil.getCompromisso()));
      //  desempenhos.add(new Desempenho("Dedicação", Qualidade.getExpressaoFeminina(perfil.getDedicacao()),perfil.getDedicacao()));
     //   desempenhos.add(new Desempenho("Frequência", Qualidade.getExpressaoFeminina(perfil.getFrequencia()),perfil.getFrequencia()));
      //  desempenhos.add(new Desempenho("Qualificação", Qualidade.getExpressaoFeminina(perfil.getQualificacao()),perfil.getQualificacao()));

        desempenhos.add(new Desempenho("Participação :: " + perfil.getParticipacao(), Qualidade.getExpressaoFeminina(perfil.getParticipacao()),perfil.getParticipacao()));
        desempenhos.add(new Desempenho("Compromisso :: "+ perfil.getCompromisso(), Qualidade.getExpressaoMasculina(perfil.getCompromisso()),perfil.getCompromisso()));
        desempenhos.add(new Desempenho("Dedicação :: "+ perfil.getDedicacao(), Qualidade.getExpressaoFeminina(perfil.getDedicacao()),perfil.getDedicacao()));
        desempenhos.add(new Desempenho("Frequência :: "+ perfil.getFrequencia(), Qualidade.getExpressaoFeminina(perfil.getFrequencia()),perfil.getFrequencia()));
        desempenhos.add(new Desempenho("Qualificação :: "+ perfil.getQualificacao(), Qualidade.getExpressaoFeminina(perfil.getQualificacao()),perfil.getQualificacao()));



        Lista_AlunoDesempenhos mLista_AlunoDesempenhos = new Lista_AlunoDesempenhos(getBaseContext(),desempenhos);
        LISTA.setAdapter(mLista_AlunoDesempenhos);


    }


}