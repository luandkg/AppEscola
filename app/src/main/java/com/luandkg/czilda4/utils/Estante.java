package com.luandkg.czilda4.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Estante extends BaseAdapter {

    private Context context;
    private static LayoutInflater inflater = null;
    private Estantizador mEstantizador;
    private int mQuantidade;

    public Estante(Context eContexto, int eQuantidade, Estantizador eEstantizador) {
        context = eContexto;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEstantizador = eEstantizador;
        mQuantidade=eQuantidade;
    }


    @Override
    public int getCount() {
        return mQuantidade;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (!mEstantizador.foiCarregado()) {
            mEstantizador.onIniciar(inflater, parent);
        }

        convertView = mEstantizador.get(position);


        return convertView;
    }


}
