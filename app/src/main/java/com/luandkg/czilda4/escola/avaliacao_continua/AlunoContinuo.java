package com.luandkg.czilda4.escola.avaliacao_continua;


import com.luandkg.czilda4.escola.chamadas.DataStatus;
import com.luandkg.czilda4.escola.avaliacao_formativa.Formativa;
import com.luandkg.czilda4.escola.avaliacao_formativa.ItemFormativo;

import java.util.ArrayList;

public class AlunoContinuo {


    private int mID;
    private String mSID = "";

    private String mNome;
    private String mTurma;
    private String mVisibilidade;
    private ArrayList<ItemFormativo> mMomentos;
    private ArrayList<Continuidade> mContinuidades;

    private double mAcumuladoContinuidadeSemRecuperacao;
    private double mAcumuladoContinuidadeComRecuperacao;


    private int mPresente;
    private int mAusente;
    private int mPresencaMinima;
    private ArrayList<DataStatus> mDatas;
    private int mAtividadesEntregues;

    private ArrayList<SemanaContinuaValores> mSemanas;

    private int mParticipacao;
    private int mCompromisso;
    private int mDedicacao;
    private int mFrequencia;
    private int mQualificacao;

    private String mUltimaAtividadeRealizada;

    public AlunoContinuo(int eID, String eNome, String eTurma, String eVisibilidade) {
        mID = eID;
        mSID = "";
        mNome = eNome;
        mTurma = eTurma;
        mVisibilidade = eVisibilidade;
        mMomentos = new ArrayList<ItemFormativo>();

        mPresente = 0;
        mAusente = 0;
        mPresencaMinima = 0;
        mDatas = new ArrayList<DataStatus>();


        mContinuidades = new ArrayList<Continuidade>();

        mAcumuladoContinuidadeSemRecuperacao = 0;
        mAcumuladoContinuidadeComRecuperacao = 0;
        mAtividadesEntregues = 0;

        mSemanas = new ArrayList<SemanaContinuaValores>();

        mParticipacao = 0;
        mCompromisso = 0;
        mDedicacao = 0;
        mFrequencia = 0;
        mQualificacao = 0;

        mUltimaAtividadeRealizada="";
    }


    public void limpar() {

        mMomentos.clear();

        mPresente = 0;
        mAusente = 0;
        mPresencaMinima = 0;
        mDatas.clear();


        mContinuidades = new ArrayList<Continuidade>();

        mAcumuladoContinuidadeSemRecuperacao = 0;
        mAcumuladoContinuidadeComRecuperacao = 0;
        mAtividadesEntregues = 0;

        mUltimaAtividadeRealizada="";
    }


    public int getID() {
        return mID;
    }

