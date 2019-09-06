package com.gdut.message.provider

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.gdut.provider.PushProvider
import com.gdut.provider.router.RouterPath


/*
    模块间接口调用
    提供PushId的实现
 */
@Route(path = RouterPath.MessagePush.PATH_MESSAGE_PUSH)
class PushProviderImpl: PushProvider {

    private var mContext:Context? = null
    override fun getPushId(): String {
        return JPushInterface.getRegistrationID(mContext)
    }

    override fun init(context: Context?) {
        mContext = context
    }
}
