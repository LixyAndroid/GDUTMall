package com.gdut.order.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.order.data.protocol.Order

/*
    订单确认页 视图回调
 */
interface EditShipAddressView : BaseView {


    fun onAddShipAddressResult(result:Boolean)

}
