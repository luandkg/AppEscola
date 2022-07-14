package com.luandkg.czilda4.escola.organizacao;

public class TurmaComHorario {

    private String mTurma;
    private String mHorario;

    public TurmaComHorario(String eTurma,String eHorario){
        mTurma=eTurma;
        mHorario=eHorario;
    }

    public String getTurma(){return mTurma;}
    public String getHorario(){return mHorario;}
    public String getHorarioSemSegundos(){

        String ret = mHorario;
        if (ret.length()==8){
            ret=ret.substring(0,5);
        }

        return ret;}

}
