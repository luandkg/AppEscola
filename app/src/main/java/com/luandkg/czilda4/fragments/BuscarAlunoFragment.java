package com.luandkg.czilda4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.czilda4.R;
import com.luandkg.czilda4.databinding.FragmentBuscarAlunoBinding;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avisos.Avisos;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.listas.Lista_AlunoContinuoNotaFinal;
import com.luandkg.czilda4.listas.Lista_BuscandoAlunos;

import java.util.ArrayList;


public class BuscarAlunoFragment extends Fragment {

    private FragmentBuscarAlunoBinding mInterface;

    private TextInputEditText mBuscar;
    private Button mBuscarAcao;
    private ListView mListagem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInterface = FragmentBuscarAlunoBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        mBuscar = (TextInputEditText) mInterface.BUSCARALUNOET;
        mBuscarAcao = (Button) mInterface.BUSCARALUNOBUSCAR;
        mListagem = (ListView) mInterface.BUSCARALUNOLISTAGEM;

        mBuscarAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aluno_procurando = mBuscar.getText().toString().toLowerCase();

                if (aluno_procurando.length() > 0) {

                    ArrayList<AlunoContinuo> alunos_continuos = HiperCacheDeAvaliacao.getTodos();
                    ArrayList<AlunoContinuo> ret = new ArrayList<AlunoContinuo>();

                    for (AlunoContinuo aa : alunos_continuos) {
                        if (aa.getNome().toLowerCase().startsWith(aluno_procurando)) {
                            ret.add(aa);
                            if (ret.size() > 30) {
                                break;
                            }
                        }
                    }

                    System.out.println(ret.size());

                    mListagem.setAdapter(new Lista_BuscandoAlunos(v.getContext(), ret));


                }


            }
        });

        return root;
    }
}