package com.luandkg.czilda4.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class ImagemCriador {


    public static void drawTextoCentralizado(Canvas canvas, int width, int height, int abaixar, String texto, Paint mPaint) {

        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(texto), 0, String.valueOf(texto).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(texto), (width / 2) - (tamanho / 2), (height / 2) + abaixar, mPaint);


    }


    public static void drawArco(Canvas canvas, int width, int height, float raio, float angulo, Paint mPaint) {

        final int mStartAngle = -90;
        canvas.drawArc(new RectF(width - raio, height - raio, width + raio, height + raio), mStartAngle, angulo, false, mPaint);

    }

    public static Bitmap vazio(){

        int largura = 200;
        int altura = 200;

        Bitmap imagem = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagem);

        return imagem;
    }




}
