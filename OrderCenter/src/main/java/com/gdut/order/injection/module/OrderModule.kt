package com.gdut.order.injection.module

import com.gdut.order.service.OrderService
import com.gdut.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/*
    订单Module
 */
@Module
class OrderModule {

    @Provides
    fun provideOrderservice(orderService: OrderServiceImpl): OrderService{
        return orderService
    }

}
