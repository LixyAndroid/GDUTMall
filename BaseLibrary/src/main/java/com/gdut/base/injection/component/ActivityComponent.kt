package com.gdut.base.injection.component

import android.content.Context
import com.gdut.base.injection.ActivityScope
import com.gdut.base.injection.module.ActivityModule
import com.gdut.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * @author  Li Xuyang
 * date  2019/8/11 17:05
 */
@ActivityScope
@Component(
    dependencies = arrayOf(AppComponent::class),
    modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class)
)
interface ActivityComponent {

    // fun activity():Activity
    fun context(): Context

    fun lifecycleProvider(): LifecycleProvider<*>
}