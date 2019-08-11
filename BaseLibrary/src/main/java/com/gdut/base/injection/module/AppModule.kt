package com.gdut.base.injection.module

import android.content.Context
import com.gdut.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author  Li Xuyang
 * date  2019/8/11 16:06
 */

@Module
class AppModule( private  val context:BaseApplication) {

    @Provides
    @Singleton
    fun providesContext():Context{
        return this.context
    }
}