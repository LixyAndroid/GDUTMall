package com.gdut.provider.router

/**
 * @author  Li Xuyang
 * date  2019/8/27 16:47
 */
object RouterPath {
    class UserCenter{
        companion object{
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    class OrderCenter{
        companion object{
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

    //消息模块
    class MessageCenter{
        companion object {
            const val PATH_MESSAGE_PUSH = "/messageCenter/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
        }
    }
}