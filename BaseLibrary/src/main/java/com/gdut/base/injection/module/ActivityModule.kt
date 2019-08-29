package com.gdut.base.injection.module

import android.app.Activity
import android.content.Context
import com.gdut.base.common.BaseApplication
import com.gdut.base.injection.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author  Li Xuyang
 * date  2019/8/11 17:06
 * Activity级别Module
 */

@Module
class ActivityModule(private  val activity:Activity) {

    @ActivityScope
    @Singleton
    fun providesActivity():Activity{
        return activity
    }
}