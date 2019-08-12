package com.gdut.user.presenter

import com.gdut.base.ext.execute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.user.data.protocol.UserInfo
import com.gdut.user.presenter.view.LoginView
import com.gdut.user.presenter.view.RegisterView
import com.gdut.user.service.UserService
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/8 16:04
 */
class LoginPresenter @Inject constructor():BasePresenter<LoginView> () {

    @Inject
    lateinit var userService : UserService



    fun login(mobile:String,pwd:String,pushId:String){

        /*
             业务逻辑
         */

        if(!checkNetWork()){
            println("网络不可用")
            return
        }

        mView.showLoading()
        userService.login(mobile,pwd,pushId)
            .execute(object :BaseSubscriber<UserInfo>(mView){

                override fun onNext(t: UserInfo) {

                        mView.onLoginResult(t)



                }
            },lifecycleProvider)
    }



}




