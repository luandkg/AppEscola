package com.luandkg.czilda4.libs.dkg;

import com.luandkg.czilda4.utils.Opcional;

import java.util.ArrayList;

public class DKGObjeto {

    private String mNome;
    private ArrayList<DKGAtributo> mDKGAtributos;
    private ArrayList<DKGObjeto> mDKGObjetos;

    public DKGObjeto(String eNome) {
        mNome = eNome;

        mDKGAtributos = new ArrayList<DKGAtributo>();
        mDKGObjetos = new ArrayList<DKGObjeto>();

    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public String getNome() {
        return mNome;
    }

    public int contagem() {

        int t = 1;


        for (DKGAtributo ePacote : mDKGAtributos) {
            t += 2;
        }


        for (DKGObjeto mDKGObjeto : mDKGObjetos) {
            t += mDKGObjeto.contagem();
        }


        return t;

    }

    // ATRIBUTOS

    public DKGAtributo identifique(String eNome) {

        boolean enc = false;
        DKGAtributo ret = null;

        for (DKGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                break;
            }

        }

        if (!enc) {
            ret = new DKGAtributo(eNome, "");
            mDKGAtributos.add(ret);
        }

        return ret;

    }

    public DKGAtributo identifique(String eNome, short eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, int eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, float eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, double eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, boolean eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, String eValor) {

        boolean enc = false;
        DKGAtributo ret = null;

        for (DKGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                ret.setValor(eValor);
                break;
            }

        }

        if (!enc) {
            ret = new DKGAtributo(eNome, eValor);
            mDKGAtributos.add(ret);
        }

        return ret;
    }

    public ArrayList<DKGAtributo> getAtributos() {
        return mDKGAtributos;
    }


    // OBJETO

    public ArrayList<DKGObjeto> getObjetos() {
        return mDKGObjetos;
    }

    public DKGObjeto criarObjeto(String eNome) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.add(ret);

        return ret;
    }

    public DKGObjeto unicoObjeto(String eNome) {

        boolean enc = false;
        DKGObjeto ret = null;

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mDKGObjeto;
                break;
            }

        }

        if (enc == false) {
            ret = new DKGObjeto(eNome);
            mDKGObjetos.add(ret);
        }

        return ret;
    }

    public void removerObjeto(DKGObjeto eDKGObjeto) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto == eDKGObjeto) {
                mDKGObjetos.remove(eDKGObjeto);
                break;
            }

        }

    }

    public void removerObjetoPorNome(String eNome) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                mDKGObjetos.remove(mDKGObjeto);
                break;
            }

        }

    }

    public String toString() {

        ParserDKG escritor = new ParserDKG();

        escritor.montar("", mDKGObjetos);

        return escritor.getTexto();
    }


    // FEATURE 2022.07

    public Opcional<DKGObjeto> procurar(String atributo_nome, String atributo_valor) {

        for (DKGObjeto objeto : mDKGObjetos) {
            if (objeto.identifique(atributo_nome).isValor(atributo_valor)) {
                return Opcional.OK(objeto);
            }
        }

        return Opcional.CANCEL();
    }


    // FEATURE 2022.07

    public String id_string(String eNome) {
        return identifique(eNome).getValor();
    }

    public int id_inteiro(String eNome) {
        return identifique(eNome).getInteiro();
    }

    public boolean id_bool(String eNome) {
        return identifique(eNome).getBool();
    }

    public double id_double(String eNome) {
        return identifique(eNome).getDouble();
    }

    public float id_float(String eNome) {
        return identifique(eNome).getFloat();
    }

    // FEATURE 2022.07

    public boolean id_is(String eNome, String eValor) {
        return identifique(eNome).isValor(eValor);
    }

    public boolean id_is(String eNome, int eValor) {
        return identifique(eNome).isInteiro(eValor);
    }

    public boolean id_is(String eNome, boolean eValor) {
        return identifique(eNome).isBool(eValor);
    }

    public boolean id_is(String eNome, double eValor) {
        return identifique(eNome).isDouble(eValor);
    }

    public boolean id_is(String eNome, float eValor) {
        return identifique(eNome).isFloat(eValor);
    }
}
