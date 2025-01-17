package com.gdut.user.data.api

import com.gdut.base.data.protocol.BaseResp
import com.gdut.user.data.protocol.*
import com.gdut.user.data.protocol.EditUserReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * @author  Li Xuyang
 * date  2019/8/8 18:09
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body req: LoginReq):Observable<BaseResp<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgerPwdReq):Observable<BaseResp<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq):Observable<BaseResp<String>>

    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserReq):Observable<BaseResp<UserInfo>>

}