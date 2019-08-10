package com.gdut.user.service.impl

import com.gdut.base.data.protocol.BaseResp
import com.gdut.base.rx.BaseExcpption
import com.gdut.user.data.repository.UserRepository
import com.gdut.user.service.UserService

import rx.Observable
import rx.functions.Func1
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
            .flatMap (object :Func1<BaseResp<String>,Observable<Boolean>>{
                override fun call(t: BaseResp<String>): Observable<Boolean> {
                    if (t.status !=0){
                        return Observable.error(BaseExcpption(t.status,t.message))

                    }else{
                        return Observable.just(true)
                    }
                }

            })
    }
}