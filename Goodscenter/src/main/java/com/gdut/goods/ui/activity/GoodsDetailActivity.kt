package com.gdut.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.goods.R
import com.gdut.goods.ui.adapter.GoodsDetailVpAdapter
import com.gdut.provider.router.RouterPath.UserCenter.Companion.PATH_LOGIN
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * @author  Li Xuyang
 * date  2019/8/26 22:28
 */
class GoodsDetailActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED

        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager,this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {

            ARouter.getInstance().build(PATH_LOGIN).navigation()
        }

    }
}