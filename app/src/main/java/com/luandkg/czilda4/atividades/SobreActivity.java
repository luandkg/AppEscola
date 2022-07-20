package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.Redizz;
import com.luandkg.czilda4.Versionador;
import com.luandkg.czilda4.dropbox.Dropbox;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        TextView TV_VERSAO = (TextView) findViewById(R.id.sobre_versao);
        TextView TV_DESENVOLVEDOR = (TextView) findViewById(R.id.sobre_desenvolvedor);
        Button BTN_SINCRONIZAR = (Button) findViewById(R.id.sobre_sincronizar);

        Versionador v = new Versionador();


        TV_VERSAO.setText(v.getVersaoCompleta());
        TV_DESENVOLVEDOR.setText(v.getAutor());

        BTN_SINCRONIZAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dropbox.iniciar(view.getContext());

                if (Redizz.obter("DROPBOXCHAVE").length() > 0) {
                    Dropbox.realizar_upload_sobrescrevendo(SigmaCollection.organizar(Local.COLECAO_AVISOS) , "CED_01/avisos.dkg");
                    Dropbox.realizar_upload_sobrescrevendo(SigmaCollection.organizar(Local.COLECAO_TUDO), "CED_01/tudo.dkg");
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Dropbox.guardarToken();

    }


}