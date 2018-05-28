package com.n1njac.weread.view.activity

import android.os.Bundle
import com.n1njac.weread.R
import com.n1njac.weread.model.entity.DetailEntity
import com.n1njac.weread.presenter.DetailContract


/*    
 *    Created by N1njaC on 2018/5/28.
 *    email:aiai173cc@gmail.com 
 */
class VideoDetailActivity : BaseActivity(), DetailContract.View{

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_video)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun freshListUI(detailEntity: DetailEntity?) {

    }

    override fun showOnFailure() {

    }
}