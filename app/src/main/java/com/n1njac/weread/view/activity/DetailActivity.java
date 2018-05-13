package com.n1njac.weread.view.activity;
/*
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.n1njac.weread.R;
import com.n1njac.weread.model.entity.CategoryListEntity;
import com.n1njac.weread.utils.KeyUtilsKt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity {

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
    @BindView(R.id.news_parse_web_ll)
    LinearLayout newsParseWebLl;
    @BindView(R.id.content_wv)
    WebView contentWv;
    @BindView(R.id.detail_tb)
    Toolbar detailTb;

    public static void startDetailAty(Context this$, CategoryListEntity.DatasBean dataBean) {
        Intent i = new Intent(this$, DetailActivity.class);
        i.putExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_DATA, dataBean);
        this$.startActivity(i);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
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
}
