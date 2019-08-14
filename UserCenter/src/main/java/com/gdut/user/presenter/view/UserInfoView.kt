package com.gdut.user.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.user.data.protocol.UserInfo

/**
 * @author  Li Xuyang
 * date  2019/8/8 16:06
 */
interface UserInfoView:BaseView {
    fun onGetUploadTokenResult(result:String)
    fun onEditUserResult(result: UserInfo)

}