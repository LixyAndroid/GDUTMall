package com.gdut.order.presenter


import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.order.data.protocol.Order
import com.gdut.order.presenter.view.OrderListView
import com.gdut.order.service.OrderService
import javax.inject.Inject

/*
    订单列表Presenter
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService

    /*
        根据订单状态获取订单列表
     */
    fun getOrderList(orderStatus: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderList(orderStatus).excute(object : BaseSubscriber<MutableList<Order>?>(mView) {
            override fun onNext(t: MutableList<Order>?) {
                    mView.onGetOrderListResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        订单确认收货
     */
    fun confirmOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.confirmOrder(orderId).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onConfirmOrderResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.cancelOrder(orderId).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onCancelOrderResult(t)
            }
        }, lifecycleProvider)

    }



}
