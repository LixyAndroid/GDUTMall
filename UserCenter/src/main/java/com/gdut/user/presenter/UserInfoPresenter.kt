package com.gdut.user.presenter

import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.user.data.protocol.UserInfo
import com.gdut.user.presenter.view.UserInfoView
import com.gdut.user.service.UploadService
import com.gdut.user.service.UserService
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/8 16:04
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {


    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService


    /*
      获取七牛云上传凭证
   */
    fun getUploadToken(){
        if (!checkNetWork())
            return

        mView.showLoading()
        uploadService.getUploadToken().excute(object :BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }


    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String){
        if (!checkNetWork()){
            return
        }

        mView.showLoading()
        userService.editUser(userIcon,userName,userGender,userSign).excute(object :BaseSubscriber<UserInfo>(mView){
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        },lifecycleProvider)
    }



}




