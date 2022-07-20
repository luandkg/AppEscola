package com.luandkg.czilda4.escola.chamadas;


import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.utils.FS;

import java.io.File;
import java.util.ArrayList;


public class Chamada {


    public static void organizar(ArrayList<Aluno> mAlunos) {

        Local.organizarPastas();


        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL_REALIZAR_CHAMADA + "/" + Calendario.getADMComTracoInferior() + ".dkg");

        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();

        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);

            DKGObjeto eChamada = eDocumento.unicoObjeto("Chamada");

            for (Aluno eAluno : mAlunos) {

                for (DKGObjeto ePacote : eChamada.getObjetos()) {
                    if (ePacote.identifique("ID").getValor().contentEquals(eAluno.getID()) ) {

                        eAluno.setStatus(ePacote.identifique("Status").getValor());

                        break;
                    }
                }


            }

        }


    }

    public static void salvarChamada(ArrayList<Aluno> mAlunos) {


        Local.organizarPastas();

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL_REALIZAR_CHAMADA+ "/" + Calendario.getADMComTracoInferior() + ".dkg");

        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();

        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);
        }

        DKGObjeto eChamada = eDocumento.unicoObjeto("Chamada");

        for (Aluno eAluno : mAlunos) {

            boolean existe = false;
            for (DKGObjeto ePacote : eChamada.getObjetos()) {
                if (ePacote.identifique("ID").getValor().contentEquals(eAluno.getID()) ) {
                    ePacote.identifique("Status", eAluno.getStatus());
                    ePacote.identifique("Data", Calendario.getData());
                    ePacote.identifique("Horario", Calendario.getHoraCompleta());
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                DKGObjeto p = eChamada.criarObjeto("Aluno");
                p.identifique("ID", eAluno.getID());
                p.identifique("Turma", eAluno.getTurma());
                p.identifique("Nome", eAluno.getNome());
                p.identifique("Status", eAluno.getStatus());
                p.identifique("Data", Calendario.getData());
                p.identifique("Horario", Calendario.getHoraCompleta());
            }
        }

        eDocumento.salvar(eArquivoLocal);


    }

    public static void salvarChamadaNoDia(String data,ArrayList<Aluno> mAlunos) {


        Local.organizarPastas();

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL_REALIZAR_CHAMADA + "/" + data + ".dkg");

        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();

        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);
        }

        DKGObjeto eChamada = eDocumento.unicoObjeto("Chamada");

        for (Aluno eAluno : mAlunos) {

            boolean existe = false;
            for (DKGObjeto ePacote : eChamada.getObjetos()) {
                if (ePacote.identifique("ID").getValor().contentEquals(eAluno.getID()) ) {
                    ePacote.identifique("Status", eAluno.getStatus());
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                DKGObjeto p = eChamada.criarObjeto("Aluno");
                p.identifique("ID", eAluno.getID());
                p.identifique("Turma", eAluno.getTurma());
                p.identifique("Nome", eAluno.getNome());
                p.identifique("Status", eAluno.getStatus());
            }
        }

        eDocumento.salvar(eArquivoLocal);


    }

    public static int getPresentesPorcentagem() {

        Local.organizarPastas();


        DKG eArquivoChamadas = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_FREQUENCIAS);

        DKGObjeto eContagem = eArquivoChamadas.unicoObjeto("FREQUENCIA");

        int todos = 0;
        int presente = 0;

        for (DKGObjeto aluno_contando : eContagem.getObjetos()) {

            int v = aluno_contando.identifique("Frequencia").getInteiro();

            if (v > 10) {
                presente += 1;
            }

            todos += 1;

        }

        return (int) (((float) presente / (float) todos) * 100.0F);
    }

}
