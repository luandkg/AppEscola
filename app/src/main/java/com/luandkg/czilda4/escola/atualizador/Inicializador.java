package com.luandkg.czilda4.escola.atualizador;

import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
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

        SigmaCollection.WRITE_COLLECTION(Local.COLECAO_TURMAS, eDKG);


    }

    public static void init(ArrayList<String> turmas) {

        Local.organizarPastas();

        if (!SigmaCollection.EXISTS_COLLECTION(Local.COLECAO_TURMAS)) {

            DKG eDKG = new DKG();
            DKGObjeto eTurmas = eDKG.unicoObjeto("Turmas");

            for (String turma : turmas) {
                eTurmas.criarObjeto("Turma").identifique("Nome").setValor(turma);
            }

            SigmaCollection.WRITE_COLLECTION(Local.COLECAO_TURMAS, eDKG);


        }


    }


}
