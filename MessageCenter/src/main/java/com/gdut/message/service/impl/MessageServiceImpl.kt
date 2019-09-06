package com.gdut.message.service.impl



import com.gdut.base.ext.convert
import com.gdut.message.data.protocol.Message
import com.gdut.message.data.repository.MessageRepository
import com.gdut.message.service.MessageService
import javax.inject.Inject

import rx.Observable


/*
   消息业务层
 */
class MessageServiceImpl @Inject constructor(): MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /*
        获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }

}
