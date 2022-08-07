package com.luandkg.czilda4.atividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.avisos.Aviso;
import com.luandkg.czilda4.escola.avisos.Avisos;
import com.luandkg.czilda4.libs.meta.Acao;
import com.luandkg.czilda4.utils.CaixaSimNao;
import com.luandkg.czilda4.utils.Itenizador;
import com.luandkg.czilda4.utils.ListaGenerica;
import com.luandkg.czilda4.utils.Widget;

import java.util.ArrayList;

public class AvisosArquivadosActivity extends AppCompatActivity {

    private ListView LISTA_AVISOS;

    private Context mContexto;
    private Acao RECARREGAR_LISTA;

    public static boolean TEM = false;
    public static Acao ACAO = null;

    public static void iniciar(Acao eAcao) {
        TEM = true;
        ACAO = eAcao;
    }

    public static void FAZER() {
        ACAO.fazer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos_arquivados);

        LISTA_AVISOS = (ListView) findViewById(R.id.avisos_arquivados_lista);


        mContexto = this.getBaseContext();

        ArrayList<Aviso> avisos = Avisos.listar_arquivados();

        RECARREGAR_LISTA = getRecarregar();
        LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, avisos.size(), onItem(avisos)));

        iniciar(getRecarregar());
    }

    public Acao getRecarregar() {
        return new Acao() {
            @Override
            public void fazer() {

                ArrayList<Aviso> avisos = Avisos.listar_arquivados();

                LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, avisos.size(), onItem(avisos)));
            }
        };
    }


    public Itenizador onItem(ArrayList<Aviso> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                Aviso eAviso = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_aviso_arquivado, inflater, parent);

                TextView texto = mWidget.getTextView(R.id.item_aviso_arquivado_texto);
                Button desarquivar = mWidget.getButton(R.id.item_aviso_arquivado_desarquivar);
                Button remover = mWidget.getButton(R.id.item_aviso_arquivado_remover);


                texto.setText(eAviso.getMensagem());

                texto.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), AvisoEditarActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("ID", eAviso.getID());
                        view.getContext().startActivity(intent);


                    }
                });

                desarquivar.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        Avisos.desarquivar(eAviso.getID());
                        RECARREGAR_LISTA.fazer();

                    }
                });

                remover.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        CaixaSimNao.perguntar(mContexto, "Excluir", "Deseja remover o aviso ?", new Acao() {
                            @Override
                            public void fazer() {

                                Avisos.remover(eAviso.getID());
                                RECARREGAR_LISTA.fazer();

                            }
                        });


                    }
                });

                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }

}