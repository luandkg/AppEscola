package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.avaliacao.Recuperacao;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.tempo.Data;

import java.util.ArrayList;

public class SemanasDeAtividades {

    public static void organizar(ArrayList<SemanaContinua> semanas_continuas, String eArquivo, String eArquivo_fluxo) {


        DKG fluxo_entrega = new DKG();

        if (FS.arquivoExiste(eArquivo)) {
            fluxo_entrega.abrir(FS.getArquivoLocal(eArquivo));
        }

        DKGObjeto contagem = fluxo_entrega.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA");
        DKGObjeto semanas = fluxo_entrega.unicoObjeto("SEMANAS");

        semanas.getObjetos().clear();

        DKG fluxo_saida = new DKG();

        if (FS.arquivoExiste(eArquivo_fluxo)) {
            fluxo_saida.abrir(FS.getArquivoLocal(eArquivo_fluxo));
        }


        ArrayList<String> fez = new ArrayList<String>();

        for (SemanaContinua semana : semanas_continuas) {

            for (Data data : semana.getDatas()) {

                for (DKGObjeto proc : contagem.getObjetos()) {


                    for (DKGObjeto em_data : proc.unicoObjeto("Momentos").getObjetos()) {

                        if (em_data.identifique("Data").getValor().contentEquals(data.getTempo())) {

                            String id = proc.identifique("ID").getValor();
                            String nome = proc.identifique("Nome").getValor();

                            int atividades = em_data.identifique("Valor").getInteiro(0);
                            if (!fez.contains(String.valueOf(id))) {
                                fez.add(String.valueOf(id));
                                //System.out.println("\tPROC ID " + proc.listar());
                                // ret.add(new Aluno(id, em_data.identifique("Data").getValor().replace("2022_", ""), nome, em_data.identifique("Valor").getValor(), ""));
                            }
                            // fez += atividades;


                        }

                    }


                }

            }

            DKGObjeto objeto_semana = semanas.criarObjeto("SEMANA");
            objeto_semana.identifique("ID", semana.getNome());
            objeto_semana.identifique("Status", semana.getStatus());
            objeto_semana.identifique("Valor", fez.size());

        }


        fluxo_saida.salvar(FS.getArquivoLocal(eArquivo_fluxo));


    }

    public static void guardar(ArrayList<Aluno> mAlunos, ArrayList<SemanaContinua> mSemanas, String eArquivoSemanas) {

        int todos = Escola.filtrarAlunosVisiveis(mAlunos).size();

        DKG arquivo_semanas = new DKG();
        DKGObjeto obj_semanas = arquivo_semanas.unicoObjeto("Semanas");

        SuperCache eSuperCache = new SuperCache(Local.LOCAL_CACHE + "/" + Local.ARQUIVO_NOTAS);

        for (SemanaContinua eSemanaContinua : mSemanas) {

            int fizeram = eSuperCache.getAlunosDaSemana(eSemanaContinua.getDatas(), eSemanaContinua.getDatas()).size();
            int atividades = eSuperCache.getAtividadesDaSemana(eSemanaContinua.getDatas(), eSemanaContinua.getDatas()).size();

            DKGObjeto obj_semana = obj_semanas.criarObjeto("Semana");
            obj_semana.identifique("Nome", "SEMANA DE ATIVIDADES " + eSemanaContinua.getNome());


            obj_semana.identifique("Fizeram", fizeram);
            obj_semana.identifique("Atividades", atividades);

            obj_semana.identifique("Todos", todos);
            obj_semana.identifique("Numero", eSemanaContinua.getNumero());

            obj_semana.identifique("Status", eSemanaContinua.getStatus());

        }


        DKGObjeto obj_recuperacao = obj_semanas.criarObjeto("Semana");
        obj_recuperacao.identifique("Nome", "RECUPERAÇÃO BIMESTRAL");
        obj_recuperacao.identifique("Fizeram", Recuperacao.getRecuperacaoContagem());
        obj_recuperacao.identifique("Atividades", Recuperacao.getRecuperacaoContagem());
        obj_recuperacao.identifique("Todos", todos);
        obj_recuperacao.identifique("Status", Recuperacao.getRecuperacaoStatus());


        arquivo_semanas.salvar(FS.getArquivoLocal(eArquivoSemanas));

    }

    public static ArrayList<SemanaContinuaCarregada> carregarSemanas(String eArquivoSemanas) {
        ArrayList<SemanaContinuaCarregada> ret = new ArrayList<SemanaContinuaCarregada>();


        DKG arquivo_semanas = new DKG();

        if (FS.arquivoExiste(eArquivoSemanas)) {
            arquivo_semanas.abrir(FS.getArquivoLocal(eArquivoSemanas));
        }

        DKGObjeto obj_semanas = arquivo_semanas.unicoObjeto("Semanas");

        for (DKGObjeto obj : obj_semanas.getObjetos()) {

            SemanaContinuaCarregada sc = new SemanaContinuaCarregada(obj.identifique("Nome").getValor(), obj.identifique("Numero").getInteiro(0), obj.identifique("Status").getValor(), obj.identifique("Todos").getInteiro(0), obj.identifique("Fizeram").getInteiro(0));
            ret.add(sc);

        }

        return ret;
    }

    public static int getAtividades(String eArquivoSemanas) {

        int total = 0;

        DKG arquivo_semanas = new DKG();

        if (FS.arquivoExiste(eArquivoSemanas)) {
            arquivo_semanas.abrir(FS.getArquivoLocal(eArquivoSemanas));
        }

        DKGObjeto obj_semanas = arquivo_semanas.unicoObjeto("Semanas");

        for (DKGObjeto obj : obj_semanas.getObjetos()) {
            total += obj.identifique("Atividades").getInteiro(0);
        }

        return total;
    }

}
