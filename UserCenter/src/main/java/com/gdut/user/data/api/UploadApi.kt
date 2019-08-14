package com.gdut.user.data.api

import com.gdut.base.data.protocol.BaseResp
import retrofit2.http.POST
import rx.Observable

/**
 * @author  Li Xuyang
 * date  2019/8/8 18:09
 */
interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}