package com.gdut.order.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.order.data.protocol.Order
import com.gdut.order.data.protocol.ShipAddress

/*
    订单确认页 视图回调
 */
interface ShipAddressView : BaseView {


    fun onGetShipAddressResult(result:MutableList<ShipAddress>?)

    fun onSetDefaultResult(result:Boolean)

}
