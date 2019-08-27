package com.gdut.goods.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.goods.data.protocol.CartGoods
import com.gdut.goods.data.protocol.Category


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:00
 */
interface CartListView:BaseView {
    fun onGetCartListResult(result:MutableList<CartGoods>)
    fun onDeleteCartListResult(result:Boolean)
}