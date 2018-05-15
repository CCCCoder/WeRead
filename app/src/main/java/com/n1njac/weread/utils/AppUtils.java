package com.n1njac.weread.utils;
/*
 *    Created by N1njaC on 2018/5/8.
 *    email:aiai173cc@gmail.com
 */

import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

public class AppUtils {
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        Logger.d("deviceId:" + deviceId);
        return deviceId;
    }

    public static int dp2px(Context paramContext, float paramFloat) {
        float scale = paramContext.getResources().getDisplayMetrics().density;
        return (int) (0.5F + paramFloat * scale);
    }

    public static int getWindowWidth(Context paramContext) {
        return getWindowManager(paramContext).getDefaultDisplay().getWidth();
    }

    public static WindowManager getWindowManager(Context paramContext) {
        return (WindowManager) paramContext.getSystemService(Context.WINDOW_SERVICE);
    }
}
