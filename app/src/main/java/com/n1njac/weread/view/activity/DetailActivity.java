package com.n1njac.weread.view.activity;
/*
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.n1njac.weread.player.IPlayback;
import com.n1njac.weread.player.PlayState;
import com.n1njac.weread.player.PlayerHelper;
import com.n1njac.weread.presenter.DetailContract;
import com.n1njac.weread.presenter.DetailPresenter;
import com.n1njac.weread.utils.AnalysisHTML;
import com.n1njac.weread.utils.KeyUtilsKt;
import com.n1njac.weread.utils.TimeUtils;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements ObservableScrollViewCallbacks, DetailContract.View, IPlayback.Callback {

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
    @BindView(R.id.detail_tb)
    Toolbar detailTb;
    @BindView(R.id.detail_wv_container_fl)
    LinearLayout detailWvContainerFl;
    @BindView(R.id.loading_avl)
    AVLoadingIndicatorView loadingAvl;
    @BindView(R.id.tool_bar_line_view)
    View toolBarLineView;

    @Inject
    DetailPresenter mDetailPresenter;
    private boolean isPlaying = false;
    private DetailEntity mDetailEntity;

    private String mItemId;
    private int mModel;
    private float mParallaxImageHeight;
    private WebView mWebView;
    int mActionBarHeight;
    private PlayerHelper mPlayerHelper;
    private String mCurrentFm;
    private Timer mTimer;

    public static void startDetailAty(Context this$, int model, String itemId) {
        Intent i = new Intent(this$, DetailActivity.class);
        i.putExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_MODEL, model);
        i.putExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_ID, itemId);
        this$.startActivity(i);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayerHelper = ((PlayerHelper.PlayerBinder) service).getHelper();
            register();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            unRegister();
            mPlayerHelper = null;
        }
    };

    private void register() {
        mPlayerHelper.registerCallback(this);
    }

    private void unRegister() {
        mPlayerHelper.unregisterCallback(this);
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
        bindPlayService();
    }

    private void bindPlayService() {
        bindService(new Intent(this, PlayerHelper.class), connection, Context.BIND_AUTO_CREATE);
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
        mWebView = new WebView(getApplicationContext());
        detailWvContainerFl.addView(mWebView);
        setTypeText();
        setSeekBar();
    }

    private void setSeekBar() {
        seekBarSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                cancelTimer();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayerHelper.seekTo(getSpecificDuration(seekBar.getProgress()));
                startTimer();
            }
        });
    }

    private int getSpecificDuration(int progress) {
        int totalDuration = 0;
        if (mPlayerHelper != null) {
            totalDuration = mPlayerHelper.getDuration();
        }
        return (int) (totalDuration * ((float) progress / seekBarSb.getMax()));
    }

    private void setTypeText() {
        switch (mModel) {
            case 2:
                //视频
                detailTypeTv.setText("视 频");
                break;
            case 3:
                detailTypeTv.setText("音 频");
                detailTypeListenLl.setVisibility(View.VISIBLE);
                break;
            default:
                detailTypeTv.setText("文 字");
                break;
        }
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mActionBarHeight = getSupportActionBar().getHeight();
        Logger.d("action bar height:" + mActionBarHeight);
    }

    @OnClick({R.id.seek_bar_sb, R.id.detail_play_ic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seek_bar_sb:
                break;
            case R.id.detail_play_ic:
                togglePlayAction();
                break;
        }
    }

    private void togglePlayAction() {
        if (mDetailEntity == null || mPlayerHelper == null) return;
        if (isPlaying) {
            isPlaying = false;
            GlideApp.with(this).load(R.drawable.ic_play).into(detailPlayIc);

            if (mPlayerHelper.isPlaying()) mPlayerHelper.pause();

        } else {
            isPlaying = true;
            GlideApp.with(this).load(R.drawable.ic_pause).into(detailPlayIc);
            if (mCurrentFm == null) return;
            if (mCurrentFm.equals(mPlayerHelper.getUrl())) {
                mPlayerHelper.resume();
            } else {
                mPlayerHelper.start(mCurrentFm);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / (mParallaxImageHeight - mActionBarHeight));
        detailTb.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        if (alpha == 1) {
            toolBarLineView.setVisibility(View.VISIBLE);
        } else {
            toolBarLineView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    public void showLoading() {
        loadingAvl.show();
    }

    @Override
    public void dismissLoading() {
        loadingAvl.hide();
    }

    @Override
    public void freshListUI(DetailEntity detailEntity) {
        mDetailEntity = detailEntity;
        mCurrentFm = detailEntity.getDatas().getFm();
        GlideApp.with(this).load(detailEntity.getDatas().getThumbnail()).centerCrop().into(detailContentIv);
        detailDateTv.setText(detailEntity.getDatas().getUpdate_time());
        detailTitleTv.setText(detailEntity.getDatas().getTitle());
        detailAuthorTv.setText(detailEntity.getDatas().getAuthor());
        detailLeaderTv.setText(Html.fromHtml(detailEntity.getDatas().getLead()));
        if (detailEntity.getDatas().getParseXML() == 1) {
            int i = detailEntity.getDatas().getLead().trim().length();
            AnalysisHTML analysisHTML = new AnalysisHTML();
            analysisHTML.loadHtml(this, detailEntity.getDatas().getContent(), analysisHTML.HTML_STRING, detailWvContainerFl, i);
        }
    }

    @Override
    public void showOnFailure() {
        loadingAvl.hide();
        Toast.makeText(this, "加载失败，请重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onPlayStatusChanged(PlayState status) {

        switch (status) {
            case INIT:
                break;
            case PAUSE:
                cancelTimer();
                break;
            case RESUME:

                startTimer();
                break;
            case PLAYING:
                updateDuration();
                startTimer();
                break;
            case PREPARE:

                break;
            case COMPLETE:
                cancelTimer();
                break;
            case ERROR:
                cancelTimer();
                durationTv.setText("0");
                break;
        }
    }

    public void startTimer() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mPlayerHelper == null) return;
                if (mPlayerHelper.isPlaying()) {
                    mHandler.post(runnable);
                }
            }
        }, 0, 1000);
    }

    public void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = null;

    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int progress = (int) (seekBarSb.getMax()
                    * ((float) mPlayerHelper.getCurrentProgress() / (float) mPlayerHelper.getDuration()));
            updateProgressText(progress);
            if (progress >= 0 && progress <= seekBarSb.getMax()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    seekBarSb.setProgress(progress, true);
                } else {
                    seekBarSb.setProgress(progress);
                }
            }
        }
    };

    private void updateProgressText(int progress) {
        progressTv.setText(TimeUtils.parseDurationTime(progress));
    }

    private void updateDuration() {
        durationTv.setText(TimeUtils.parseDurationTime(mPlayerHelper.getDuration()));
    }
}
