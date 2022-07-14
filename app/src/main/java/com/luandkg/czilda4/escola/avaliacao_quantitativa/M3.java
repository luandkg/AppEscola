package com.luandkg.czilda4.escola.avaliacao_quantitativa;

public class M3 {

    public static int getExtra(int i1, int i2, int i3) {

        int extra = 0;

        if (i1 >= 2 && i2 >= 2 && i2 >= 2 && i3 >= 2) {

            int t1 = i2 - i1;
            int t2 = i3 - i2;

            if (t1 >= 0 && t2 >= 0) {
                extra = 1;
            }
        }


        return extra;

    }

    public static int c3(int i1, int i2, int i3) {

        int extra = 0;

        if (i1 >= 2 && i2 >= 2 && i2 >= 2 && i3 >= 2) {

            int t1 = i2 - i1;
            int t2 = i3 - i2;

            if (t1 >= 0 && t2 >= 0) {
                extra = 1;
            }
        }


        return i1 + i2 + i3 + extra;

    }

    public static int v3(String mNota1, String mNota2, String mNota3) {


        int i1 = 0;
        int i2 = 0;
        int i3 = 0;


        if (mNota1.length() > 0) {
            if (isNumero(mNota1)) {
                i1 = Integer.parseInt(mNota1);
            }
        }

        if (mNota2.length() > 0) {
            if (isNumero(mNota2)) {
                i2 = Integer.parseInt(mNota2);
            }
        }

        if (mNota3.length() > 0) {
            if (isNumero(mNota3)) {
                i3 = Integer.parseInt(mNota3);
            }
        }


        return c3(i1, i2, i3);

    }

    public static boolean isNumero(String s) {
        boolean ret = false;

        if (s.contentEquals("0")) {
            ret = true;
        } else if (s.contentEquals("1")) {
            ret = true;
        } else if (s.contentEquals("2")) {
            ret = true;
        } else if (s.contentEquals("3")) {
            ret = true;
        } else if (s.contentEquals("4")) {
            ret = true;
        } else if (s.contentEquals("5")) {
            ret = true;
        } else if (s.contentEquals("6")) {
            ret = true;
        } else if (s.contentEquals("7")) {
            ret = true;
        } else if (s.contentEquals("8")) {
            ret = true;
        } else if (s.contentEquals("9")) {
            ret = true;
        }

        return ret;
    }
}
