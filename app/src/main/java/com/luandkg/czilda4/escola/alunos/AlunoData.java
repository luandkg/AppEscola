package com.luandkg.czilda4.escola.alunos;


public class AlunoData {

    private String mNome;
    private boolean mStatus;

    public AlunoData( String eNome,boolean eStatus ){
        mNome=eNome;
        mStatus=eStatus;
    }

    public String getNome(){
        return mNome;
    }


    public boolean getStatus(){
        return mStatus;
    }
}
