package com.luandkg.czilda4.escola.avisos;

public class Aviso {

    private String mID;
    private String mMensagem;

    public Aviso(String eID, String eMensagem) {
        mID = eID;
        mMensagem = eMensagem;
    }

    public String getID() {
        return mID;
    }

    public int getIDNumerico() {
        int v = 0;
        if (mID.length() > 0) {
            v = Integer.parseInt(mID);
        }
        return v;
    }

    public String getMensagem() {
        return mMensagem;
    }
}
