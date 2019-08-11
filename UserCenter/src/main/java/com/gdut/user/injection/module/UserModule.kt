package com.gdut.user.injection.module

import com.gdut.user.service.UserService
import com.gdut.user.service.impl.UserServiceImpl
import com.gdut.user.service.impl.UserServiceImpl2
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author  Li Xuyang
 * date  2019/8/10 19:49
 */
@Module
class UserModule {

    @Provides
    @Named("service")
    fun providesUserService(service: UserServiceImpl):UserService{
        return service
    }

    @Provides
    @Named("service2")
    fun providesUserService2(service: UserServiceImpl2):UserService{
        return service
    }


}