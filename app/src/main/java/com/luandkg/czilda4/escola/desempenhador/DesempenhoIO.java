package com.luandkg.czilda4.escola.desempenhador;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;

import java.util.ArrayList;

public class DesempenhoIO {

    public static void limpar(String colecao_desempenho) {


        DKG documento = new DKG();


        DKGObjeto mRaiz = documento.unicoObjeto("DESEMPENHO");
        DKGObjeto mSemRecuperacao = mRaiz.unicoObjeto("SEM_RECUPERACAO");
        DKGObjeto mComRecuperacao = mRaiz.unicoObjeto("COM_RECUPERACAO");

        SigmaCollection.WRITE_COLLECTION(colecao_desempenho,documento);

    }

    public static void guardar(String colecao_desempenho, String eData, ArrayList<AlunoContinuo> alunos_continuos) {


        DKG documento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(colecao_desempenho);


        //  System.out.println(documento.toString());


        DKGObjeto mRaiz = documento.unicoObjeto("DESEMPENHO");
        DKGObjeto mSemRecuperacao = mRaiz.unicoObjeto("SEM_RECUPERACAO");
        DKGObjeto mComRecuperacao = mRaiz.unicoObjeto("COM_RECUPERACAO");


        DKGObjeto obj_sem = null;
        boolean existe_sem = false;

        for (DKGObjeto eProc : mSemRecuperacao.getObjetos()) {
            if (eProc.identifique("Data").getValor().contentEquals(eData)) {
                obj_sem = eProc;
                existe_sem = true;
                break;
            }
        }

        if (!existe_sem) {
            obj_sem = mSemRecuperacao.criarObjeto("Desempenho");
            obj_sem.identifique("Data").setValor(eData);
            existe_sem = true;
        }

        DKGObjeto obj_com = null;
        boolean existe_com = false;

        for (DKGObjeto eProc : mComRecuperacao.getObjetos()) {
            if (eProc.identifique("Data").getValor().contentEquals(eData)) {
                obj_com = eProc;
                existe_com = true;
                break;
            }
        }

        if (!existe_com) {
            obj_com = mComRecuperacao.criarObjeto("Desempenho");
            obj_com.identifique("Data").setValor(eData);
            existe_com = true;
        }


        for (int a = 0; a <= 10; a++) {

            int sem = contar_sem_recuperacao(alunos_continuos, a);
            int com = contar_com_recuperacao(alunos_continuos, a);

            obj_sem.identifique("N" + String.valueOf(a)).setInteiro(sem);
            obj_com.identifique("N" + String.valueOf(a)).setInteiro(com);

            // System.out.println("Guardando :: " + a + " -->> " + sem);
        }


        SigmaCollection.WRITE_COLLECTION(colecao_desempenho,documento);
    }

    public static DesempenhoReferencia getDesempenho_Sem(String colecao_desempenho, String eData) {

        DKG documento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(colecao_desempenho);

        System.out.println(documento.toString());

        DKGObjeto mRaiz = documento.unicoObjeto("DESEMPENHO");
        DKGObjeto mSemRecuperacao = mRaiz.unicoObjeto("SEM_RECUPERACAO");
        DKGObjeto mComRecuperacao = mRaiz.unicoObjeto("COM_RECUPERACAO");


        DKGObjeto obj_sem = null;
        boolean existe_sem = false;

        for (DKGObjeto eProc : mSemRecuperacao.getObjetos()) {
            if (eProc.identifique("Data").getValor().contentEquals(eData)) {
                obj_sem = eProc;
                existe_sem = true;
                break;
            }
        }

        if (!existe_sem) {
            obj_sem = mSemRecuperacao.criarObjeto("Desempenho");
            obj_sem.identifique("Data").setValor(eData);
            existe_sem = true;
        }

        int n0 = obj_sem.identifique("N0").getInteiro(0);
        int n1 = obj_sem.identifique("N1").getInteiro(0);
        int n2 = obj_sem.identifique("N2").getInteiro(0);
        int n3 = obj_sem.identifique("N3").getInteiro(0);
        int n4 = obj_sem.identifique("N4").getInteiro(0);
        int n5 = obj_sem.identifique("N5").getInteiro(0);
        int n6 = obj_sem.identifique("N6").getInteiro(0);
        int n7 = obj_sem.identifique("N7").getInteiro(0);
        int n8 = obj_sem.identifique("N8").getInteiro(0);
        int n9 = obj_sem.identifique("N9").getInteiro(0);
        int n10 = obj_sem.identifique("N10").getInteiro(0);


        return new DesempenhoReferencia(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10);

    }

    public static DesempenhoReferencia getDesempenho_Com(String arquivo, String eData) {
        DKG documento = new DKG();
        documento.abrir(arquivo);

        DKGObjeto mRaiz = documento.unicoObjeto("DESEMPENHO");
        DKGObjeto mSemRecuperacao = mRaiz.unicoObjeto("SEM_RECUPERACAO");
        DKGObjeto mComRecuperacao = mRaiz.unicoObjeto("COM_RECUPERACAO");


        DKGObjeto obj_com = null;
        boolean existe_com = false;

        for (DKGObjeto eProc : mComRecuperacao.getObjetos()) {
            if (eProc.identifique("Data").getValor().contentEquals(eData)) {
                obj_com = eProc;
                existe_com = true;
                break;
            }
        }

        if (!existe_com) {
            obj_com = mComRecuperacao.criarObjeto("Desempenho");
            obj_com.identifique("Data").setValor(eData);
            existe_com = true;
        }

        int n0 = obj_com.identifique("N0").getInteiro(0);
        int n1 = obj_com.identifique("N1").getInteiro(0);
        int n2 = obj_com.identifique("N2").getInteiro(0);
        int n3 = obj_com.identifique("N3").getInteiro(0);
        int n4 = obj_com.identifique("N4").getInteiro(0);
        int n5 = obj_com.identifique("N5").getInteiro(0);
        int n6 = obj_com.identifique("N6").getInteiro(0);
        int n7 = obj_com.identifique("N7").getInteiro(0);
        int n8 = obj_com.identifique("N8").getInteiro(0);
        int n9 = obj_com.identifique("N9").getInteiro(0);
        int n10 = obj_com.identifique("N10").getInteiro(0);


        return new DesempenhoReferencia(n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10);
     //   return new DesempenhoReferencia(obj_com.identifique("N0").getInteiro(0), obj_com.identifique("N3").getInteiro(0), obj_com.identifique("N5").getInteiro(0), obj_com.identifique("N7").getInteiro(0));
    }


    public static int contar_sem_recuperacao(ArrayList<AlunoContinuo> alunos_continuos, int valor) {

        int f = 0;

        double acima = (double) valor;
        double abaixo = (double) valor + 1.0;

        for (AlunoContinuo v : alunos_continuos) {
            if (v.getNotaSemRecuperacao() >= acima && (v.getNotaSemRecuperacao() < abaixo)) {
                f += 1;
            }
        }

        return f;

    }

    public static int contar_com_recuperacao(ArrayList<AlunoContinuo> alunos_continuos, int valor) {

        int f = 0;

        double acima = (double) valor;
        double abaixo = (double) valor + 1.0;


        for (AlunoContinuo v : alunos_continuos) {
            if (v.getNotaComRecuperacao() >= acima && (v.getNotaComRecuperacao() < abaixo)) {
                f += 1;
            }
        }

        return f;

    }

}
