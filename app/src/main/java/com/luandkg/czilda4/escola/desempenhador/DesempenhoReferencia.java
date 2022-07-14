package com.luandkg.czilda4.escola.desempenhador;

public class DesempenhoReferencia {

    private int m0;
    private int m3;
    private int m5;
    private int m7;

    private int m1;
    private int m2;
    private int m4;
    private int m6;
    private int m8;
    private int m9;
    private int m10;

    public DesempenhoReferencia(int e0, int e3, int e5, int e7) {
        m0 = e0;
        m3 = e3;
        m5 = e5;
        m7 = e7;
    }

    public DesempenhoReferencia(int e0,int e1,int e2, int e3,int e4, int e5, int e6,int e7,int e8,int e9,int e10) {
        m0 = e0;
        m3 = e3;
        m5 = e5;
        m7 = e7;

        m1=e1;
        m2=e2;
        m4=e4;
        m6=e6;
        m8=e8;
        m9=e9;
        m10=e10;

    }

    public void set(int e0, int e3, int e5, int e7) {
        m0 = e0;
        m3 = e3;
        m5 = e5;
        m7 = e7;
    }


    public int get0() {
        return m0;
    }

    public int get3() {
        return m3;
    }

    public int get5() {
        return m5;
    }

    public int get7() {
        return m7;
    }

    public int getValor(int v) {
        int ret = 0;

        if(v==0) {
            ret = m0;
        }else if(v==3){
            ret=m3;
        }else if(v==5){
            ret=m5;
        }else if(v==7){
            ret=m7;


        }else if(v==1){
            ret=m1;
        }else if(v==2){
            ret=m2;
        }else if(v==4){
            ret=m4;
        }else if(v==6){
            ret=m6;

        }else if(v==8){
            ret=m8;
        }else if(v==9){
            ret=m9;
        }else if(v==10){
            ret=m10;
        }


        return ret;
    }
}
