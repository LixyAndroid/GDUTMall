package com.gdut.goods.injection.component


import com.gdut.base.injection.PerComponentScope
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.goods.injection.module.CartModule
import com.gdut.goods.injection.module.GoodsModule
import com.gdut.goods.ui.activity.GoodsActivity
import com.gdut.goods.ui.fragment.CartFragment
import com.gdut.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * @author  Li Xuyang
 * date  2019/8/27 17:05
 * 商品分类Component
 */
@PerComponentScope
@Component(
    dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(CartModule::class)
)
interface CartComponent {

    fun inject(fragment: CartFragment)

}