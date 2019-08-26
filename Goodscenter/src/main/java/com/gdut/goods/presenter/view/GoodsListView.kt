package com.gdut.goods.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.goods.data.protocol.Goods


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:00
 */
interface GoodsListView : BaseView {
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}