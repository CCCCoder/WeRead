package com.n1njac.weread.utils;
/*    
 *    Created by N1njaC on 2018/5/8.
 *    email:aiai173cc@gmail.com 
 */

import android.content.Context;
import android.telephony.TelephonyManager;

public class AppUtils {
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
