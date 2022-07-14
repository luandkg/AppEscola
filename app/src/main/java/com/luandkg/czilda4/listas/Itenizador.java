package com.luandkg.czilda4.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Itenizador {

    public abstract View onItem(LayoutInflater inflater, ViewGroup parent,int position);

}
