package com.luandkg.czilda4.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class EstanteComplexa extends BaseAdapter {

    private Context context;
    private static LayoutInflater inflater = null;
    private EstantizadorComplexo mEstantizador;
    private int mQuantidade;

    public EstanteComplexa(Context eContexto, int eQuantidade, EstantizadorComplexo eEstantizador) {
        context = eContexto;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEstantizador = eEstantizador;
        mQuantidade = eQuantidade;
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


        if (convertView == null) {
            convertView = mEstantizador.onIniciar(inflater, parent, position);
        }


        return convertView;
    }


}
