package com.gdut.base.rx

import com.gdut.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * @author  Li Xuyang
 * date  2019/8/11 20:34
 */
class BaseFunc<T>:Func1<BaseResp<T>,Observable<T>>{

        override fun call(t: BaseResp<T>): Observable<T> {
            if (t.status !=0){
                return Observable.error(BaseExcpption(t.status,t.message))

            }else{
                return Observable.just(t.data)
            }
        }

}