package com.gdut.base.rx

import com.gdut.base.presenter.view.BaseView
import rx.Subscriber

/**
 * @author  Li Xuyang
 * date  2019/8/8 17:21
 */
open class BaseSubscriber<T>(val baseView:BaseView):Subscriber<T>() {
    override fun onNext(t: T) {

        baseView.hideLoading()
    }

    override fun onCompleted() {

    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
    }
}