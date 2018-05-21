package com.n1njac.weread.utils;
/*
 *    Created by N1njaC on 2018/5/7.
 *    email:aiai173cc@gmail.com
 */

import android.annotation.SuppressLint;
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


    //hh:mm:ss or mm:ss
    @SuppressLint("DefaultLocale")
    public static String parseDurationTime(int time) {
        time /= 1000;
        int minute = time / 60;
        int hour = minute / 60;
        int second = time % 60;
        minute %= 60;
        if (hour != 0)
            return String.format("%02d:%02d:%02d", hour, minute, second);
        else
            return String.format("%02d:%02d", minute, second);
    }

}
