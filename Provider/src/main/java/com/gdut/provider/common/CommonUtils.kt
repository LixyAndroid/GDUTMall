package com.gdut.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.gdut.base.common.BaseConstant
import com.gdut.base.utils.AppPrefsUtils
import com.gdut.provider.router.RouterPath

/**
 * @author  Li Xuyang
 * date  2019/8/24 22:22
 * 全局函数
 */

fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method:() -> Unit){
    if (isLogined()){
        method()
    }
    else{
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}