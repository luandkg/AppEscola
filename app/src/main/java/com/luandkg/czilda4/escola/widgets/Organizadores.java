package com.luandkg.czilda4.escola.widgets;

import android.graphics.Color;
import android.widget.Button;

public class Organizadores {

    public static void limpar(Button b1, Button b2, Button b3, Button b4) {

        b1.setBackgroundColor(Color.parseColor("#90a4ae"));
        b2.setBackgroundColor(Color.parseColor("#90a4ae"));
        b3.setBackgroundColor(Color.parseColor("#90a4ae"));
        b4.setBackgroundColor(Color.parseColor("#90a4ae"));


    }

    public static void onNivel(String eNota, Button b1, Button b2, Button b3, Button b4) {

        if (eNota.contentEquals("0")) {
            b1.setBackgroundColor(Color.parseColor("#37474f"));
        } else if (eNota.contentEquals("1")) {
            b2.setBackgroundColor(Color.parseColor("#e64a19"));
        } else if (eNota.contentEquals("2")) {
            b3.setBackgroundColor(Color.parseColor("#fdd835"));
        } else if (eNota.contentEquals("3")) {
            b4.setBackgroundColor(Color.parseColor("#66bb6a"));
        }

    }
}
