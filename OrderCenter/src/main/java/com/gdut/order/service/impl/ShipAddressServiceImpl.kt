package com.gdut.order.service.impl

import com.gdut.base.ext.convertBoolean
import com.gdut.order.data.repository.ShipAddressRepository
import com.gdut.order.service.ShipAddressService
import rx.Observable
import javax.inject.Inject

/*
    订单业务实现类
 */
class ShipAddressServiceImpl @Inject constructor() : ShipAddressService {


    @Inject
    lateinit var repository: ShipAddressRepository

    override fun addShipAddress(
        shipUserName: String,
        shipUserMobile: String,
        shipAddress: String
    ): Observable<Boolean> {
        return repository.addShipAddress(shipUserName, shipUserMobile, shipAddress).convertBoolean()
    }

}
