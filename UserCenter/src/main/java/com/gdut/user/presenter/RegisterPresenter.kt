package com.gdut.user.presenter

import com.gdut.base.presenter.BasePresenter
import com.gdut.user.presenter.view.RegisterView

/**
 * @author  Li Xuyang
 * date  2019/8/8 16:04
 */
class RegisterPresenter:BasePresenter<RegisterView> () {

    fun register(movile:String,verifyCode:String){
        /*
        业务逻辑
         */
        mView.onRegisterResult(true)
    }
}