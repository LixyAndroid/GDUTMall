package com.gdut.base.ext

import com.gdut.base.rx.BaseSubscriber
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author  Li Xuyang
 * date  2019/8/8 17:25
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>){
     this. observeOn(AndroidSchedulers.mainThread() )
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}