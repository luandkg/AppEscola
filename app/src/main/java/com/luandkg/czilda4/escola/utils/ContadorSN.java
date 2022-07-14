package com.luandkg.czilda4.escola.utils;

public class ContadorSN {

    private int mSim ;
    private int mNao;

    public ContadorSN(){
        mSim=0;
        mNao=0;
    }

    public ContadorSN(int eSim,int eNao){
        mSim=eSim;
        mNao=eNao;
    }

    public int getSim(){return mSim;}
    public int getNao(){return mNao;}

    public int getTudo(){return mSim+mNao;}

    public void aumentar_sim(int e){mSim+=e;}
    public void aumentar_nao(int e){mNao+=e;}

}
