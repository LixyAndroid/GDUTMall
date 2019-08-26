package com.gdut.provider.common

import com.gdut.base.utils.AppPrefsUtils

/**
 * @author  Li Xuyang
 * date  2019/8/24 22:22
 * 全局函数
 */

fun isLogined(): Boolean {
    return AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON).isNotEmpty()
}