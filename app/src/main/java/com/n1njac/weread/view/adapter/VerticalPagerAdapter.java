package com.n1njac.weread.view.adapter;
/*    
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com 
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.n1njac.weread.model.entity.CategoryListEntity;
import com.n1njac.weread.view.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class VerticalPagerAdapter extends FragmentStatePagerAdapter {

    private List<CategoryListEntity.DatasBean> datasBeans = new ArrayList<>();

    public VerticalPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(datasBeans.get(position));
    }

    @Override
    public int getCount() {
        return datasBeans.size();
    }

    public void setDataList(List<CategoryListEntity.DatasBean> dataList) {
        datasBeans.addAll(dataList);
        notifyDataSetChanged();
    }

    public String getLastItemId() {
        if (datasBeans.size() == 0) {
            return "0";
        } else {
            CategoryListEntity.DatasBean datasBean = datasBeans.get(datasBeans.size() - 1);
            return datasBean.getId();
        }
    }

    public String getLastItemCreateTime() {
        if (datasBeans.size() == 0) {
            return "0";
        }
        CategoryListEntity.DatasBean datasBean = datasBeans.get(datasBeans.size() - 1);
        return datasBean.getCreate_time();
    }
}
