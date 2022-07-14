package com.luandkg.czilda4.utils;

public class Armazem {

    private  String mRaiz = "";

    public   Armazem(String raiz_nome) {
        mRaiz = raiz_nome;
    }

    public  String LOCAL() {
        return mRaiz;
    }


    public  String ARQUIVO(String eArquivo) {
        return LOCAL() + "/" + eArquivo;
    }


    // EM PASTAS

    public  String PASTA(String ePasta) {
        return LOCAL() + "/" + ePasta;
    }

    public  String PASTA_ARQUIVO(String ePasta, String eArquivo) {
        return PASTA(ePasta) + "/" + eArquivo;
    }


    // SISTEMA DE CACHE
    public  String CACHE_LOCAL() {
        return mRaiz + "/Cache";
    }

    public  String CACHE_ARQUIVO(String eArquivo) {
        return CACHE_LOCAL() + "/" + eArquivo;
    }
}
