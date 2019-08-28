package com.gdut.order.service

import rx.Observable

/*
    订单业务 接口
 */
interface ShipAddressService {

    /*
        添加收货地址
     */
    fun addShipAddress(
        shipUserName: String,
        shipUserMobile: String,
        shipAddress: String
    ): Observable<Boolean>
}
