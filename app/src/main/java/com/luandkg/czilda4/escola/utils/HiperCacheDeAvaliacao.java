package com.luandkg.czilda4.escola.utils;

import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.libs.dkg.DKG;
import com.luandkg.czilda4.libs.dkg.DKGObjeto;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.Escola;
import com.luandkg.czilda4.escola.avaliacao_continua.AlunoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.MetodoContinuo;
import com.luandkg.czilda4.escola.avaliacao_continua.Perfilometro;
import com.luandkg.czilda4.escola.avaliacao_continua.SemanaContinua;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;

import java.util.ArrayList;

public class HiperCacheDeAvaliacao {

    private static boolean isTurmaCarrega = false;
    private static String turmaCarregada = "";

    private static ArrayList<AlunoContinuo> alunos_continuos = null;

    private static boolean isDocumentoCarregado = false;
    private static DKG eDocumento = null;

    public static void zerar() {
        isDocumentoCarregado = false;
        isTurmaCarrega = false;
    }

    public static ArrayList<AlunoContinuo> get(String eTurma) {

        if (isTurmaCarrega && eTurma.contentEquals(turmaCarregada)) {

        } else {
            eDocumento = getDocumento();

            alunos_continuos = Escola.getAlunosContinuosVisiveisEOrdenadosDaTurma(eTurma);

            MetodoContinuo.avaliarComDocumento(eDocumento, alunos_continuos, BimestreCorrente.GET().getSemanas());

            System.out.println("-->> Construir cache do Metodo de Avaliacao :: Turma " + turmaCarregada);

            isTurmaCarrega = true;
            turmaCarregada = eTurma;
        }


        return alunos_continuos;

    }

    public static DKG getDocumento() {

        if (!isDocumentoCarregado) {

            System.out.println("-->> Construir cache do Metodo de Avaliacao");

            eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(Local.COLECAO_NOTAS);

            isDocumentoCarregado = true;

        }

        return eDocumento;
    }

    public static AlunoContinuo getPerfil(String aluno_id, ArrayList<SemanaContinua> semanas) {

        System.out.println("-->> Pegar do Cache :: " + aluno_id);

        AlunoContinuo perfil = Perfilometro.getPerfilDe(aluno_id, getDocumento().unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA").getObjetos(), semanas);

        return perfil;
    }

    public static ArrayList<DKGObjeto> getPerfis() {
        return getDocumento().unicoObjeto("AVALIACAO_CONTINUA_FORMATIVA").getObjetos();
    }
}
