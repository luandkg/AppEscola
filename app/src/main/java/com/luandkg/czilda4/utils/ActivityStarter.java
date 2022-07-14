package com.luandkg.czilda4.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class ActivityStarter {

    public static void iniciar(Context eContexto, Button eBotao,Class<?> eClasse) {

        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(eContexto, eClasse).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                eContexto.startActivity(intent);

            }
        });


    }

    public static void iniciar_com_argumento(Context eContexto, Button eBotao,Class<?> eClasse, String eCampo,String eValor) {

        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(eContexto, eClasse).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(eCampo, eValor);
                eContexto.startActivity(intent);

            }
        });


    }


}
