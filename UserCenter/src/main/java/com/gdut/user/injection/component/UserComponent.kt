package com.gdut.user.injection.component

import com.gdut.base.injection.PerComponentScope
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.user.injection.module.UserModule
import com.gdut.user.ui.activity.LoginActivity
import com.gdut.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * @author  Li Xuyang
 * date  2019/8/10 22:44
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
}