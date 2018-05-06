package com.n1njac.weread.utils;
/*    
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com 
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

public class EntityUtils {
    private EntityUtils() {
    }

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
            .create();
}
