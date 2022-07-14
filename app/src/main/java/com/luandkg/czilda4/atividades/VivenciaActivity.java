package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.listas.Lista_AlunosEmVivencia;

public class VivenciaActivity extends AppCompatActivity {

    private ListView LISTA;
    private Lista_AlunosEmVivencia mLista_AlunosEmVivencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivencia);

        LISTA = findViewById(R.id.vivencia_lista);

        mLista_AlunosEmVivencia = new Lista_AlunosEmVivencia(getBaseContext(), Escola.getAlunosEmVivencia());


        LISTA.setAdapter(mLista_AlunosEmVivencia);
    }
}