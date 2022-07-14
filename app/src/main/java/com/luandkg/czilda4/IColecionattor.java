package com.luandkg.czilda4;

import com.luandkg.czilda4.dkg.DKG;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.Texto;

import java.io.File;

public class IColecionattor {

    public static String organizar(String caminho) {

        caminho = caminho.replace(" ", "");
        caminho = caminho.replace("\t", "");

        // @ESCOLA::ALUNOS
        // @ESCOLA->CACHE::NOTAS

        String DOMINIO = "";
        String PASTA = "";
        String ARQUIVO = "";

        int i = 0;
        int o = caminho.length();

        int e = 0;

        while (i < o) {
            String letra = String.valueOf(caminho.charAt(i));
            if (letra.contentEquals("@")) {
                i += 1;
                e = 1;
                break;
            }
            i += 1;
        }

        if (e == 1) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals("-")) {
                    i += 1;
                    e = 2;
                    break;
                } else if (letra.contentEquals(":")) {
                    i += 1;
                    e = 3;
                    break;
                } else {
                    DOMINIO += letra;
                }
                i += 1;
            }
        }

        if (e == 2) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(">")) {
                    i += 1;
                    e = 4;
                    break;
                }
                i += 1;
            }
        }

        if (e == 4) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(":")) {
                    i += 1;
                    e = 3;
                    break;
                } else {
                    PASTA += letra;
                }
                i += 1;
            }
        }

        if (e == 3) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(":")) {
                    i += 1;
                    e = 5;
                    break;
                }
                i += 1;
            }
        }

        if (e == 5) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(":")) {
                    i += 1;
                    e = 6;
                    break;
                } else {
                    ARQUIVO += letra;
                }
                i += 1;
            }
        }


        System.out.println("PATH :: " + caminho);

        System.out.println("\t - DOMINIO :: " + DOMINIO);

        if (PASTA.length() > 0) {
            System.out.println("\t - PASTA :: " + PASTA);
        }

        System.out.println("\t - ARQUIVO :: " + ARQUIVO);

        String transformado = "";

        if (PASTA.length() == 0) {
            transformado = toCapital(DOMINIO) + "/" + toAbaixo(ARQUIVO) + ".dkg";
        } else {
            transformado = toCapital(DOMINIO) + "/" + toCapital(PASTA) + "/" + toAbaixo(ARQUIVO) + ".dkg";
        }


        System.out.println("\t - SAIDA :: " + transformado);
        return transformado;
    }

    public static String toCapital(String entrada) {

        int i = 0;
        int o = entrada.length();

        String saida = "";


        while (i < o) {
            String letra = String.valueOf(entrada.charAt(i));

            if (i == 0) {
                saida += letra.toUpperCase();
            } else {
                saida += letra.toLowerCase();
            }

            i += 1;
        }

        return saida;
    }

    public static String toAbaixo(String entrada) {
        return entrada.toLowerCase();
    }

    public static DKG REQUIRED_COLLECTION(String caminho) {
        return DKG.GET(FS.getArquivoLocal(organizar(caminho)));
    }

    public static boolean EXISTS_COLLECTION(String caminho) {

        String eArquivoLocal = FS.getArquivoLocal(organizar(caminho));

        File eArquivo = new File(eArquivoLocal);
        return eArquivo.exists();
    }

    public static void BUILD_COLLECTION(String caminho) {

        String eArquivoLocal = FS.getArquivoLocal(organizar(caminho));

        DKG doc = new DKG();
        doc.salvar(eArquivoLocal);

    }
}
