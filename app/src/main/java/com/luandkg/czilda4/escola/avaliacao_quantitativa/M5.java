package com.luandkg.czilda4.escola.avaliacao_quantitativa;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.avaliacao.AtividadeContador;
import com.luandkg.czilda4.utils.Armazem;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;

public class M5 {


    public static ArrayList<QuadroDeNotas> getAtividades() {


        ArrayList<QuadroDeNotas> mQuadros = new ArrayList<QuadroDeNotas>();

        mQuadros.add(new QuadroDeNotas("ATIVIDADE 1"));
        mQuadros.add(new QuadroDeNotas("ATIVIDADE 2"));
        mQuadros.add(new QuadroDeNotas("ATIVIDADE 3"));
        mQuadros.add(new QuadroDeNotas("ATIVIDADE 4"));
        mQuadros.add(new QuadroDeNotas("ATIVIDADE 5"));

        mQuadros.add(new QuadroDeNotas("PRE - FINAL"));

        mQuadros.add(new QuadroDeNotas("RECUPERACAO"));
        mQuadros.add(new QuadroDeNotas("FINAL"));


        ArrayList<AlunoComNota> mTodos = Escola.carregarAlunosComNota();

        ArrayList<AlunoComNota> mAlunos = Escola.filtarVisiveis(mTodos);

        Armazem armazenar = new Armazem("Escola");

        String ARQUIVO_NOTAS_01 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_01.dkg");
        String ARQUIVO_NOTAS_02 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_02.dkg");
        String ARQUIVO_NOTAS_03 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_03.dkg");
        String ARQUIVO_NOTAS_04 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_04.dkg");
        String ARQUIVO_NOTAS_05 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_05.dkg");

        String ARQUIVO_RECUPERACAO = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_RECUPERACAO.dkg");

        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_01), "Nota01", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_02), "Nota02", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_03), "Nota03", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_04), "Nota04", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_05), "Nota05", mAlunos);

        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_RECUPERACAO), "RECUPERACAO", mAlunos);


        passagem(getQuadro(mQuadros, "ATIVIDADE 1"), mAlunos, "Nota01");
        passagem(getQuadro(mQuadros, "ATIVIDADE 2"), mAlunos, "Nota02");
        passagem(getQuadro(mQuadros, "ATIVIDADE 3"), mAlunos, "Nota03");
        passagem(getQuadro(mQuadros, "ATIVIDADE 4"), mAlunos, "Nota04");
        passagem(getQuadro(mQuadros, "ATIVIDADE 5"), mAlunos, "Nota05");

        passagem_pre_final(getQuadro(mQuadros, "PRE - FINAL"), mAlunos);

        passagem_recuperacao(getQuadro(mQuadros, "RECUPERACAO"), mAlunos, "RECUPERACAO");

        passagem_final(getQuadro(mQuadros, "FINAL"), mAlunos);


        return mQuadros;

    }

    public static void organizarFinal(ArrayList<AlunoComNota> mAlunos, int minimo_frequencia) {

        Armazem armazenar = new Armazem("Escola");

        String ARQUIVO_NOTAS_01 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_01.dkg");
        String ARQUIVO_NOTAS_02 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_02.dkg");
        String ARQUIVO_NOTAS_03 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_03.dkg");
        String ARQUIVO_NOTAS_04 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_04.dkg");
        String ARQUIVO_NOTAS_05 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_05.dkg");

        String ARQUIVO_RECUPERACAO = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_RECUPERACAO.dkg");

        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_01), "Nota01", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_02), "Nota02", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_03), "Nota03", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_04), "Nota04", mAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_05), "Nota05", mAlunos);

        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_RECUPERACAO), "RECUPERACAO", mAlunos);

        // String arquivo_contagem = Local.ARQUIVO_FREQUENCIA_BIMESTRAL();
        String arquivo_contagem = "";

        if (arquivo_contagem.length() > 0) {

            DKG eArquivoChamadas = new DKG();
            eArquivoChamadas.abrir(FS.getArquivoLocal(Local.LOCAL_CACHE + "/" + arquivo_contagem));
            DKGObjeto eContagem = eArquivoChamadas.unicoObjeto("CONTAGEM");


            for (AlunoComNota aluno : mAlunos) {

                for (DKGObjeto aluno_contando : eContagem.getObjetos()) {

                    String aluno_chamada_id = aluno_contando.identifique("ID").getValor();
                    if (aluno_chamada_id.contentEquals(aluno.getID())) {

                        int v = aluno_contando.identifique("Frequencia").getInteiro();

                        System.out.println("-->> " + aluno_chamada_id + " :: " + v);
                        if (v >= minimo_frequencia) {
                            aluno.marcarPresente();
                        }

                        break;
                    }

                }

            }


        }

    }


    public static QuadroDeNotas getQuadro(ArrayList<QuadroDeNotas> mQuadros, String procurado) {
        QuadroDeNotas ret = null;

        for (QuadroDeNotas qn : mQuadros) {
            if (qn.getTurma().contentEquals(procurado)) {
                ret = qn;
                break;
            }

        }

        return ret;

    }


    public static void passagem(QuadroDeNotas atividade, ArrayList<AlunoComNota> mAlunos, String campo) {

        for (AlunoComNota aluno : mAlunos) {

            int valor = 0;
            String sValor = aluno.getNota(campo);

            if (sValor.length() > 0) {
                valor = Integer.parseInt(sValor);
            }

            atividade.adicionar(valor);
        }


    }

    public static void passagem_recuperacao(QuadroDeNotas atividade, ArrayList<AlunoComNota> mAlunos, String campo) {

        for (AlunoComNota aluno : mAlunos) {

            if (aluno.getNotaPreFinal() < 5) {

                String sValor = aluno.getNota(campo);
                int valor = 0;

                if (sValor.length() > 0) {
                    valor = Integer.parseInt(sValor);
                }

                atividade.adicionar(valor);

            }

        }


    }


    public static void passagem_pre_final(QuadroDeNotas atividade, ArrayList<AlunoComNota> mAlunos) {

        for (AlunoComNota aluno : mAlunos) {
            int valor = aluno.getNotaPreFinal();
            atividade.adicionar(valor);
        }


    }

    public static void passagem_final(QuadroDeNotas atividade, ArrayList<AlunoComNota> mAlunos) {

        for (AlunoComNota aluno : mAlunos) {
            int valor = aluno.getNotaFinal();
            atividade.adicionar(valor);
        }


    }

    public static Bitmap criarFluxoDeEntrega(ArrayList<Data> datas) {

        int w = 400;
        int h = 200;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        Paint pintor_atividades = new Paint();
        pintor_atividades.setColor(Color.parseColor("#689F38"));


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);

      //  CED1_Calendario calendario = new CED1_Calendario();

        int dias = datas.size();
        int parcela = w / dias;

        Armazem armazenar = new Armazem("Escola");

        String ARQUIVO_NOTAS_01 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_01.dkg");
        String ARQUIVO_NOTAS_02 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_02.dkg");
        String ARQUIVO_NOTAS_03 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_03.dkg");
        String ARQUIVO_NOTAS_04 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_04.dkg");
        String ARQUIVO_NOTAS_05 = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_05.dkg");

        String ARQUIVO_RECUPERACAO = armazenar.PASTA_ARQUIVO("Notas", "notas_BIMESTRE_01_RECUPERACAO.dkg");

        ArrayList<AlunoComNota> eAlunos = Escola.carregarAlunosComNotaDaEscola();

        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_01), "Nota01", eAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_02), "Nota02", eAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_03), "Nota03", eAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_04), "Nota04", eAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_NOTAS_05), "Nota05", eAlunos);
        Atividade.organizarNota(FS.getArquivoLocal(ARQUIVO_RECUPERACAO), "RECUPERACAO", eAlunos);

        int eixo_x = 0;

        for (Data data : datas) {

            int atividades = AtividadeContador.contar(eAlunos, data.getTempo());

            if (atividades > 0) {

                int altura = (atividades * 3);
                canvas.drawRect(eixo_x, 200 - altura, eixo_x + parcela, 200, pintor_atividades);
            }

            eixo_x += parcela;

        }

        return bmp;

    }

}
