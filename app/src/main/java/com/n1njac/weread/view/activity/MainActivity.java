package com.n1njac.weread.view.activity;
/*    
 *    Created by N1njaC on 2018/5/2.
 *    email:aiai173cc@gmail.com 
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.n1njac.weread.R;
import com.n1njac.weread.model.entity.DetailEntity;
import com.n1njac.weread.presenter.MainContract;
import com.n1njac.weread.view.widget.VerticalViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.main_vvp)
    VerticalViewPager mainVvp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showNoMore() {

    }

    @Override
    public void showOnFailure() {

    }

    @Override
    public void showLunar() {

    }

    @Override
    public void refreshMainList(List<DetailEntity> items) {

    }
}
