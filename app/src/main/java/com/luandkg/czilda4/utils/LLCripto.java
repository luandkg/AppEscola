package com.luandkg.czilda4.utils;

public class LLCripto {

    public final static String AUTOR = "LUAN ALVES FREITAS";
    public final static String IMPLEMENTADO = "13/07/2022";

    private final static String ORDEM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKMLNOPQRSTUVWXYZ_-0123456789,.<>:-+[]<>{}/|\\";
    private final static String DESORDEM = "<KMLWXYwxyzT}/|rsBCD789,.<>:-+[NOZ_-0>{62345tuvGHIAUPQRSabcdefghijklm\\qnopJ1EFV]";

    private static int get(String entrada, String ordem) {

        int i = 0;
        int o = ordem.length();
        boolean encontrado = false;

        while (i < o) {
            String letra = String.valueOf(ordem.charAt(i));
            if (letra.contentEquals(entrada)) {
                encontrado = true;
                break;
            }
            i += 1;
        }

        if (!encontrado) {
            i = -1;
        }

        return i;
    }

    public static String to(String entrada) {


        String montando = "";

        int i = 0;
        int o = entrada.length();
        while (i < o) {
            String letra = String.valueOf(entrada.charAt(i));

            int chave = get(letra, ORDEM);

            String novo = letra;

            if (chave >= 0) {
                novo = String.valueOf(DESORDEM.charAt(chave));
            }

            montando += novo;

            i += 1;
        }

        return montando;
    }

    public static String from(String entrada) {

        String montando = "";

        int i = 0;
        int o = entrada.length();
        while (i < o) {
            String letra = String.valueOf(entrada.charAt(i));

            int chave = get(letra, DESORDEM);

            String novo = letra;

            if (chave >= 0) {
                novo = String.valueOf(ORDEM.charAt(chave));
            }

            montando += novo;

            i += 1;
        }

        return montando;
    }

}
