package com.luandkg.czilda4.dropbox;

import android.os.AsyncTask;

public abstract  class DropboxTarefa extends AsyncTask<String, Void, String> {

    public String doInBackground(String... argumentos) {
        return "OK";
    }

    protected void onPostExecute(String feed) {

    }

}
