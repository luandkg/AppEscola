package com.luandkg.czilda4.escola.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.luandkg.czilda4.escola.avaliacao.CoresDeAvaliacao;
import com.luandkg.czilda4.utils.ImagemCriador;
import com.luandkg.czilda4.utils.Matematica;

public class CicloDeProgresso {

    public static Bitmap criarProgresso(int fizeram, int total) {


        int largura = 500;
        int altura = 500;

        Bitmap imagem = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagem);


        Paint escritor = new Paint();
        escritor.setTextSize(100);
        escritor.setColor(Color.parseColor("#388e3c"));


        Paint desenhador_arco = new Paint();
        desenhador_arco.setStrokeWidth(20);
        desenhador_arco.setAntiAlias(true);
        desenhador_arco.setStrokeCap(Paint.Cap.BUTT);
        desenhador_arco.setStyle(Paint.Style.STROKE);

        float porcentagem = Matematica.getPorcentagem(fizeram, total);


        String E = "#FF3D00";
        String D = "#FF9100";
        String C = "#FFC400";
        String B = "#64DD17";
        String A = "#00B0FF";


        if (porcentagem >= 90) {
            desenhador_arco.setColor(Color.parseColor(A));
        } else if (porcentagem >= 75 && porcentagem < 90) {
            desenhador_arco.setColor(Color.parseColor(B));
        } else if (porcentagem >= 50 && porcentagem < 75) {
            desenhador_arco.setColor(Color.parseColor(C));
        } else if (porcentagem >= 25 && porcentagem < 50) {
            desenhador_arco.setColor(Color.parseColor(D));
        } else {
            desenhador_arco.setColor(Color.parseColor(E));
        }


        float angulo = Matematica.getAngulo(porcentagem);

        ImagemCriador.drawArco(canvas, largura / 2, altura / 2, 200, angulo, desenhador_arco);

        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor(CoresDeAvaliacao.NAO_AVALIADO));
        paint2.setTextSize(110);


        String l = String.valueOf((int) porcentagem) + "%";


        Rect bounds = new Rect();
        paint2.getTextBounds(String.valueOf(l), 0, String.valueOf(l).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(l), (largura / 2) - (tamanho / 2)-15, (altura / 2) + 50, paint2);


        return imagem;

    }


}
