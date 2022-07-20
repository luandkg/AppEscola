package com.luandkg.czilda4.libs.profile;

import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.libs.tempo.Calendario;

public class ProfileStamp {

    private DKGObjeto mObjeto;

    public ProfileStamp(DKGObjeto eObjeto) {
        mObjeto = eObjeto;
    }

    public void iniciar() {
        mObjeto.identifique("Iniciar", System.nanoTime());
        mObjeto.identifique("TCIniciar", Calendario.getTempoCompleto());
    }

    public void terminar() {
        mObjeto.identifique("Terminar", System.nanoTime());
        mObjeto.identifique("TCTerminar", Calendario.getTempoCompleto());
    }
}