    public String getNome() {
        return mNome;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getVisibilidade() {
        return mVisibilidade;
    }

    public double getAcumuladoContinuidadeSemRecuperacao() {
        return mAcumuladoContinuidadeSemRecuperacao;
    }

    public void setAcumuladoContinuidadeSemRecuperacao(double eValor) {
        mAcumuladoContinuidadeSemRecuperacao = eValor;
    }

    public double getAcumuladoContinuidadeComRecuperacao() {
        return mAcumuladoContinuidadeComRecuperacao;
    }

    public int getNivel() {
        return (int) getAcumuladoContinuidadeComRecuperacao();
    }

    public void setAcumuladoContinuidadeComRecuperacao(double eValor) {
        mAcumuladoContinuidadeComRecuperacao = eValor;
    }


    public boolean isTransferido() {
        if (mVisibilidade.contentEquals("NAO")) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isMatriculado() {
        if (mVisibilidade.contentEquals("SIM")) {
            return true;
        } else {
            return false;
        }
    }


    public void marcar(String data, String status) {
        mDatas.add(new DataStatus(data, status));
    }

    public boolean isPresente(String qual_data) {
        boolean ret = false;

        boolean enc = false;

        for (DataStatus dt : mDatas) {
            if (dt.getData().contentEquals(qual_data)) {
                if (dt.getStatus().contentEquals("PRESENTE")) {
                    ret = true;
                }
                enc = true;
                break;
            }
        }

        if (!enc) {
            ret = true;
        }

        return ret;
    }

    public ArrayList<DataStatus> getDatas() {
        return mDatas;
    }

    public int getQuantidadeDeMomentos() {
        return mMomentos.size();
    }

    public ItemFormativo getMomento(int m) {
        return mMomentos.get(m);
    }

    public void criarMomento(String v, String data) {
        mMomentos.add(new ItemFormativo(v, data));
    }


    public ArrayList<ItemFormativo> getMomentos() {
        return mMomentos;
    }


    public void setPresencaMinima(int ePresencaMinima) {
        mPresencaMinima = ePresencaMinima;
    }

    public String getPreNota() {

        String n1 = getMomento(0).getValor();
        String n2 = getMomento(1).getValor();
        String n3 = getMomento(2).getValor();
        String n4 = getMomento(3).getValor();
        String n5 = getMomento(4).getValor();

        String recuperacao = getMomento(5).getValor();

        boolean isPresente = false;

        if (mPresencaMinima > 0) {
            if (mPresente >= mPresencaMinima) {
                isPresente = true;
            }

        }

        return String.valueOf(Formativa.continua(n1, n2, n3, n4, n5, "0", isPresente));
    }

    public String getNota() {

        String n1 = getMomento(0).getValor();
        String n2 = getMomento(1).getValor();
        String n3 = getMomento(2).getValor();
        String n4 = getMomento(3).getValor();
        String n5 = getMomento(4).getValor();

        String recuperacao = getMomento(5).getValor();

        boolean isPresente = false;

        if (mPresencaMinima > 0) {
            if (mPresente >= mPresencaMinima) {
                isPresente = true;
            }

        }

        return String.valueOf(Formativa.continua(n1, n2, n3, n4, n5, recuperacao, isPresente));
    }


    // FREQUENCIA

    public void marcarPresenca() {
        mPresente += 1;
    }

    public void marcarFalta() {
        mAusente += 1;
    }

    public int getPresente() {
        return mPresente;
    }

    public int getAusente() {
        return mAusente;
    }


    // METODO FORMATIVO E CONTINUO

    public void continuidade_construir(int quantidade) {
        for (int n = 0; n < quantidade; n++) {
            mContinuidades.add(new Continuidade("", 0.0));
        }
    }

    public Continuidade getContinudade(int p) {
        return mContinuidades.get(p);
    }

    public ArrayList<Continuidade> getContinudades() {
        return mContinuidades;
    }

    public int getAtividadesEntregues() {
        return mAtividadesEntregues;
    }

    public void setAtividadesEntregues(int v) {
        mAtividadesEntregues = v;
    }


    // FEATURES

    public void setSID(String eID) {
        mSID = eID;
    }

    public String getSID() {
        return mSID;
    }

    public void setNotas(double sem, double com) {
        mAcumuladoContinuidadeSemRecuperacao = sem;
        mAcumuladoContinuidadeComRecuperacao = com;
    }


    public void adicionarSemana(SemanaContinuaValores eSemanaContinuaValores) {
        mSemanas.add(eSemanaContinuaValores);
    }

    public double getNotaSemRecuperacao() {
        return mAcumuladoContinuidadeSemRecuperacao;
    }


    public double getNotaComRecuperacao() {
        return mAcumuladoContinuidadeComRecuperacao;
    }


    public ArrayList<SemanaContinuaValores> getSemanas() {
        return mSemanas;
    }

    public void setMetricas(int eParticipacao, int eCompromisso, int eDedicacao, int eFrequencia, int eQualificacao) {
        mParticipacao = eParticipacao;
        mCompromisso = eCompromisso;
        mDedicacao = eDedicacao;
        mFrequencia = eFrequencia;
        mQualificacao = eQualificacao;
    }

    public int getParticipacao() {
        return mParticipacao;
    }

    public int getCompromisso() {
        return mCompromisso;
    }

    public int getDedicacao() {
        return mDedicacao;
    }

    public int getFrequencia() {
        return mFrequencia;
    }

    public int getQualificacao() {
        return mQualificacao;
    }


    public void setUltimaAtividadeRealizada(String u){
        mUltimaAtividadeRealizada=u;
    }

    public String getUltimaAtividadeRealizada(){return mUltimaAtividadeRealizada;}
}
