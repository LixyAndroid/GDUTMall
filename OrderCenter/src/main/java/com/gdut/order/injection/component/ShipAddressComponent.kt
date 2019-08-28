package com.gdut.order.injection.component

import com.gdut.base.injection.PerComponentScope
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.order.injection.module.ShipAddressModule
import com.gdut.order.ui.activity.ShipAddressActivity
import com.gdut.order.ui.activity.ShipAddressEditActivity
import dagger.Component

/*
    订单Component
 */
@PerComponentScope
@Component(
    dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(ShipAddressModule::class)
)
interface ShipAddressComponent {

    fun inject(activity: ShipAddressEditActivity)

    fun inject(activity: ShipAddressActivity)

}
