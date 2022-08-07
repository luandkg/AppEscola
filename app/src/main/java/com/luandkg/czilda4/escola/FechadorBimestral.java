package com.luandkg.czilda4.escola;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.dropbox.Dropbox;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.atualizador.Atualizador;
import com.luandkg.czilda4.escola.avaliacao.MoverAtividade;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.FluxoFormativoContinuado;
import com.luandkg.czilda4.escola.avaliacao_continua.Perfilometro;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanasDeAtividades;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.avaliacao_continua.SuperCache;
import com.luandkg.czilda4.escola.chamadas.ArquivarFrequencia;
import com.luandkg.czilda4.escola.chamadas.CarregadorDeFrequencia;
import com.luandkg.czilda4.escola.chamadas.Frequenciometro;
import com.luandkg.czilda4.escola.chamadas.TurmaChamadas;
import com.luandkg.czilda4.escola.desempenhador.DesempenhoIO;
import com.luandkg.czilda4.escola.organizacao.TurmaTipo;
import com.luandkg.czilda4.escola.professores.Luan;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.escola.utils.CicloDeProgresso;
import com.luandkg.czilda4.escola.utils.HiperCacheDeAvaliacao;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.ImagemCriador;
import com.luandkg.czilda4.utils.Redizz;
import com.luandkg.czilda4.libs.profile.KhronosProfiler;
import com.luandkg.czilda4.libs.profile.ProfileStamp;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.utils.Threader;

import java.util.ArrayList;

public class FechadorBimestral implements Runnable {

    private TextView TX_EXECUTANDO;

    private ImageView IV_VISUALIZADOR;
    private ImageView FLUXO_VISUALIZADOR;

    private Context mContexto;
    private Bimestre mBimestre;

    public FechadorBimestral(Context eContext, Bimestre eBimestre, TextView eTX_EXECUTANDO, ImageView eIV_VISUALIZADOR, ImageView eFLUXO_VISUALIZADOR) {
        mContexto = eContext;
        TX_EXECUTANDO = eTX_EXECUTANDO;
        IV_VISUALIZADOR = eIV_VISUALIZADOR;
        FLUXO_VISUALIZADOR = eFLUXO_VISUALIZADOR;
        mBimestre = eBimestre;
    }


