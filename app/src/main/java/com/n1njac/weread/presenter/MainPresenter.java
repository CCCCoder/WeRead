package com.n1njac.weread.presenter;
/*    
 *    Created by N1njaC on 2018/5/2.
 *    email:aiai173cc@gmail.com 
 */

import com.n1njac.weread.model.api.ApiService;
import com.n1njac.weread.model.entity.CategoryListEntity;
import com.n1njac.weread.utils.TimeUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainPresenter implements MainContract.Presenter {

    private ApiService mApiService;
    private MainContract.View mView;


    public MainPresenter(ApiService mApiService, MainContract.View mView) {
        this.mApiService = mApiService;
        this.mView = mView;
    }

    @Override
    public void getListByPage(int page, int model, String pageId, String deviceId, String createTime) {
        mApiService.getCategoryListData("api", "getList", page, model, pageId,
                createTime, "android", "1.3.0", TimeUtils.getCurrentSeconds(), deviceId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryListEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryListEntity categoryListEntity) {
                        int size = categoryListEntity.getDatas().size();
                        if (size > 0) {
                            mView.refreshMainList(categoryListEntity.getDatas());
                        } else {
                            mView.showNoMore();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showOnFailure();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void getRecommend(String deviceId) {

    }
}
