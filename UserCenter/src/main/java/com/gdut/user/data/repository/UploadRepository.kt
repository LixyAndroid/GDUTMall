package com.gdut.user.data.repository

import com.gdut.base.data.net.RetrofitFactory
import com.gdut.base.data.protocol.BaseResp
import com.gdut.user.data.api.UploadApi
import com.gdut.user.data.api.UserApi
import com.gdut.user.data.protocol.*
import rx.Observable
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/4 15:35
 * 直接访问网络
 */
class UploadRepository @Inject constructor() {

    fun getUploadToken(): Observable<BaseResp<String>> {
        //单例创建
        return RetrofitFactory.instance.create(UploadApi::class.java)
            .getUploadToken()
    }


}