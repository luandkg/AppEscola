package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.avaliacao.Recuperacao;
import com.luandkg.czilda4.escola.tempo.Semanador;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.utils.Texto;

import java.util.ArrayList;

public class Perfilometro {

    public static ArrayList<DKGObjeto> getPerfilRaiz() {

        DKG documento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_NOTAS);


        return documento.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA").getObjetos();
    }

    public static AlunoContinuo getPerfilDe(String aluno_id, ArrayList<DKGObjeto> perfils, ArrayList<SemanaContinua> semanas) {

        AlunoContinuo perfil = new AlunoContinuo(-1, "", "", "");

        for (DKGObjeto aluno_objeto : perfils) {

            String id = aluno_objeto.identifique("ID").getValor();

            if (Texto.is_igual(aluno_id, id)) {

                perfil = toPerfil(aluno_objeto, semanas);


                break;
            }

        }

        return perfil;

    }

    public static AlunoContinuo getPerfilDeSemSemanas(String aluno_id, ArrayList<DKGObjeto> perfils) {

        AlunoContinuo perfil = new AlunoContinuo(-1, "", "", "");

        for (DKGObjeto aluno_objeto : perfils) {

            String id = aluno_objeto.identifique("ID").getValor();

            if (Texto.is_igual(aluno_id, id)) {

                perfil = new AlunoContinuo(aluno_objeto.identifique("ID").getInteiro(), aluno_objeto.identifique("Nome").getValor(), aluno_objeto.identifique("Turma").getValor(), "SIM");
                perfil.setNotas(aluno_objeto.identifique("NotaSemRecuperacao").getDouble(0.0), aluno_objeto.identifique("NotaComRecuperacao").getDouble(0.0));

                int mParticipacao = aluno_objeto.identifique("Participacao").getInteiro(0);
                int mCompromisso = aluno_objeto.identifique("Compromisso").getInteiro(0);
                int mDedicacao = aluno_objeto.identifique("Dedicacao").getInteiro(0);
                int mFrequencia = aluno_objeto.identifique("Frequencia").getInteiro(0);
                int mQualificacao = aluno_objeto.identifique("Qualificacao").getInteiro(0);


                perfil.setMetricas(mParticipacao, mCompromisso, mDedicacao, mFrequencia, mQualificacao);


                break;
            }

        }

        return perfil;

    }

    public static ArrayList<AlunoContinuo> getPerfis(ArrayList<DKGObjeto> perfils, ArrayList<SemanaContinua> semanas) {

        ArrayList<AlunoContinuo> lista = new ArrayList<AlunoContinuo>();

        for (DKGObjeto aluno_objeto : perfils) {

            AlunoContinuo perfil = toPerfil(aluno_objeto, semanas);

            lista.add(perfil);

        }

        return lista;

    }

    public static AlunoContinuo toPerfil(DKGObjeto aluno_objeto, ArrayList<SemanaContinua> semanas) {


        AlunoContinuo perfil = new AlunoContinuo(aluno_objeto.identifique("ID").getInteiro(), aluno_objeto.identifique("Nome").getValor(), aluno_objeto.identifique("Turma").getValor(), "SIM");
        perfil.setNotas(aluno_objeto.identifique("NotaSemRecuperacao").getDouble(0.0), aluno_objeto.identifique("NotaComRecuperacao").getDouble(0.0));

        int mParticipacao = aluno_objeto.identifique("Participacao").getInteiro(0);
        int mCompromisso = aluno_objeto.identifique("Compromisso").getInteiro(0);
        int mDedicacao = aluno_objeto.identifique("Dedicacao").getInteiro(0);
        int mFrequencia = aluno_objeto.identifique("Frequencia").getInteiro(0);
        int mQualificacao = aluno_objeto.identifique("Qualificacao").getInteiro(0);


        perfil.setMetricas(mParticipacao, mCompromisso, mDedicacao, mFrequencia, mQualificacao);

        String semana_nome = "";
        String semana_conteudo = "";

        boolean temAnterior = false;
        SemanaContinuaValores anterior = null;


        for (DKGObjeto momento : aluno_objeto.unicoObjeto("Momentos").getObjetos()) {

            String data = momento.identifique("Data").getValor();
            String valor = momento.identifique("Valor").getValor();
            String data_realizada = momento.identifique("Realizada").getValor();

            String semana_nome_agora = Semanador.getSemanaNome(data, semanas);

            if (semana_nome_agora.contentEquals(semana_nome)) {
                semana_conteudo += valor;
            } else {
                semana_conteudo = valor;
                semana_nome = semana_nome_agora;
            }

            double parcela = MetodoContinuo.getParcela(semana_conteudo);

            String sData = Data.organizarData(data);

            String sNomeCompleto = "";
            String sStatusCompleto = "";

            for (SemanaContinua semana : semanas) {
                if (semana.temData(sData)) {
                    sNomeCompleto = "Semana " + semana.getNome();
                    sStatusCompleto = semana.getStatus();
                    break;
                }
            }

            String sData_realizada = Data.organizarData(data_realizada);

            if (sData_realizada.length() < 10) {
                sData_realizada = sData;
            }

            SemanaContinuaValores scv = new SemanaContinuaValores(sData, sData_realizada, valor, Texto.doubleNumC2(String.valueOf(parcela)), sNomeCompleto, sStatusCompleto);

            if (temAnterior) {
                if (anterior.getNomeCompleto().contentEquals(sNomeCompleto)) {

                } else {
                    anterior.marcarUltima();
                }
            }


            perfil.adicionarSemana(scv);

            temAnterior = true;
            anterior = scv;

        }

        if (temAnterior) {
            anterior.marcarUltima();
        }


        if (aluno_objeto.identifique("RecuperacaoRealizada").isValor("SIM")) {


            String sNome = "RECUPERAÇÃO";
            String status_completo = Recuperacao.getRecuperacaoStatus();

            String rValor = aluno_objeto.identifique("RecuperacaoValor").getValor();
            String rData = aluno_objeto.identifique("RecuperacaoData").getValor();
            String rGanhou = aluno_objeto.identifique("RecuperacaoGanhou").getValor();


            SemanaContinuaValores rec = new SemanaContinuaValores(rData, Data.toData(rData).getFluxo(), rValor, Texto.doubleNumC2(String.valueOf(rGanhou)), sNome, status_completo);

            rec.marcarUltima();
            perfil.adicionarSemana(rec);


        }

        return perfil;
    }
}
