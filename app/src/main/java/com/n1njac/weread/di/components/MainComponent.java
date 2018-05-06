package com.n1njac.weread.di.components;

import com.n1njac.weread.di.modules.MainModule;
import com.n1njac.weread.di.modules.NetModule;
import com.n1njac.weread.di.scopes.UserScope;
import com.n1njac.weread.view.activity.MainActivity;

import dagger.Component;

/*
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com 
 */
@UserScope
@Component(modules = MainModule.class, dependencies = NetComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
