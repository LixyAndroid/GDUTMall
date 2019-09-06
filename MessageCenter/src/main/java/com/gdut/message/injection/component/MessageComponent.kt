package com.gdut.message.injection.component


import com.gdut.base.injection.PerComponentScope
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.message.injection.module.MessageModule
import com.gdut.message.ui.fragment.MessageFragment

import dagger.Component

/*
    消息模块注入组件
 */
@PerComponentScope
@Component(
    dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(MessageModule::class)
)
interface MessageComponent {
    fun inject(fragment: MessageFragment)
}
