package com.gdut.order.injection.component

import com.gdut.base.injection.PerComponentScope
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.order.injection.module.OrderModule
import com.gdut.order.ui.activity.OrderConfirmActivity
import com.gdut.order.ui.activity.OrderDetailActivity
import com.gdut.order.ui.fragment.OrderFragment
import dagger.Component

/*
    订单Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(fragment: OrderFragment)
    fun inject(activity: OrderDetailActivity)

}
