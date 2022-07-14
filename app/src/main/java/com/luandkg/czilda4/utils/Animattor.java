package com.luandkg.czilda4.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Animattor {

    private ImageView mImaginador;

    private final Handler myHandler = new Handler();
    private Timer temporizador;
    private Context mContexto;

    private ArrayList<Bitmap> mImagens;
    private int index;
    private int quantidade;

    private boolean mComecou;
    private int mDeveEsperar;
    private long mIniciado;

    public Animattor(Context eContext, ImageView eImaginador) {
        mContexto = eContext;
        mImaginador = eImaginador;

        mImagens = new ArrayList<Bitmap>();
        index = 0;
        quantidade = 0;
        mComecou = false;
        mDeveEsperar = 0;
        mIniciado = 0;
    }

    public void adicionar(Bitmap eImagem) {
        mImagens.add(eImagem);
    }


    public void iniciar() {

        index = 0;
        quantidade = mImagens.size() - 1;

        temporizador = new Timer();
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        }, 0, 200);

    }

    public void esperarParaComecar(int eMilissegundos) {
        mComecou = false;
        mDeveEsperar = eMilissegundos;
    }

    public Runnable myRunnable = new Runnable() {
        public void run() {

            if (mComecou) {

                index += 1;
                if (index >= quantidade) {
                    index = 0;
                }

                mImaginador.setImageBitmap(mImagens.get(index));

            } else {
                if (mIniciado == 0) {
                    mIniciado = System.currentTimeMillis() + mDeveEsperar;
                } else {
                    long agora = System.currentTimeMillis();
                    if (agora >= mIniciado) {
                        mComecou = true;
                    }
                  //  System.out.println(" -->> " + mIniciado + " :: " + agora);
                }
            }

        }
    };


}
