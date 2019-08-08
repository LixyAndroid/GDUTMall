package com.gdut.base.ui.activity

import com.gdut.base.presenter.BasePresenter
import com.gdut.base.presenter.view.BaseView

/**
 * @author  Li Xuyang
 * date  2019/8/8 15:58
 */
open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }

    lateinit var mPresenter: T
}