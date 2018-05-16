package com.n1njac.weread.presenter;

import android.util.Log;

import com.n1njac.weread.model.api.ApiService;
import com.n1njac.weread.model.entity.DetailEntity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *    Created by N1njaC on 2018/5/13.
 *    email:aiai173cc@gmail.com
 */
public class DetailPresenter implements DetailContract.Presenter {

    public static final String TAG = DetailPresenter.class.getSimpleName();
    private DetailContract.View mDetailView;
    private ApiService mApiService;

    @Inject
    public DetailPresenter(DetailContract.View detailView, ApiService apiService) {
        this.mDetailView = detailView;
        this.mApiService = apiService;
    }

    @Override
    public void getDetailData(String itemId) {
        mApiService.getDetailData("api", "getPost", itemId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe");
                        mDetailView.showLoading();
                    }

                    @Override
                    public void onNext(DetailEntity detailEntity) {
                        Log.i(TAG, "onNext status:" + detailEntity.getStatus());
                        mDetailView.freshListUI(detailEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i(TAG, "onError e:" + e.getMessage());
                        mDetailView.showOnFailure();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                        mDetailView.dismissLoading();
                    }
                });
    }
}
