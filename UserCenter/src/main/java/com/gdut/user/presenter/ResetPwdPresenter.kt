package com.gdut.user.presenter


import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.user.presenter.view.ForgetPwdView
import com.gdut.user.presenter.view.RegisterView
import com.gdut.user.presenter.view.ResetPwdView
import com.gdut.user.service.UserService
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/8 16:04
 */
class ResetPwdPresenter @Inject constructor():BasePresenter<ResetPwdView> () {

    @Inject
    lateinit var userService : UserService


    fun resetPwd(mobile:String,verifyCode:String){

        /*
             业务逻辑
         */

        if(!checkNetWork()){
            println("网络不可用")
            return
        }

        mView.showLoading()
        userService.forgetPwd(mobile,verifyCode)
            .excute(object :BaseSubscriber<Boolean>(mView){

                override fun onNext(t: Boolean) {
                    if (t){
                        mView.onResetPwdResult("重置密码成功")

                    }

                }
            },lifecycleProvider)
    }



}




