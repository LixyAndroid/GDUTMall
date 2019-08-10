package com.gdut.user.injection.component

import com.gdut.user.injection.module.UserModule
import com.gdut.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * @author  Li Xuyang
 * date  2019/8/10 22:44
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
}