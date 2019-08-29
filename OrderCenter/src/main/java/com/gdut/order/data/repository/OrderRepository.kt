package com.gdut.order.data.repository


import javax.inject.Inject
import rx.Observable
import com.gdut.order.data.protocol.SubmitOrderReq
import com.gdut.order.data.api.OrderApi
import com.gdut.base.data.net.RetrofitFactory
import com.gdut.order.data.protocol.Order
import com.gdut.base.data.protocol.BaseResp
import com.gdut.order.data.protocol.GetOrderListReq
import com.gdut.order.data.protocol.GetOrderByIdReq
import com.gdut.order.data.protocol.ConfirmOrderReq
import com.gdut.order.data.protocol.CancelOrderReq

/*
   订单数据层
 */
class OrderRepository @Inject constructor() {

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).cancelOrder(CancelOrderReq(orderId))
    }

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).confirmOrder(ConfirmOrderReq(orderId))
    }

    /*
        根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<BaseResp<Order>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).getOrderById(GetOrderByIdReq(orderId))
    }

    /*
        根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<BaseResp<MutableList<Order>?>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).getOrderList(GetOrderListReq(orderStatus))
    }

    /*
        提交订单
     */
    fun submitOrder(order: Order): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java).submitOrder(SubmitOrderReq(order))
    }

}
