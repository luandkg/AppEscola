package com.luandkg.czilda4.escola.avaliacao_continua;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.Chaveador;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.Opcional;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.utils.Texto;

import java.util.ArrayList;

public class MetodoContinuo {

    public static void montar(ArrayList<Aluno> mAlunos, ArrayList<Data> quais_datas, String colecao_notas, String colecao_fluxo) {

        Local.organizarPastas();

        DKG eArquivoAvaliacao = SigmaCollection.INIT_COLLECTION(colecao_notas);

        DKGObjeto alunos_formativos_continuos = eArquivoAvaliacao.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA");

        alunos_formativos_continuos.identifique("DDC").setValor(Calendario.getTempoCompleto());
        alunos_formativos_continuos.identifique("DDM").setValor(Calendario.getTempoCompleto());

        for (Aluno aluno : mAlunos) {

            DKGObjeto obj_aluno = alunos_formativos_continuos.criarObjeto("Aluno");

            obj_aluno.identifique("ID", aluno.getID());
            obj_aluno.identifique("Nome", aluno.getNome());
            obj_aluno.identifique("Turma", aluno.getTurma());

            obj_aluno.identifique("DDC").setValor(Calendario.getTempoCompleto());
            obj_aluno.identifique("DDM").setValor(Calendario.getTempoCompleto());
            obj_aluno.identifique("DDP").setValor(Calendario.getTempoCompleto());

            obj_aluno.identifique("Chave").setValor(Chaveador.criarchave());
            obj_aluno.identifique("RecuperacaoRealizada").setValor("NAO");

        }

        DKG eArquivoFluxos = new DKG();
        DKGObjeto eFluxo = eArquivoFluxos.unicoObjeto("FLUXOS");


        for (Data eData : quais_datas) {

            int quantidade = 0;

            String eArquivoCaminho = Local.LOCAL_AVALIANDO + "/" + eData.getTempo() + ".dkg";

            if (FS.arquivoExiste(eArquivoCaminho)) {

                DKG eDocumento = DKG.GET(FS.getArquivoLocal(eArquivoCaminho));
                DKGObjeto alunos_notas = eDocumento.unicoObjeto("Notas");

                //  System.out.println(eDocumento.toString());

                for (DKGObjeto aluno_avaliando : alunos_notas.getObjetos()) {

                    String aluno_chamada_id = aluno_avaliando.identifique("ID").getValor();
                    String aluno_chamada_nota = aluno_avaliando.identifique("AVALIAR").getValor();
                    String aluno_chamada_data = aluno_avaliando.identifique("Data").getValor();

                    if (isValorValido(aluno_chamada_nota)) {

                        Opcional<DKGObjeto> proc_aluno_contando = alunos_formativos_continuos.procurar("ID", aluno_chamada_id);

                        if (proc_aluno_contando.isOK()) {

                            DKGObjeto aluno_contando = proc_aluno_contando.get();

                            String aluno_contando_id = aluno_contando.identifique("ID").getValor();

                            if (Texto.is_igual(aluno_chamada_id, aluno_contando_id)) {


                                DKGObjeto momento = aluno_contando.unicoObjeto("Momentos").criarObjeto("Momento");

                                momento.identifique("Data", eData.getTempo());
                                momento.identifique("Realizada", aluno_chamada_data);
                                momento.identifique("Valor", aluno_chamada_nota);

                                quantidade += 1;

                                aluno_contando.identifique("DDM").setValor(Calendario.getTempoCompleto());

                            }


                        }


                    }

                }

            }

            DKGObjeto fluxo = eFluxo.criarObjeto("Fluxo");
            fluxo.identifique("Data", eData.getTempo());
            fluxo.identifique("Quantidade", quantidade);

            //  System.out.println("-->> SALVAR :: " + eData.getTempo() + " com " + quantidade);

        }

        //   System.out.println(eArquivoChamadas.toString());

        // FREQUENCIA


        DKG eFrequencia = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_FREQUENCIAS);
        DKGObjeto frequencia_raiz = eFrequencia.unicoObjeto("FREQUENCIA");

