package com.gdut.user.data.repository

import com.gdut.base.data.net.RetrofitFactory
import com.gdut.base.data.protocol.BaseResp
import com.gdut.user.data.api.UserApi
import com.gdut.user.data.protocol.*
import com.gdut.user.data.protocol.EditUserReq
import rx.Observable
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/8 18:16
 * 直接访问网络
 */
class UserRepository @Inject constructor() {
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<BaseResp<String>> {

        //单例创建
        return RetrofitFactory.instance.create(UserApi::class.java)
            .register(RegisterReq(mobile,pwd,verifyCode))
    }


    fun login(mobile:String,pwd:String,pushId:String): Observable<BaseResp<UserInfo>> {

        //单例创建
        return RetrofitFactory.instance.create(UserApi::class.java)
            .login(LoginReq(mobile,pwd,pushId))
    }

    fun forgetPwd(mobile:String,verifyCode:String): Observable<BaseResp<String>> {

        //单例创建
        return RetrofitFactory.instance.create(UserApi::class.java)
            .forgetPwd(ForgerPwdReq(mobile,verifyCode))
    }

    fun resetPwd(mobile:String,pwd:String): Observable<BaseResp<String>> {

        //单例创建
        return RetrofitFactory.instance.create(UserApi::class.java)
            .resetPwd(ResetPwdReq(mobile,pwd))
    }
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String): Observable<BaseResp<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
            .editUser(EditUserReq(userIcon,userName,userGender,userSign))
    }
}