package com.luandkg.czilda4.escola.avaliacao_continua;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.tempo.Hoje;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.avaliacao.Mensionador;
import com.luandkg.czilda4.escola.avaliacao.CoresDeAvaliacao;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.ImagemCriador;
import com.luandkg.czilda4.utils.Matematica;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;

import java.util.ArrayList;

public class FluxoFormativoContinuado {

    public static Bitmap criarFluxoDeEntrega(int quantidade_alunos, Bimestre eBimestre, String colecao_fluxo) {

        ArrayList<Data> quais_datas = eBimestre.getDatas();


        int w = 400;
        int h = 300;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint pintor_atividades = new Paint();
        pintor_atividades.setColor(Color.parseColor("#689F38"));

        Paint pintor_atual = new Paint();
        pintor_atual.setColor(Color.parseColor("#FFC107"));

        Paint nivelador = new Paint();
        nivelador.setColor(Color.parseColor("#D84315"));


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        DKG fluxo_entrega = SigmaCollection.REQUIRED_COLLECTION(colecao_fluxo);


        DKGObjeto eFLUXO = fluxo_entrega.unicoObjeto("FLUXOS");

        // System.out.println("-->>> " + eArquivo);

        // System.out.println(fluxo_entrega.toString());

        int eixo_x = 0;

        int entre_semanas = 5;

        int coluna_largura = (w - ((eBimestre.getSemanas().size() + 1) * entre_semanas)) / quais_datas.size();
        int tamanho_completo = (coluna_largura * quais_datas.size()) + ((eBimestre.getSemanas().size() + 1) * entre_semanas);

        int sobrou = w - tamanho_completo;
        int afastar = 0;

        if (sobrou > 0) {
            afastar = sobrou / 2;
        }

        int coluna_largura_real = coluna_largura - 1;

        int somando = 0;
        int contagem = 0;

        String hoje = Calendario.getData();

        int minimo = 0;
        int maximo = 0;

        String HOJE_DIA = Calendario.getADMComTracoInferior();

        ArrayList<String> turmas_hoje = Hoje.getTurmas(HOJE_DIA);
        int atividades_realizadas_hoje = Hoje.getQuantidadeDeAtividadesRealizadas(HOJE_DIA, turmas_hoje);


        int semana_contador = 1;

        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            eixo_x = afastar + (semana_contador * entre_semanas) + ((semana_contador - 1) * 7 * coluna_largura);
            semana_contador += 1;

            for (Data data : eSemana.getDatas()) {


                int atividades = 0;

                for (DKGObjeto proc : eFLUXO.getObjetos()) {
                    if (proc.identifique("Data").getValor().contentEquals(data.getTempo())) {
                        atividades = proc.identifique("Quantidade").getInteiro(0);
                        break;
                    }
                }

                // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades);
                // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades + " vs " + HOJE_DIA );

                if (data.getTempo().contentEquals(HOJE_DIA)) {

                    // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades + " vs " + HOJE_DIA + " entao " + atividades_realizadas_hoje);

                    if (atividades_realizadas_hoje > atividades) {
                        atividades = atividades_realizadas_hoje;
                    }

                }


                if (atividades > 0) {
                    int coluna_altura = (atividades * 3);

                    if (data.isIgual(hoje)) {
                        canvas.drawRect(eixo_x, h - coluna_altura - 20, eixo_x + coluna_largura_real, h - 20, pintor_atual);
                    } else {
                        canvas.drawRect(eixo_x, h - coluna_altura - 20, eixo_x + coluna_largura_real, h - 20, pintor_atividades);
                    }

                    if (contagem == 0) {
                        minimo = eixo_x;
                    }

                    somando += atividades;
                    contagem += 1;
                    maximo = eixo_x + coluna_largura;

                }

                eixo_x += coluna_largura;

            }

        }


        if (contagem > 1) {
            int media = somando / contagem;
            canvas.drawRect(minimo, h - media, maximo, (h - media + 3), nivelador);
        }


        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#689F38"));


        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor("#FFC107"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#E53935"));

        Paint pintor_preto = new Paint();
        pintor_preto.setColor(Color.parseColor("#78909C"));

        Paint pintor_branco = new Paint();
        pintor_branco.setColor(Color.parseColor("#fafafa"));


        int coluna_largura_pintar2 = coluna_largura * 7;

        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);


        int alunos_semana = quantidade_alunos;

