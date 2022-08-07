package com.luandkg.czilda4.escola;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoComNota;
import com.luandkg.czilda4.escola.alunos.OrdenarAlunos;
import com.luandkg.czilda4.escola.avaliacao.Atividade;
import com.luandkg.czilda4.escola.avaliacao.MoverAtividade;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanasDeAtividades;
import com.luandkg.czilda4.escola.chamadas.ArquivarFrequencia;
import com.luandkg.czilda4.escola.chamadas.CarregadorDeFrequencia;
import com.luandkg.czilda4.escola.chamadas.Frequenciometro;
import com.luandkg.czilda4.escola.chamadas.TurmaChamadas;
import com.luandkg.czilda4.escola.professores.Luan;
import com.luandkg.czilda4.escola.tempo.Bimestre;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.utils.FS;

import java.util.ArrayList;

public class PontoExtra2Para3 {

    public static String TEMP_SEGUNDO = "@ESCOLA->CACHE::TEMP2";
    public static String ATIVIDADE_DESTINO = Local.LOCAL_AVALIANDO + "/2022_07_29.dkg";
    public static String ATIVIDADE_DATA = "2022_07_29";


    public static void mostrar_ponto_extra(String eAtividade) {

        System.out.println("============================================== CALCULAR PONTO EXTRA");

        // CALCULAR NOTAS

        CED1_Calendario cal = new CED1_Calendario();

        Bimestre SEGUNDO = cal.SEGUNDO_BIMESTRE();


        ArrayList<Aluno> alunos_visiveis = Escola.getAlunosVisiveisEOrdenadosDaEscola();
        ArrayList<AlunoContinuo> alunos_visiveis_continuos = Escola.getAlunosContinuosVisiveisEOrdenadosDaEscola();


        Frequenciometro.contarFrequencia(alunos_visiveis, SEGUNDO.getDatas());
        ArrayList<TurmaChamadas> CHAMADAS_ESCOLA = CarregadorDeFrequencia.carregar(Luan.getLuan(), cal.SEGUNDO_BIMESTRE());
        ArquivarFrequencia.arquivar(CHAMADAS_ESCOLA, TEMP_SEGUNDO);
        Frequenciometro.unirChamadas();

        MetodoContinuo.montar(alunos_visiveis, SEGUNDO.getDatas(), TEMP_SEGUNDO, Local.COLECAO_FLUXO);
        SemanasDeAtividades.organizar(SEGUNDO.getSemanas(), TEMP_SEGUNDO, Local.COLECAO_FLUXO);
        MetodoContinuo.avaliar(TEMP_SEGUNDO, alunos_visiveis_continuos, SEGUNDO.getSemanas());


        // MIGRAR PONTOS


        System.out.println("Adicionando ponto extra :: ");


        System.out.println("============================================== RECUPERACAO MIGRAR PONTO EXTRA");


        String mArquivoRecuperacao = Local.LOCAL_NOTAS + "/notas_RECUPERACAO_02.dkg";
        final String RECUPERACAO = "RECUPERACAO";

        ArrayList<AlunoComNota> rAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));


        Atividade.organizarNota(FS.getArquivoLocal(mArquivoRecuperacao), RECUPERACAO, rAlunos);


        ArrayList<AlunoComNota> mAlunos = OrdenarAlunos.ordendarComNotas(Escola.filtarVisiveis(Escola.carregarAlunosComNota()));

        DKG documento_de_migracao = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(TEMP_SEGUNDO);

        for (DKGObjeto aluno_objeto : documento_de_migracao.unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA").getObjetos()) {

            String aluno_id = aluno_objeto.id_string("ID");

            double sem = aluno_objeto.identifique("NotaSemRecuperacao").getDouble(0.0);
            double com = aluno_objeto.identifique("NotaComRecuperacao").getDouble(0.0);

            System.out.println(" :: " + aluno_objeto.identifique("Nome").getValor() + " -->> " + sem);

            System.out.println(aluno_objeto.toString());


            if (sem >= 5) {

                for (AlunoComNota aluno_rec : rAlunos) {
                    if (aluno_rec.getID().contentEquals(aluno_id)) {


                        String rec_valor = aluno_rec.getNota(RECUPERACAO);

                        for (AlunoComNota aluno_avaliar : mAlunos) {
                            if (aluno_avaliar.getID().contentEquals(aluno_objeto.identifique("ID").getValor())) {

                                aluno_avaliar.setNota("AVALIAR", rec_valor, ATIVIDADE_DATA);
                                break;
                            }
                        }

                        break;
                    }
                }

            }
        }


        Atividade.salvarNota("AVALIAR", mAlunos, FS.getArquivoLocal(eAtividade));


        System.out.println("Mostrando ponto extra :: ");

        DKG atv = new DKG();
        atv.abrir(FS.getArquivoLocal(eAtividade));

        System.out.println(atv.toString());


    }



}
