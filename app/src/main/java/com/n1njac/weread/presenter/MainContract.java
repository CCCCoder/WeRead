package com.n1njac.weread.presenter;
/*    
 *    Created by N1njaC on 2018/5/2.
 *    email:aiai173cc@gmail.com 
 */

import com.n1njac.weread.model.entity.DetailEntity;

import java.util.List;

public interface MainContract {
    interface Presenter {
        void getListByPage(int page, int model, String pageId, String deviceId, String createTime);

        void getRecommend(String deviceId);
    }

    interface View {
        void showLoading();

        void dismissLoading();

        void showNoData();

        void showNoMore();

        void showOnFailure();

        void showLunar();

        void refreshMainList(List<DetailEntity> items);
    }
}
