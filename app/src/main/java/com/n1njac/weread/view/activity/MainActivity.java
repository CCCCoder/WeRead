package com.n1njac.weread.view.activity;
/*
 *    Created by N1njaC on 2018/5/2.
 *    email:aiai173cc@gmail.com
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.n1njac.weread.R;


import com.n1njac.weread.app.GlideApp;
import com.n1njac.weread.app.WeReadApplication;

import com.n1njac.weread.di.components.DaggerMainComponent;
import com.n1njac.weread.di.modules.MainModule;
import com.n1njac.weread.model.api.StringConvert;
import com.n1njac.weread.model.entity.CategoryListEntity;
import com.n1njac.weread.model.entity.DetailEntity;
import com.n1njac.weread.model.entity.Event;
import com.n1njac.weread.presenter.MainContract;
import com.n1njac.weread.presenter.MainPresenter;
import com.n1njac.weread.utils.AppUtils;
import com.n1njac.weread.utils.PreferenceUtils;
import com.n1njac.weread.utils.RxBus;
import com.n1njac.weread.utils.TimeUtils;
import com.n1njac.weread.view.adapter.VerticalPagerAdapter;
import com.n1njac.weread.view.fragment.LeftMenuFragment;
import com.n1njac.weread.view.fragment.RightMenuFragment;
import com.n1njac.weread.view.widget.LunarDialog;
import com.n1njac.weread.view.widget.VerticalViewPager;
import com.orhanobut.logger.Logger;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.main_vvp)
    VerticalViewPager mainVvp;
    private SlidingMenu mSlidingMenu;
    private long mLastClickTime;
    private VerticalPagerAdapter mPagerAdapter;
    private String mDeviceId;

    private LeftMenuFragment mLeftMenuFragment;
    private RightMenuFragment mRightMenuFragment;

    @Inject
    MainPresenter mPresenter;
    private int mPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainActivityPermissionsDispatcher.getDeviceIdWithPermissionCheck(this);
        initMenu();
        initViewPager();
//        getDeviceId();
        loadData(mPage, 0, "0", "0");
        initLunar();
    }

    private void initLunar() {
        String current_day = (String) PreferenceUtils.INSTANCE.getSP("current_day", "", this);
        if (!TimeUtils.getCurrentData("yyyyMMdd").equals(current_day)){
            loadRecommendData();
        }
    }

    private void loadRecommendData() {
        mPresenter.getRecommend(mDeviceId);
    }

    @SuppressLint("CheckResult")
    private void initMenu() {
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.setFadeEnabled(true);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setMenu(R.layout.container_left_menu);
        mLeftMenuFragment = new LeftMenuFragment();
        mRightMenuFragment = new RightMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.left_menu_ll, mLeftMenuFragment).commit();
        mSlidingMenu.setSecondaryMenu(R.layout.container_right_menu);
        getSupportFragmentManager().beginTransaction().add(R.id.right_menu_ll, mRightMenuFragment).commit();

        RxBus.get().toObservable(Event.class).subscribe(new Consumer<Event>() {
            @Override
            public void accept(Event event) throws Exception {
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

        mainVvp.setAdapter(mPagerAdapter);
        mainVvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (mPagerAdapter.getCount() <= position + 2) {
                    loadData(mPage, 0, mPagerAdapter.getLastItemId(), mPagerAdapter.getLastItemCreateTime());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
    public void showLunar(String thumbnailPath) {
        Logger.d(thumbnailPath);
        PreferenceUtils.INSTANCE.putSP("current_day", TimeUtils.getCurrentData("yyyyMMdd"), this);
        LunarDialog dialog = new LunarDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_lunar, null, false);
        ImageView lunarIv = view.findViewById(R.id.lunar_iv);
        GlideApp.with(this).load(thumbnailPath).into(lunarIv);
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void refreshMainList(List<CategoryListEntity.DatasBean> items) {
        Logger.d("items" + items);
        mPage++;
        mPagerAdapter.setDataList(items);
    }


    private void loadData(int page, int mode, String pageId, String createTime) {
        mPresenter.getListByPage(page, mode, pageId, mDeviceId, createTime);
    }

    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
    public void getDeviceId() {
        mDeviceId = AppUtils.getDeviceId(this);
    }

    @OnClick({R.id.go_to_column_iv, R.id.go_to_person_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_to_column_iv:
                mSlidingMenu.showMenu();
                mLeftMenuFragment.startAnim();
                break;
            case R.id.go_to_person_iv:
                mSlidingMenu.showSecondaryMenu();
                mRightMenuFragment.startAnim();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