        for (DKGObjeto aluno_avaliando : alunos_formativos_continuos.getObjetos()) {

            Opcional<DKGObjeto> proc_aluno_frequencia = frequencia_raiz.procurar("ID", aluno_avaliando.identifique("ID").getValor());

            if (proc_aluno_frequencia.isOK()) {
                DKGObjeto aluno_frequencia = proc_aluno_frequencia.get();

                int presente = aluno_frequencia.identifique("Frequencia").getInteiro();
                int aulas = aluno_frequencia.identifique("Aulas").getInteiro();

                int aulas_metade = aulas / 2;
                int falta = aulas - presente;

                //System.out.println("-->> " + aluno_chamada_id + " :: " + presente);

                if (presente >= aulas_metade) {
                    aluno_avaliando.identifique("PRESENTE").setValor("SIM");
                } else {
                    aluno_avaliando.identifique("PRESENTE").setValor("NAO");
                }

                aluno_avaliando.identifique("Aulas").setInteiro(aulas);
                aluno_avaliando.identifique("Presenca").setInteiro(presente);
                aluno_avaliando.identifique("Falta").setInteiro(falta);

                aluno_avaliando.identifique("DDM").setValor(Calendario.getTempoCompleto());

            }


        }


        // RECUPERACAO

        String eArquivoRecuperacao = Local.ARQUIVO_RECUPERACAO(BimestreCorrente.GET().getID());

        if (FS.arquivoExiste(eArquivoRecuperacao)) {

            DKG fluxo_recuperacao = DKG.GET(FS.getArquivoLocal(eArquivoRecuperacao));

            DKGObjeto recuperacao_raiz = fluxo_recuperacao.unicoObjeto("Notas");

            // System.out.println("-->> Iniciar Recuperacao :: " + eArquivoRecuperacao);

            for (DKGObjeto aluno_avaliando : alunos_formativos_continuos.getObjetos()) {

                // System.out.println("Avaliando -->> " + aluno.identifique("Nome").getValor());

                Opcional<DKGObjeto> proc_ePacote = recuperacao_raiz.procurar("ID", aluno_avaliando.identifique("ID").getValor());

                if (proc_ePacote.isOK()) {

                    DKGObjeto ePacote = proc_ePacote.get();

                    String recuperacao_valor = ePacote.identifique("RECUPERACAO").getValor();
                    String recuperacao_data = ePacote.identifique("Data").getValor();


                    if (isValorValido(recuperacao_valor)) {
                        aluno_avaliando.identifique("RecuperacaoRealizada").setValor("SIM");
                        aluno_avaliando.identifique("RecuperacaoValor").setValor(recuperacao_valor);
                        aluno_avaliando.identifique("RecuperacaoData").getValor(recuperacao_data);

                        aluno_avaliando.identifique("DDM").setValor(Calendario.getTempoCompleto());

                    }

                }


            }

        }

        alunos_formativos_continuos.identifique("DDM").setValor(Calendario.getTempoCompleto());

        SigmaCollection.WRITE_COLLECTION(colecao_notas, eArquivoAvaliacao);
        SigmaCollection.WRITE_COLLECTION(colecao_fluxo, eArquivoFluxos);


