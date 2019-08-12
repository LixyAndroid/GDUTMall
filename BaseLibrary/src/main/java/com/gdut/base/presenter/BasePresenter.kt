package com.gdut.base.presenter

import android.content.Context
import com.gdut.base.presenter.view.BaseView
import com.kotlin.base.utils.NetWorkUtils
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

    @Inject
    lateinit var  context: Context

    fun checkNetWork():Boolean{

        if (NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("当前网络不可用")
        return false
    }
}