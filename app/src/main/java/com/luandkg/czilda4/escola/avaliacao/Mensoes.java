package com.luandkg.czilda4.escola.avaliacao;

public class Mensoes {

    private int mOmega;
    private int mZeta;
    private int mDelta;
    private int mAlfa;

    public Mensoes(int eOmega,int eZeta,int eDelta,int eAlfa){
        mOmega=eOmega;
        mZeta=eZeta;
        mDelta=eDelta;
        mAlfa=eAlfa;
    }

    public int omega(){return mOmega;}
    public int zeta(){return mZeta;}
    public int delta(){return mDelta;}
    public int alfa(){return mAlfa;}

}
