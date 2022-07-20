package com.luandkg.czilda4.zilda2020.exportadores;

import android.content.Context;
import android.widget.Toast;

import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.zilda2020.SEDF;
import com.luandkg.czilda4.escola.organizacao.TurmaData;
import com.luandkg.czilda4.libs.tempo.HorarioTurma;
import com.luandkg.czilda4.utils.FS;

import java.util.ArrayList;

public class ExportarTudo {

    private String LOCAL;
    private String LOCAL_CHAMADAS;
    private String LOCAL_TURMAS;
    private String LOCAL_RELATORIOS;


    public ExportarTudo(String eLOCAL, String eLOCAL_RELATORIOS, String eLOCAL_CHAMADAS, String eLOCAL_TURMAS ) {
        LOCAL = eLOCAL;
        LOCAL_RELATORIOS = eLOCAL_RELATORIOS;
        LOCAL_CHAMADAS = eLOCAL_CHAMADAS;
        LOCAL_TURMAS = eLOCAL_TURMAS;
    }



    public void exportarTudo(Context eContext, String BimestreNumero, String BimestreTitulo) {

        FS.dirCriar(LOCAL);

        Toast.makeText(eContext, "Organizando chamadas !", Toast.LENGTH_SHORT).show();


        RenderGeral eRenderGeral = new RenderGeral();

        for (HorarioTurma eTurma : SEDF.getHorariosDasTurmas()) {

            String eArquivoTurma = FS.getArquivoLocal(LOCAL_RELATORIOS + "/Frequencia_" + BimestreNumero + "_" + eTurma.getTurma() + ".png");
            ArrayList<TurmaData> eDatasDaTurma = eRenderGeral.exportarTudoTurma(Escola.carregarAlunos(), LOCAL + "/" + LOCAL_CHAMADAS, eTurma.getTurma(), BimestreTitulo, SEDF.getHorariosDasTurmas(),  SEDF.get4Bimestre(), eArquivoTurma);

            RenderData eRenderData = new RenderData();

            Toast.makeText(eContext, "Turma " + eArquivoTurma + " !", Toast.LENGTH_SHORT).show();

            for (TurmaData eChamadaDaTurmaData : eDatasDaTurma) {

                String eArquivo = FS.getArquivoLocal(LOCAL + "/" + LOCAL_TURMAS + "/" + eTurma.getTurma() + "/" + eChamadaDaTurmaData.getData().getTempo() + ".png");

                Toast.makeText(eContext, "Arquivo :: " + eArquivo, Toast.LENGTH_SHORT).show();

                eRenderData.exportar(eTurma.getTurma(), eChamadaDaTurmaData, eArquivo);

            }

        }


        Toast.makeText(eContext, "Exportando chamada !", Toast.LENGTH_SHORT).show();

    }


}
