package com.gdut.base.data.protocol

/**
 * @author  Li Xuyang
 * date  2019/8/8 18:06
 */
class BaseResp<out T>(val status:Int,val message:String,val data:T)
