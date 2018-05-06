package com.n1njac.weread.di.components;
/*    
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com 
 */

import com.n1njac.weread.di.modules.NetModule;
import com.n1njac.weread.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {

    ApiService getApiService();

    OkHttpClient getOkHttpClient();

    Retrofit getRetrofit();
}
