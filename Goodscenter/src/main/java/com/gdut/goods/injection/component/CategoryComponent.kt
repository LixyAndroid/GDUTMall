package com.gdut.goods.injection.component

import com.gdut.base.injection.PerComponentScope
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.goods.injection.module.CategoryModule
import com.gdut.goods.ui.fragment.CategoryFragment


import dagger.Component

/**
 * @author  Li Xuyang
 * date  2019/8/26 15:04
 * 商品分类Component
 */
@PerComponentScope
@Component(
    dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(CategoryModule::class)
)
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)

}