package com.luandkg.czilda4.escola;


import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.Texto;

import java.io.File;
import java.util.ArrayList;

public class Escola {

    public static ArrayList<Aluno> carregarAlunos() {

        ArrayList<Aluno> mAlunos = new ArrayList<Aluno>();

        Local.organizarPastas();


        if (SigmaCollection.EXISTS_COLLECTION(Local.COLECAO_ALUNOS)) {

            //  System.out.println("Carregar :: Alunos Existe " + eDocumento.getObjetos().size());
            // System.out.println("Carregar :: Alunos Existe " + eAlunos.getObjetos().size());

            for (DKGObjeto aluno_objeto : SigmaCollection.REQUIRED_COLLECTION(Local.COLECAO_ALUNOS, "Alunos")) {

                String eID = aluno_objeto.id_string("ID");

                String eTurma = aluno_objeto.id_string("Turma");
                String eNome = aluno_objeto.id_string("Nome");
                String eStatus = aluno_objeto.id_string("Status");
                String eVisibilidade = aluno_objeto.id_string("Visibilidade");

                if (eStatus.length() == 0) {
                    eStatus = "PRESENTE";
                }

                if (eVisibilidade.length() == 0) {
                    eVisibilidade = "SIM";
                }


                Aluno AlunoCorrente = new Aluno(eID, eTurma, eNome, eStatus, eVisibilidade);

                if (aluno_objeto.id_is("Vivencia","SIM")) {
                    AlunoCorrente.setVivencia(aluno_objeto.identifique("Origem").getValor());
                }

                mAlunos.add(AlunoCorrente);

            }


        } else {
            SigmaCollection.BUILD_COLLECTION(Local.COLECAO_ALUNOS);
        }

        //  System.out.println("Carregar :: Alunos " + mAlunos.size());

        return mAlunos;
    }

    public static ArrayList<Aluno> getAlunos(String eTurma) {

        ArrayList<Aluno> selAlunos = new ArrayList<Aluno>();

        for (Aluno eAluno : carregarAlunos()) {
            if (eAluno.getTurma().contentEquals(eTurma)) {
                selAlunos.add(eAluno);
            }
        }

        return selAlunos;

    }

    public static ArrayList<Aluno> getAlunosVisiveis() {
        return filtrarAlunosVisiveis(Escola.carregarAlunos());
    }

    public static ArrayList<AlunoComNota> carregarAlunosComNota() {

        ArrayList<AlunoComNota> mAlunos = new ArrayList<AlunoComNota>();

        Local.organizarPastas();


        //System.out.println("Carregar :: Alunos");

        if (SigmaCollection.EXISTS_COLLECTION(Local.COLECAO_ALUNOS)) {

            //  System.out.println("Carregar :: Alunos Existe " + eDocumento.getObjetos().size());
            //   System.out.println("Carregar :: Alunos Existe " + eAlunos.getObjetos().size());

            for (DKGObjeto aluno_objeto : SigmaCollection.REQUIRED_COLLECTION(Local.COLECAO_ALUNOS, "Alunos")) {

                String eID = aluno_objeto.identifique("ID").getValor();

                String eTurma = aluno_objeto.identifique("Turma").getValor();
                String eNome = aluno_objeto.identifique("Nome").getValor();
                String eVisibilidade = aluno_objeto.identifique("Visibilidade").getValor();

                if (eVisibilidade.length() == 0) {
                    eVisibilidade = "SIM";
                }

                mAlunos.add(new AlunoComNota(eID, eTurma, eNome, eVisibilidade));

            }


        } else {

            SigmaCollection.BUILD_COLLECTION(Local.COLECAO_ALUNOS, "Alunos");
            // System.out.println("---->> SALVANDO :: " + FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAlunos));

        }

        // System.out.println("Carregar :: Alunos " + mAlunos.size());

        return mAlunos;
    }

    public static ArrayList<AlunoComNota> carregarAlunosComNota(String qualTurma) {

        ArrayList<AlunoComNota> mAlunos = new ArrayList<AlunoComNota>();

        Local.organizarPastas();


        System.out.println("Carregar :: Alunos");

        if (SigmaCollection.EXISTS_COLLECTION(Local.COLECAO_ALUNOS)) {

            for (DKGObjeto aluno_objeto : SigmaCollection.REQUIRED_COLLECTION(Local.COLECAO_ALUNOS, "Alunos")) {

                String eID = aluno_objeto.identifique("ID").getValor();
                String eTurma = aluno_objeto.identifique("Turma").getValor();
                String eNome = aluno_objeto.identifique("Nome").getValor();
                String eVisibilidade = aluno_objeto.identifique("Visibilidade").getValor();


                if (eVisibilidade.length() == 0) {
                    eVisibilidade = "SIM";
                }

                if (qualTurma.contentEquals(eTurma)) {
                    mAlunos.add(new AlunoComNota(eID, eTurma, eNome, eVisibilidade));
                }

            }


        } else {

            SigmaCollection.BUILD_COLLECTION(Local.COLECAO_ALUNOS, "Alunos");

            //System.out.println("---->> SALVANDO :: " + FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAlunos));

        }

        System.out.println("Carregar :: Alunos " + mAlunos.size());

        return mAlunos;
    }

