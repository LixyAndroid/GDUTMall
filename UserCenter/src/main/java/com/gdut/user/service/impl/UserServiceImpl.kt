package com.gdut.user.service.impl

import com.gdut.user.service.UserService

import rx.Observable

/**
 * @author  Li Xuyang
 * date  2019/8/8 16:38
 */
class UserServiceImpl:UserService {

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return  Observable.just(true)
    }
}