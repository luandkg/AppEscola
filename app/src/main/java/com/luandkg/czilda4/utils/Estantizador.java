package com.luandkg.czilda4.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Estantizador {

    private View[] mViews;
    private boolean mCarregar = false;

    public void onIniciar(LayoutInflater inflater,ViewGroup parent) {

    }

    public void construir(int tamanho){
        mViews = new View[tamanho];
    }

    public void onZerar() {
        mCarregar = false;
    }

    public void onPronto() {
        mCarregar = true;
    }

    public boolean foiCarregado(){return mCarregar;}

    public View get(int ePosicao){
        return mViews[ePosicao];
    }

    public void set(int ePosicao,View eView){
         mViews[ePosicao]=eView;
    }

    public Estante toEstante(Context eContexto, int eQuantidade){
      return  new Estante(eContexto, eQuantidade, this);
    }
}
