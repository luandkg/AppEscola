package com.luandkg.czilda4.escola.organizacao;



import com.luandkg.czilda4.escola.alunos.AlunoData;
import com.luandkg.czilda4.utils.tempo.Data;

import java.util.ArrayList;

public class TurmaData {

    private Data mData;
    private ArrayList<AlunoData> mAlunos;

    public TurmaData(Data eData) {
        mData = eData;
        mAlunos = new ArrayList<AlunoData>();
    }

    public void chamar(String eNome, boolean eStatus ) {
        mAlunos.add(new AlunoData(eNome, eStatus));
    }

    public Data getData(){return mData;}
    public ArrayList<AlunoData> getAlunos(){return mAlunos;}

}
