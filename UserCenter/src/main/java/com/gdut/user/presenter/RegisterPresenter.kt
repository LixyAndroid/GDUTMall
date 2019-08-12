package com.gdut.user.presenter

import com.gdut.base.ext.execute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.user.presenter.view.RegisterView
import com.gdut.user.service.UserService
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/8 16:04
 */
class RegisterPresenter @Inject constructor():BasePresenter<RegisterView> () {

    @Inject
    lateinit var userService : UserService



    fun register(mobile:String,verifyCode:String,pwd:String){

        /*
             业务逻辑
         */

        if(!checkNetWork()){
            println("网络不可用")
            return
        }

        mView.showLoading()
        userService.register(mobile,verifyCode,pwd)
            .execute(object :BaseSubscriber<Boolean>(mView){

                override fun onNext(t: Boolean) {
                    if (t){
                        mView.onRegisterResult("注册成功")

                    }

                }
            },lifecycleProvider)
    }



}




