package com.gdut.user.service.impl

import com.gdut.base.ext.convertBoolean
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

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return  repository.register(mobile,pwd,verifyCode)
            .convertBoolean()
    }
}