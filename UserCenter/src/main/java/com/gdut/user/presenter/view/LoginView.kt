package com.gdut.user.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.user.data.protocol.UserInfo

/**
 * @author  Li Xuyang
 * date  2019/8/8 16:06
 */
interface LoginView:BaseView {
    fun onLoginResult(result:UserInfo)
}