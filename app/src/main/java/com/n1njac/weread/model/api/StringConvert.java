package com.n1njac.weread.model.api;
/*    
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com 
 */

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class StringConvert implements Converter<ResponseBody,String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
