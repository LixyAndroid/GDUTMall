package com.gdut.base.injection.component

import android.content.Context
import com.gdut.base.injection.ActivityScope
import com.gdut.base.injection.module.ActivityModule
import dagger.Component

/**
 * @author  Li Xuyang
 * date  2019/8/11 17:05
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun context():Context
}