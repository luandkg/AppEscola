package com.luandkg.czilda4.zilda2020.exportadores;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoFrequencia;
import com.luandkg.czilda4.zilda2020.SEDF;
import com.luandkg.czilda4.escola.organizacao.TurmaData;
import com.luandkg.czilda4.utils.tempo.Data;
import com.luandkg.czilda4.utils.tempo.HorarioTurma;
import com.luandkg.czilda4.utils.FS;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class RenderGeral {


    public boolean isVisivel(String eNome, ArrayList<String> eVisiveis) {
        return eVisiveis.contains(eNome);
    }

    public ArrayList<TurmaData> exportarTudoTurma(ArrayList<Aluno> mZildaAlunos, String eLocalChamadas, String eTurma, String eBimestre, ArrayList<HorarioTurma> mTurmas, ArrayList<Data> mDatas, String eNomeArquivo) {


        ArrayList<TurmaData> eChamadas = new ArrayList<TurmaData>();

        ArrayList<AlunoFrequencia> mTodos = new ArrayList<AlunoFrequencia>();

        ArrayList<String> eVisiveis = new ArrayList<String>();

        for (Aluno eAluno : mZildaAlunos) {
            mTodos.add(new AlunoFrequencia(eAluno.getTurma(), eAluno.getNome()));
            if (eAluno.getVisibilidade().contentEquals("SIM")) {
                eVisiveis.add(eAluno.getNome());
            }
        }

        System.out.println("-->> Turma :: " + eTurma);


        //   int eQuantosDias = 0;

        HorarioTurma eHorarioTurma = SEDF.getHorarioDaTurma(mTurmas, eTurma);

        ArrayList<Data> mDatasDaTurma = SEDF.getAulasDaTurma(mDatas, eHorarioTurma);


        for (Data eData : mDatasDaTurma) {

            String eArquivoLocal = FS.getArquivoLocal(eLocalChamadas + "/" + eData.getTempo() + ".dkg");
            File eArquivo = new File(eArquivoLocal);
            //System.out.println("-->> Passando :: " + eArquivoLocal + " :: " + eArquivo.exists());

            if (eArquivo.exists()) {
                //   System.out.println("-->> Abrindo :: " + eArquivoLocal);

                DKG eDocumento = new DKG();
                eDocumento.abrir(eArquivoLocal);

                DKGObjeto eChamada = eDocumento.unicoObjeto("Chamada");

                for (AlunoFrequencia eAluno : mTodos) {

                    for (DKGObjeto ePacote : eChamada.getObjetos()) {
                        if (ePacote.identifique("Turma").getValor().contentEquals(eAluno.getTurma()) && ePacote.identifique("Nome").getValor().contentEquals(eAluno.getNome())) {
                            eAluno.marcar(eData.getTempo(), ePacote.identifique("Status").getValor());
                            //  System.out.println("-->> Marcar Status :: " + eAluno.getNome());
                            break;
                        }
                    }

                }

            }

        }

        int eLargura = 5000;
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
        vermelho.setTextSize(20);

        Paint amarelo = new Paint();
        amarelo.setColor(Color.YELLOW);

        Paint azul = new Paint();
        azul.setColor(Color.BLUE);


        canvas.drawRect(0, 0, eLargura, 100, eLapis);
        canvas.drawRect(0, eAltura - 100, eLargura, eAltura, eLapis);

        canvas.drawText("TURMA " + eTurma, (eLargura / 2) - 200, 75, eBranco);


        int INICIO_Y = 100;

        int INICIO_DEPOIS_NOME = 600;

        String grupo = "";
        canvas.drawText(grupo, INICIO_DEPOIS_NOME + 100, INICIO_Y + 50, eLapis);

        final int ESPACO_ENTREDATAS = 110;

        int eMaximoHorizontal = 0;

        int mxo = INICIO_DEPOIS_NOME;


        for (Data eData : mDatasDaTurma) {

            String eGrupoDia = SEDF.getSemanaGrupo(eData.getMes(), eData.getDia());


            if (!eGrupoDia.contentEquals(grupo)) {
                mxo += ESPACO_ENTREDATAS + 40;
                grupo = eGrupoDia;


                int posx = mxo + 40;

                if (grupo.contentEquals("AMARELO")) {
                    posx -= 50;
                }

                canvas.drawText(grupo, posx, INICIO_Y + 50, eLapis);

            }

            canvas.drawText(eData.getTempoLegivel(), mxo - 50, INICIO_Y + 90, eLapis);


            mxo += ESPACO_ENTREDATAS;

        }


        for (AlunoFrequencia eAluno : mTodos) {

            int presente = 0;

            if (eAluno.getTurma().contentEquals(eTurma) && eAluno.getNome().length() > 5) {


                if (isVisivel(eAluno.getNome(), eVisiveis)) {

                        canvas.drawRect(80, INICIO_Y + (mY - 12), 90, INICIO_Y + (mY - 12) + 10, amarelo);



                    grupo = "";

                    int mx = INICIO_DEPOIS_NOME;

                    for (Data eData : mDatasDaTurma) {

                        String eGrupoDia = SEDF.getSemanaGrupo(eData.getMes(), eData.getDia());
                        if (!eGrupoDia.contentEquals(grupo)) {
                            mx += ESPACO_ENTREDATAS + 40;
                            grupo = eGrupoDia;
                        }

                        boolean isPresente = eAluno.isPresente(eData.getTempo());
                        boolean outraSemana = false;

                        if (!isPresente) {

                            outraSemana = true;

                            int ePassado = veioAntes(eAluno, eData, mDatasDaTurma);
                            if (ePassado > 0) {
                                isPresente = true;
                                outraSemana = true;
                            }

                        }


                        if (isPresente) {

                            if (outraSemana) {
                                canvas.drawRect(mx, INICIO_Y + (mY - 20), mx + 10, INICIO_Y + (mY - 20) + 10, azul);

                            } else {
                                canvas.drawRect(mx, INICIO_Y + (mY - 20), mx + 10, INICIO_Y + (mY - 20) + 10, verde);
                            }

                            presente += 1;
                        } else {
                            canvas.drawRect(mx, INICIO_Y + (mY - 20), mx + 10, INICIO_Y + (mY - 20) + 10, vermelho);
                        }

                        TurmaData eChamada = getChamada(eChamadas, eData);
                        eChamada.chamar(eAluno.getNome(), isPresente);

                        mx += ESPACO_ENTREDATAS;
                    }

                    if (mx > eMaximoHorizontal) {
                        eMaximoHorizontal = mx;
                    }

                    String estaAusente = "";
                    if (presente == 0) {
                        estaAusente = " #AUSENTE";
                    }

                    if (presente == 0) {
                        canvas.drawText(eAluno.getNome() + estaAusente, 100, INICIO_Y + mY, vermelho);
                    } else {
                        canvas.drawText(eAluno.getNome(), 100, INICIO_Y + mY, eLapis);
                    }

                }

                mY += 30;
            }

        }


        eMaximoHorizontal += 100;

        int ey = 200;

        canvas.drawText("Data - CN", eMaximoHorizontal, ey, eLapis);
        canvas.drawRect(eMaximoHorizontal, ey + 15, eMaximoHorizontal + 200, ey + 10, eLapis);

        ey += 50;

        for (Data eData : mDatasDaTurma) {
            canvas.drawText("-- " + eData.getTempoLegivel(), eMaximoHorizontal + 50, ey, eLapis);
            ey += 30;
        }


        ey += 100;
        canvas.drawText("Data - PD", eMaximoHorizontal, ey, eLapis);
        canvas.drawRect(eMaximoHorizontal, ey + 15, eMaximoHorizontal + 200, ey + 10, eLapis);

        ey += 50;

        for (Data eData : SEDF.getAulasDePDTurma(mDatas, eHorarioTurma)) {
            canvas.drawText("-- " + eData.getTempoLegivel(), eMaximoHorizontal + 50, ey, eLapis);
            ey += 30;
        }

        try {
            eImagem.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(eNomeArquivo));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eChamadas;
    }

    public int veioAntes(AlunoFrequencia eAluno, Data eDataCirrente, ArrayList<Data> mDatasDaTurma) {


        int r = 0;

        int indice = getDataIndice(eDataCirrente.getTempo(), mDatasDaTurma);

        int inicio = indice - 3;

        int i = 0;

        for (Data eData : mDatasDaTurma) {
            if (i >= inicio && i <= indice) {

                boolean isPresente = eAluno.isPresente(eData.getTempo());
                if (isPresente) {
                    r += 1;
                }

            }
            i += 1;
        }


        return r;
    }

    public int getDataIndice(String eDataCorrente, ArrayList<Data> mDatasDaTurma) {

        int i = 0;

        for (Data eData : mDatasDaTurma) {
            if (eData.getTempo().contentEquals(eDataCorrente)) {
                break;
            }
            i += 1;
        }

        return i;

    }


    public TurmaData getChamada(ArrayList<TurmaData> eChamadas, Data eData) {

        boolean enc = false;
        TurmaData ret = null;

        for (TurmaData eTurma : eChamadas) {
            if (eTurma.getData().getTempo().contentEquals(eData.getTempo())) {
                enc = true;
                ret = eTurma;
                break;
            }
        }

        if (!enc) {
            TurmaData a = new TurmaData(eData);
            eChamadas.add(a);
            ret = a;
        }

        return ret;
    }
}
