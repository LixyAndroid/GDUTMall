package com.gdut.user.injection.module

import com.gdut.user.service.UserService
import com.gdut.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @author  Li Xuyang
 * date  2019/8/10 19:49
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl):UserService{
        return service
    }


}