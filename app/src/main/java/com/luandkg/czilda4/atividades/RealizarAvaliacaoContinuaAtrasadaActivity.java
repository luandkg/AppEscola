package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.atualizador.Atualizador;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.avaliacao.AtividadeContador;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.utils.ContadorSN;
import com.luandkg.czilda4.fragments.SegundoBimestreFragment;
import com.luandkg.czilda4.listas.Lista_AlunoContinuoNotaFinal;
import com.luandkg.czilda4.listas.Lista_RealizarAvaliacao;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.escola.widgets.CaixaDeAtividade;

import java.util.ArrayList;

public class RealizarAvaliacaoContinuaAtrasadaActivity extends AppCompatActivity {

    private TextView BTN_TITULO;

 //   private Button BTN_AVALIAR;


   // private Button BTN_RECUPERACAO;

   // private Button BTN_NOTA_FINAL;

    private ListView LISTA;

    private Button BTN_SALVAR;

    private TextView TXT_NAO;
    private TextView TXT_SIM;

    private CaixaDeAtividade CX_CAIXADEATIVIDADES;

    private final String AVALIAR = "AVALIAR";
    private final String NOTA_FINAL = "FINAL";
    private final String RECUPERACAO = "RECUPERACAO";

    private String mAntiga;
    private String mAntigaArquivo;

    private String mTurma;
    private String mNota;

    private String mArquivoCorrente;
    private String mArquivoRecuperacao;

    private Context mContexto;

    private ArrayList<AlunoComNota> mAlunos;

   // private Button BTN_ATIVIDADES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_avaliacao_continua_atrasada);

        Intent it = getIntent();
        mTurma = it.getStringExtra("Turma");
        mAntiga = it.getStringExtra("Antiga");
        mAntigaArquivo = it.getStringExtra("AntigaArquivo");

        mNota = AVALIAR;

        mContexto = this.getBaseContext();

        Local.organizarPastas();


        BTN_TITULO = (TextView) findViewById(R.id.atrasada_avaliador_continuo_titulo);

        CX_CAIXADEATIVIDADES = (CaixaDeAtividade) findViewById(R.id.atrasada_avaliador_continuo_caixa);

      //  BTN_AVALIAR = (Button) findViewById(R.id.atrasada_avaliador_continuo_avaliar);

      //  BTN_RECUPERACAO = (Button) findViewById(R.id.atrasada_avaliador_continuo_recuperacao);

       // BTN_NOTA_FINAL = (Button) findViewById(R.id.atrasada_avaliador_continuo_notafinal);

        LISTA = (ListView) findViewById(R.id.atrasada_avaliador_continuo_lista);

        BTN_SALVAR = (Button) findViewById(R.id.atrasada_avaliador_continuo_salvar);
    //    BTN_ATIVIDADES = (Button) findViewById(R.id.atrasada_avaliador_continuo_atividades);

        TXT_NAO = (TextView) findViewById(R.id.atrasada_avaliador_continuo_nao);
        TXT_SIM = (TextView) findViewById(R.id.atrasada_avaliador_continuo_sim);


        BTN_TITULO.setText("Avaliação - " + mTurma);
        TXT_NAO.setText("0");
        TXT_SIM.setText("0");

        BTN_SALVAR.setBackgroundColor(Color.parseColor("#66bb6a"));

        BTN_SALVAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        if (mAntiga.contentEquals("SIM")) {
            mArquivoCorrente = mAntigaArquivo;
        //    BTN_ATIVIDADES.setVisibility(View.INVISIBLE);
         //   BTN_RECUPERACAO.setVisibility(View.INVISIBLE);
         //   BTN_NOTA_FINAL.setVisibility(View.INVISIBLE);
        } else {
            mArquivoCorrente = Local.ARQUIVO_AVALIACAO_CONTINUA_HOJE();
        }

        mArquivoRecuperacao = Local.ARQUIVO_RECUPERACAO(BimestreCorrente.GET().getID());


     //   mudarAcao(BTN_AVALIAR, AVALIAR);


     //   mudarAcao(BTN_RECUPERACAO, RECUPERACAO);


      //  mudarAcao(BTN_NOTA_FINAL, NOTA_FINAL);




