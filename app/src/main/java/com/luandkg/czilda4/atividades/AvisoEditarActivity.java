package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.avisos.Aviso;
import com.luandkg.czilda4.escola.avisos.Avisos;
import com.luandkg.czilda4.fragments.AvisadorFragment;

public class AvisoEditarActivity extends AppCompatActivity {

    private TextInputEditText mTextInputEditText;
    private Button mOK;
    private Button mCancelar;

    private AvisoEditarActivity mContexto;
    private String mID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_editar);

        mTextInputEditText = (TextInputEditText) findViewById(R.id.AVISO_EDITAR_TEXTO);
        mOK = (Button) findViewById(R.id.AVISO_EDITAR_OK);
        mCancelar = (Button) findViewById(R.id.AVISO_EDITAR_CANCELAR);


        Intent it = getIntent();
        mID = it.getStringExtra("ID");

        Aviso anotacao = Avisos.getAviso(mID);

        mTextInputEditText.setText(anotacao.getMensagem());

        mContexto = this;

        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContexto.finish();

            }
        });

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTextInputEditText.getText().toString().length() > 0) {

                    Avisos.alterar(mID, mTextInputEditText.getText().toString());

                    AvisadorFragment.FAZER();

                    mContexto.finish();
                }


            }
        });

    }
}