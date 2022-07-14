package com.luandkg.czilda4.dropbox;

import android.content.Context;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.users.FullAccount;
import com.luandkg.czilda4.Local;
import com.luandkg.czilda4.utils.Redizz;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.utils.Texto;
import com.luandkg.czilda4.utils.profile.KhronosDebug;
import com.luandkg.czilda4.utils.tempo.Calendario;

import java.io.FileInputStream;
import java.io.InputStream;

public class Dropbox {


    public static void iniciar(Context eContexto) {

        String DROPBOX_KEY = "n6dy38y0rfdmlef";

        Auth.startOAuth2Authentication(eContexto, DROPBOX_KEY);

    }

    public static String getTokenDeAcesso() {
        return Auth.getOAuth2Token();
    }

    public static void guardarToken() {
        String token = Dropbox.getTokenDeAcesso();
        if (token != null) {
            Redizz.guardar("DROPBOXDATA", Calendario.getTempoCompleto());
            Redizz.guardar("DROPBOXCHAVE", token);
        }
    }


    public static void listar(DbxClientV2 client) {

        try {
            System.out.println("DROPBOX LISTAR -->> COMECAR");

            ListFolderResult result = client.files().listFolder("");
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    System.out.println(metadata.getPathLower());
                }

                if (!result.getHasMore()) {
                    break;
                }

                result = client.files().listFolderContinue(result.getCursor());
            }

            System.out.println("DROPBOX LISTAR -->> TERMINAR");

        } catch (Exception e) {
            System.out.println("DROPBOX ERROR :: " + e.toString());
        }
    }


    public static void realizar_upload_sobrescrevendo(String origem, String destino) {

        DropboxTarefa DropboxTarefaCorrente = new DropboxTarefa() {
            @Override
            public String doInBackground(String... argumentos) {

                String arg_origem = argumentos[0];
                String arg_destino = argumentos[1];

                String meu_token = Redizz.obter("DROPBOXCHAVE");

                KhronosDebug.TIME_DEBUG("Dropxxxxx.....");
                KhronosDebug.TIME_DEBUG("Token : " + meu_token);

                DbxClientV2 dropbox_cliente = new DbxClientV2(DbxRequestConfig.newBuilder("dropbox/java").build(), meu_token);

                try {

                    FullAccount account = dropbox_cliente.users().getCurrentAccount();
                    KhronosDebug.TIME_DEBUG("DROPBOX ACCOUNT :: " + account.getName().getDisplayName());

                    KhronosDebug.TIME_DEBUG("DROPBOX UPLOADED OVERWRITE -->> COMECAR");

                    InputStream arquivo_origem_dados = new FileInputStream(FS.getArquivoLocal(arg_origem));

                    KhronosDebug.TIME_DEBUG("DROPBOX METADATA :: " + FS.getArquivoLocal(arg_origem));

                    FileMetadata metadata = dropbox_cliente.files().uploadBuilder("/" + arg_destino).withMode(WriteMode.OVERWRITE).uploadAndFinish(arquivo_origem_dados);

                    KhronosDebug.TIME_DEBUG("DROPBOX METADATA :: " + metadata.getName());

                    arquivo_origem_dados.close();

                    KhronosDebug.TIME_DEBUG("DROPBOX UPLOADED OVERWRITE :: " + arg_destino);

                } catch (Exception e) {
                    KhronosDebug.TIME_DEBUG("DROPBOX ERROR :: " + e.toString());
                }

                KhronosDebug.SALVAR();

                return "OK";
            }
        };

        // AUTO EXECUTAR
        DropboxTarefaCorrente.execute(Texto.toArray(origem, destino));


    }

}
