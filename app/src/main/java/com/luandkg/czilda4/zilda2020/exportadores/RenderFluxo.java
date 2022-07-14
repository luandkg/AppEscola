package com.luandkg.czilda4.zilda2020.exportadores;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.avaliacao_quantitativa.M3;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class RenderFluxo {

    private final int TRABALHO_1 = Color.GREEN;
    private final int TRABALHO_2 = Color.RED;
    private final int TRABALHO_3 = Color.BLUE;

    public static DKG carregarDocumento(String eArquivoLocal) {

        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();

        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);
        }

        return eDocumento;

    }
    public void render(String eAlunos, String n1, String n2, String n3, String arquivoSaida) {

        int eLargura = 5500;
        int eAltura = 2500;

        Bitmap eImagem = Bitmap.createBitmap(eLargura, eAltura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(eImagem);

        Paint eBranco = new Paint();
        eBranco.setStyle(Paint.Style.FILL);
        eBranco.setColor(Color.WHITE);
        eBranco.setTextSize(60);


        canvas.drawRect(0, 0, eImagem.getWidth(), eImagem.getHeight(), eBranco);


        ArrayList<AlunoComNota> mAlunos = Escola.carregarAlunosComNota();

        DKG g1 = carregarDocumento(n1);
        DKG g2 = carregarDocumento(n2);
        DKG g3 = carregarDocumento(n3);


        drawTurma(canvas, mAlunos, "A", 200, g1, g2, g3);
        drawTurma(canvas, mAlunos, "B", 1000, g1, g2, g3);
        drawTurma(canvas, mAlunos, "C", 1800, g1, g2, g3);
        drawTurma(canvas, mAlunos, "D", 2600, g1, g2, g3);
        drawTurma(canvas, mAlunos, "E", 3400, g1, g2, g3);
        drawTurma(canvas, mAlunos, "F", 4200, g1, g2, g3);


        try {
            eImagem.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(arquivoSaida));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void renderTurma(String eAlunos, String n1, String n2, String n3, String eTurma, String arquivoSaida) {

        int eLargura = 3500;
        int eAltura = 2500;

        Bitmap eImagem = Bitmap.createBitmap(eLargura, eAltura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(eImagem);

        Paint eBranco = new Paint();
        eBranco.setStyle(Paint.Style.FILL);
        eBranco.setColor(Color.WHITE);
        eBranco.setTextSize(60);


        Paint ePreto = new Paint();
        ePreto.setStyle(Paint.Style.FILL);
        ePreto.setColor(Color.BLACK);
        ePreto.setTextSize(60);

        canvas.drawRect(0, 0, eImagem.getWidth(), eImagem.getHeight(), eBranco);


        ArrayList<AlunoComNota> mAlunos = Escola.carregarAlunosComNota();

        DKG g1 = carregarDocumento(n1);
        DKG g2 = carregarDocumento(n2);
        DKG g3 = carregarDocumento(n3);


        drawTurma(canvas, mAlunos, eTurma, 200, g1, g2, g3);

        drawTurmaTrabalho(canvas, mAlunos, eTurma, 1000, g1, "Nota01", TRABALHO_1);
        drawTurmaTrabalho(canvas, mAlunos, eTurma, 1800, g2, "Nota02", TRABALHO_2);
        drawTurmaTrabalhoComExtra(canvas, mAlunos, eTurma, 2600, g3, g1, g2, g3, "Nota03", "Nota01", "Nota02", "Nota03", TRABALHO_3);


        int sweepAngle = (int) (360 * (50.0f / 100.f));
        int startAngle = 270 - sweepAngle / 2;

        int mX = 500;
        int mY = 500;

        float radius = 20;
        final RectF oval = new RectF(mX - radius, mY - radius, mX + radius, mY + radius);

        // canvas.drawArc(oval, startAngle, sweepAngle, true, ePreto);


        try {
            eImagem.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(arquivoSaida));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getNota(DKG eDocumento, String eNome, String eTag) {

        DKGObjeto eChamada = eDocumento.unicoObjeto("Notas");

        int existe = 0;
        for (DKGObjeto ePacote : eChamada.getObjetos()) {
            if (ePacote.identifique("Nome").getValor().contentEquals(eNome)) {

                String s = ePacote.identifique(eTag).getValor();
                if (s.length() > 0) {
                    int v = Integer.parseInt(s);
                    if (v > 0) {
                        existe = v;
                    }
                }
                break;
            }
        }

        return existe;
    }


    public void drawTurma(Canvas canvas, ArrayList<AlunoComNota> mAlunos, String eTurma, int eX, DKG g1, DKG g2, DKG g3) {

        Paint ePretoGrande = new Paint();
        ePretoGrande.setStyle(Paint.Style.FILL);
        ePretoGrande.setColor(Color.BLACK);
        ePretoGrande.setTextSize(60);


        canvas.drawText("TURMA - " + eTurma, eX + 100, 100, ePretoGrande);


        Paint ePreto = new Paint();
        ePreto.setStyle(Paint.Style.FILL);
        ePreto.setColor(Color.BLACK);
        ePreto.setTextSize(20);

        Paint eVerde = new Paint();
        eVerde.setStyle(Paint.Style.FILL);
        eVerde.setColor(TRABALHO_1);
        eVerde.setTextSize(20);

        Paint eVermelho = new Paint();
        eVermelho.setStyle(Paint.Style.FILL);
        eVermelho.setColor(TRABALHO_2);
        eVermelho.setTextSize(20);

        Paint eAzul = new Paint();
        eAzul.setStyle(Paint.Style.FILL);
        eAzul.setColor(TRABALHO_3);
        eAzul.setTextSize(20);

        int py = 200;

        int Q1 = eX - 90;
        int Q2 = eX - 60;
        int Q3 = eX - 30;


        OnTrilha mPrimeiro = new OnTrilha();
        OnTrilha mSegundo = new OnTrilha();
        OnTrilha mTerceiro = new OnTrilha();


        for (AlunoComNota eAluno : mAlunos) {
            if (eAluno.getTurma().contains(eTurma)) {

                if (eAluno.getVisibilidade().contains("SIM")) {

                    int n1 = getNota(g1, eAluno.getNome(), "Nota01");
                    int n2 = getNota(g2, eAluno.getNome(), "Nota02");
                    int n3 = getNota(g3, eAluno.getNome(), "Nota03");


                    if (n1 > 0) {
                        canvas.drawRect(Q1, py - 20, Q1 + 20, py, eVerde);

                        if (!mPrimeiro.tem()) {
                            mPrimeiro.comecar();
                            mPrimeiro.setPrimeiro(py - 20);
                        } else {
                            mPrimeiro.setUltimo(py - 20);
                        }

                    }

                    if (n2 > 0) {
                        canvas.drawRect(Q2, py - 20, Q2 + 20, py, eVermelho);

                        if (!mSegundo.tem()) {
                            mSegundo.comecar();
                            mSegundo.setPrimeiro(py - 20);
                        } else {
                            mSegundo.setUltimo(py - 20);
                        }
                    }

                    if (n3 > 0) {
                        canvas.drawRect(Q3, py - 20, Q3 + 20, py, eAzul);

                        if (!mTerceiro.tem()) {
                            mTerceiro.comecar();
                            mTerceiro.setPrimeiro(py - 20);
                        } else {
                            mTerceiro.setUltimo(py - 20);
                        }

                    }

                    int notaFinal = M3.c3(n1, n2, n3);

                    canvas.drawText(eAluno.getNome() + " :: " + notaFinal, eX, py, ePreto);


                }

                py += 50;
            }
        }

        if (mPrimeiro.tem()) {
            canvas.drawRect(Q1 + 8, mPrimeiro.getPrimeiro(), Q1 + 10, mPrimeiro.getUltimo(), eVerde);
        }

        if (mSegundo.tem()) {
            canvas.drawRect(Q2 + 8, mSegundo.getPrimeiro(), Q2 + 10, mSegundo.getUltimo(), eVermelho);
        }

        if (mTerceiro.tem()) {
            canvas.drawRect(Q3 + 8, mTerceiro.getPrimeiro(), Q3 + 10, mTerceiro.getUltimo(), eAzul);
        }

    }

    public void drawTurmaTrabalho(Canvas canvas, ArrayList<AlunoComNota> mAlunos, String eTurma, int eX, DKG g1, String eNota, int eCor) {

        Paint ePretoGrande = new Paint();
        ePretoGrande.setStyle(Paint.Style.FILL);
        ePretoGrande.setColor(Color.BLACK);
        ePretoGrande.setTextSize(60);


        //  canvas.drawText("TURMA - " + eTurma, eX + 100, 100, ePretoGrande);


        Paint ePreto = new Paint();
        ePreto.setStyle(Paint.Style.FILL);
        ePreto.setColor(Color.BLACK);
        ePreto.setTextSize(20);

        Paint ePincel = new Paint();
        ePincel.setStyle(Paint.Style.FILL);
        ePincel.setColor(eCor);
        ePincel.setTextSize(20);


        int py = 200;

        int Q1 = eX - 90;


        OnTrilha mPrimeiro = new OnTrilha();

        for (AlunoComNota eAluno : mAlunos) {
            if (eAluno.getTurma().contains(eTurma)) {

                if (eAluno.getVisibilidade().contains("SIM")) {


                    int nn = getNota(g1, eAluno.getNome(), eNota);

                    canvas.drawText(eAluno.getNome() + " :: " + nn, eX, py, ePreto);


                    if (nn > 0) {
                        canvas.drawRect(Q1, py - 20, Q1 + 20, py, ePincel);

                        if (!mPrimeiro.tem()) {
                            mPrimeiro.comecar();
                            mPrimeiro.setPrimeiro(py - 20);
                        } else {
                            mPrimeiro.setUltimo(py - 20);
                        }

                    }


                }

                py += 50;
            }
        }

        if (mPrimeiro.tem()) {
            canvas.drawRect(Q1 + 8, mPrimeiro.getPrimeiro(), Q1 + 10, mPrimeiro.getUltimo(), ePincel);
        }


    }

    public void drawTurmaTrabalhoComExtra(Canvas canvas, ArrayList<AlunoComNota> mAlunos, String eTurma, int eX, DKG gSelecionado, DKG g1, DKG g2, DKG g3, String eNotaSelecionada, String eNota1, String eNota2, String eNota3, int eCor) {

        Paint ePretoGrande = new Paint();
        ePretoGrande.setStyle(Paint.Style.FILL);
        ePretoGrande.setColor(Color.BLACK);
        ePretoGrande.setTextSize(60);


        //  canvas.drawText("TURMA - " + eTurma, eX + 100, 100, ePretoGrande);


        Paint ePreto = new Paint();
        ePreto.setStyle(Paint.Style.FILL);
        ePreto.setColor(Color.BLACK);
        ePreto.setTextSize(20);

        Paint ePincel = new Paint();
        ePincel.setStyle(Paint.Style.FILL);
        ePincel.setColor(eCor);
        ePincel.setTextSize(20);


        int py = 200;

        int Q1 = eX - 90;


        OnTrilha mPrimeiro = new OnTrilha();

        for (AlunoComNota eAluno : mAlunos) {
            if (eAluno.getTurma().contains(eTurma)) {

                if (eAluno.getVisibilidade().contains("SIM")) {

                    int n1 = getNota(g1, eAluno.getNome(), eNota1);
                    int n2 = getNota(g2, eAluno.getNome(), eNota2);
                    int n3 = getNota(g3, eAluno.getNome(), eNota3);


                    int nn = getNota(gSelecionado, eAluno.getNome(), eNotaSelecionada) + M3.getExtra(n1, n2, n3);

                    canvas.drawText(eAluno.getNome() + " :: " + nn, eX, py, ePreto);


                    if (nn > 0) {
                        canvas.drawRect(Q1, py - 20, Q1 + 20, py, ePincel);

                        if (!mPrimeiro.tem()) {
                            mPrimeiro.comecar();
                            mPrimeiro.setPrimeiro(py - 20);
                        } else {
                            mPrimeiro.setUltimo(py - 20);
                        }

                    }


                }

                py += 50;
            }
        }

        if (mPrimeiro.tem()) {
            canvas.drawRect(Q1 + 8, mPrimeiro.getPrimeiro(), Q1 + 10, mPrimeiro.getUltimo(), ePincel);
        }


    }

}
