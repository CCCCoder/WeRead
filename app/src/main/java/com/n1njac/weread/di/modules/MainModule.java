package com.n1njac.weread.di.modules;

import com.n1njac.weread.presenter.MainContract;
import com.n1njac.weread.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/*
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com 
 */
@Module
public class MainModule {
    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.View provideMainView()   {
        return view;
    }
}
