package com.luandkg.czilda4.zilda2020.exportadores;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.luandkg.czilda4.escola.avaliacao_quantitativa.QuadroDeNotas;

public class FluxoDeAtividades {

    public static Bitmap onFluxo(int presente, int recuperacao,int v1, int v2, int v3, int v4, int v5) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        int mStrokeWidth = 20;              // Width of outline
        boolean mRoundedCorners = false;     // Set to true if rounded corners should be applied to outline ends
       // float mStartAngle = -90;      // Always start from top (default is: "3 o'clock on a watch.")

        Paint paint = new Paint();

        paint.setStrokeWidth(mStrokeWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);

        int arco_0 = Color.parseColor("#ff6f00");

        int arco_1 = Color.parseColor("#c62828");
        int arco_2 = Color.parseColor("#6a1b9a");
        int arco_3 = Color.parseColor("#1565c0");
        int arco_4 = Color.parseColor("#2e7d32");
        int arco_5 = Color.parseColor("#f9a825");


        // ARCO 0
        if (presente > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(arco_0);

            float arco1 = calcSweepAngleFromProgress(presente);
            canvas.drawArc(new RectF(width - 240, height - 240, width + 240, height + 240), 50, arco1, false, desenhar_fino);
        }


        // ARCO 0
        if (recuperacao > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(arco_4);

            float tamanho_arco = calcSweepAngleFromProgress(recuperacao);
            canvas.drawArc(new RectF(width - 220, height - 220, width + 220, height + 220), 120, tamanho_arco, false, desenhar_fino);
        }


        // ARCO 1
        if (v1 > 0) {


            float tamanho_arco = calcSweepAngleFromProgress(v1);
            paint.setColor(arco_1);
            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), 270, tamanho_arco, false, paint);
        }

        // ARCO 2
        if (v2 > 0) {
            float tamanho_arco = calcSweepAngleFromProgress(v2);
            paint.setColor(arco_2);
            canvas.drawArc(new RectF(width - 160, height - 160, width + 160, height + 160), 90, tamanho_arco, false, paint);
        }

        if (v3 > 0) {

            // ARCO 3
            float tamanho_arco = calcSweepAngleFromProgress(v3);
            paint.setColor(arco_3);
            canvas.drawArc(new RectF(width - 120, height - 120, width + 120, height + 120), -180, tamanho_arco, false, paint);


        }

        if (v4 > 0) {

            // ARCO 4
            float tamanho_arco = calcSweepAngleFromProgress(v4);
            paint.setColor(arco_4);
            canvas.drawArc(new RectF(width - 80, height - 80, width + 80, height + 80), 70, tamanho_arco, false, paint);


        }

        if (v5 > 0) {

            // ARCO 5
            float tamanho_arco = calcSweepAngleFromProgress(v5);
            paint.setColor(arco_5);
            canvas.drawArc(new RectF(width - 40, height - 40, width + 40, height + 40), 45, tamanho_arco, false, paint);


        }

        return bmp;

    }

    private static float calcSweepAngleFromProgress(int progress) {
        return (360.0F / 100.0F) * progress;
    }

    public static Bitmap onAtividade(QuadroDeNotas atividade) {

        int w = 600;
        int h = 200;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        int mStrokeWidth = 1;              // Width of outline
        boolean mRoundedCorners = false;     // Set to true if rounded corners should be applied to outline ends

        Paint paint = new Paint();
        paint.setStrokeWidth(mStrokeWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#455a64"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#f4511e"));
        pintor_vermelho.setStyle(Paint.Style.FILL);

        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#7cb342"));
        pintor_verde.setStyle(Paint.Style.FILL);


        int ex = 200;

        int alunos = 4;
        int px = 0;

        int altura = h;

        for (int a = 0; a < alunos; a++) {

            paint.setColor(Color.parseColor("#455a64"));

            int total = quantosDesse(atividade, a);
            double prop = (double) total / (double) atividade.getNotas().size();

            int real = (int) (prop * 100.0F);

            if (real > 0) {
                canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, paint);

                if (a == 0) {
                    canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pintor_vermelho);
                } else if (a == 1) {
                    canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pintor_vermelho);

                } else if (a == 2) {
                    canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pintor_verde);

                } else if (a == 3) {
                    canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pintor_verde);
                }

                paint.setColor(Color.parseColor("#fafafa"));

                if (String.valueOf(total).length()==3){
                    canvas.drawText(String.valueOf(total), ex + px , altura - real - 30, paint);
                }else{
                    canvas.drawText(String.valueOf(total), ex + px + 5, altura - real - 30, paint);
                }


            }


            paint.setColor(Color.parseColor("#fafafa"));
            canvas.drawText(String.valueOf(a), ex + px + 5, altura - 5, paint);


            px += 50;
        }

        return bmp;

    }

    public static int quantosDesse(QuadroDeNotas atividade, int valor) {

        int f = 0;

        for (int v : atividade.getNotas()) {
            if (v == valor) {
                f += 1;
            }
        }

        return f;

    }

    public static Bitmap onAtividadeFinal(QuadroDeNotas atividade) {

        int w = 600;
        int h = 200;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        int mStrokeWidth = 1;              // Width of outline
        boolean mRoundedCorners = false;     // Set to true if rounded corners should be applied to outline ends

        Paint paint = new Paint();
        paint.setStrokeWidth(mStrokeWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#455a64"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#f4511e"));
        pintor_vermelho.setStyle(Paint.Style.FILL);

        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#7cb342"));
        pintor_verde.setStyle(Paint.Style.FILL);

        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor("#fdd835"));
        pintor_amarelo.setStyle(Paint.Style.FILL);


        int ex = 130;

        int alunos = 11;
        int px = 0;

        int altura = h;

        for (int a = 0; a < alunos; a++) {

            paint.setColor(Color.parseColor("#455a64"));

            int total = quantosDesse(atividade, a);
            double prop = (double) total / (double) atividade.getNotas().size();

            int real = (int) (prop * 100.0F);

            if (real > 0) {
                canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, paint);

                if (a < 5) {
                    canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pintor_vermelho);
                } else if (a >= 5 && a <= 7) {
                    canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pintor_amarelo);
                } else if (a > 7) {
                    canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pintor_verde);
                }


                paint.setColor(Color.parseColor("#fafafa"));

                if (String.valueOf(total).length()==3){
                    canvas.drawText(String.valueOf(total), ex + px , altura - real - 30, paint);
                }else{
                    canvas.drawText(String.valueOf(total), ex + px + 5, altura - real - 30, paint);
                }

            }


            paint.setColor(Color.parseColor("#fafafa"));
            canvas.drawText(String.valueOf(a), ex + px + 5, altura - 5, paint);


            px += 30;
        }

        return bmp;

    }


    public static Bitmap onBimestre(int v, int acabar) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        int mStrokeWidth = 20;
        float mStartAngle = -90;

        Paint paint = new Paint();
        paint.setStrokeWidth(mStrokeWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);


        // ARCO 1
        if (v > 0) {
            float arco1 = calcSweepAngleFromProgress(v);
            paint.setColor(Color.parseColor("#1565C0"));
            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), mStartAngle, arco1, false, paint);
        }

        Paint escritor = new Paint();
        escritor.setTextSize(150);
        escritor.setColor(Color.WHITE);


        Rect bounds = new Rect();
        escritor.getTextBounds(String.valueOf(acabar), 0, String.valueOf(acabar).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(acabar), (w / 2) - (tamanho / 2) - 20, (h / 2) + 30, escritor);


        return bmp;

    }


}
