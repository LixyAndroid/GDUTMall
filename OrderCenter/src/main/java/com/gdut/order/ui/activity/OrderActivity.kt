package com.gdut.order.ui.activity

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.order.R
import com.gdut.order.common.OrderConstant
import com.gdut.order.common.OrderStatus
import com.gdut.order.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

/**
 * @author  Li Xuyang
 * date  2019/9/4 21:16
 */
class OrderActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    private fun initView() {

        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        //根据订单状态设置当前页面
        mOrderVp.currentItem =
            intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)

    }
}