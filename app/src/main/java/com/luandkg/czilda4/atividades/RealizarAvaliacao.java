package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.escola.atualizador.Atualizador;
import com.luandkg.czilda4.escola.avaliacao.AtividadeContador;
import com.luandkg.czilda4.escola.avaliacao_quantitativa.M5;
import com.luandkg.czilda4.escola.avaliacao_formativa.Formativa;
import com.luandkg.czilda4.fragments.AtualizacoesFragment;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.utils.Itenizador;
import com.luandkg.czilda4.utils.ListaGenerica;
import com.luandkg.czilda4.utils.Armazem;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.utils.Widget;
import com.luandkg.czilda4.escola.widgets.CaixaDeAtividade;

import java.util.ArrayList;

public class RealizarAvaliacao extends AppCompatActivity {

    private TextView BTN_TITULO;

    private Button BTN_NOTA_1;
    private Button BTN_NOTA_2;
    private Button BTN_NOTA_3;
    private Button BTN_NOTA_4;
    private Button BTN_NOTA_5;

    private Button BTN_RECUPERACAO;

    private Button BTN_NOTA_FINAL;

    private ListView LISTA;

    private Button BTN_SALVAR;

    private TextView TXT_NAO;
    private TextView TXT_SIM;

    private CaixaDeAtividade CX_CAIXADEATIVIDADES;

    private final String NOTA_01 = "1";
    private final String NOTA_02 = "2";
    private final String NOTA_03 = "3";
    private final String NOTA_04 = "4";
    private final String NOTA_05 = "5";

    private final String NOTA_FINAL = "FINAL";
    private final String RECUPERACAO = "RECUPERACAO";


    // private ListaDeNotas mNotas_01;
    // private ListaDeNotas mNotas_02;
    // private ListaDeNotas mNotas_03;
    // private ListaDeNotas mNotas_04;
    //  private ListaDeNotas mNotas_05;
    //  private ListaDeNotas mRecuperacao;
    private ArrayList<AlunoComNota> mAlunosAtividade;


