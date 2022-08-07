package com.luandkg.czilda4.escola.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.luandkg.czilda4.escola.organizacao.TurmaComHorario;
import com.luandkg.czilda4.utils.ImagemCriador;
import com.luandkg.czilda4.utils.Matematica;

import java.util.ArrayList;

public class EmblemadorHoje {

    public static Bitmap criar(ArrayList<TurmaComHorario> turmas_realizadas, int fizeram, int total,boolean isDark) {


        int largura = 800;
        int altura = 500;

        Bitmap imagem = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagem);


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

        // fizeram=3;
        // porcentagem=70;

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


        Paint escritor = new Paint();
        escritor.setTextSize(80);

        if (isDark){
            escritor.setColor(Color.WHITE);
        }else{
            escritor.setColor(Color.BLACK);
        }


        if (fizeram > 0) {
            canvas.drawText(fizeram + " de " + total, 280, 270, escritor);
            escritor.setTextSize(40);
            canvas.drawText("aulas realizadas", 250, 320, escritor);
        } else {
            canvas.drawText("     --", 290, 270, escritor);
        }

        int mais_y = 100;

        for (TurmaComHorario turma : turmas_realizadas) {

            //540
            escritor.setTextSize(35);
            canvas.drawText(turma.getHorarioSemSegundos() + " :: " + turma.getTurma(), 0, mais_y, escritor);

            mais_y += 70;
        }


        return imagem;

    }

    public static Bitmap criarSimples(int fizeram, int total) {


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


        return imagem;

    }

}
