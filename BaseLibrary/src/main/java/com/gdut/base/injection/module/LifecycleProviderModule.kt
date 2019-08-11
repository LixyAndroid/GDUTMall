package com.gdut.base.injection.module

import com.gdut.base.injection.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author  Li Xuyang
 * date  2019/8/11 20:06
 */

@Module
class LifecycleProviderModule(private  val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider():LifecycleProvider<*>{
        return lifecycleProvider
    }
}