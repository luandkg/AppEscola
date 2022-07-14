package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.czilda4.escola.avisos.Avisos;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.fragments.AvisadorFragment;

public class CriarAvisoActivity extends AppCompatActivity {


    private TextInputEditText mTextInputEditText;
    private Button mOK;
    private Button mCancelar;

    private CriarAvisoActivity mContexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_aviso);


        mTextInputEditText = (TextInputEditText) findViewById(R.id.TXT_AVISO);
        mOK = (Button) findViewById(R.id.BTN_CRIAR_AVISO);
        mCancelar = (Button) findViewById(R.id.BTN_CANCELAR_AVISO);

        mTextInputEditText.setText("");

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
                    Avisos.aviso_criar(mTextInputEditText.getText().toString());

                    AvisadorFragment.FAZER();

                    mContexto.finish();
                }


            }
        });

    }
}