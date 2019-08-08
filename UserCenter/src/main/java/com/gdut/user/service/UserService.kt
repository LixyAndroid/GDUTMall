package com.gdut.user.service

import rx.Observable


/**
 * @author  Li Xuyang
 * date  2019/8/8 16:35
 */
interface UserService {
    fun register(mobile:String,verifyCode:String,pwd:String): Observable<Boolean>
}