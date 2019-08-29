package com.gdut.order.service

import com.gdut.base.data.protocol.BaseResp
import com.gdut.order.data.protocol.ShipAddress
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


    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>

    //修改
    fun editShipAddress(address:ShipAddress): Observable<Boolean>

    /*
        删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<Boolean>
}
