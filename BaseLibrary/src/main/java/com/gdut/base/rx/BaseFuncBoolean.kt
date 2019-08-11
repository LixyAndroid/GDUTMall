package com.gdut.base.rx

import com.gdut.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * @author  Li Xuyang
 * date  2019/8/11 20:27
 */
class BaseFuncBoolean<T>:Func1<BaseResp<T>,Observable<Boolean>>{

        override fun call(t: BaseResp<T>): Observable<Boolean> {
            if (t.status !=0){
                return Observable.error(BaseExcpption(t.status,t.message))

            }else{
                return Observable.just(true)
            }
        }

}