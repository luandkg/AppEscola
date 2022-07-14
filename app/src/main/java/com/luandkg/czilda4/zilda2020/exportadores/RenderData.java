package com.luandkg.czilda4.zilda2020.exportadores;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import com.luandkg.czilda4.escola.alunos.AlunoData;
import com.luandkg.czilda4.zilda2020.SEDF;
import com.luandkg.czilda4.escola.organizacao.TurmaData;

import java.io.FileOutputStream;

public class RenderData {

    public void exportar(String eTurma, TurmaData eTurmaData, String eArquivo) {


        int eLargura = 1000;
        int eAltura = 1700;

        Bitmap eImagem = Bitmap.createBitmap(eLargura, eAltura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(eImagem);

        Paint eBranco = new Paint();
        eBranco.setStyle(Paint.Style.FILL);
        eBranco.setColor(Color.WHITE);
        eBranco.setTextSize(60);


        canvas.drawRect(0, 0, eImagem.getWidth(), eImagem.getHeight(), eBranco);

        int mY = 150;
        Paint eLapis = new Paint();
        eLapis.setStyle(Paint.Style.FILL);
        eLapis.setColor(Color.BLACK);
        eLapis.setTextSize(20);

        Paint verde = new Paint();
        verde.setColor(Color.GREEN);

        Paint vermelho = new Paint();
        vermelho.setColor(Color.RED);

        Paint amarelo = new Paint();
        amarelo.setColor(Color.YELLOW);

        Paint azul = new Paint();
        azul.setColor(Color.BLUE);


        canvas.drawRect(0, 0, eLargura, 100, eLapis);
        canvas.drawRect(0, eAltura - 100, eLargura, eAltura, eLapis);

        canvas.drawText("TURMA " + eTurma + " :: " + eTurmaData.getData().getTempoLegivel(), (eLargura / 2) - 200, 75, eBranco);


        int INICIO_Y = 100;

      //  int mxo = 600;
      //  String grupo = "AMARELO";
      //  canvas.drawText(grupo, mxo + 100, INICIO_Y + 50, eLapis);



        String eGrupoDia = SEDF.getSemanaGrupo(eTurmaData.getData().getMes(), eTurmaData.getData().getDia());


        for (AlunoData eAluno : eTurmaData.getAlunos()) {


            canvas.drawRect(80, INICIO_Y + (mY - 12), 90, INICIO_Y + (mY - 12) + 10, amarelo);


            canvas.drawText(eAluno.getNome(), 100, INICIO_Y + mY, eLapis);

            int mx = 600;

            if (eAluno.getStatus()) {

                canvas.drawRect(mx, INICIO_Y + (mY - 20), mx + 10, INICIO_Y + (mY - 20) + 10, verde);


            } else {
                canvas.drawRect(mx, INICIO_Y + (mY - 20), mx + 10, INICIO_Y + (mY - 20) + 10, vermelho);
            }

            mY += 30;

        }


        try {
            eImagem.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(eArquivo));
        } catch ( Exception e) {
            e.printStackTrace();

        }

    }


}
