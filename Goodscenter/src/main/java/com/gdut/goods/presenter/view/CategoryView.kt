package com.gdut.goods.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Category


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:00
 */
interface CategoryView:BaseView {
    fun onGetCategoryResult(result:MutableList<Category>)
}