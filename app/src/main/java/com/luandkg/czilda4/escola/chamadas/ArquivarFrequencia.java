package com.luandkg.czilda4.escola.chamadas;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.alunos.Aluno;
import com.luandkg.czilda4.escola.alunos.AlunoChamadas;
import com.luandkg.czilda4.escola.organizacao.AulaTurmaDia;
import com.luandkg.czilda4.escola.organizacao.Professor;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;

import java.io.File;
import java.util.ArrayList;

public class ArquivarFrequencia {


    public static ArrayList<TurmaChamadas> carregar(Professor eProfessor) {

          ArrayList<TurmaChamadas> turmas= new ArrayList<TurmaChamadas>();

        for (String turma : eProfessor.getQuaisTurmas()) {
            TurmaChamadas tc = new TurmaChamadas(turma);

            for (AulaTurmaDia dia : eProfessor.getDiaComAulaNessaTurma(turma)) {
                tc.marcarDia(dia);
            }


            for (Aluno eAluno : Escola.getAlunos(turma)) {
                if (eAluno.getVisibilidade().contentEquals("SIM")) {
                    tc.registrar(eAluno.getID(), eAluno.getNome());
                }
            }

            turmas.add(tc);
        }

        CED1_Calendario calendario = new CED1_Calendario();
        ArrayList<Data> BIMESTRE = calendario.getSegundo();
        ArrayList<Data> ate = calendario.getSegundo();

        int pos = Calendario.getIndice(BIMESTRE, Calendario.getData());

        if (pos >= 0) {
            ate = Calendario.filtrar_ate(BIMESTRE, pos);
        }

        for (Data data : ate) {

            for (TurmaChamadas proc : turmas) {

                if (proc.temDia(data.toDia().toString())) {

                    AulaTurmaDia qual_dia = proc.getDia(data.toDia());

                    String arquivo_nome = data.getTempo() + ".dkg";

                    String eArquivoCaminho = FS.getArquivoLocal(Local.LOCAL_REALIZAR_CHAMADA + "/" + arquivo_nome);
                    if (new File(eArquivoCaminho).exists()) {

                        DKG eDocumento = new DKG();
                        eDocumento.abrir(eArquivoCaminho);

                        DKGObjeto chamada_dia = eDocumento.unicoObjeto("Chamada");


                        for (AlunoChamadas aluno : proc.getAlunos()) {
                            String valor = "AUSENTE";

                            for (DKGObjeto aluno_chamada : chamada_dia.getObjetos()) {
                                String aluno_chamada_id = aluno_chamada.identifique("ID").getValor();
                                String aluno_chamada_status = aluno_chamada.identifique("Status").getValor();

                                if (aluno_chamada_id.contentEquals(aluno.getID())) {
                                    valor = aluno_chamada_status;
                                    break;
                                }

                            }

                            if (qual_dia.getQuantidade() == 1) {
                                aluno.marcar(data.getTitulo(), valor);
                            } else if (qual_dia.getQuantidade() == 2) {
                                aluno.marcar(data.getTitulo(), valor);
                                aluno.marcar(data.getTitulo(), valor);
                            }
                        }


                    } else {

                        //for (AlunoChamadas aluno : proc.getAlunos()) {
                        // String valor = "PRESENTE";
                        //  aluno.marcar(data.getTempoLegivel(), valor);
                        //}


                    }


                }


            }

        }


        return turmas;
    }


    public static void arquivar(ArrayList<TurmaChamadas> tudo, String arquivo) {

        DKG documento = new DKG();

        DKGObjeto mEscola = documento.unicoObjeto("ESCOLA");

        for (TurmaChamadas turma : tudo) {

            DKGObjeto mturma = mEscola.criarObjeto("Turma");
            mturma.identifique("Nome", turma.getTurma());

            for (AlunoChamadas aluno : turma.getAlunos()) {
                DKGObjeto maluno = mturma.criarObjeto("Aluno");
                maluno.identifique("Nome", aluno.getNome());

                for (DataChamada data : aluno.getDatas()) {

                    DKGObjeto mdata = maluno.criarObjeto("Data");
                    mdata.identifique("Data", data.getData());
                    mdata.identifique("Status", data.getStatus());


                }
            }


        }


        documento.salvar(arquivo);


    }

}
