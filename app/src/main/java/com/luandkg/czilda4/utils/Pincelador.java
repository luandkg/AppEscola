package com.luandkg.czilda4.utils;

import android.graphics.Color;
import android.graphics.Paint;

public class Pincelador {

    public static Paint criarPincel(String eCorHexa){
        Paint ePincel = new Paint();
        ePincel.setColor(Color.parseColor(eCorHexa));
        ePincel.setStyle(Paint.Style.FILL);
        return ePincel;
    }

    public static Paint criarLapis(String eCorHexa){
        Paint ePincel = new Paint();
        ePincel.setColor(Color.parseColor(eCorHexa));
        ePincel.setStrokeWidth(1);
        ePincel.setAntiAlias(true);
        ePincel.setStrokeCap(Paint.Cap.BUTT);
        ePincel.setStyle(Paint.Style.STROKE);
        return ePincel;
    }
}
