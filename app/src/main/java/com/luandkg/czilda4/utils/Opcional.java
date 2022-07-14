package com.luandkg.czilda4.utils;

public class Opcional<T> {

    private boolean mValido;
    private T mValor;

    public Opcional(T eValor) {

        mValor = eValor;

        if (mValor != null) {
            mValido = true;
        } else {
            mValido = false;
        }

    }

    public boolean isOK() {
        return mValido;
    }

    public T get() {
        return mValor;
    }

    public static <T> Opcional<T> OK(T eValor) {
        return new Opcional<T>(eValor);
    }

    public static <T> Opcional<T> CANCEL() {
        return new Opcional<T>(null);
    }
}
