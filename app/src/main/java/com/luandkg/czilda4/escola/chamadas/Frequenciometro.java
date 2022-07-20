package com.luandkg.czilda4.escola.chamadas;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.utils.ContadorSN;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.Texto;
import com.luandkg.czilda4.libs.tempo.Data;

import java.io.File;
import java.util.ArrayList;

public class Frequenciometro {

    public static ContadorSN contarDaTurma(ArrayList<Aluno> mAlunos) {

        ContadorSN contador = new ContadorSN();

        for (Aluno eAluno : mAlunos) {
            if (eAluno.getVisibilidade().contentEquals("SIM")) {
                if (eAluno.getStatus().contentEquals("PRESENTE")) {
                    contador.aumentar_sim(1);
                } else {
                    contador.aumentar_nao(1);
                }

            }
        }

        return contador;

    }

    public static void unirChamadas() {

        Local.organizarPastas();

        DKG eArquivoChamadas = new DKG();
        DKGObjeto eChamadas = eArquivoChamadas.unicoObjeto("CHAMADAS");

        for (File arquivo : FS.listarArquivos(Local.LOCAL_REALIZAR_CHAMADA)) {

            DKG eDocumento = DKG.GET(arquivo.getAbsolutePath());

            DKGObjeto chamada_dia = eChamadas.criarObjeto("CHAMADA");

            chamada_dia.identifique("Data", arquivo.getName());
            chamada_dia.getObjetos().addAll(eDocumento.unicoObjeto("Chamada").getObjetos());

        }

        eArquivoChamadas.salvar(FS.getArquivoLocal(Local.LOCAL_CACHE + "/" + Local.ARQUIVO_CHAMADAS));

    }


    public static void contarFrequencia(ArrayList<Aluno> mAlunos, ArrayList<Data> quais_datas) {

        Local.organizarPastas();

        DKG eArquivoChamadas = new DKG();
        DKGObjeto eContagem = eArquivoChamadas.unicoObjeto("FREQUENCIA");

        for (Aluno aluno : mAlunos) {

            DKGObjeto obj_aluno = eContagem.criarObjeto("Aluno");

            obj_aluno.identifique("ID", aluno.getID());
            obj_aluno.identifique("Nome", aluno.getNome());
            obj_aluno.identifique("Frequencia", 0);
            obj_aluno.identifique("Aulas", 0);

        }


        DKG eArquivoEstatisticas = new DKG();
        DKGObjeto eEstatisticas = eArquivoEstatisticas.unicoObjeto("ESTATISTICAS");


        for (Data eData : quais_datas) {

            int presentes = 0;
            int todos = 0;

            String eArquivoCaminho = Local.LOCAL_REALIZAR_CHAMADA + "/" + eData.getTempo() + ".dkg";

            if (FS.arquivoExiste(eArquivoCaminho)) {

                DKG eDocumento = new DKG();
                eDocumento.abrir(FS.getArquivoLocal(eArquivoCaminho));

                DKGObjeto chamada_dia = eDocumento.unicoObjeto("Chamada");
                for (DKGObjeto aluno_chamada : chamada_dia.getObjetos()) {

                    String aluno_chamada_id = aluno_chamada.identifique("ID").getValor();
                    String aluno_chamada_status = aluno_chamada.identifique("Status").getValor();

                    for (DKGObjeto aluno_contando : eContagem.getObjetos()) {

                        String aluno_contando_id = aluno_contando.identifique("ID").getValor();

                        if (Texto.is_igual(aluno_chamada_id, aluno_contando_id)) {

                            if (Texto.is_igual(aluno_chamada_status, "PRESENTE")) {

                                int v = aluno_contando.identifique("Frequencia").getInteiro(0);
                                v += 1;
                                aluno_contando.identifique("Frequencia").setInteiro(v);
                                presentes += 1;

                            } else {
                                todos += 1;
                            }

                            int aulas = aluno_contando.identifique("Aulas").getInteiro(0);
                            aulas += 1;
                            aluno_contando.identifique("Aulas").setInteiro(aulas);

                            break;
                        }

                    }


                }

            }

            DKGObjeto obj_estatistica = eEstatisticas.criarObjeto("ESTATISTICA");
            obj_estatistica.identifique("Data", eData.getTempo());
            obj_estatistica.identifique("Presente", presentes);
            obj_estatistica.identifique("Todos", todos);


        }


        eArquivoChamadas.salvar(FS.getArquivoLocal(Local.ARQUIVO_CACHE_FREQUENCIA));
        eArquivoEstatisticas.salvar(FS.getArquivoLocal(Local.ARQUIVO_CACHE_FREQUENCIA_ESTATISTICAS));

        System.out.println(eArquivoEstatisticas.toString());

    }

}
