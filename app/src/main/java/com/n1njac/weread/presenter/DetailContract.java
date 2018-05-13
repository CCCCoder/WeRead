package com.n1njac.weread.presenter;

import com.n1njac.weread.model.entity.DetailEntity;

/*
 *    Created by N1njaC on 2018/5/13.
 *    email:aiai173cc@gmail.com
 */
public interface DetailContract {
    interface Presenter {
        void getDetailData(String itemId);
    }

    interface View {
        void showLoading();

        void dismissLoading();

        void freshListUI(DetailEntity detailEntity);

        void showOnFailure();
    }
}
