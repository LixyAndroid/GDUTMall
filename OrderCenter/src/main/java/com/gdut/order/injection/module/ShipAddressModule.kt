package com.gdut.order.injection.module

import com.gdut.order.service.ShipAddressService
import com.gdut.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/*
    订单Module
 */
@Module
class ShipAddressModule {

    @Provides
    fun provideShipAddressService(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }

}
