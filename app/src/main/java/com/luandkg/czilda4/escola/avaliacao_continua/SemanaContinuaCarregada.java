package com.luandkg.czilda4.escola.avaliacao_continua;

public class SemanaContinuaCarregada {

    private String mNome;
    private String mStatus;

    private int mTodos;
    private int mFizeram;

    private int mNumero;

    public SemanaContinuaCarregada(String eNome,int eNumero,String eStatus,int todos,int fizeram){
        mNome=eNome;
        mStatus=eStatus;
        mTodos=todos;
        mFizeram=fizeram;
        mNumero=eNumero;
    }

    public String getNome(){return mNome;}
    public String getStatus(){return mStatus;}
    public int getTodos(){return mTodos;}
    public int getFizeram(){return mFizeram;}
    public int getNumero(){return mNumero;}

}
