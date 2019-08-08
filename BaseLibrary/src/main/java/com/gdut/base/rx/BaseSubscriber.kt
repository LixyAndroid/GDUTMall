package com.gdut.base.rx

import rx.Subscriber

/**
 * @author  Li Xuyang
 * date  2019/8/8 17:21
 */
open class BaseSubscriber<T>:Subscriber<T>() {
    override fun onNext(t: T) {

    }

    override fun onCompleted() {

    }

    override fun onError(e: Throwable?) {

    }
}