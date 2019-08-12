package com.gdut.base.presenter.view

/**
 * @author  Li Xuyang
 * date  2019/8/8 15:54
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
}