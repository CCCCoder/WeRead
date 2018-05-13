package com.n1njac.weread.di.modules;

import com.n1njac.weread.presenter.DetailContract;

import dagger.Module;
import dagger.Provides;

/*
 *    Created by N1njaC on 2018/5/13.
 *    email:aiai173cc@gmail.com
 */
@Module
public class DetailModule {
    private DetailContract.View mView;

    public DetailModule(DetailContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public DetailContract.View provideView() {
        return mView;
    }
}
