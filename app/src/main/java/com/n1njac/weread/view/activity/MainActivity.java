package com.n1njac.weread.view.activity;
/*    
 *    Created by N1njaC on 2018/5/2.
 *    email:aiai173cc@gmail.com 
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.n1njac.weread.R;

import com.n1njac.weread.app.WeReadApplication;
import com.n1njac.weread.di.components.DaggerMainComponent;
import com.n1njac.weread.di.modules.MainModule;
import com.n1njac.weread.model.entity.DetailEntity;
import com.n1njac.weread.model.entity.Event;
import com.n1njac.weread.presenter.MainContract;
import com.n1njac.weread.presenter.MainPresenter;
import com.n1njac.weread.utils.RxBus;
import com.n1njac.weread.view.adapter.VerticalPagerAdapter;
import com.n1njac.weread.view.fragment.LeftMenuFragment;
import com.n1njac.weread.view.fragment.RightMenuFragment;
import com.n1njac.weread.view.widget.VerticalViewPager;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.main_vvp)
    VerticalViewPager mainVvp;
    private SlidingMenu mSlidingMenu;
    private Subscription mSubscription;
    private long mLastClickTime;
    private VerticalPagerAdapter mPagerAdapter;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMenu();
        initViewPager();
        Logger.d("onCreate");
    }

    private void initMenu() {
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.setFadeEnabled(true);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setMenu(R.layout.container_left_menu);
        getSupportFragmentManager().beginTransaction().add(R.id.left_menu_ll, new LeftMenuFragment()).commit();
        mSlidingMenu.setSecondaryMenu(R.layout.container_right_menu);
        getSupportFragmentManager().beginTransaction().add(R.id.right_menu_ll, new RightMenuFragment()).commit();
        mSubscription = RxBus.getInstance().toObservable(Event.class).subscribe(new Action1<Event>() {
            @Override
            public void call(Event event) {
                mSlidingMenu.showContent();
            }
        });
    }

    private void initViewPager() {
        mPagerAdapter = new VerticalPagerAdapter(getSupportFragmentManager());
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .netComponent(WeReadApplication.get(this).getNetComponent())
                .build()
                .inject(this);

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

    @OnClick({R.id.go_to_column_iv, R.id.go_to_person_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_to_column_iv:
                mSlidingMenu.showMenu();
                break;
            case R.id.go_to_person_iv:
                mSlidingMenu.showSecondaryMenu();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mSlidingMenu.isMenuShowing() || mSlidingMenu.isSecondaryMenuShowing()) {
            mSlidingMenu.showContent();
        } else {
            if (System.currentTimeMillis() - mLastClickTime < 2000L) {
                super.onBackPressed();
            } else {
                mLastClickTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
