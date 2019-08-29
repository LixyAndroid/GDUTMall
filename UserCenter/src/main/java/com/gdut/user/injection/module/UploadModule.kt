package com.gdut.user.injection.module

import com.gdut.user.service.UploadService
import com.gdut.user.service.UserService
import com.gdut.user.service.impl.UploadServiceImpl
import com.gdut.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author  Li Xuyang
 * date  2019/8/14 15:30
 */
@Module
class UploadModule {

    @Provides
    fun providesUploadService(uploadService: UploadServiceImpl):UploadService{
        return uploadService
    }




}