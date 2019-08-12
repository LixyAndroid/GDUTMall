package com.gdut.user.service.impl

import com.gdut.base.ext.convert
import com.gdut.base.ext.convertBoolean
import com.gdut.user.data.protocol.UserInfo
import com.gdut.user.data.repository.UserRepository
import com.gdut.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/8 16:38
 */
class UserServiceImpl @Inject  constructor():UserService {




    @Inject
    lateinit var repository: UserRepository
    /*
        注册
      */

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return  repository.register(mobile,pwd,verifyCode)
            .convertBoolean()
    }
     /*
        登录
      */
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return  repository.login(mobile,pwd,pushId)
            .convert()
    }


    /*
    忘记密码
     */
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return  repository.forgetPwd(mobile,verifyCode)
            .convertBoolean()
    }

    /*
    重置密码
     */
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return  repository.resetPwd(mobile,pwd)
            .convertBoolean()
    }
}