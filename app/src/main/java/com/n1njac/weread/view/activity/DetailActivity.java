package com.n1njac.weread.view.activity;
/*
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.n1njac.weread.R;
import com.n1njac.weread.app.GlideApp;
import com.n1njac.weread.app.WeReadApplication;
import com.n1njac.weread.di.components.DaggerDetailComponent;
import com.n1njac.weread.di.modules.DetailModule;
import com.n1njac.weread.model.entity.DetailEntity;
import com.n1njac.weread.presenter.DetailContract;
import com.n1njac.weread.presenter.DetailPresenter;
import com.n1njac.weread.utils.KeyUtilsKt;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements ObservableScrollViewCallbacks, DetailContract.View {

    @BindView(R.id.detail_content_iv)
    ImageView detailContentIv;
    @BindView(R.id.progress_tv)
    TextView progressTv;
    @BindView(R.id.seek_bar_sb)
    AppCompatSeekBar seekBarSb;
    @BindView(R.id.duration_tv)
    TextView durationTv;
    @BindView(R.id.detail_play_ic)
    AppCompatImageView detailPlayIc;
    @BindView(R.id.detail_type_listen_ll)
    LinearLayout detailTypeListenLl;
    @BindView(R.id.detail_type_tv)
    TextView detailTypeTv;
    @BindView(R.id.detail_date_tv)
    TextView detailDateTv;
    @BindView(R.id.detail_title_tv)
    TextView detailTitleTv;
    @BindView(R.id.detail_author_tv)
    TextView detailAuthorTv;
    @BindView(R.id.detail_leader_tv)
    TextView detailLeaderTv;
    @BindView(R.id.detail_osv)
    ObservableScrollView detailOsv;
    @BindView(R.id.detail_content_tv)
    TextView detailContentTv;
    @BindView(R.id.detail_tb)
    Toolbar detailTb;
    @Inject
    DetailPresenter mDetailPresenter;


    private String mItemId;
    private int mModel;
    private float mParallaxImageHeight;

    public static void startDetailAty(Context this$, int model, String itemId) {
        Intent i = new Intent(this$, DetailActivity.class);
        i.putExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_MODEL, model);
        i.putExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_ID, itemId);
        this$.startActivity(i);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initPresenter();
        initVar();
        initView();
        loadData();
    }


    private void initVar() {
        Intent intent = getIntent();
        mModel = intent.getIntExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_MODEL, 0);
        mItemId = intent.getStringExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_ID);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initPresenter() {
        DaggerDetailComponent.builder()
                .detailModule(new DetailModule(this))
                .netComponent(WeReadApplication.get(this).getNetComponent())
                .build()
                .inject(this);
    }

    private void initView() {
        initToolBar();
        detailOsv.setScrollViewCallbacks(this);
    }

    private void initToolBar() {
        setSupportActionBar(detailTb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        detailTb.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.colorPrimary)));
        detailTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadData() {
        mDetailPresenter.getDetailData(mItemId);
    }

    @OnClick({R.id.seek_bar_sb, R.id.detail_play_ic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seek_bar_sb:
                break;
            case R.id.detail_play_ic:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        detailTb.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void freshListUI(DetailEntity detailEntity) {
        GlideApp.with(this).load(detailEntity.getDatas().getThumbnail()).centerCrop().into(detailContentIv);
        detailDateTv.setText(detailEntity.getDatas().getUpdate_time());
        detailTitleTv.setText(detailEntity.getDatas().getTitle());
        detailAuthorTv.setText(detailEntity.getDatas().getAuthor());
        detailLeaderTv.setText(Html.fromHtml(detailEntity.getDatas().getLead()));
        detailContentTv.setText(Html.fromHtml(detailEntity.getDatas().getContent()));
    }

    @Override
    public void showOnFailure() {

    }
}
