package com.luandkg.czilda4.escola.avisos;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.utils.FS;

import java.io.File;
import java.util.ArrayList;

public class Avisos {

    public static ArrayList<Aviso> getAvisosOrdenados(){
       return ordenar(Avisos.getAvisos());
    }

    public static ArrayList<Aviso> getAvisos() {

        ArrayList<Aviso> mAvisos = new ArrayList<Aviso>();

        FS.dirCriar(Local.LOCAL);
        FS.dirCriar(Local.LOCAL_REALIZAR_CHAMADA);

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAvisos);

        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();


        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);


            DKGObjeto avisos = eDocumento.unicoObjeto("Avisos");


            for (DKGObjeto ePacote : avisos.getObjetos()) {

                String eID = ePacote.identifique("ID").getValor();
                String mensagem = ePacote.identifique("Mensagem").getValor();

                mAvisos.add(new Aviso(eID, mensagem));

            }


        }


        return mAvisos;
    }

    public static void aviso_criar(String eMensagem) {


        FS.dirCriar(Local.LOCAL);
        FS.dirCriar(Local.LOCAL_REALIZAR_CHAMADA);

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAvisos);

        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();


        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);
        }


        DKGObjeto avisos = eDocumento.unicoObjeto("Avisos");

        int idGeral = avisos.identifique("ID").getInteiro(0);
        avisos.identifique("ID").setInteiro(idGeral + 1);

        DKGObjeto aviso = avisos.criarObjeto("Aviso");
        aviso.identifique("ID").setValor(String.valueOf(idGeral));
        aviso.identifique("Mensagem").setValor(eMensagem);


        eDocumento.salvar(eArquivoLocal);

    }

    public static void aviso_remover(String removerID) {

        Local.organizarPastas();

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoAvisos);


        DKG existente = DKG.GET(eArquivoLocal);


        DKG eNovo = new DKG();
        DKGObjeto avisos_novos = eNovo.unicoObjeto("Avisos");

        avisos_novos.identifique("ID").setValor(existente.unicoObjeto("Avisos").identifique("ID").getValor());


        for (DKGObjeto ePacote : existente.unicoObjeto("Avisos").getObjetos()) {
            String eID = ePacote.identifique("ID").getValor();

            if (!eID.contentEquals(removerID)) {
                avisos_novos.getObjetos().add(ePacote);
            }

        }


        eNovo.salvar(eArquivoLocal);
    }

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
