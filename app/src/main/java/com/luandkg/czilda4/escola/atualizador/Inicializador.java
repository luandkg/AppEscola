package com.luandkg.czilda4.escola.atualizador;

import com.luandkg.czilda4.FakerSchool;
import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.utils.FS;

import java.util.ArrayList;

public class Inicializador {

    public static void zerar(ArrayList<String> turmas) {

        Local.organizarPastas();

        DKG eDKG = new DKG();
        DKGObjeto eTurmas = eDKG.unicoObjeto("Turmas");

        for (String turma : turmas) {
            eTurmas.criarObjeto("Turma").identifique("Nome").setValor(turma);
        }


        eDKG.salvar(FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoTurmas));


    }

    public static void init(ArrayList<String> turmas) {

        FS.dirCriar(Local.LOCAL);
        FS.dirCriar(Local.LOCAL_CACHE);

        if (!FS.arquivoExiste(Local.LOCAL, Local.ArquivoTurmas)) {

            DKG eDKG = new DKG();
            DKGObjeto eTurmas = eDKG.unicoObjeto("Turmas");

            for (String turma : turmas) {
                eTurmas.criarObjeto("Turma").identifique("Nome").setValor(turma);
            }



            eDKG.salvar(FS.getArquivoLocal(Local.LOCAL + "/" + Local.ArquivoTurmas));

        }


    }


}