    private String mTurma;
    private String mNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_avaliacao);


        Intent it = getIntent();
        mTurma = it.getStringExtra("Turma");
        mNota = NOTA_01;

        Local.organizarPastas();

        BTN_TITULO = (TextView) findViewById(R.id.avaliador_titulo);

        CX_CAIXADEATIVIDADES = (CaixaDeAtividade) findViewById(R.id.avaliador_caixa);

        BTN_NOTA_1 = (Button) findViewById(R.id.avaliador_nota1);
        BTN_NOTA_2 = (Button) findViewById(R.id.avaliador_nota2);
        BTN_NOTA_3 = (Button) findViewById(R.id.avaliador_nota3);
        BTN_NOTA_4 = (Button) findViewById(R.id.avaliador_nota4);
        BTN_NOTA_5 = (Button) findViewById(R.id.avaliador_nota5);
        BTN_RECUPERACAO = (Button) findViewById(R.id.avaliador_recuperacao);

        BTN_NOTA_FINAL = (Button) findViewById(R.id.avaliador_notafinal);

        LISTA = (ListView) findViewById(R.id.avaliador_lista);

        BTN_SALVAR = (Button) findViewById(R.id.avaliador_salvar);


        TXT_NAO = (TextView) findViewById(R.id.avaliador_nao);
        TXT_SIM = (TextView) findViewById(R.id.avaliador_sim);


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


        mudarAcao(BTN_NOTA_1, NOTA_01);
        mudarAcao(BTN_NOTA_2, NOTA_02);
        mudarAcao(BTN_NOTA_3, NOTA_03);
        mudarAcao(BTN_NOTA_4, NOTA_04);
        mudarAcao(BTN_NOTA_5, NOTA_05);

        mudarAcao(BTN_RECUPERACAO, RECUPERACAO);


        mudarAcao(BTN_NOTA_FINAL, NOTA_FINAL);


        escolherNota(NOTA_01);

    }

    public void mudarAcao(Button eBotao, String eAcao) {
        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escolherNota(eAcao);
            }
        });
    }


    public void salvar() {

        Armazem armazenar = new Armazem("Escola");

        String ARQUIVO_NOTAS_01 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_01.dkg");
        String ARQUIVO_NOTAS_02 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_02.dkg");
        String ARQUIVO_NOTAS_03 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_03.dkg");
        String ARQUIVO_NOTAS_04 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_04.dkg");
        String ARQUIVO_NOTAS_05 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_05.dkg");

        String ARQUIVO_RECUPERACAO = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_RECUPERACAO.dkg");

        if (mNota.contentEquals(NOTA_01)) {

            Atividade.salvarNota("Nota01", mAlunosAtividade, FS.getArquivoLocal(ARQUIVO_NOTAS_01));
            mudarContadores(mAlunosAtividade, "Nota01");

        } else if (mNota.contentEquals(NOTA_02)) {

            Atividade.salvarNota("Nota02", mAlunosAtividade, FS.getArquivoLocal(ARQUIVO_NOTAS_02));
            mudarContadores(mAlunosAtividade, "Nota02");

        } else if (mNota.contentEquals(NOTA_03)) {

            Atividade.salvarNota("Nota03", mAlunosAtividade, FS.getArquivoLocal(ARQUIVO_NOTAS_03));
            mudarContadores(mAlunosAtividade, "Nota03");

        } else if (mNota.contentEquals(NOTA_04)) {

            Atividade.salvarNota("Nota04", mAlunosAtividade, FS.getArquivoLocal(ARQUIVO_NOTAS_04));
            mudarContadores(mAlunosAtividade, "Nota04");

        } else if (mNota.contentEquals(NOTA_05)) {

            Atividade.salvarNota("Nota05", mAlunosAtividade, FS.getArquivoLocal(ARQUIVO_NOTAS_05));
            mudarContadores(mAlunosAtividade, "Nota05");

        } else if (mNota.contentEquals(RECUPERACAO)) {

            Atividade.salvarNota("RECUPERACAO", mAlunosAtividade, FS.getArquivoLocal(ARQUIVO_RECUPERACAO));
            mudarContadores(mAlunosAtividade, "RECUPERACAO");

        }


        int com_atividade = Atividade.getComNotas(mTurma);

        Atualizador.avaliacao(mTurma, com_atividade);


        AtualizacoesFragment.RECARREGAR_LISTA.fazer();

    }

    public void escolherNota(String eNota) {

        mNota = eNota;

        int CINZA = Color.parseColor("#90a4ae");
        int VERDE = Color.parseColor("#66bb6a");

        CX_CAIXADEATIVIDADES.setMudar(0, 0, 0, 0, 0);

        BTN_NOTA_1.setBackgroundColor(CINZA);
        BTN_NOTA_2.setBackgroundColor(CINZA);
        BTN_NOTA_3.setBackgroundColor(CINZA);
        BTN_NOTA_4.setBackgroundColor(CINZA);
        BTN_NOTA_5.setBackgroundColor(CINZA);

        BTN_RECUPERACAO.setBackgroundColor(CINZA);

        BTN_NOTA_FINAL.setBackgroundColor(CINZA);


        if (mNota.contentEquals(NOTA_01)) {
            BTN_NOTA_1.setBackgroundColor(VERDE);
        } else if (mNota.contentEquals(NOTA_02)) {
            BTN_NOTA_2.setBackgroundColor(VERDE);
        } else if (mNota.contentEquals(NOTA_03)) {
            BTN_NOTA_3.setBackgroundColor(VERDE);
        } else if (mNota.contentEquals(NOTA_04)) {
            BTN_NOTA_4.setBackgroundColor(VERDE);
        } else if (mNota.contentEquals(NOTA_05)) {
            BTN_NOTA_5.setBackgroundColor(VERDE);

        } else if (mNota.contentEquals(RECUPERACAO)) {
            BTN_RECUPERACAO.setBackgroundColor(VERDE);
        } else if (mNota.contentEquals(NOTA_FINAL)) {
            BTN_NOTA_FINAL.setBackgroundColor(VERDE);

        }

        Armazem armazenar = new Armazem("Escola");

        String ARQUIVO_NOTAS_01 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_01.dkg");
        String ARQUIVO_NOTAS_02 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_02.dkg");
        String ARQUIVO_NOTAS_03 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_03.dkg");
        String ARQUIVO_NOTAS_04 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_04.dkg");
        String ARQUIVO_NOTAS_05 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_05.dkg");

        String ARQUIVO_RECUPERACAO = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_RECUPERACAO.dkg");

        if (mNota.contentEquals(NOTA_01)) {


            mAlunosAtividade = OrdenarAlunos.ordendarComNotas(Escola.carregarAlunosComNota(mTurma));
            Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_01), "Nota01", mAlunosAtividade);
            LISTA.setAdapter(new ListaGenerica(getBaseContext(), mAlunosAtividade.size(), onAtividade("Nota01", mAlunosAtividade)));

            mudarContadores(mAlunosAtividade, "Nota01");


        } else if (mNota.contentEquals(NOTA_02)) {

            mAlunosAtividade = OrdenarAlunos.ordendarComNotas(Escola.carregarAlunosComNota(mTurma));
            Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_02), "Nota02", mAlunosAtividade);
            LISTA.setAdapter(new ListaGenerica(getBaseContext(), mAlunosAtividade.size(), onAtividade("Nota02", mAlunosAtividade)));

            mudarContadores(mAlunosAtividade, "Nota02");

        } else if (mNota.contentEquals(NOTA_03)) {

            mAlunosAtividade = OrdenarAlunos.ordendarComNotas(Escola.carregarAlunosComNota(mTurma));
            Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_03), "Nota03", mAlunosAtividade);
            LISTA.setAdapter(new ListaGenerica(getBaseContext(), mAlunosAtividade.size(), onAtividade("Nota03", mAlunosAtividade)));

            mudarContadores(mAlunosAtividade, "Nota03");

        } else if (mNota.contentEquals(NOTA_04)) {

            mAlunosAtividade = OrdenarAlunos.ordendarComNotas(Escola.carregarAlunosComNota(mTurma));
            Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_04), "Nota04", mAlunosAtividade);
            LISTA.setAdapter(new ListaGenerica(getBaseContext(), mAlunosAtividade.size(), onAtividade("Nota04", mAlunosAtividade)));

            mudarContadores(mAlunosAtividade, "Nota04");


        } else if (mNota.contentEquals(NOTA_05)) {

            mAlunosAtividade = OrdenarAlunos.ordendarComNotas(Escola.carregarAlunosComNota(mTurma));
            Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_05), "Nota05", mAlunosAtividade);
            LISTA.setAdapter(new ListaGenerica(getBaseContext(), mAlunosAtividade.size(), onAtividade("Nota05", mAlunosAtividade)));

            mudarContadores(mAlunosAtividade, "Nota05");

        } else if (mNota.contentEquals(RECUPERACAO)) {

            mAlunosAtividade = OrdenarAlunos.ordendarComNotas(Escola.carregarAlunosComNota(mTurma));
            Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_RECUPERACAO), "RECUPERACAO", mAlunosAtividade);
            LISTA.setAdapter(new ListaGenerica(getBaseContext(), mAlunosAtividade.size(), onAtividade("RECUPERACAO", mAlunosAtividade)));

            mudarContadores(mAlunosAtividade, "RECUPERACAO");

        } else if (mNota.contentEquals(NOTA_FINAL)) {

            int minimo_frequencia = 10;

            if (mTurma.contains("7") || mTurma.contains("8")) {
                minimo_frequencia = 5;
            }


            ArrayList<AlunoComNota> eAlunos = OrdenarAlunos.ordendarComNotas(Escola.carregarAlunosComNota(mTurma));
            M5.organizarFinal(eAlunos, minimo_frequencia);

            LISTA.setAdapter(new ListaGenerica(getBaseContext(), eAlunos.size(), onItem(eAlunos)));

            mudarContadoresFinal(eAlunos);

        }

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

    public void mudarContadoresFinal(ArrayList<AlunoComNota> mAlunos) {


        int sim = 0;
        int nao = 0;

        for (AlunoComNota aluno : mAlunos) {
            int v = aluno.getNotaFinal();
            if (v >= 5) {
                sim += 1;
            } else {
                nao += 1;
            }
        }


        TXT_NAO.setText(String.valueOf(nao));
        TXT_SIM.setText(String.valueOf(sim));


        CX_CAIXADEATIVIDADES.setMudar(mAlunos.size(), nao, 0, 0, sim);

    }


    public Itenizador onAtividade(String eCampo, ArrayList<AlunoComNota> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                AlunoComNota eAluno = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_aluno_nota, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.item_aluno_nome);

                Button zero = mWidget.getButton(R.id.item_aluno_zero);
                Button um = mWidget.getButton(R.id.item_aluno_um);
                Button dois = mWidget.getButton(R.id.item_aluno_dois);
                Button tres = mWidget.getButton(R.id.item_aluno_tres);


                atividade_limpar(zero, um, dois, tres);

                nome.setText(eAluno.getNome());

                atividade_atribuir(eAluno.getNota(eCampo), zero, um, dois, tres);

                atividade_clicar(zero, eAluno, eCampo, "0", zero, um, dois, tres);
                atividade_clicar(um, eAluno, eCampo, "1", zero, um, dois, tres);
                atividade_clicar(dois, eAluno, eCampo, "2", zero, um, dois, tres);
                atividade_clicar(tres, eAluno, eCampo, "3", zero, um, dois, tres);

                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


    public void atividade_clicar(Button eBotao, AlunoComNota eAluno, String eCampo, String eStatus, Button zero, Button um, Button dois, Button tres) {

        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!eAluno.getNota(eCampo).contentEquals(eStatus)) {
                    eAluno.setNota(eCampo, eStatus, Calendario.getADMComBarras());
                }

                atividade_limpar(zero, um, dois, tres);
                atividade_atribuir(eAluno.getNota(eCampo), zero, um, dois, tres);

                System.out.println("Mudar :: " + eCampo + " -->> " + eAluno.getNota(eCampo));

            }
        });


    }


    public Itenizador onItem(ArrayList<AlunoComNota> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                AlunoComNota eAluno = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_aluno_nota_final, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.notafinal_nome);
                Button nota = mWidget.getButton(R.id.notafinal_valor);
                Button parte1 = mWidget.getButton(R.id.notafinal_parte1);
                Button parte2 = mWidget.getButton(R.id.notafinal_parte2);


                nome.setText(eAluno.getNome());


                parte1.setText(String.valueOf(Formativa.PARTE_A(eAluno.getNotaFinal())));
                parte2.setText(String.valueOf(Formativa.PARTE_B(eAluno.getNotaFinal())));

                nota.setText(String.valueOf(eAluno.getNotaFinal()));


                final_limpar(nota, parte1, parte2);

                final_atribuir(eAluno.getNotaFinal(), nota);


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


    public void atividade_limpar(Button zero, Button um, Button dois, Button tres) {

        zero.setBackgroundColor(Color.parseColor("#90a4ae"));
        um.setBackgroundColor(Color.parseColor("#90a4ae"));
        dois.setBackgroundColor(Color.parseColor("#90a4ae"));
        tres.setBackgroundColor(Color.parseColor("#90a4ae"));


    }

    public void atividade_atribuir(String eNota, Button zero, Button um, Button dois, Button tres) {

        if (eNota.contentEquals("0")) {
            zero.setBackgroundColor(Color.parseColor("#37474f"));
        } else if (eNota.contentEquals("1")) {
            um.setBackgroundColor(Color.parseColor("#e64a19"));
        } else if (eNota.contentEquals("2")) {
            dois.setBackgroundColor(Color.parseColor("#fdd835"));
        } else if (eNota.contentEquals("3")) {
            tres.setBackgroundColor(Color.parseColor("#66bb6a"));
        }

    }

    public void final_limpar(Button nota, Button parte1, Button parte2) {

        nota.setBackgroundColor(Color.parseColor("#90a4ae"));

        parte1.setBackgroundColor(Color.parseColor("#90a4ae"));
        parte2.setBackgroundColor(Color.parseColor("#90a4ae"));

    }


    public void final_atribuir(int eNota, Button eBotao) {

        if (eNota == 0) {
            eBotao.setBackgroundColor(Color.parseColor("#37474f"));
        } else if (eNota >= 0 && eNota < 5) {
            eBotao.setBackgroundColor(Color.parseColor("#e64a19"));
        } else if (eNota >= 5 && eNota < 8) {
            eBotao.setBackgroundColor(Color.parseColor("#fdd835"));
        } else if (eNota >= 8) {
            eBotao.setBackgroundColor(Color.parseColor("#66bb6a"));
        }

    }
}