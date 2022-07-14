package com.luandkg.czilda4.escola.avaliacao_quantitativa;

import java.util.ArrayList;

public class QuadroDeNotas {


    private String mTurma;

    private ArrayList<Integer> mNotas;


    public void adicionar(Integer nota) {
        mNotas.add(nota);
    }

    public ArrayList<Integer> getNotas() {
        return mNotas;
    }

    public QuadroDeNotas(String eTurma) {
        mTurma = eTurma;
        mNotas = new ArrayList<Integer>();
    }

    public String getTurma() {
        return mTurma;
    }

    public int contarRealizaram() {

        int f = 0;

        for (int v : getNotas()) {
            if (v > 0) {
                f += 1;
            }
        }

        return f;

    }

    public int contarPorcentagemFizeram() {

        int fizeram = contarRealizaram();
        int todos = getNotas().size();

        double fracao = (double) fizeram / (double) todos;
        return (int) (fracao * 100.0F);

    }
}
