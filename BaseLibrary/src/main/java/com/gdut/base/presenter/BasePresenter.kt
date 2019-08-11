package com.gdut.base.presenter

import com.gdut.base.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/8 15:54
 */
open class BasePresenter<T:BaseView> {
    lateinit var mView:T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}