    public static ArrayList<AlunoComNota> carregarAlunosComNotaDaEscola() {

        ArrayList<AlunoComNota> mAlunos = new ArrayList<AlunoComNota>();

        Local.organizarPastas();

        System.out.println("Carregar :: Alunos");

        if (SigmaCollection.EXISTS_COLLECTION(Local.COLECAO_ALUNOS)) {


            for (DKGObjeto aluno_objeto : SigmaCollection.REQUIRED_COLLECTION(Local.COLECAO_ALUNOS, "Alunos")) {

                String eID = aluno_objeto.identifique("ID").getValor();
                String eTurma = aluno_objeto.identifique("Turma").getValor();
                String eNome = aluno_objeto.identifique("Nome").getValor();
                String eVisibilidade = aluno_objeto.identifique("Visibilidade").getValor();


                if (eVisibilidade.length() == 0) {
                    eVisibilidade = "SIM";
                }

                mAlunos.add(new AlunoComNota(eID, eTurma, eNome, eVisibilidade));


            }


        } else {

            SigmaCollection.BUILD_COLLECTION(Local.COLECAO_ALUNOS, "Alunos");

          //  System.out.println("---->> SALVANDO :: " + FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAlunos));


        }

        System.out.println("Carregar :: Alunos " + mAlunos.size());

        return mAlunos;
    }

    public static ArrayList<Aluno> getAlunosVisiveisEOrdenadosDaEscola() {

        ArrayList<Aluno> alunos_continuos = new ArrayList<Aluno>();

        for (Aluno eAluno : getAlunosVisiveis()) {
            alunos_continuos.add(new Aluno(eAluno.getID(), eAluno.getTurma(), eAluno.getNome(), "", eAluno.getVisibilidade()));

        }

        alunos_continuos = OrdenarAlunos.ordendar(alunos_continuos);

        return alunos_continuos;

    }

    public static ArrayList<Aluno> getAlunosVisiveisEOrdenadosDasTurmas(ArrayList<String> turmas_realizadas) {

        ArrayList<Aluno> ret = new ArrayList<Aluno>();

        ArrayList<Aluno> alunos_visiveis = getAlunosVisiveisEOrdenadosDaEscola();

        for (Aluno aluno : alunos_visiveis) {
            if (turmas_realizadas.contains(aluno.getTurma())) {
                ret.add(aluno);
            }
        }

        return ret;

    }


    public static ArrayList<AlunoContinuo> getAlunosContinuosVisiveisEOrdenadosDaEscola() {

        ArrayList<AlunoContinuo> alunos_continuos = new ArrayList<AlunoContinuo>();

        for (Aluno eAluno : Escola.filtrarAlunosVisiveis(Escola.carregarAlunos())) {
            alunos_continuos.add(new AlunoContinuo(Integer.parseInt(eAluno.getID()), eAluno.getNome(), eAluno.getTurma(), eAluno.getVisibilidade()));

        }

        alunos_continuos = OrdenarAlunos.ordendarContinuos(alunos_continuos);

        return alunos_continuos;

    }

    public static ArrayList<AlunoContinuo> getAlunosContinuosVisiveisEOrdenadosDaTurma(String mTurma) {

        ArrayList<AlunoContinuo> alunos_continuos = new ArrayList<AlunoContinuo>();

        for (Aluno eAluno : Escola.filtrarAlunosVisiveis(Escola.carregarAlunos())) {
            if (eAluno.getTurma().contentEquals(mTurma)) {
                alunos_continuos.add(new AlunoContinuo(Integer.parseInt(eAluno.getID()), eAluno.getNome(), eAluno.getTurma(), eAluno.getVisibilidade()));
            }
        }

        alunos_continuos = OrdenarAlunos.ordendarContinuos(alunos_continuos);

        return alunos_continuos;

    }


    public static ArrayList<Aluno> filtrarAlunosVisiveis(ArrayList<Aluno> todos) {

        ArrayList<Aluno> visiveis = new ArrayList<Aluno>();

        for (Aluno eAluno : todos) {
            if (eAluno.getVisibilidade().contentEquals("SIM")) {
                visiveis.add(eAluno);
            }
        }

        return visiveis;
    }

    public static ArrayList<AlunoComNota> filtarVisiveis(ArrayList<AlunoComNota> todos) {

        ArrayList<AlunoComNota> visiveis = new ArrayList<AlunoComNota>();

        for (AlunoComNota eAluno : todos) {
            if (eAluno.getVisibilidade().contentEquals("SIM")) {
                visiveis.add(eAluno);
            }
        }

        return visiveis;
    }


    public static String getTurmaDe(String aluno_id) {
        String ret = "";
        for (Aluno aluno : getAlunosVisiveisEOrdenadosDaEscola()) {
            if (aluno.getID().contentEquals(aluno_id)) {
                ret = aluno.getTurma();
                break;
            }
        }
        return ret;
    }

    public static ArrayList<Aluno> filtrar_visiveis_da_turma(String eTurma) {

        ArrayList<Aluno> selAlunos = new ArrayList<Aluno>();

        for (Aluno eAluno : Escola.carregarAlunos()) {
            if (eAluno.getTurma().contentEquals(eTurma)) {
                if (eAluno.getVisibilidade().contentEquals("SIM")) {
                    selAlunos.add(eAluno);
                }
            }
        }

        return selAlunos;

    }

    public static ArrayList<Aluno> getAlunosEmVivencia() {

        ArrayList<Aluno> selAlunos = new ArrayList<Aluno>();

        for (Aluno eAluno : Escola.carregarAlunos()) {
            if (eAluno.getVisibilidade().contentEquals("SIM")) {
                if (eAluno.getVivencia().contentEquals("SIM")) {
                    selAlunos.add(eAluno);
                }
            }
        }

        return selAlunos;

    }

    public static ArrayList<AlunoComNota> getAlunosComNotasVisiveisEOrdenadosDaTurma(String mTurma) {
        return OrdenarAlunos.ordendarComNotas(filtarVisiveis(carregarAlunosComNota(mTurma)));
    }

}
