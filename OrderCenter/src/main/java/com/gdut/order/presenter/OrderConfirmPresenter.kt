package com.gdut.order.presenter

import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.order.data.protocol.Order
import com.gdut.order.presenter.view.OrderConfirmView
import com.gdut.order.service.OrderService
import javax.inject.Inject

/*
    订单确认页 Presenter
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService

    /*
        根据ID获取订单
     */
    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId).excute(object : BaseSubscriber<Order>(mView) {
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        提交订单
     */
    fun submitOrder(order: Order) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderResult(t)
            }
        }, lifecycleProvider)

    }


}
