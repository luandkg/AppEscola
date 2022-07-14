package com.luandkg.czilda4.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EstantizadorComplexo {


    public View onIniciar(LayoutInflater inflater, ViewGroup parent, int posicao) {
        return null;
    }


    public EstanteComplexa toEstante(Context eContexto, int eQuantidade) {
        return new EstanteComplexa(eContexto, eQuantidade, this);
    }
}
