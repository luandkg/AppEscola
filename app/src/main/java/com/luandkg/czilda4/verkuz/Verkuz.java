package com.luandkg.czilda4.verkuz;

import java.util.ArrayList;

public class Verkuz {

    private ArrayList<Commit> mBuilds;
private String mAutor;

    public Verkuz() {
        mAutor="";
        mBuilds = new ArrayList<Commit>();
    }
    public void setAutor(String eAutor){
        mAutor=eAutor;
    }

    public String getAutor(){return mAutor;}

    public void DEV(String eData, String eCommit) {
        mBuilds.add(new Commit(eData, eCommit));
    }


    public String getVersao() {

        int maior = 0;
        int menor = 0;

        int quantidade = getBuilds();

        while (quantidade > 10) {
            quantidade -= 10;
            menor += 1;
        }

        while (menor > 10) {
            menor -= 10;
            maior += 1;
        }

        return maior + "." + menor + " #" + quantidade;
    }

    public String getVersaoCompleta() {

        int maior = 0;
        int menor = 0;

        int quantidade = getBuilds();

        while (quantidade > 10) {
            quantidade -= 10;
            menor += 1;
        }

        while (menor > 10) {
            menor -= 10;
            maior += 1;
        }

        if (quantidade == 0) {
            return maior + "." + menor;
        } else {
            return maior + "." + menor + " #PATCH " + quantidade;
        }
    }

    public int getBuilds() {
        return mBuilds.size();
    }


    public String getUltima() {
        String ret = "";

        if (mBuilds.size() > 0) {
            ret = mBuilds.get(0).getData();
        }

        return ret;
    }

    public String getIniciado() {
        String ret = "";

        if (mBuilds.size() > 0) {
            ret = mBuilds.get(mBuilds.size() - 1).getData();
        }

        return ret;
    }


}
