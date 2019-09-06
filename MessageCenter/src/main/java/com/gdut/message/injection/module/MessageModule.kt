package com.gdut.message.injection.module

import com.gdut.message.service.MessageService
import com.gdut.message.service.impl.MessageServiceImpl
import dagger.Module
import dagger.Provides

/*
    消息模块业务注入
 */
@Module
class MessageModule {

    @Provides
    fun provideMessageService(messageService: MessageServiceImpl): MessageService {
        return  messageService
    }

}
