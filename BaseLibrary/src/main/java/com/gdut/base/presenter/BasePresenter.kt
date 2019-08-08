package com.gdut.base.presenter

import com.gdut.base.presenter.view.BaseView

/**
 * @author  Li Xuyang
 * date  2019/8/8 15:54
 */
open class BasePresenter<T:BaseView> {
    lateinit var mView:T
}