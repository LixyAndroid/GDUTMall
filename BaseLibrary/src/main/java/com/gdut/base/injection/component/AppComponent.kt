package com.gdut.base.injection.component

import android.content.Context
import com.gdut.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author  Li Xuyang
 * date  2019/8/10 23:01
 * Application级别Module
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context():Context
}