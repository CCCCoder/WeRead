package com.n1njac.weread.view.fragment;
/*
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.n1njac.weread.R;
import com.n1njac.weread.app.GlideApp;
import com.n1njac.weread.model.entity.CategoryListEntity;
import com.n1njac.weread.model.entity.DetailEntity;
import com.n1njac.weread.utils.KeyUtilsKt;
import com.n1njac.weread.view.activity.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    @BindView(R.id.main_advertise_iv)
    ImageView mainAdvertiseIv;
    @BindView(R.id.main_pic_iv)
    ImageView mainPicIv;
    @BindView(R.id.content_type_iv)
    ImageView contentTypeIv;
    @BindView(R.id.download_iv)
    ImageView downloadIv;
    @BindView(R.id.main_type_tv)
    TextView mainTypeTv;
    @BindView(R.id.main_title_tv)
    TextView mainTitleTv;
    @BindView(R.id.main_content_tv)
    TextView mainContentTv;
    @BindView(R.id.main_author_tv)
    TextView mainAuthorTv;
    @BindView(R.id.main_comment_tv)
    TextView mainCommentTv;
    @BindView(R.id.main_like_tv)
    TextView mainLikeTv;
    @BindView(R.id.main_read_count_tv)
    TextView mainReadCountTv;
    @BindView(R.id.main_page_content_tl)
    RelativeLayout mainPageContentTl;
    Unbinder unbinder;

    private CategoryListEntity.DatasBean mDatasBean;

    public static Fragment newInstance(CategoryListEntity.DatasBean detailEntity) {
        Fragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KeyUtilsKt.FRAGMENT_MAIN_DETAIL_DATA, detailEntity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        mDatasBean = getArguments().getParcelable(KeyUtilsKt.FRAGMENT_MAIN_DETAIL_DATA);
        if (mDatasBean == null) return;
        int model = Integer.parseInt(mDatasBean.getModel());
        if (model == 5) {
            //广告
            mainPageContentTl.setVisibility(View.GONE);
            mainAdvertiseIv.setVisibility(View.VISIBLE);
            GlideApp.with(getActivity()).load(mDatasBean.getThumbnail()).centerCrop().into(mainAdvertiseIv);
        } else {
            mainPageContentTl.setVisibility(View.VISIBLE);
            mainAdvertiseIv.setVisibility(View.GONE);
            GlideApp.with(getActivity()).load(mDatasBean.getThumbnail()).centerCrop().into(mainPicIv);
            mainContentTv.setText(mDatasBean.getExcerpt());
            mainTitleTv.setText(mDatasBean.getTitle());
            mainAuthorTv.setText(mDatasBean.getAuthor());
            mainCommentTv.setText(mDatasBean.getComment());
            mainLikeTv.setText(mDatasBean.getGood());
            mainReadCountTv.setText(mDatasBean.getView());
            switch (model) {
                case 2:
                    //视频
                    mainTypeTv.setText(R.string.str_type_watch);
                    contentTypeIv.setVisibility(View.VISIBLE);
                    downloadIv.setVisibility(View.GONE);
                    contentTypeIv.setImageResource(R.mipmap.library_video_play_symbol);
                    break;
                case 3:
                    mainTypeTv.setText(R.string.str_type_listen);
                    contentTypeIv.setVisibility(View.VISIBLE);
                    downloadIv.setVisibility(View.VISIBLE);
                    contentTypeIv.setImageResource(R.mipmap.library_voice_play_symbol);
                    break;
                default:
                    mainTypeTv.setText(R.string.str_type_read);
                    contentTypeIv.setVisibility(View.GONE);
                    downloadIv.setVisibility(View.GONE);
                    break;
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.main_advertise_iv, R.id.content_type_iv, R.id.download_iv, R.id.main_page_content_tl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_advertise_iv:
                go2AdvertiseView();
                break;
            case R.id.content_type_iv:
                break;
            case R.id.download_iv:
                break;
            case R.id.main_page_content_tl:
                mainPageClick();
                break;
        }
    }

    private void mainPageClick() {
        if (mDatasBean != null) {
            String model = mDatasBean.getModel();
            String id = mDatasBean.getId();
            Log.d(TAG, "model:" + model + " id:" + id);
            DetailActivity.startDetailAty(getActivity(), Integer.parseInt(model), id);
        }
    }

    private void go2AdvertiseView() {
        if (mDatasBean != null) {
            Uri uri = Uri.parse(mDatasBean.getHtml5());
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}
