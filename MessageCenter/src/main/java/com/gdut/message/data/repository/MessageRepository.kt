package com.gdut.message.data.repository


import com.gdut.base.data.net.RetrofitFactory
import com.gdut.base.data.protocol.BaseResp
import com.gdut.message.data.api.MessageApi
import com.gdut.message.data.protocol.Message
import javax.inject.Inject

import rx.Observable


/*
   消息数据层
 */
class MessageRepository @Inject constructor() {

    /*
        获取消息列表
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()
    }



}
