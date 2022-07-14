package com.luandkg.czilda4.escola.avaliacao_continua;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.luandkg.czilda4.escola.desempenhador.DesempenhoIO;
import com.luandkg.czilda4.escola.desempenhador.DesempenhoReferencia;
import com.luandkg.czilda4.escola.avaliacao.Mensionador;
import com.luandkg.czilda4.escola.avaliacao.CoresDeAvaliacao;
import com.luandkg.czilda4.utils.PaletaDeCores;
import com.luandkg.czilda4.utils.Pincelador;
import com.luandkg.czilda4.utils.geometria.Ponto;

import java.util.ArrayList;

public class DesempenhoGrafico {

    public static Bitmap onMedias(ArrayList<AlunoContinuo> alunos_continuos, DesempenhoReferencia mAntes, DesempenhoReferencia mAgora, boolean isComRecuperacao) {

        int w = 600;
        int h = 250;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);


        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#455a64"));
        paint.setTextSize(15);

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor(Mensionador.COR_OMEGA));
        pintor_vermelho.setStyle(Paint.Style.FILL);

        Paint pintor_laranja = new Paint();
        pintor_laranja.setColor(Color.parseColor(Mensionador.COR_ZETA));
        pintor_laranja.setStyle(Paint.Style.FILL);


        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor(Mensionador.COR_DELTA));
        pintor_amarelo.setStyle(Paint.Style.FILL);


        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor(Mensionador.COR_ALFA));
        pintor_verde.setStyle(Paint.Style.FILL);

        Paint pintor_colorido = new Paint();
        pintor_colorido.setColor(Color.parseColor(Mensionador.COR_ALFA));
        pintor_colorido.setStyle(Paint.Style.FILL);

        int ex = 50;

        int px = 0;

        int altura = h;

        for (int a = 0; a <= 10; a++) {

            paint.setColor(Color.parseColor("#455a64"));

            int total = 0;

            if (isComRecuperacao) {
                total = DesempenhoIO.contar_com_recuperacao(alunos_continuos, a);
            } else {
                total = DesempenhoIO.contar_sem_recuperacao(alunos_continuos, a);
            }

            double prop = (double) total / (double) alunos_continuos.size();

            int real = (int) (prop * 230.0F);

            //real = 40;

            if (real > 0) {
                canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, paint);

                Paint pp = null;

                if (a >= 0 && a < 3) {
                    pp = pintor_vermelho;
                } else if (a >= 3 && a < 5) {
                    pp = pintor_laranja;
                } else if (a >= 5 && a < 7) {
                    pp = pintor_amarelo;
                } else {
                    pp = pintor_verde;
                }

                canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pp);


                paint.setColor(PaletaDeCores.BRANCO);

                if (String.valueOf(total).length() == 3) {
                    canvas.drawText(String.valueOf(total), ex + px, altura - real - 30, paint);
                } else {
                    canvas.drawText(String.valueOf(total), ex + px + 5, altura - real - 30, paint);
                }

                if (a >=0 && a<=10) {

                    int pos_y = (altura - real - 30) - 20;

                    int antes = mAntes.getValor(a);
                    int agora = mAgora.getValor(a);

                    int diferenca = 0;
                    boolean visao_para_baixo = false;

                    if (agora > antes) {
                        pintor_colorido.setColor(PaletaDeCores.VERDE);
                        diferenca = agora - antes;
                        visao_para_baixo = false;
                    } else if (antes > agora) {
                        pintor_colorido.setColor(PaletaDeCores.VERMELHO);
                        diferenca = antes - agora;
                        visao_para_baixo = true;
                    }

                    System.out.println("Diferenca :: " + diferenca + " de " + antes + " e " + agora);

                    if (diferenca != 0) {
                        //  canvas.drawRect(ex + px, pos_y - 20, ex + px + 20, pos_y, pintor_colorido);

                        paint.setColor(PaletaDeCores.CINZA);

                        if (visao_para_baixo) {
                            drawTriangle(ex + px, pos_y - 25, 20, 20, true, pintor_colorido, canvas);
                            canvas.drawText("- " + String.valueOf(diferenca), ex + px, pos_y - 30, paint);
                        } else {
                            drawTriangle(ex + px, pos_y - 5, 20, 20, false, pintor_colorido, canvas);
                            canvas.drawText("+ " + String.valueOf(diferenca), ex + px, pos_y - 35, paint);
                        }


                    }


                }

            }


            paint.setColor(PaletaDeCores.BRANCO);
            canvas.drawText(String.valueOf(a), ex + px + 5, altura - 5, paint);


            px += 50;
        }

        return bmp;

    }

    private static void drawTriangle(int x, int y, int width, int height, boolean inverted, Paint paint, Canvas canvas) {

        Point p1 = new Point(x, y);
        int pointX = x + width / 2;
        int pointY = inverted ? y + height : y - height;

        Point p2 = new Point(pointX, pointY);
        Point p3 = new Point(x + width, y);


        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        canvas.drawPath(path, paint);
    }


    public static Bitmap onDesempenho(int eParticipacao, int eCompromisso, int eDedicacao, int eFrequencia, int eQualificacao) {

        int w = 400;
        int h = 400;


        // eParticipacao = 2;
        //  eCompromisso = 1;
        //   eDedicacao = 2;
        //   eFrequencia = 2;
        //   eQualificacao = 1;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);


        Paint paint = Pincelador.criarLapis("#455a64");


        Paint pintor_preencher = Pincelador.criarPincel("#455a64");

        Paint pintor_vermelho = Pincelador.criarPincel("#f4511e");
        Paint pintor_amarelo = Pincelador.criarPincel(CoresDeAvaliacao.BOM);
        Paint pintor_verde = Pincelador.criarPincel("#7cb342");


        int metade_largura = w / 2;
        int metade_altura = h / 2;


        int altura = h;
        int largura = w;


        ArrayList<Ponto> MAXIMOS = new ArrayList<Ponto>();
        ArrayList<Ponto> MEDIOS = new ArrayList<Ponto>();
        ArrayList<Ponto> MINIMOS = new ArrayList<Ponto>();


        ArrayList<Ponto> EIXOS_1 = new ArrayList<Ponto>();
        ArrayList<Ponto> EIXOS_2 = new ArrayList<Ponto>();
        ArrayList<Ponto> EIXOS_3 = new ArrayList<Ponto>();
        ArrayList<Ponto> EIXOS_4 = new ArrayList<Ponto>();
        ArrayList<Ponto> EIXOS_5 = new ArrayList<Ponto>();

        canvas.drawRect(metade_largura - 2, 0, metade_largura + 2, altura, pintor_preencher);
        canvas.drawRect(0, metade_altura - 2, largura, metade_altura + 2, pintor_preencher);


        int dentro = 50;
        int fora = 10;

        int lapso_1 = 50;
        int lapso_2 = 70;

        MAXIMOS.add(new Ponto(50 + dentro, 30));
        MAXIMOS.add(new Ponto(350 - dentro, 30));

        MAXIMOS.add(new Ponto(350 - fora, 220));
        MAXIMOS.add(new Ponto(metade_largura - 4, altura - 30));
        MAXIMOS.add(new Ponto(50 + fora, 220));


        MEDIOS.add(new Ponto(50 + dentro + lapso_1, 30 + lapso_1));
        MEDIOS.add(new Ponto(350 - dentro - lapso_1, 30 + lapso_1));

        MEDIOS.add(new Ponto(350 - fora - lapso_1, 220 + lapso_1));
        MEDIOS.add(new Ponto(metade_largura - 4, altura - 30 - lapso_1));

        MEDIOS.add(new Ponto(50 + fora + lapso_1, 220 + lapso_1));


        MINIMOS.add(new Ponto(50 + dentro + lapso_2, 30 + lapso_2));
        MINIMOS.add(new Ponto(350 - dentro - lapso_2, 30 + lapso_2));

        MINIMOS.add(new Ponto(350 - fora - lapso_2, 220 + lapso_2));
        MINIMOS.add(new Ponto(metade_largura - 4, altura - 30 - lapso_2));

        MINIMOS.add(new Ponto(50 + fora + lapso_2, 220 + lapso_2));


        EIXOS_1.add(MINIMOS.get(0));
        EIXOS_1.add(MEDIOS.get(0));
        EIXOS_1.add(MAXIMOS.get(0));

        EIXOS_2.add(MINIMOS.get(1));
        EIXOS_2.add(MEDIOS.get(1));
        EIXOS_2.add(MAXIMOS.get(1));

        EIXOS_3.add(MINIMOS.get(2));
        EIXOS_3.add(MEDIOS.get(2));
        EIXOS_3.add(MAXIMOS.get(2));

        EIXOS_4.add(MINIMOS.get(3));
        EIXOS_4.add(MEDIOS.get(3));
        EIXOS_4.add(MAXIMOS.get(3));

        EIXOS_5.add(MINIMOS.get(4));
        EIXOS_5.add(MEDIOS.get(4));
        EIXOS_5.add(MAXIMOS.get(4));

        for (Ponto ePonto : MAXIMOS) {
            //  canvas.drawRect(ePonto.x(), ePonto.y(), ePonto.x() + 10, ePonto.y() + 10, pintor_verde);
        }
        for (Ponto ePonto : MEDIOS) {
            //canvas.drawRect(ePonto.x(), ePonto.y(), ePonto.x() + 10, ePonto.y() + 10, pintor_amarelo);
        }
        for (Ponto ePonto : MINIMOS) {
            // canvas.drawRect(ePonto.x(), ePonto.y(), ePonto.x() + 10, ePonto.y() + 10, pintor_vermelho);
        }

        if (eParticipacao >= 0 && eCompromisso >= 0) {
            canvas.drawLine(EIXOS_1.get(eParticipacao).x() + 5, EIXOS_1.get(eParticipacao).y() + 5, EIXOS_2.get(eCompromisso).x() + 5, EIXOS_2.get(eCompromisso).y() + 5, paint);
        }

        if (eCompromisso >= 0 && eDedicacao >= 0) {
            canvas.drawLine(EIXOS_2.get(eCompromisso).x() + 5, EIXOS_2.get(eCompromisso).y() + 5, EIXOS_3.get(eDedicacao).x() + 5, EIXOS_3.get(eDedicacao).y() + 5, paint);
        }

        if (eDedicacao >= 0 && eFrequencia >= 0) {
            canvas.drawLine(EIXOS_3.get(eDedicacao).x() + 5, EIXOS_3.get(eDedicacao).y() + 5, EIXOS_4.get(eFrequencia).x() + 5, EIXOS_4.get(eFrequencia).y() + 5, paint);
        }

        if (eFrequencia >= 0 && eQualificacao >= 0) {
            canvas.drawLine(EIXOS_4.get(eFrequencia).x() + 5, EIXOS_4.get(eFrequencia).y() + 5, EIXOS_5.get(eQualificacao).x() + 5, EIXOS_5.get(eQualificacao).y() + 5, paint);
        }

        if (eQualificacao >= 0 && eParticipacao >= 0) {
            canvas.drawLine(EIXOS_5.get(eQualificacao).x() + 5, EIXOS_5.get(eQualificacao).y() + 5, EIXOS_1.get(eParticipacao).x() + 5, EIXOS_1.get(eParticipacao).y() + 5, paint);
        }


        mostrarPonto(eParticipacao, EIXOS_1, canvas);
        mostrarPonto(eCompromisso, EIXOS_2, canvas);
        mostrarPonto(eDedicacao, EIXOS_3, canvas);
        mostrarPonto(eFrequencia, EIXOS_4, canvas);
        mostrarPonto(eQualificacao, EIXOS_5, canvas);

        return bmp;

    }

    public static void mostrarPonto(int p, ArrayList<Ponto> pontos, Canvas canvas) {

        Paint pintor_vermelho = Pincelador.criarPincel("#f4511e");
        Paint pintor_amarelo = Pincelador.criarPincel(CoresDeAvaliacao.BOM);
        Paint pintor_verde = Pincelador.criarPincel("#7cb342");

        Ponto ePonto = pontos.get(p);

        if (p == 0) {
            //  canvas.drawRect(ePonto.x(), ePonto.y(), ePonto.x() + 10, ePonto.y() + 10, pintor_vermelho);
            canvas.drawCircle(ePonto.x() + 3, ePonto.y() + 3, 6.0f, pintor_vermelho);

        } else if (p == 1) {
            // canvas.drawRect(ePonto.x(), ePonto.y(), ePonto.x() + 10, ePonto.y() + 10, pintor_amarelo);
            canvas.drawCircle(ePonto.x() + 3, ePonto.y() + 3, 6.0f, pintor_amarelo);

        } else if (p == 2) {
            canvas.drawCircle(ePonto.x() + 3, ePonto.y() + 3, 6.0f, pintor_verde);
        }

    }

}
