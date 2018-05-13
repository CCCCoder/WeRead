package com.n1njac.weread.app;
/*    
 *    Created by N1njaC on 2018/5/2.
 *    email:aiai173cc@gmail.com 
 */

import android.app.Application;
import android.content.Context;

import com.n1njac.weread.R;
import com.n1njac.weread.di.components.DaggerNetComponent;
import com.n1njac.weread.di.components.NetComponent;
import com.n1njac.weread.di.modules.NetModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import net.danlew.android.joda.JodaTimeAndroid;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class WeReadApplication extends Application {

    private NetComponent mNetComponent;

    public static WeReadApplication get(Context context){
        return (WeReadApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initTypeface();
        initLogger();
//        initTimer();
        initNetComponent();
    }

    private void initNetComponent() {
        mNetComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    private void initTimer() {
        JodaTimeAndroid.init(this);
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    //初始化字体
    private void initTypeface() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
