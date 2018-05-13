package com.n1njac.weread.di.components;

import com.n1njac.weread.di.modules.DetailModule;
import com.n1njac.weread.di.scopes.UserScope;
import com.n1njac.weread.view.activity.DetailActivity;

import dagger.Component;

/*
 *    Created by N1njaC on 2018/5/13.
 *    email:aiai173cc@gmail.com
 */
@UserScope
@Component(modules = DetailModule.class, dependencies = NetComponent.class)
public interface DetailComponent {
    void inject(DetailActivity activity);
}
