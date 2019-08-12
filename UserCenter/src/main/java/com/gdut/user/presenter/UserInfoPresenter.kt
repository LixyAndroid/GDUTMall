package com.gdut.user.presenter

import com.gdut.base.ext.execute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.user.presenter.view.RegisterView
import com.gdut.user.presenter.view.UserInfoView
import com.gdut.user.service.UserService
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/8 16:04
 */
class UserInfoPresenter @Inject constructor():BasePresenter<UserInfoView> () {

    @Inject
    lateinit var userService : UserService




}




