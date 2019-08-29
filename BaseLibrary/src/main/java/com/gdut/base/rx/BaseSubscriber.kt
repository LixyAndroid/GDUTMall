package com.gdut.base.rx

import com.gdut.base.presenter.view.BaseView
import rx.Subscriber

/**
 * @author  Li Xuyang
 * date  2019/8/8 17:21
 */
open class BaseSubscriber<T>(val baseView:BaseView):Subscriber<T>() {

    override fun onNext(t: T) {

    }

    override fun onCompleted() {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseExcpption){
            baseView.onError(e.msg)
        }
    }
}