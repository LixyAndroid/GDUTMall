package com.gdut.user.data.api

import com.gdut.base.data.protocol.BaseResp
import com.gdut.user.data.protocol.LoginReq
import com.gdut.user.data.protocol.RegisterReq
import com.gdut.user.data.protocol.UserInfo
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
}