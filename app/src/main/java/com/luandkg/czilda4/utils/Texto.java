package com.luandkg.czilda4.utils;

import com.luandkg.czilda4.Local;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Texto {

    public static void escrever(String eArquivo, String eConteudo) {

        BufferedWriter writer = null;
        try {
            File logFile = new File(eArquivo);

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(eConteudo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }

    public static String ler(String eArquivo) {

        String ret = "";

        try {
            FileReader arq = new FileReader(eArquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            ret += linha;

            while (linha != null) {

                linha = lerArq.readLine();
                if (linha != null) {
                    ret += "\n" + linha;
                }

            }

            arq.close();
        } catch (IOException e) {

        }

        return ret;
    }

    public static String anexar(String eConteudo, String eLinha) {

        if (eConteudo.contentEquals("")) {
            return eLinha;

        } else {
            return eConteudo + "\n" + eLinha;

        }
    }

    public static boolean is_igual(String a, String b) {
        return a.contentEquals(b);
    }

    public static String doubleNumC2(double numero) {
        String s = String.valueOf(numero);
        String f = "";

        int e = 1;
        int c = 2;

        boolean ja = false;

        int i = 0;
        int o = s.length();

        while (i < o) {
            String letra = String.valueOf(s.charAt(i));

            if (ja) {
                if (e < c) {
                    f += letra;
                }
                e += 1;
            } else {
                if (letra.contentEquals(".")) {
                    ja = true;
                    f += letra;
                } else {
                    f += letra;
                }
            }


            i += 1;
        }

        if (!ja) {
            f = f + ".0";
        }

        return f;
    }

    public static String doubleNumC2(String numero) {
        String s = numero;
        String f = "";

        int e = 1;
        int c = 2;

        boolean ja = false;

        int i = 0;
        int o = s.length();

        while (i < o) {
            String letra = String.valueOf(s.charAt(i));

            if (ja) {
                if (e < c) {
                    f += letra;
                }
                e += 1;
            } else {
                if (letra.contentEquals(".")) {
                    ja = true;
                    f += letra;
                } else {
                    f += letra;
                }
            }


            i += 1;
        }

        if (!ja) {
            f = f + ".0";
        }

        return f;
    }


    public static int contagem(String entrada, String proc) {
        int contando = 0;
        int index = 0;
        int maximo = entrada.length();
        while (index < maximo) {
            String letra = String.valueOf(entrada.charAt(index));
            if (letra.contentEquals(proc)) {
                contando += 1;
            }
            index += 1;
        }
        return contando;
    }


    public static String[] toArray(String a, String b) {
        String valores[] = {a, b};
        return valores;
    }
}