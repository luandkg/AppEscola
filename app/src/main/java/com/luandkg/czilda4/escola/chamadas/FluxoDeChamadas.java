package com.luandkg.czilda4.escola.chamadas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.AlunoChamadas;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.tempo.Calendario;
import com.luandkg.czilda4.utils.tempo.Data;

import java.util.ArrayList;

public class FluxoDeChamadas {

    public static Bitmap criarFluxoDePresenca(int quantidade_alunos,Bimestre eBimestre, String eArquivo) {

        int w = 400;
        int h = 250;



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

        ArrayList<Data> quais_datas=eBimestre.getDatas();

        DKG fluxo_entrega = new DKG();

        if (FS.arquivoExiste(eArquivo)) {
            fluxo_entrega.abrir(FS.getArquivoLocal(eArquivo));
        }

        System.out.println(fluxo_entrega.toString());

        DKGObjeto eESTATISTICAS = fluxo_entrega.unicoObjeto("ESTATISTICAS");

        int entre_semanas = 5;

        int coluna_largura = (w - ((eBimestre.getSemanas().size()+1) * entre_semanas)) / quais_datas.size();
        int tamanho_completo = (coluna_largura * quais_datas.size()) + ((eBimestre.getSemanas().size()+1) * entre_semanas);

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

        int eixo_x = 0;

        int semana_contador = 1;

        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            eixo_x = afastar + (semana_contador * entre_semanas) + ((semana_contador - 1) * 7 * coluna_largura);
            semana_contador += 1;

            for (Data data : eSemana.getDatas()) {

                int presente = 0;

                for (DKGObjeto proc : eESTATISTICAS.getObjetos()) {
                    if (proc.identifique("Data").getValor().contentEquals(data.getTempo())) {
                        presente = proc.identifique("Presente").getInteiro(0);
                        break;
                    }
                    System.out.println("\t -->> " +proc.identifique("Data").getValor());
                }


                double tx = (double) presente / (double) quantidade_alunos;

                presente = (int) (tx * (double) h);

                System.out.println("-->> DATA : " + data.getTempo() + " :: " + data.getTempo() + " -->> " + presente);

                if (presente > 0) {
                    int coluna_altura = (presente * 1);

                    if (data.isIgual(hoje)) {
                        canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_real, h, pintor_atual);
                    } else {
                        canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_real, h, pintor_atividades);
                    }

                    if (contagem == 0) {
                        minimo = eixo_x;
                    }

                    somando += presente;
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


        int metade = quantidade_alunos / 2;
        int quarta = (quantidade_alunos / 2) + (quantidade_alunos / 4);

        canvas.drawRect(afastar, h - 20, tamanho_completo+afastar, h, pintor_preto);

        int come = afastar + 5;


        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            int presencas = 0;

            for (Data data : eSemana.getDatas()) {

                for (DKGObjeto proc : eESTATISTICAS.getObjetos()) {
                    if (proc.identifique("Data").getValor().contentEquals(data.getTempo())) {
                        presencas += proc.identifique("Presente").getInteiro(0);
                        break;
                    }
                }


            }

            if (presencas >= quarta) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_verde);
            } else if (presencas >= metade) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_amarelo);
            } else if (presencas > 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_vermelho);
            } else if (presencas == 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            }

            System.out.println("Presenca :: " + presencas);

            come += coluna_largura_pintar2 + 5;

        }


        return bmp;

    }

    public static int getFluxoDePresenca(String retornarData, String eArquivo) {

        int Presente = 0;

        DKG fluxo_entrega = new DKG();

        System.out.println(eArquivo+" -->> " + FS.arquivoExiste(eArquivo));

        if (FS.arquivoExiste(eArquivo)) {

            fluxo_entrega.abrir(FS.getArquivoLocal(eArquivo));

            System.out.println(fluxo_entrega.toString());

            for (DKGObjeto proc : fluxo_entrega.unicoObjeto("ESTATISTICAS").getObjetos()) {

                System.out.println("dt :: " + proc.identifique("Data").getValor());

                if (proc.identifique("Data").getValor().contentEquals(retornarData)) {
                    Presente = proc.identifique("Presente").getInteiro(0);
                    break;
                }
            }

        }


        return Presente;

    }

    public static Bitmap criarFluxoDePresencaDaTurma(TurmaChamadas turma,  Bimestre eBimestre ) {

        int w = 450;
        int h = 250;

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

        ArrayList<Data> quais_datas=eBimestre.getDatas();

        int entre_semanas = 5;

        int coluna_largura = (w - ((eBimestre.getSemanas().size()+1) * entre_semanas)) / quais_datas.size();
        int tamanho_completo = (coluna_largura * quais_datas.size()) + ((eBimestre.getSemanas().size()+1) * entre_semanas);

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

        int eixo_x = 0;

        int semana_contador = 1;

        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            eixo_x = afastar + (semana_contador * entre_semanas) + ((semana_contador - 1) * 7 * coluna_largura);
            semana_contador += 1;

            for (Data data : eSemana.getDatas()) {

                int presente=0;

                for(AlunoChamadas a : turma.getAlunos()){
                    for(DataChamada d : a.getDatas()){
                       // System.out.println(d.getDataSemDia() + " :: " + data.getTempoLegivel());
                        if (d.getDataSemDia().contentEquals(data.getTempoLegivel())){
                            if (d.getStatus()){
                                presente+=1;
                            }
                        }
                    }
                }


                double tx = (double) presente / (double) turma.getAlunos().size();

                presente = (int) (tx * (double) h);

                System.out.println("-->> DATA : " + data.getTempo() + " :: " + presente + " -->> " + presente);

                if (presente > 0) {
                    int coluna_altura = (presente * 1);

                    if (data.isIgual(hoje)) {
                        canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_real, h, pintor_atual);
                    } else {
                        canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_real, h, pintor_atividades);
                    }

                    if (contagem == 0) {
                        minimo = eixo_x;
                    }

                    somando += presente;
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


        int metade = turma.getAlunos().size() / 2;
        int quarta = (turma.getAlunos().size() / 2) + (turma.getAlunos().size() / 4);

        canvas.drawRect(afastar, h - 20, tamanho_completo+afastar, h, pintor_preto);

        int come = afastar + 5;


        for (SemanaContinua eSemana : eBimestre.getSemanas()) {

            int presencas = 0;

            for (Data data : eSemana.getDatas()) {

                for(AlunoChamadas a : turma.getAlunos()){
                    for(DataChamada d : a.getDatas()){
                        if (d.getDataSemDia().contentEquals(data.getTempoLegivel())){
                            if (d.getStatus()){
                                presencas+=1;
                            }
                        }
                    }
                }


            }

            if (presencas >= quarta) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_verde);
            } else if (presencas >= metade) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_amarelo);
            } else if (presencas > 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_vermelho);
            } else if (presencas == 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            }

            System.out.println("Presenca :: " + presencas);

            come += coluna_largura_pintar2 + 5;

        }


        return bmp;

    }

}