        int metade = alunos_semana / 2;
        int quarta = (alunos_semana / 2) + (alunos_semana / 4);

        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);

        int come = afastar + 5;


        for (SemanaContinua eSemana : eBimestre.getSemanas()) {


            int atividades = 0;

            for (Data data : eSemana.getDatas()) {


                for (DKGObjeto proc : eFLUXO.getObjetos()) {
                    if (proc.identifique("Data").getValor().contentEquals(data.getTempo())) {
                        atividades += proc.identifique("Quantidade").getInteiro(0);
                        break;
                    }
                }

            }

            if (atividades >= quarta) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_verde);
            } else if (atividades >= metade) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_amarelo);
            } else if (atividades > 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_vermelho);
            } else if (atividades == 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            }


            come += coluna_largura_pintar2 + 5;

        }


        return bmp;

    }


    public static Bitmap criarFluxoDeEntrega_ate(int quantidade_alunos, Bimestre eBimestre, String ate, String colecao_fluxo) {

        ArrayList<Data> quais_datas = eBimestre.getDatas();

        System.out.println("DATA ATE :: " + ate);

        Data data_ate = Data.toData(ate);


        int w = 400;
        int h = 300;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint pintor_atividades = new Paint();
        pintor_atividades.setColor(Color.parseColor("#689F38"));

        Paint pintor_atual = new Paint();
        pintor_atual.setColor(Color.parseColor("#FFC107"));

        Paint nivelador = new Paint();
        nivelador.setColor(Color.parseColor("#D84315"));


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        DKG fluxo_entrega = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(colecao_fluxo);


        DKGObjeto eFLUXO = fluxo_entrega.unicoObjeto("FLUXOS");

        // System.out.println("-->>> " + eArquivo);

        // System.out.println(fluxo_entrega.toString());

        int eixo_x = 0;

        int entre_semanas = 5;

        int coluna_largura = (w - ((eBimestre.getSemanas().size() + 1) * entre_semanas)) / quais_datas.size();
        int tamanho_completo = (coluna_largura * quais_datas.size()) + ((eBimestre.getSemanas().size() + 1) * entre_semanas);

        int sobrou = w - tamanho_completo;
        int afastar = 0;

        if (sobrou > 0) {
            afastar = sobrou / 2;
        }

        int coluna_largura_real = coluna_largura - 1;

        int somando = 0;
        int contagem = 0;

        String hoje = Calendario.getData();

        int minimo = 0;
        int maximo = 0;

        String HOJE_DIA = Calendario.getADMComTracoInferior();

        ArrayList<String> turmas_hoje = Hoje.getTurmas(HOJE_DIA);
        int atividades_realizadas_hoje = Hoje.getQuantidadeDeAtividadesRealizadas(HOJE_DIA, turmas_hoje);


        int semana_contador = 1;

        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            eixo_x = afastar + (semana_contador * entre_semanas) + ((semana_contador - 1) * 7 * coluna_largura);
            semana_contador += 1;

            for (Data data_agora : eSemana.getDatas()) {


                int atividades = 0;


                for (DKGObjeto proc : eFLUXO.getObjetos()) {
                    if (proc.identifique("Data").getValor().contentEquals(data_agora.getTempo())) {
                        atividades = proc.identifique("Quantidade").getInteiro(0);
                        break;
                    }
                }

                // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades);
                // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades + " vs " + HOJE_DIA );

                if (data_agora.getTempo().contentEquals(HOJE_DIA)) {

                    // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades + " vs " + HOJE_DIA + " entao " + atividades_realizadas_hoje);

                    if (atividades_realizadas_hoje > atividades) {
                        atividades = atividades_realizadas_hoje;
                    }

                }


                if (data_agora.isMenorOuIgual(data_ate)) {

                    if (atividades > 0) {
                        int coluna_altura = (atividades * 3);

                        if (data_agora.isIgual(hoje)) {
                            canvas.drawRect(eixo_x, h - coluna_altura - 20, eixo_x + coluna_largura_real, h - 20, pintor_atual);
                        } else {
                            canvas.drawRect(eixo_x, h - coluna_altura - 20, eixo_x + coluna_largura_real, h - 20, pintor_atividades);
                        }

                        if (contagem == 0) {
                            minimo = eixo_x;
                        }

                        somando += atividades;
                        contagem += 1;
                        maximo = eixo_x + coluna_largura;

                    }

                }


                eixo_x += coluna_largura;

            }

        }


        if (contagem > 1) {
            int media = somando / contagem;
            canvas.drawRect(minimo, h - media, maximo, (h - media + 3), nivelador);
        }


        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#689F38"));


        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor("#FFC107"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#E53935"));

        Paint pintor_preto = new Paint();
        pintor_preto.setColor(Color.parseColor("#78909C"));

        Paint pintor_branco = new Paint();
        pintor_branco.setColor(Color.parseColor("#fafafa"));


        int coluna_largura_pintar2 = coluna_largura * 7;

        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);


        int alunos_semana = quantidade_alunos;

        int metade = alunos_semana / 2;
        int quarta = (alunos_semana / 2) + (alunos_semana / 4);

        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);

        int come = afastar + 5;


        for (SemanaContinua eSemana : eBimestre.getSemanas()) {


            int atividades = 0;

            for (Data data_agora : eSemana.getDatas()) {

                if (data_agora.isMenorOuIgual(data_ate)) {

                    for (DKGObjeto proc : eFLUXO.getObjetos()) {
                        if (proc.identifique("Data").getValor().contentEquals(data_agora.getTempo())) {
                            atividades += proc.identifique("Quantidade").getInteiro(0);
                            break;
                        }
                    }

                }


            }

            if (atividades >= quarta) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_verde);
            } else if (atividades >= metade) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_amarelo);
            } else if (atividades > 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_vermelho);
            } else if (atividades == 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            }


            come += coluna_largura_pintar2 + 5;

        }


        return bmp;

    }

    public static Bitmap criarAvaliacao(int fizeram, int total) {


        int largura = 500;
        int altura = 500;

        Bitmap imagem = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagem);


        Paint escritor = new Paint();
        escritor.setTextSize(100);
        escritor.setColor(Color.parseColor("#388e3c"));


        Paint desenhador_arco = new Paint();
        desenhador_arco.setStrokeWidth(40);
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


    public static Bitmap onFluxo(int presente, int recuperacao, ArrayList<SemanaContinuaCarregada> semanas, ArrayList<Aluno> alunos) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        boolean mRoundedCorners = false;


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

            float arco1 = Matematica.getAnguloInt(presente);
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

            float tamanho_arco = Matematica.getAnguloInt(recuperacao);
            canvas.drawArc(new RectF(width - 220, height - 220, width + 220, height + 220), 120, tamanho_arco, false, desenhar_fino);
        }


        int i = 0;
        int distancia = 200;

        for (SemanaContinuaCarregada semana : semanas) {

            int v = semana.getFizeram();

            double taxa = (double) v / (double) alunos.size();

            taxa = taxa * 100.0;

            float porcentagem = Matematica.getPorcentagem(v, alunos.size());

            Paint desenhador_arco = new Paint();

            desenhador_arco.setStrokeWidth(5);
            desenhador_arco.setAntiAlias(true);
            desenhador_arco.setStrokeCap(Paint.Cap.BUTT);
            desenhador_arco.setStyle(Paint.Style.STROKE);
            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.COR_PORCENTAGEM(porcentagem)));

            System.out.println("SEMANA " + semana.getNome() + " >>> " + v + " de " + alunos.size() + " ::> " + taxa);

            float tamanho_arco = Matematica.getAnguloInt((int) taxa);

            canvas.drawArc(new RectF(width - distancia, height - distancia, width + distancia, height + distancia), 270, tamanho_arco, false, desenhador_arco);

            distancia -= 15;
            i += 1;
        }


        return bmp;

    }


    public static Bitmap criarFluxoDeEntregaDoAluno(Bimestre eBimestre, ArrayList<SemanaContinuaValores> mSemanas) {

        ArrayList<Data> quais_datas = eBimestre.getDatas();

        int w = 400;
        int h = 300;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#689F38"));


        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor("#FFC107"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#E53935"));

        Paint pintor_preto = new Paint();
        pintor_preto.setColor(Color.parseColor("#78909C"));

        Paint pintor_branco = new Paint();
        pintor_branco.setColor(Color.parseColor("#fafafa"));

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        int eixo_x = 0;

        int entre_semanas = 5;

        int coluna_largura = (w - ((eBimestre.getSemanas().size() + 1) * entre_semanas)) / quais_datas.size();
        int tamanho_completo = (coluna_largura * quais_datas.size()) + ((eBimestre.getSemanas().size() + 1) * entre_semanas);

        int sobrou = w - tamanho_completo;
        int afastar = 0;

        if (sobrou > 0) {
            afastar = sobrou / 2;
        }

        int coluna_largura_real = coluna_largura - 1;


        int semana_contador = 1;

        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            eixo_x = afastar + (semana_contador * entre_semanas) + ((semana_contador - 1) * 7 * coluna_largura);
            semana_contador += 1;


            for (Data data : eSemana.getDatas()) {

                for (SemanaContinuaValores eSemanaContinuaValores : mSemanas) {

                    if (eSemanaContinuaValores.getData().contentEquals(data.getFluxo())) {

                        if (eSemanaContinuaValores.getValor().contentEquals("1")) {
                            int coluna_altura = 50;
                            canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_real, h, pintor_vermelho);
                        } else if (eSemanaContinuaValores.getValor().contentEquals("2")) {
                            int coluna_altura = 100;
                            canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_real, h, pintor_amarelo);
                        } else if (eSemanaContinuaValores.getValor().contentEquals("3")) {
                            int coluna_altura = 200;
                            canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_real, h, pintor_verde);
                        }

                        break;
                    }

                }
                eixo_x += coluna_largura;

            }


        }


        canvas.drawRect(0, h - 20, quais_datas.size() * coluna_largura, h, pintor_preto);

        int coluna_largura_pintar2 = coluna_largura * 7;

        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);


        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);

        int come = afastar + 5;


        for (SemanaContinua eSemana : eBimestre.getSemanas()) {


            int UM = 0;
            int DOIS = 0;
            int TRES = 0;

            for (Data data : eSemana.getDatas()) {

                for (SemanaContinuaValores eSemanaContinuaValores : mSemanas) {

                    if (eSemanaContinuaValores.getData().contentEquals(data.getFluxo())) {
                        if (eSemanaContinuaValores.getValor().contentEquals("1")) {
                            UM += 1;
                        } else if (eSemanaContinuaValores.getValor().contentEquals("2")) {
                            DOIS += 1;
                        } else if (eSemanaContinuaValores.getValor().contentEquals("3")) {
                            TRES += 1;
                        }
                    }
                }

                //   eixo_x2 += coluna_largura;
            }


            int maior = 0;
            int quantidade = 0;

            if (UM > 0) {


                if (UM >= quantidade) {
                    quantidade = UM;
                    maior = 1;
                }
            }


            if (DOIS > 0) {
                if (DOIS >= quantidade) {
                    quantidade = DOIS;
                    maior = 2;
                }
                if (UM > 0) {
                    if (DOIS <= UM) {
                        maior = 1;
                    }
                }
            }

            if (TRES > 0) {
                if (TRES >= quantidade) {
                    quantidade = TRES;
                    maior = 3;
                }

                if (DOIS > 0) {
                    if (TRES <= DOIS) {
                        maior = 2;
                    }
                }

                if (UM > 0) {
                    if (TRES <= UM) {
                        maior = 2;
                    }
                }

            }


            //System.out.println("Semana :: " + eSemana.getNumero() + " ->> " + UM + " _ " + DOIS + " _ " + TRES + " :: " + maior);


            if (maior == 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            } else if (maior == 1) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_vermelho);
            } else if (maior == 2) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_amarelo);
            } else if (maior == 3) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_verde);

            } else {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            }

            come += coluna_largura_pintar2 + 5;

        }


        return bmp;

    }


    public static Bitmap criarDesempenho(ArrayList<AlunoContinuo> alunos_continuos_grupo, String eMensao) {

        int w = alunos_continuos_grupo.size() * 5;
        int h = 100;

        if (w == 0) {
            w = 10;
        }


        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);

        double acima = Mensionador.acima(eMensao);
        double abaixo = Mensionador.abaixo(eMensao);

        int eixo_x = 0;
        int coluna_largura = 3;
        int coluna_altura = 0;

        Paint pintor_atividades = new Paint();


        if (eMensao.contentEquals(Mensionador.OMEGA)) {
            pintor_atividades.setColor(Color.parseColor(Mensionador.COR_OMEGA));
            coluna_altura = 100;
        } else if (eMensao.contentEquals(Mensionador.ZETA)) {
            pintor_atividades.setColor(Color.parseColor(Mensionador.COR_ZETA));
            coluna_altura = 100;
        } else if (eMensao.contentEquals(Mensionador.DELTA)) {
            pintor_atividades.setColor(Color.parseColor(Mensionador.COR_DELTA));
            coluna_altura = 100;
        } else if (eMensao.contentEquals(Mensionador.ALFA)) {
            pintor_atividades.setColor(Color.parseColor(Mensionador.COR_ALFA));
            coluna_altura = 100;
        }

        for (AlunoContinuo aluno : alunos_continuos_grupo) {

            if (aluno.getNotaComRecuperacao() >= acima && aluno.getNotaComRecuperacao() < abaixo) {
                canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura, h, pintor_atividades);
            }

            eixo_x += 5;

        }


        return bmp;

    }

    public static Bitmap onFluxoCom(int presente, int recuperacao, ArrayList<SemanaContinuaCarregada> semanas, ArrayList<Aluno> alunos, int mais) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);


        int i = 0;
        int distancia = 200;

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        numeros.add(270);
        numeros.add(40);
        numeros.add(120);
        numeros.add(300);
        numeros.add(150);
        numeros.add(60);
        numeros.add(95);
        numeros.add(45);
        numeros.add(120);
        numeros.add(100);
        numeros.add(40);
        numeros.add(60);
        numeros.add(200);
        numeros.add(160);
        numeros.add(50);
        numeros.add(260);
        numeros.add(95);
        numeros.add(45);
        numeros.add(120);
        numeros.add(100);
        numeros.add(40);
        numeros.add(60);
        numeros.add(200);

        int index = 0;

        for (SemanaContinuaCarregada semana : semanas) {

            int v = semana.getFizeram();

            double taxa = (double) v / (double) alunos.size();

            taxa = taxa * 100.0;

            float porcentagem = Matematica.getPorcentagem(v, alunos.size());


            Paint desenhador_arco = new Paint();

            desenhador_arco.setStrokeWidth(5);
            desenhador_arco.setAntiAlias(true);
            desenhador_arco.setStrokeCap(Paint.Cap.BUTT);
            desenhador_arco.setStyle(Paint.Style.STROKE);

            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.COR_PORCENTAGEM(porcentagem)));


            System.out.println("SEMANA " + semana.getNome() + " >>> " + v + " de " + alunos.size() + " ::> " + taxa);

            float tamanho_arco = Matematica.getAnguloInt((int) taxa);

            int comecar = numeros.get(index) + mais;

            if (i % 2 == 0) {
                comecar = numeros.get(index) - mais;
            }

            canvas.drawArc(new RectF(width - distancia, height - distancia, width + distancia, height + distancia), comecar, tamanho_arco, false, desenhador_arco);

            distancia -= 15;
            i += 1;
            index += 1;

        }

        // ARCO 0
        if (presente > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(Color.parseColor("#ff6f00"));

            int comecar = numeros.get(index) + mais;

            if (i % 2 == 0) {
                comecar = numeros.get(index) - mais;
            }

            float presente_porcentagem = Matematica.getAnguloInt(presente);

            canvas.drawArc(new RectF(width - 240, height - 240, width + 240, height + 240), comecar, presente_porcentagem, false, desenhar_fino);
            index += 1;

        }


        // ARCO 0
        if (recuperacao > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(Color.parseColor("#2e7d32"));

            int comecar = numeros.get(index) + mais;

            if (i % 2 == 0) {
                comecar = numeros.get(index) - mais;
            }

            float recuperacao_porcentagem = Matematica.getAnguloInt(recuperacao);
            canvas.drawArc(new RectF(width - 220, height - 220, width + 220, height + 220), comecar, recuperacao_porcentagem, false, desenhar_fino);
        }


        return bmp;

    }


    public static Bitmap onSemanaFluxo(ArrayList<Aluno> alunos) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        boolean mRoundedCorners = false;
        float mStartAngle = -90;


        int um = 0;
        int dois = 0;
        int tres = 0;

        for (Aluno eAluno : alunos) {
            if (eAluno.getStatus().contentEquals("1")) {
                um += 1;
            } else if (eAluno.getStatus().contentEquals("2")) {
                dois += 1;
            } else if (eAluno.getStatus().contentEquals("3")) {
                tres += 1;
            }
        }


        // ARCO 0
        if (um > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(Color.parseColor(CoresDeAvaliacao.RUIM));


            double porcentagem = (double) um / (double) alunos.size();
            int iporcentagem = (int) (porcentagem * 100.0f);

            float angulo_tamanho = Matematica.getAnguloInt(iporcentagem);
            canvas.drawArc(new RectF(width - 240, height - 240, width + 240, height + 240), mStartAngle, angulo_tamanho, false, desenhar_fino);
        }

        // ARCO 2
        if (dois > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(Color.parseColor(CoresDeAvaliacao.BOM));


            double porcentagem = (double) dois / (double) alunos.size();
            int iporcentagem = (int) (porcentagem * 100.0f);

            float angulo_tamanho = Matematica.getAnguloInt(iporcentagem);
            canvas.drawArc(new RectF(width - 220, height - 220, width + 220, height + 220), mStartAngle, angulo_tamanho, false, desenhar_fino);
        }

        // ARCO 2
        if (tres > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));


            double porcentagem = (double) tres / (double) alunos.size();
            int iporcentagem = (int) (porcentagem * 100.0f);

            float angulo_tamanho = Matematica.getAnguloInt(iporcentagem);
            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), mStartAngle, angulo_tamanho, false, desenhar_fino);
        }


        // MODO DE ATIVIDADE

        int c_um = tres;
        int c_dois = dois;
        int c_tres = um;

        boolean tem_classificao = false;

        if (um + dois + tres > 0) {
            tem_classificao = true;
        }

        if (tem_classificao) {

            String classificao = "Z";
            String classifica_cor = "#b71c1c";

            if (c_um > (c_dois + c_tres)) {
                classificao = "A+";
                classifica_cor = "#4caf50";
            }

            if (c_um == (c_dois + c_tres)) {
                classificao = "A";
                classifica_cor = "#4caf50";
            }

            if (c_dois > (c_um + c_tres)) {
                classificao = "B";
                classifica_cor = "#f57f17";
            }

            if (c_dois == (c_um + c_tres)) {
                classificao = "B+";
                classifica_cor = "#f57f17";
            }

            if (c_tres > (c_um + c_dois)) {
                classificao = "C";
                classifica_cor = "#e53935";
            }

            if (c_tres == (c_um + c_dois)) {
                classificao = "C+";
                classifica_cor = "#e53935";
            }

            Paint paint2 = new Paint();
            paint2.setColor(Color.parseColor(classifica_cor));
            paint2.setTextSize(120);

            Rect bounds = new Rect();
            paint2.getTextBounds(String.valueOf(classificao), 0, String.valueOf(classificao).length(), bounds);
            int tamanho = bounds.width();

            canvas.drawText(String.valueOf(classificao), (w / 2) - (tamanho / 2), (h / 2) + 40, paint2);
        }


        return bmp;

    }

    public static Bitmap criarFluxoDeEntregaDaTurma(int quantidade_alunos, Bimestre eBimestre, ArrayList<AtividadeDaTurma> lista_atividades) {

        ArrayList<Data> quais_datas = eBimestre.getDatas();


        int w = 400;
        int h = 200;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint pintor_atividades = new Paint();
        pintor_atividades.setColor(Color.parseColor("#689F38"));

        Paint pintor_atual = new Paint();
        pintor_atual.setColor(Color.parseColor("#FFC107"));

        Paint nivelador = new Paint();
        nivelador.setColor(Color.parseColor("#D84315"));


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        // System.out.println("-->>> " + eArquivo);

        // System.out.println(fluxo_entrega.toString());

        int eixo_x = 0;

        int entre_semanas = 5;

        int coluna_largura = (w - ((eBimestre.getSemanas().size() + 1) * entre_semanas)) / quais_datas.size();
        int tamanho_completo = (coluna_largura * quais_datas.size()) + ((eBimestre.getSemanas().size() + 1) * entre_semanas);

        int sobrou = w - tamanho_completo;
        int afastar = 0;

        if (sobrou > 0) {
            afastar = sobrou / 2;
        }

        int coluna_largura_real = coluna_largura - 1;

        int somando = 0;
        int contagem = 0;

        String hoje = Calendario.getData();

        int minimo = 0;
        int maximo = 0;

        String HOJE_DIA = Calendario.getADMComTracoInferior();

        ArrayList<String> turmas_hoje = Hoje.getTurmas(HOJE_DIA);
        int atividades_realizadas_hoje = Hoje.getQuantidadeDeAtividadesRealizadas(HOJE_DIA, turmas_hoje);


        int semana_contador = 1;

        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            eixo_x = afastar + (semana_contador * entre_semanas) + ((semana_contador - 1) * 7 * coluna_largura);
            semana_contador += 1;

            for (Data data : eSemana.getDatas()) {


                int atividades = 0;

                for (AtividadeDaTurma at : lista_atividades) {
                    if (Data.toData(at.getData()).isIgual(data)) {
                        atividades = at.getFizeram();
                        break;
                    }
                }

                // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades);
                // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades + " vs " + HOJE_DIA );

                if (data.getTempo().contentEquals(HOJE_DIA)) {

                    // System.out.println("Atividades :: " + data.getTempo() + " com " + atividades + " vs " + HOJE_DIA + " entao " + atividades_realizadas_hoje);

                    if (atividades_realizadas_hoje > atividades) {
                        atividades = atividades_realizadas_hoje;
                    }

                }


                if (atividades > 0) {
                    int coluna_altura = (atividades * 10);

                    if (data.isIgual(hoje)) {
                        canvas.drawRect(eixo_x, h - coluna_altura - 20, eixo_x + coluna_largura_real, h - 20, pintor_atual);
                    } else {
                        canvas.drawRect(eixo_x, h - coluna_altura - 20, eixo_x + coluna_largura_real, h - 20, pintor_atividades);
                    }

                    if (contagem == 0) {
                        minimo = eixo_x;
                    }

                    somando += atividades;
                    contagem += 1;
                    maximo = eixo_x + coluna_largura;

                }

                eixo_x += coluna_largura;

            }

        }


        if (contagem > 1) {
            int media = somando / contagem;
            canvas.drawRect(minimo, h - media, maximo, (h - media + 3), nivelador);
        }


        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#689F38"));


        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor("#FFC107"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#E53935"));

        Paint pintor_preto = new Paint();
        pintor_preto.setColor(Color.parseColor("#78909C"));

        Paint pintor_branco = new Paint();
        pintor_branco.setColor(Color.parseColor("#fafafa"));


        int coluna_largura_pintar2 = coluna_largura * 7;

        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);


        int alunos_semana = quantidade_alunos;

        int metade = alunos_semana / 2;
        int quarta = (alunos_semana / 2) + (alunos_semana / 4);

        canvas.drawRect(afastar, h - 20, tamanho_completo + afastar, h, pintor_preto);

        int come = afastar + 5;


        for (SemanaContinua eSemana : eBimestre.getSemanas()) {


            int atividades = 0;

            for (Data data : eSemana.getDatas()) {


                for (AtividadeDaTurma at : lista_atividades) {
                    if (Data.toData(at.getData()).isIgual(data)) {
                        atividades += at.getFizeram();
                        break;
                    }
                }


            }

            if (atividades >= quarta) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_verde);
            } else if (atividades >= metade) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_amarelo);
            } else if (atividades > 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_vermelho);
            } else if (atividades == 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            }


            come += coluna_largura_pintar2 + 5;

        }


        return bmp;

    }

    public static Bitmap criarDesempenhoCirculo(String letra, int valor) {


        int largura = 500;
        int altura = 500;

        Bitmap imagem = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagem);


        Paint escritor = new Paint();
        escritor.setTextSize(100);
        escritor.setColor(Color.parseColor("#388e3c"));


        Paint desenhador_arco = new Paint();
        desenhador_arco.setStrokeWidth(40);
        desenhador_arco.setAntiAlias(true);
        desenhador_arco.setStrokeCap(Paint.Cap.BUTT);
        desenhador_arco.setStyle(Paint.Style.STROKE);

        float porcentagem = Matematica.getPorcentagem(100, 100);


        if (valor == 0) {
            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.RUIM));
        } else if (valor == 1) {
            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.BOM));
        } else if (valor == 2) {
            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));
        }


        float angulo = Matematica.getAngulo(porcentagem);

        ImagemCriador.drawArco(canvas, largura / 2, altura / 2, 200, angulo, desenhador_arco);


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);

        String l = String.valueOf(letra.charAt(0));

        Rect bounds = new Rect();
        paint2.getTextBounds(String.valueOf(l), 0, String.valueOf(l).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(l), (largura / 2) - (tamanho / 2), (altura / 2) + 60, paint2);


        return imagem;

    }

}
