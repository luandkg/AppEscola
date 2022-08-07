package com.luandkg.czilda4.escola.avisos;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;

import java.io.File;
import java.util.ArrayList;

public class Avisos {

    public static ArrayList<Aviso> listar() {

        Local.organizarPastas();

        ArrayList<Aviso> mAvisos = new ArrayList<Aviso>();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");

            if (!ePacote.identifique("EstaArquivado").isValor("SIM")) {
                String mensagem = ePacote.id_string("Mensagem");
                mAvisos.add(new Aviso(eID, mensagem));
            }


        }


        return ordenar(mAvisos);
    }

    public static ArrayList<Aviso> listar_arquivados() {

        Local.organizarPastas();

        ArrayList<Aviso> mAvisos = new ArrayList<Aviso>();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");

            if (ePacote.identifique("EstaArquivado").isValor("SIM")) {
                String mensagem = ePacote.id_string("Mensagem");
                mAvisos.add(new Aviso(eID, mensagem));
            }


        }


        return ordenar(mAvisos);
    }

    public static Aviso getAviso(String mID) {

        Local.organizarPastas();

        Aviso ret = null;

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(mID)) {
                String mensagem = ePacote.id_string("Mensagem");
                ret = new Aviso(eID, mensagem);
                break;
            }

        }


        return ret;
    }

    public static void arquivar(String mID) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(mID)) {
                ePacote.identifique("EstaArquivado").setValor("SIM");
                break;
            }

        }

        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_AVISOS, eDocumento);
    }

    public static void desarquivar(String mID) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(mID)) {
                ePacote.identifique("EstaArquivado").setValor("NAO");
                break;
            }

        }

        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_AVISOS, eDocumento);
    }


    public static void alterar(String mID, String eMensagem) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(mID)) {
                ePacote.identifique("Mensagem").setValor(eMensagem);
                break;
            }

        }

        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_AVISOS, eDocumento);
    }


    public static void criar(String eMensagem) {


        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        DKGObjeto avisos = eDocumento.unicoObjeto("Avisos");


        DKGObjeto aviso = avisos.criarObjeto("Aviso");
        aviso.identifique("ID").setInteiro(SigmaCollection.INDEX(avisos, "ID"));
        aviso.identifique("Mensagem").setValor(eMensagem);


        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_AVISOS, eDocumento);

    }

    public static void remover(String removerID) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_AVISOS);

        ArrayList<DKGObjeto> objetos = eDocumento.unicoObjeto("Avisos").getObjetos();

        for (DKGObjeto ePacote : objetos) {
            if (ePacote.identifique("ID").isValor(removerID)) {
                objetos.remove(ePacote);
                break;
            }
        }


        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_AVISOS, eDocumento);
    }

    // UTILITARIO

    public static ArrayList<Aviso> ordenar(ArrayList<Aviso> entrada) {

        int todos = entrada.size();
        Aviso aux = null;

        for (int i = 0; i < todos; i++) {
            for (int j = 0; j < (todos - 1); j++) {
                if (entrada.get(j).getIDNumerico() < entrada.get(j + 1).getIDNumerico()) {
                    aux = entrada.get(j);
                    entrada.set(j, entrada.get(j + 1));
                    entrada.set(j + 1, aux);
                }
            }
        }

        return entrada;

    }


}
