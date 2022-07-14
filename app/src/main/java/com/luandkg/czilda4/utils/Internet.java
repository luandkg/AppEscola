package com.luandkg.czilda4.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

public class Internet {



    public static void pedirPermissoes(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.INTERNET) !=
                PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{Manifest.permission.INTERNET}, 1000);

        }

    }

}
