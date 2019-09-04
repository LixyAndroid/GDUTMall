package com.gdut.order.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.order.data.protocol.Order


/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
