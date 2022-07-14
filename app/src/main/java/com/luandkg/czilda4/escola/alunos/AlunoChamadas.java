package com.luandkg.czilda4.escola.alunos;

import com.luandkg.czilda4.escola.chamadas.DataChamada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AlunoChamadas {

    private String mID;
    private String mNome;
    private String mTurma;
    private int mPresente;
    private int mFalta;

    private ArrayList<DataChamada> mDatas;

    public AlunoChamadas(String eID, String eTurma, String eNome) {
        mID = eID;
        mNome = eNome;
        mTurma = eTurma;

        mDatas = new ArrayList<DataChamada>();

        mPresente = 0;
        mFalta = 0;

    }

    public String getID() {
        return mID;
    }

    public String getNome() {
        return mNome;
    }

    public String getTurma() {
        return mTurma;
    }

    public int getPresente() {
        return mPresente;
    }

    public int getFalta() {
        return mFalta;
    }

    public void marcar(String data, String valor) {
        if (valor.contentEquals("PRESENTE")) {
            mDatas.add(new DataChamada(data, true));
            mPresente += 1;
        } else {
            mDatas.add(new DataChamada(data, false));
            mFalta += 1;
        }
    }


    public ArrayList<DataChamada> getDatas() {
        return mDatas;
    }


    public static ArrayList<AlunoChamadas> ordenar( ArrayList<AlunoChamadas>  lista){

        Collections.sort(lista, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return ((AlunoChamadas) objeto_um).getNome().compareTo(((AlunoChamadas) objeto_dois).getNome());
            }
        });

        return lista;

    }
}
