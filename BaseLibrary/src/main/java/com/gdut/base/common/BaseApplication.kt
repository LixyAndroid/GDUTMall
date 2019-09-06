package com.gdut.base.common

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.gdut.base.injection.component.AppComponent
import com.gdut.base.injection.component.DaggerAppComponent
import com.gdut.base.injection.module.AppModule

/**
 * @author  Li Xuyang
 * date  2019/8/11 16:07
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        // 初始化MultiDex
        MultiDex.install(this)

        initAppInjection()

        context = this

        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog()    // 打印日志
        ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context: Context
    }
}