    public void run() {

        ProfileStamp PXX_Run = KhronosProfiler.profile_started("FechadorBimestral.run()");

        System.out.println("-->> Comecar...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(0, 100));
        Threader.atualizar_imagem(FLUXO_VISUALIZADOR, ImagemCriador.vazio());


        HiperCacheDeAvaliacao.zerar();


        // LEVAR PONTO EXTRA DO 2 -> 3
        //  PontoExtra2Para3.mostrar_ponto_extra(Local.LOCAL_AVALIANDO + "/2022_07_29.dkg");


        HiperCacheDeAvaliacao.zerar();


        Threader.atualizar_texto(TX_EXECUTANDO, "Organizando fechamento de bimestre ...");


        ArrayList<Data> SEGUNDO_BIMESTRE = mBimestre.getDatas();
        ArrayList<SemanaContinua> SEGUNDO_SEMANAS = mBimestre.getSemanas();

        Threader.atualizar_texto(TX_EXECUTANDO, "Obtendo alunos...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(5, 100));

        ProfileStamp PXX_obtendo_alunos = KhronosProfiler.profile_started("FechadorBimestral.run().ObtendoAlunos()");

        ArrayList<Data> bimestre_datas = mBimestre.getDatas();

        ArrayList<Aluno> alunos_visiveis = Escola.getAlunosVisiveisEOrdenadosDaEscola();
        ArrayList<AlunoContinuo> alunos_visiveis_continuos = Escola.getAlunosContinuosVisiveisEOrdenadosDaEscola();

        PXX_obtendo_alunos.terminar();


        int alunos_total = alunos_visiveis.size();

        Threader.atualizar_texto(TX_EXECUTANDO, "Organizando chamadas...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(10, 100));
        Frequenciometro.contarFrequencia(alunos_visiveis, bimestre_datas);


        Threader.atualizar_texto(TX_EXECUTANDO, "Arquivando chamadas...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(20, 100));
        ArrayList<TurmaChamadas> CHAMADAS_ESCOLA = CarregadorDeFrequencia.carregar(Luan.getLuan(), mBimestre);

        ProfileStamp PXX_arquivando = KhronosProfiler.profile_started("FechadorBimestral.run().Arquivando()");

        Threader.atualizar_texto(TX_EXECUTANDO, "Arquivando chamadas...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(30, 100));
        ArquivarFrequencia.arquivar(CHAMADAS_ESCOLA, Local.COLECAO_TUDO);


        PXX_arquivando.terminar();


        ProfileStamp PXX_unindo = KhronosProfiler.profile_started("FechadorBimestral.run().UnindoChamadas()");

        Threader.atualizar_texto(TX_EXECUTANDO, "Montando arquivo chamadas...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(40, 100));

        Frequenciometro.unirChamadas();

        PXX_unindo.terminar();

        ProfileStamp PXX_Montando = KhronosProfiler.profile_started("FechadorBimestral.run().Montando()");

        Threader.atualizar_texto(TX_EXECUTANDO, "Montando avaliação...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(50, 100));
        MetodoContinuo.montar(alunos_visiveis, SEGUNDO_BIMESTRE, Local.COLECAO_NOTAS, Local.COLECAO_FLUXO);

        PXX_Montando.terminar();

        Threader.atualizar_texto(TX_EXECUTANDO, "Organizando semanas contínuas...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(55, 100));
        SemanasDeAtividades.organizar(mBimestre.getSemanas(), Local.COLECAO_NOTAS, Local.COLECAO_FLUXO);


        Threader.atualizar_texto(TX_EXECUTANDO, "Organizando atividades contínuas...");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(60, 100));
        SemanasDeAtividades.guardar(alunos_visiveis, SEGUNDO_SEMANAS, Local.COLECAO_SEMANAS);

        ProfileStamp PXX_Avaliando = KhronosProfiler.profile_started("FechadorBimestral.run().Avaliando()");

        Threader.atualizar_texto(TX_EXECUTANDO, "Avaliando relatórios....");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(65, 100));
        MetodoContinuo.avaliar(Local.COLECAO_NOTAS, alunos_visiveis_continuos, mBimestre.getSemanas());

        PXX_Avaliando.terminar();

        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(70, 100));

        SuperCache eSuperCache = new SuperCache(Local.COLECAO_NOTAS);

        for (TurmaTipo turma : Professores.getProfessorCorrente().getListaDeTurmas()) {

            int quantidade = eSuperCache.contagem(alunos_visiveis_continuos, turma.getTurma());

            Atualizador.mudarQuantidadeAvaliacao(turma.getTurma(), quantidade);
        }

        // DESEMPENHOS

        int MAIS = mBimestre.getSemanas().size();
        int AGORA = 99 - (2 * MAIS);
        int avancando = AGORA;

        Threader.atualizar_texto(TX_EXECUTANDO, "Desempenhos semanais....");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(AGORA, 100));

        DesempenhoIO.limpar(Local.COLECAO_DESEMPENHOS);

        ProfileStamp PXX_Semanas = KhronosProfiler.profile_started("FechadorBimestral.run().Semanas()");
        KhronosProfiler.entrar();

        ArrayList<AlunoContinuo> alunos_continuos_semana = Escola.getAlunosContinuosVisiveisEOrdenadosDaEscola();

        for (SemanaContinua semana : mBimestre.getSemanas()) {

            ProfileStamp PXX_SemanaItem = KhronosProfiler.profile_started("FechadorBimestral.run().Semana().SemanaCorrente()");
            KhronosProfiler.entrar();

            Threader.atualizar_texto(TX_EXECUTANDO, "Montando semana :: " + semana.getUltimaData());
            Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(avancando, 100));

            for (AlunoContinuo aluno : alunos_continuos_semana) {
                aluno.limpar();
            }

            ///   ArrayList<AlunoContinuo> alunos_continuos_semana = Escola.getAlunosContinuosVisiveisEOrdenadosDaEscola();

            ProfileStamp PXX_Organizando = KhronosProfiler.profile_started("FechadorBimestral.run().Semana().SemanaCorrente().Organizando()");

            MetodoContinuo.montar(alunos_visiveis, SEGUNDO_BIMESTRE, Local.COLECAO_DESEMPENHANDO, Local.COLECAO_FLUXO);
            SemanasDeAtividades.organizar(mBimestre.getSemanas(), Local.COLECAO_DESEMPENHANDO, Local.COLECAO_FLUXO);
            avancando += 1;

            PXX_Organizando.terminar();


            Threader.atualizar_texto(TX_EXECUTANDO, "Avaliando semana :: " + semana.getUltimaData());
            Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(avancando, 100));

            ProfileStamp PXX_Avaliando_ate = KhronosProfiler.profile_started("FechadorBimestral.run().Semana().SemanaCorrente().AvaliandoAte()");

            MetodoContinuo.avaliar_ate(Local.COLECAO_DESEMPENHANDO, alunos_continuos_semana, mBimestre.getSemanas(), semana.getDatas().get(semana.getDatas().size() - 1));

            //  MetodoContinuo.avaliar_ate(Local.LOCAL_CACHE + "/" + Local.ARQUIVO_NOTAS, alunos_continuos_semana, mBimestre.getSemanas(), semana.getDatas().get(semana.getDatas().size() - 1));

            PXX_Avaliando_ate.terminar();


            HiperCacheDeAvaliacao.zerar();
            ArrayList<AlunoContinuo> perfis_semanais = Perfilometro.getPerfis(HiperCacheDeAvaliacao.getPerfis(), mBimestre.getSemanas());

            DesempenhoIO.guardar(Local.COLECAO_DESEMPENHOS, semana.getUltimaData(), perfis_semanais);

            Bitmap fluxo_ate = FluxoFormativoContinuado.criarFluxoDeEntrega_ate(alunos_total, mBimestre, semana.getUltimaData(), Local.COLECAO_FLUXO);
            Threader.atualizar_imagem(FLUXO_VISUALIZADOR, fluxo_ate);

            avancando += 1;
            //  alunos_continuos_semana.clear();

            PXX_SemanaItem.terminar();
            KhronosProfiler.sair();

        }

        PXX_Semanas.terminar();
        KhronosProfiler.sair();


        ProfileStamp PXX_Tudo = KhronosProfiler.profile_started("FechadorBimestral.Tudo()");

        ArquivadorEscolaCompleta.salvar( SigmaCollection.getARQUIVO(Local.COLECAO_TUDO));


        PXX_Tudo.terminar();


        Threader.atualizar_texto(TX_EXECUTANDO, "Tudo Pronto !");
        Threader.atualizar_imagem(IV_VISUALIZADOR, CicloDeProgresso.criarProgresso(100, 100));


        Bitmap imagem = FluxoFormativoContinuado.criarFluxoDeEntrega(alunos_total, mBimestre, Local.COLECAO_FLUXO);
        Threader.atualizar_imagem(FLUXO_VISUALIZADOR, imagem);

        HiperCacheDeAvaliacao.zerar();


        System.out.println("-->> Terminar ...");


        PXX_Run.terminar();

        KhronosProfiler.salvar(FS.getArquivoLocal(Local.CACHE_ARQUIVO("fechador_bimestral.khronos")));


        if (Redizz.obter("DROPBOXCHAVE").length() > 0) {

            ProfileStamp PXX_Sincronizar = KhronosProfiler.profile_started("FechadorBimestral.sincronizar()");

            Dropbox.realizar_upload_sobrescrevendo(SigmaCollection.organizar(Local.COLECAO_AVISOS), "CED_01/avisos.dkg");
            Dropbox.realizar_upload_sobrescrevendo(SigmaCollection.organizar(Local.COLECAO_TUDO), "CED_01/tudo.dkg");


            Dropbox.realizar_upload_sobrescrevendo(Local.CACHE_ARQUIVO("fechador_bimestral.khronos"), "CED_01/fechador_bimestral.dkg");

            PXX_Sincronizar.terminar();

        }


        KhronosProfiler.salvar(FS.getArquivoLocal(Local.CACHE_ARQUIVO("fechador_bimestral.khronos")));

    }


}
