package com.luandkg.czilda4.utils;

public class AutoInt {

    private static int valor = 0;

    public static int getNovoID() {
        valor += 1;
        return valor;
    }
}
