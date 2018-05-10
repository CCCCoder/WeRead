package com.n1njac.weread.view.activity;
/*    
 *    Created by N1njaC on 2018/5/6.
 *    email:aiai173cc@gmail.com 
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.n1njac.weread.R;
import com.n1njac.weread.model.entity.CategoryListEntity;
import com.n1njac.weread.utils.KeyUtilsKt;

public class DetailActivity extends BaseActivity {

    public static void startDetailAty(Context this$, CategoryListEntity.DatasBean dataBean) {
        Intent i = new Intent();
        i.putExtra(KeyUtilsKt.FRAGMENT_TO_DETAIL_ACTIVITY_DATA, dataBean);
        this$.startActivity(i);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
