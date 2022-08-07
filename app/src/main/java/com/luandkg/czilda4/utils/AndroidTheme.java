package com.luandkg.czilda4.utils;

import android.content.Context;
import android.content.res.Configuration;

public class AndroidTheme {

    public static boolean isDark(Context eContext) {

        boolean ret = false;

        int nightModeFlags = eContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            ret = true;
        }

        return ret;
    }
}
