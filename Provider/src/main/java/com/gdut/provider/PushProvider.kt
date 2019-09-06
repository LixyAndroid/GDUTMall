package com.gdut.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author  Li Xuyang
 * date  2019/9/4 22:49
 */
interface PushProvider: IProvider {
    fun getPushId():String
}