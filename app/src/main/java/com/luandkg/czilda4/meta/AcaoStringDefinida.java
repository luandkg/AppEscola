package com.luandkg.czilda4.meta;


public class AcaoStringDefinida {

    private AcaoString mAcaoString;
    private String mArgumento;

    public AcaoStringDefinida(AcaoString eAcaoString, String eArgumento) {
        mAcaoString = eAcaoString;
        mArgumento = eArgumento;
    }

    public void fazer(){
        toAcaoDefinida().fazer();
    }

    public Acao toAcaoDefinida() {
        return new Acao() {
            @Override
            public void fazer() {
                mAcaoString.fazer(mArgumento);
            }
        };
    }

}
