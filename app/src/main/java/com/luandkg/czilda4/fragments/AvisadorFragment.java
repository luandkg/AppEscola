package com.luandkg.czilda4.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.czilda4.atividades.AvisoCriarActivity;
import com.luandkg.czilda4.atividades.AvisoEditarActivity;
import com.luandkg.czilda4.atividades.AvisosArquivadosActivity;
import com.luandkg.czilda4.atividades.SobreActivity;
import com.luandkg.czilda4.escola.avisos.Aviso;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.utils.Itenizador;
import com.luandkg.czilda4.utils.ListaGenerica;
import com.luandkg.czilda4.libs.meta.Acao;
import com.luandkg.czilda4.escola.avisos.Avisos;
import com.luandkg.czilda4.utils.CaixaSimNao;
import com.luandkg.czilda4.utils.Widget;
import com.luandkg.czilda4.databinding.FragmentAvisadorBinding;

import java.util.ArrayList;


public class AvisadorFragment extends Fragment {

    private FragmentAvisadorBinding mInterface;

    private ListView LISTA_AVISOS;
    private Button BTN_CRIAR_AVISO;
    private Button BTN_ARQUIVADOS;

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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInterface = FragmentAvisadorBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        LISTA_AVISOS = mInterface.avisadorListagem;
        BTN_CRIAR_AVISO = mInterface.avisadorCriarAviso;
        BTN_ARQUIVADOS = mInterface.avisadorArquivados;

        BTN_CRIAR_AVISO.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AvisoCriarActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

        BTN_ARQUIVADOS.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), AvisosArquivadosActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);


            }
        });


        mContexto = this.getContext();

        ArrayList<Aviso> avisos = Avisos.listar();

        RECARREGAR_LISTA = getRecarregar();
        LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, avisos.size(), onItem(avisos)));

        iniciar(getRecarregar());


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<Aviso> avisos = Avisos.listar();
        LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, avisos.size(), onItem(avisos)));
    }


    public Acao getRecarregar() {
        return new Acao() {
            @Override
            public void fazer() {

                ArrayList<Aviso> avisos = Avisos.listar();

                LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, avisos.size(), onItem(avisos)));
            }
        };
    }


    public Itenizador onItem(ArrayList<Aviso> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                Aviso eAviso = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_aviso, inflater, parent);

                TextView texto = mWidget.getTextView(R.id.item_aviso_texto);
                Button arquivar = mWidget.getButton(R.id.item_aviso_arquivar);
                Button remover = mWidget.getButton(R.id.item_aviso_remover);


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

                arquivar.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        Avisos.arquivar(eAviso.getID());
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mInterface = null;
    }
}