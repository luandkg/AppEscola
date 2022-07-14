package com.luandkg.czilda4.atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.R;
import com.luandkg.czilda4.escola.utils.Aprovados;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;

import java.util.ArrayList;

public class AprovadorActivity extends AppCompatActivity {

    private TextView TX_TITULO;
    private ImageView IV_GRAFICO;
    private TextView TX_FRASE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovador);


        TX_TITULO = (TextView) findViewById(R.id.aprovador_titulo);
        IV_GRAFICO = (ImageView) findViewById(R.id.aprovador_grafico);
        TX_FRASE = (TextView) findViewById(R.id.aprovador_titulo2);

        ArrayList<AlunoContinuo> alunos_continuos = Escola.getAlunosContinuosVisiveisEOrdenadosDaEscola();

        MetodoContinuo.avaliarComDocumento(HiperCacheDeAvaliacao.getDocumento(), alunos_continuos, BimestreCorrente.GET().getSemanas());

        int aprovados = 0;
        int quase = 0;

        int total = 0;

        for (AlunoContinuo aluno : alunos_continuos) {

            if (aluno.getAcumuladoContinuidadeComRecuperacao() >= 4) {
                quase += 1;
            }

            if (aluno.getAcumuladoContinuidadeComRecuperacao() >= 5) {
                aprovados += 1;
            }

            total += 1;
        }

        IV_GRAFICO.setImageBitmap(Aprovados.criar(quase,aprovados, total));

        if (aprovados == 0) {
            TX_FRASE.setText("Nenhum aluno foi aprovado de " + total);
        } else if (aprovados == 1) {
            TX_FRASE.setText(aprovados + " aluno aprovado de " + total);
        } else {
            TX_FRASE.setText(aprovados + " alunos aprovados de " + total);
        }

    }


}