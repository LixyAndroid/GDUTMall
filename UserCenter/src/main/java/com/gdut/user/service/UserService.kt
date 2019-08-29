package com.gdut.user.service

import com.gdut.user.data.protocol.UserInfo
import rx.Observable


/**
 * @author  Li Xuyang
 * date  2019/8/8 16:35
 */
interface UserService {
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<Boolean>


    fun login(mobile:String,pwd:String,pushId:String): Observable<UserInfo>

    fun forgetPwd(mobile:String,verifyCode:String): Observable<Boolean>

    fun resetPwd(mobile:String,pwd:String): Observable<Boolean>

    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String): Observable<UserInfo>
}