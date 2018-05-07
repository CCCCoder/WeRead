package com.n1njac.weread.utils;
/*    
 *    Created by N1njaC on 2018/5/7.
 *    email:aiai173cc@gmail.com 
 */

import android.os.SystemClock;

public class TimeUtils {

    public static long getCurrentSeconds() {
        return System.currentTimeMillis() / 1000;
    }
}
