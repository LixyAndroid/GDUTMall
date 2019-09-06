package com.gdut.message.data.api

import com.gdut.base.data.protocol.BaseResp
import com.gdut.message.data.protocol.Message
import retrofit2.http.POST
import rx.Observable


/*
    消息 接口
 */
interface MessageApi {

    /*
        获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>>

}
