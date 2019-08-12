package com.gdut.base.common

import android.app.Application
import android.content.Context
import com.gdut.base.injection.component.AppComponent
import com.gdut.base.injection.component.DaggerAppComponent
import com.gdut.base.injection.module.AppModule

/**
 * @author  Li Xuyang
 * date  2019/8/11 16:07
 */
class BaseApplication :Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()

        context = this
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object{
        lateinit var context:Context
    }
}