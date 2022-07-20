package com.luandkg.czilda4.libs.dkg;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DKG {

    private ArrayList<DKGObjeto> mDKGObjetos;

    public DKG() {

        mDKGObjetos = new ArrayList<DKGObjeto>();

    }

    public static DKG GET(String eArquivoLocal) {
        DKG existente = new DKG();

        File eArquivo = new File(eArquivoLocal);

        if (eArquivo.exists()) {
            existente.abrir(eArquivoLocal);
        }

        return existente;
    }


    // IO

    public void abrir(String eArquivo) {
        ParserDKG parserDKGC = new ParserDKG();
        parserDKGC.parse(texto_ler(eArquivo), this);
    }

    public void salvar(String eArquivo) {

        ParserDKG escritor = new ParserDKG();
        escritor.montar("", mDKGObjetos);

        texto_escrever(eArquivo, escritor.getTexto());
    }


    public String toString() {

        ParserDKG escritor = new ParserDKG();

        escritor.montar("", mDKGObjetos);

        return escritor.getTexto();
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

    // FUNCOES INTERNAS DE IO

    private void texto_escrever(String eArquivo, String eConteudo) {

        BufferedWriter writer = null;
        try {
            File logFile = new File(eArquivo);

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(eConteudo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }

    private String texto_ler(String eArquivo) {

        String ret = "";

        try {
            FileReader arq = new FileReader(eArquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            ret += linha;

            while (linha != null) {

                linha = lerArq.readLine();
                if (linha != null) {
                    ret += "\n" + linha;
                }

            }

            arq.close();
        } catch (IOException e) {

        }

        return ret;
    }

}