        escolherNota(AVALIAR);
    }

    public void escolherNota(String eNota) {

        mNota = eNota;

        int CINZA = Color.parseColor("#90a4ae");
        int VERDE = Color.parseColor("#66bb6a");

        CX_CAIXADEATIVIDADES.setMudar(0, 0, 0, 0, 0);

      //  BTN_AVALIAR.setBackgroundColor(CINZA);
      //  BTN_RECUPERACAO.setBackgroundColor(CINZA);
      //  BTN_NOTA_FINAL.setBackgroundColor(CINZA);


        if (mNota.contentEquals(AVALIAR)) {
        //    BTN_AVALIAR.setBackgroundColor(VERDE);
        } else if (mNota.contentEquals(RECUPERACAO)) {
      //      BTN_RECUPERACAO.setBackgroundColor(VERDE);
        } else if (mNota.contentEquals(NOTA_FINAL)) {
       //     BTN_NOTA_FINAL.setBackgroundColor(VERDE);

        }


        if (mNota.contentEquals(AVALIAR)) {


            mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota(mTurma)));
            Atividade.organizarNota(FS.getArquivoLocal(mArquivoCorrente), AVALIAR, mAlunos);

            LISTA.setAdapter(new Lista_RealizarAvaliacao(mContexto, AVALIAR, mAlunos));


            mudarContadores(mAlunos, AVALIAR);


        } else if (mNota.contentEquals(RECUPERACAO)) {


            mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota(mTurma)));
            Atividade.organizarNota(FS.getArquivoLocal(mArquivoRecuperacao), RECUPERACAO, mAlunos);

            LISTA.setAdapter(new Lista_RealizarAvaliacao(mContexto, RECUPERACAO, mAlunos));

            mudarContadores(mAlunos, RECUPERACAO);


        } else if (mNota.contentEquals(NOTA_FINAL)) {


            ArrayList<AlunoContinuo> alunos_continuos = Escola.getAlunosContinuosVisiveisEOrdenadosDaTurma(mTurma);


            MetodoContinuo.avaliar(Local.COLECAO_NOTAS, alunos_continuos, BimestreCorrente.GET().getSemanas());


            LISTA.setAdapter(new Lista_AlunoContinuoNotaFinal(mContexto, alunos_continuos));


            ContadorSN contagem = AtividadeContador.contarAlunosContinuosNotas(alunos_continuos);

            TXT_NAO.setText(String.valueOf(contagem.getNao()));
            TXT_SIM.setText(String.valueOf(contagem.getSim()));

            CX_CAIXADEATIVIDADES.setMudar(contagem.getTudo(), contagem.getNao(), 0, 0, contagem.getSim());

        }

    }

    public void salvar() {

        int eFizeram = 0;

        if (mNota.contentEquals(AVALIAR)) {

            Atividade.salvarNota(AVALIAR, mAlunos, FS.getArquivoLocal(mArquivoCorrente));
            mudarContadores(mAlunos, AVALIAR);

            eFizeram = AtividadeContador.getSim(mAlunos, AVALIAR);


        } else if (mNota.contentEquals(RECUPERACAO)) {

            Atividade.salvarNota(RECUPERACAO, mAlunos, FS.getArquivoLocal(mArquivoRecuperacao));
            mudarContadores(mAlunos, RECUPERACAO);

            eFizeram = AtividadeContador.getSim(mAlunos, RECUPERACAO);

        }


        if (mAntiga.contentEquals("NAO")) {

            String evento = "Nenhum aluno realizou a atividade";

            if (eFizeram == 1) {
                evento = "Apenas 1 aluno realizou a atividade !";
            } else if (eFizeram > 1) {
                evento = eFizeram + " alunos realizaram a atividade !";
            }


            Atualizador.mudarStatusAvaliacao(mTurma, evento);
            SegundoBimestreFragment.RECARREGAR_LISTA.fazer();

        }



    }

    public void mudarAcao(Button eBotao, String eAcao) {
        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escolherNota(eAcao);
            }
        });
    }

    public void mudarContadores(ArrayList<AlunoComNota> mAlunos, String eCampo) {


        int sim = AtividadeContador.getSim(mAlunos, eCampo);
        int nao = AtividadeContador.getNao(mAlunos, eCampo);

        TXT_NAO.setText(String.valueOf(nao));
        TXT_SIM.setText(String.valueOf(sim));

        int todos = mAlunos.size();
        int zero = AtividadeContador.getContagem(mAlunos, eCampo, 0);
        int um = AtividadeContador.getContagem(mAlunos, eCampo, 1);
        int dois = AtividadeContador.getContagem(mAlunos, eCampo, 2);
        int tres = AtividadeContador.getContagem(mAlunos, eCampo, 3);


        CX_CAIXADEATIVIDADES.setMudar(todos, zero, um, dois, tres);

    }
}