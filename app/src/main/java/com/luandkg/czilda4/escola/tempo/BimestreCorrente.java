package com.luandkg.czilda4.escola.tempo;

public class BimestreCorrente {

    private static Bimestre BIMESTRE_CONTEUDO = null;

    public static void SET(Bimestre eBimestre){
        BIMESTRE_CONTEUDO=eBimestre;
    }

    public static Bimestre GET(){
        return BIMESTRE_CONTEUDO;
    }
}
