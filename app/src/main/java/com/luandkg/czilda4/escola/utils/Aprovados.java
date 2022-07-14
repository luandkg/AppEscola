package com.luandkg.czilda4.escola.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.luandkg.czilda4.utils.ImagemCriador;
import com.luandkg.czilda4.utils.Matematica;
import com.luandkg.czilda4.utils.Texto;

public class Aprovados {

    public static Bitmap criar(int quase, int aprovados, int total) {


        int largura = 500;
        int altura = 500;

        Bitmap imagem = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagem);


        Paint desenhador_aprovados = new Paint();
        desenhador_aprovados.setStrokeWidth(20);
        desenhador_aprovados.setAntiAlias(true);
        desenhador_aprovados.setStrokeCap(Paint.Cap.BUTT);
        desenhador_aprovados.setStyle(Paint.Style.STROKE);
        desenhador_aprovados.setColor(Color.parseColor("#64DD17"));

        Paint desenhador_quase = new Paint();
        desenhador_quase.setStrokeWidth(10);
        desenhador_quase.setAntiAlias(true);
        desenhador_quase.setStrokeCap(Paint.Cap.BUTT);
        desenhador_quase.setStyle(Paint.Style.STROKE);
        desenhador_quase.setColor(Color.parseColor("#FF9100"));

        float aprovados_porcentagem = Matematica.getPorcentagem(aprovados, total);
        float quase_porcentagem = Matematica.getPorcentagem(quase, total);


        String E = "#FF3D00";
        String D = "#FF9100";
        String C = "#FFC400";
        String B = "#64DD17";
        String A = "#00B0FF";


        ImagemCriador.drawArco(canvas, largura / 2, altura / 2, 200, Matematica.getAngulo(quase_porcentagem), desenhador_quase);


        ImagemCriador.drawArco(canvas, largura / 2, altura / 2, 200, Matematica.getAngulo(aprovados_porcentagem), desenhador_aprovados);

        String l = Texto.doubleNumC2(String.valueOf(aprovados_porcentagem)) + " %";

        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor(B));
        paint2.setTextSize(60);


        Rect bounds = new Rect();
        paint2.getTextBounds(String.valueOf(l), 0, String.valueOf(l).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(l), (largura / 2) - (tamanho / 2), (altura / 2) + 40, paint2);


        return imagem;

    }


}
