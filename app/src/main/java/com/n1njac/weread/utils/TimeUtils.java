package com.n1njac.weread.utils;
/*    
 *    Created by N1njaC on 2018/5/7.
 *    email:aiai173cc@gmail.com 
 */

import android.os.SystemClock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static long getCurrentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getCurrentData(String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date());
    }
}