        //  System.out.println(eArquivoFluxo.toString());
    }


    public static DKG getDocumentoAvaliacao(String eArquivo) {

        DKG documento = new DKG();
        documento.abrir(FS.getArquivoLocal(eArquivo));

        return documento;
    }

    public static void avaliar(String eArquivo, ArrayList<AlunoContinuo> alunos, ArrayList<SemanaContinua> semanas) {

        DKG documento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(eArquivo);

        avaliarComDocumento(documento, alunos, semanas);

        documento.salvar(FS.getArquivoLocal(SigmaCollection.organizar(eArquivo)));


        //   System.out.println(documento.toString());

    }

    public static void avaliar_ate(String colecao_notas, ArrayList<AlunoContinuo> alunos, ArrayList<SemanaContinua> semanas, Data ate) {

        DKG documento = SigmaCollection.REQUIRED_COLLECTION(colecao_notas);

        // System.out.println("Com data limite :: " + ate.getFluxo());

        avaliarComDocumentoComDataLimite(documento, alunos, semanas, true, ate);

        SigmaCollection.WRITE_COLLECTION(colecao_notas, documento);

        //   System.out.println(documento.toString());

    }

    public static void avaliarComDocumento(DKG documento, ArrayList<AlunoContinuo> alunos, ArrayList<SemanaContinua> semanas) {
        avaliarComDocumentoComDataLimite(documento, alunos, semanas, false, null);
    }


    public static void avaliarComDocumentoComDataLimite(DKG documento, ArrayList<AlunoContinuo> alunos, ArrayList<SemanaContinua> semanas, boolean temDataLimite, Data ate) {


        DKGObjeto notas_objeto = documento.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA");

        for (DKGObjeto aluno_objeto : notas_objeto.getObjetos()) {

            String id = aluno_objeto.identifique("ID").getValor();
            int i_id = Integer.parseInt(id);

            String nome = aluno_objeto.identifique("Nome").getValor();
            String turma = "";
            // String momentos = "";


            for (AlunoContinuo a : alunos) {
                if (a.getID() == i_id) {

                    turma = a.getTurma();

                    String relatorio = "";


                    double avaliando_sem_recuperacao = 0.0;
                    double avaliando_com_recuperacao = 0.0;

                    int participando = 0;
                    int compromissando = 0;
                    int dedicando = 0;

                    // System.out.println("----------------- TUDO -----------------");
                    for (DKGObjeto momento : aluno_objeto.unicoObjeto("Momentos").getObjetos()) {

                        String data = momento.identifique("Data").getValor();
                        String valor = momento.identifique("Valor").getValor();


                        if (temDataLimite) {

                            //System.out.println(" DATA MENOR :: " + data + " - " + ate.getFluxo() + " -->> " +Data.toData(data).isMenor(ate) );

                            //  if (Data.toData(data).isMenorOuIgual(ate)) {
                            //      momentos += valor;
                            //      a.criarMomento(valor, data);
                            //  }
                        } else {
                            //  momentos += valor;
                            //   a.criarMomento(valor, data);
                        }


                    }

                    int SEMANAS_TOTAL = semanas.size();


                    a.continuidade_construir(SEMANAS_TOTAL);

                    int atividades_entregues = 0;

                    relatorio += "----------------- SEMANAS -----------------";
                    for (SemanaContinua semana : semanas) {

                        String atividades_da_semana = "";


                        for (DKGObjeto momento : aluno_objeto.unicoObjeto("Momentos").getObjetos()) {
                            String data = Data.organizarData(momento.identifique("Data").getValor());
                            String valor = momento.identifique("Valor").getValor();

                            if (temDataLimite) {
                                if (Data.toData(data).isMenor(ate)) {
                                    if (semana.temData(data)) {
                                        atividades_da_semana += valor;
                                        atividades_entregues += 1;
                                    }
                                }
                            } else {
                                if (semana.temData(data)) {
                                    atividades_da_semana += valor;
                                    atividades_entregues += 1;
                                }
                            }

                            a.setUltimaAtividadeRealizada(data);
                        }


                        int ZETA = Texto.contagem(atividades_da_semana, "1");
                        int DELTA = Texto.contagem(atividades_da_semana, "2");
                        int ALFA = Texto.contagem(atividades_da_semana, "3");


                        double parcela = getParcela(atividades_da_semana);

                        // EVENTO DA ESCOLA - SEXTA MOSTRA DE CIÊNCIAS E CULTURA ->> RETIRAR NO FUTURO
                        if (semana.temData("03/07/2022")) {
                            parcela = parcela * 2.0;
                            if (parcela > 2.0) {
                                parcela = 2.0;
                            }
                        }


                        if ((ALFA + DELTA + ZETA) >= 1) {
                            participando += 1;
                        }

                        if ((ALFA + DELTA) >= 1) {
                            compromissando += 1;
                        }

                        if ((ALFA) >= 1) {
                            dedicando += 1;
                        }


                        //  parcela = getParcela(dessa);

                        DKGObjeto semana_objeto = aluno_objeto.unicoObjeto("Semanas").criarObjeto("Semana");
                        semana_objeto.identifique("Nome", semana.getNome());
                        semana_objeto.identifique("Valor", parcela);

                        relatorio += "\nSEMANA " + semana.getNome() + " - " + semana.getStatus() + " -->> " + atividades_da_semana + " :: " + parcela;

                        avaliando_sem_recuperacao += parcela;

                        a.getContinudade(Integer.parseInt(semana.getNome()) - 1).setValor(parcela);
                    }

                    a.setAtividadesEntregues(atividades_entregues);

                    if (turma.contentEquals("7C") || turma.contentEquals("8D")) {
                        avaliando_sem_recuperacao = (avaliando_sem_recuperacao * 1.5);
                    }

                    if (avaliando_sem_recuperacao > 10) {
                        avaliando_sem_recuperacao = 10;
                    }

                    avaliando_com_recuperacao = avaliando_sem_recuperacao;


                    if (avaliando_sem_recuperacao < 5) {

                        // Tem Recuperacao

                        String recuperacao_tem = aluno_objeto.identifique("RecuperacaoRealizada").getValor();
                        if (recuperacao_tem.contentEquals("SIM")) {

                            String recuperacao_valor = aluno_objeto.identifique("RecuperacaoValor").getValor();
                            String recuperacao_data = aluno_objeto.identifique("RecuperacaoData").getValor();

                            boolean deve_aplicar_recuperacao = false;

                            if (temDataLimite) {
                                if (Data.toData(recuperacao_data).isMenorOuIgual(ate)) {
                                    deve_aplicar_recuperacao = true;
                                }
                            } else {
                                deve_aplicar_recuperacao = true;
                            }

                            if (deve_aplicar_recuperacao) {

                                if (aluno_objeto.identifique("PRESENTE").getValor().contentEquals("SIM")) {

                                    double precisa_de = 5 - avaliando_com_recuperacao;

                                    if (recuperacao_valor.contentEquals("3")) {
                                        avaliando_com_recuperacao += precisa_de;
                                    } else if (recuperacao_valor.contentEquals("2")) {
                                        avaliando_com_recuperacao += (precisa_de / 2.0);
                                    } else if (recuperacao_valor.contentEquals("1")) {
                                        avaliando_com_recuperacao += (precisa_de / 3.0);
                                    }

                                } else {
                                    if (avaliando_com_recuperacao < 3) {

                                        if (recuperacao_valor.contentEquals("3")) {
                                            avaliando_com_recuperacao = 3.0;
                                        } else if (recuperacao_valor.contentEquals("2")) {
                                            avaliando_com_recuperacao = 2.0;
                                        } else if (recuperacao_valor.contentEquals("1")) {
                                            avaliando_com_recuperacao = 1.0;
                                        }

                                    } else if (avaliando_com_recuperacao >= 3) {

                                        double precisa_de = 5 - avaliando_com_recuperacao;

                                        if (recuperacao_valor.contentEquals("3")) {
                                            avaliando_com_recuperacao += precisa_de;
                                        } else if (recuperacao_valor.contentEquals("2")) {
                                            avaliando_com_recuperacao += (precisa_de / 2.0);
                                        } else if (recuperacao_valor.contentEquals("1")) {
                                            avaliando_com_recuperacao += (precisa_de / 3.0);
                                        }

                                    }

                                }

                            }

                            double diferenca = avaliando_sem_recuperacao - avaliando_sem_recuperacao;
                            aluno_objeto.identifique("RecuperacaoGanhou").setValor(String.valueOf(diferenca));

                        }

                    }

                    if (avaliando_sem_recuperacao > 10) {
                        avaliando_sem_recuperacao = 10.0;
                    }

                    if (avaliando_com_recuperacao > 10) {
                        avaliando_com_recuperacao = 10.0;
                    }

                    a.setAcumuladoContinuidadeSemRecuperacao(avaliando_sem_recuperacao);
                    a.setAcumuladoContinuidadeComRecuperacao(avaliando_com_recuperacao);


                    aluno_objeto.identifique("NotaSemRecuperacao").setValor(String.valueOf(avaliando_sem_recuperacao));
                    aluno_objeto.identifique("NotaComRecuperacao").setValor(String.valueOf(avaliando_com_recuperacao));


                    // CONCEITOS QUALITATIVOS

                    int CONCEITO_MAXIMO = 2;
                    int CONCEITO_MEDIANO = 1;
                    int CONCEITO_PESSIMO = 0;


                    int mParticipacao = CONCEITO_PESSIMO;
                    int mCompromisso = CONCEITO_PESSIMO;
                    int mDedicacao = CONCEITO_PESSIMO;
                    int mFrequencia = CONCEITO_PESSIMO;
                    int mQualificacao = CONCEITO_PESSIMO;


                    String DATA_HOJE = Calendario.getData();

                    // System.out.println(DATA_HOJE);
                    // System.out.println("S0 :: " + semanas.get(0).getDatas().get(0).getFluxo());
                    //  System.out.println("SF :: " + semanas.get(semanas.size() - 1).getDatas().get(0).getFluxo());

                    int qual_semana = 0;
                    String semana_nome = "";

                    for (SemanaContinua eSemana : semanas) {
                        if (eSemana.temData(DATA_HOJE)) {
                            semana_nome = eSemana.getNome();
                            break;
                        }
                        qual_semana += 1;
                    }

                    int SEMANAS_REF = SEMANAS_TOTAL;

                    if (qual_semana < semanas.size()) {
                        SEMANAS_REF = qual_semana;
                    }

                    int isMuitoParticipativo = SEMANAS_REF - 2;
                    int isParticipativo = SEMANAS_REF - 5;

                    int isMuitoComprometido = SEMANAS_REF - 4;
                    int isComprometido = SEMANAS_REF - 5;

                    int isMuitoDedicado = SEMANAS_REF - 3;
                    int isDedicado = SEMANAS_REF - 5;

                    int aulas = aluno_objeto.identifique("Aulas").getInteiro(0);

                    int aulas_excelente = aulas - (aulas / 3);
                    int aulas_muito = (aulas / 2) + (aulas / 4);


                    //System.out.println("Semana Selecionada :: " + semana_nome + " :: " + qual_semana);

                    if (participando >= isMuitoParticipativo) {
                        mParticipacao = CONCEITO_MAXIMO;
                    } else if (participando >= isParticipativo) {
                        mParticipacao = CONCEITO_MEDIANO;
                    }


                    if (compromissando >= isMuitoComprometido) {
                        mCompromisso = CONCEITO_MAXIMO;
                    } else if (compromissando >= isComprometido) {
                        mCompromisso = CONCEITO_MEDIANO;
                    }


                    if (dedicando >= isMuitoDedicado) {
                        mDedicacao = CONCEITO_MAXIMO;
                    } else if (dedicando >= isDedicado) {
                        mDedicacao = CONCEITO_MEDIANO;
                    }

                    int presenca = aluno_objeto.identifique("Presenca").getInteiro(0);


                    if (presenca >= aulas_excelente) {
                        mFrequencia = CONCEITO_MAXIMO;
                    } else if (presenca >= aulas_muito) {
                        mFrequencia = CONCEITO_MEDIANO;
                    }


                    int meds = 0;
                    int maxs = 0;

                    if (mFrequencia == CONCEITO_MAXIMO) {
                        maxs += 1;
                    } else if (mFrequencia == CONCEITO_MEDIANO) {
                        meds += 1;
                    }

                    if (mParticipacao == CONCEITO_MAXIMO) {
                        maxs += 1;
                    } else if (mParticipacao == CONCEITO_MEDIANO) {
                        meds += 1;
                    }

                    if (mCompromisso == CONCEITO_MAXIMO) {
                        maxs += 1;
                    } else if (mCompromisso == CONCEITO_MEDIANO) {
                        meds += 1;
                    }


                    if ((meds + maxs) == 3) {
                        mQualificacao = CONCEITO_MEDIANO;
                    }


                    if (mDedicacao == CONCEITO_MAXIMO) {
                        maxs += 1;
                    } else if (mDedicacao == CONCEITO_MEDIANO) {
                        meds += 1;
                    }


                    if (mFrequencia == CONCEITO_MEDIANO && mParticipacao == CONCEITO_MEDIANO && mCompromisso == CONCEITO_MEDIANO) {
                        mQualificacao = CONCEITO_MEDIANO;
                    }

                    if (mFrequencia == CONCEITO_MAXIMO && mParticipacao == CONCEITO_MAXIMO && mCompromisso >= CONCEITO_MEDIANO && mDedicacao >= CONCEITO_MEDIANO) {
                        mQualificacao = CONCEITO_MAXIMO;
                    }


                    aluno_objeto.identifique("Participacao").setInteiro(mParticipacao);
                    aluno_objeto.identifique("Compromisso").setInteiro(mCompromisso);
                    aluno_objeto.identifique("Dedicacao").setInteiro(mDedicacao);
                    aluno_objeto.identifique("Frequencia").setInteiro(mFrequencia);
                    aluno_objeto.identifique("Qualificacao").setInteiro(mQualificacao);

                    aluno_objeto.identifique("DDP").setValor(Calendario.getTempoCompleto());

                    break;
                }
            }

        }

        // documento.salvar(FS.getArquivoLocal(eArquivo));


        //   System.out.println(documento.toString());

    }

    public static boolean isValorValido(String v) {
        boolean ret = false;

        if (v.contentEquals("1") || v.contentEquals("2") || v.contentEquals("3")) {
            ret = true;
        }

        return ret;
    }

    public static double getParcela(String dessa) {

        int ZETA = Texto.contagem(dessa, "1");
        int DELTA = Texto.contagem(dessa, "2");
        int ALFA = Texto.contagem(dessa, "3");

        return toParcela(ZETA, DELTA, ALFA);

    }

    public static double toParcela(double ZETA, double DELTA, double ALFA) {

        double parcela = 0.0;

        if (ZETA == 1) {
            parcela = 0.3;
        }

        if (ZETA >= 2) {
            parcela = 0.4;
        }

        if (DELTA == 1) {
            parcela = 0.5;
        }

        if (DELTA >= 2) {
            parcela = 0.6;
        }

        if (ZETA >= 1 && DELTA >= 1) {
            parcela = 0.8;
        }

        if (ALFA == 1) {
            parcela = 1.0;
        }

        if (ZETA >= 1 && ALFA >= 1) {
            parcela = 1.2;
        }

        if (DELTA >= 1 && ALFA >= 1) {
            parcela = 1.2;
        }

        if (ALFA >= 2) {
            parcela = 1.3;
        }

        return parcela;
    }


}
