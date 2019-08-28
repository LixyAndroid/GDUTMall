package com.gdut.order.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.order.R
import com.gdut.provider.router.RouterPath.OrderCenter.Companion.PATH_ORDER_CONFIRM


/**
 * @author  Li Xuyang
 * date  2019/8/28 14:42
 */
@Route(path = PATH_ORDER_CONFIRM)
class OrderConfirmActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
    }
}