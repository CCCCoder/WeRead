package com.n1njac.weread.view.fragment;
/*
 *    Created by N1njaC on 2018/5/5.
 *    email:aiai173cc@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.n1njac.weread.R;
import com.n1njac.weread.model.entity.Event;
import com.n1njac.weread.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RightMenuFragment extends Fragment {
    @BindView(R.id.version_tv)
    TextView versionTv;
    Unbinder unbinder;
    @BindView(R.id.left_slide_close_iv)
    ImageView leftSlideCloseIv;
    @BindView(R.id.setting_iv)
    ImageView settingIv;
    @BindView(R.id.home_page_tv)
    TextView homePageTv;
    @BindView(R.id.words_tv)
    TextView wordsTv;
    @BindView(R.id.voice_tv)
    TextView voiceTv;
    @BindView(R.id.video_tv)
    TextView videoTv;

    private List<View> mAnimViews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        addView();
        return view;
    }

    private void addView() {
        mAnimViews = new ArrayList<>();
        mAnimViews.add(homePageTv);
        mAnimViews.add(wordsTv);
        mAnimViews.add(voiceTv);
        mAnimViews.add(videoTv);
    }

    public void startAnim() {

        startColumnAnim();
        startIconAnim(settingIv);
        startIconAnim(leftSlideCloseIv);
    }


    private void startColumnAnim() {
        for (int i = 1; i < mAnimViews.size(); i++) {
            View view = mAnimViews.get(i);
            TranslateAnimation animation = new TranslateAnimation(i * 35, 0, 0, 0);
            animation.setDuration(900L);
            view.startAnimation(animation);
        }
    }

    private void startIconAnim(View view) {
        ScaleAnimation animation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, view.getWidth() / 2, view.getHeight() / 2);
        animation.setDuration(1000L);
        view.startAnimation(animation);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.left_slide_close_iv, R.id.setting_iv, R.id.avatar_iv, R.id.home_page_tv, R.id.words_tv, R.id.voice_tv, R.id.video_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_slide_close_iv:

                RxBus.get().post(new Event());

                break;
            case R.id.setting_iv:
                break;
            case R.id.avatar_iv:
                break;
            case R.id.home_page_tv:
                break;
            case R.id.words_tv:
                break;
            case R.id.voice_tv:
                break;
            case R.id.video_tv:
                break;
        }
    }
}
