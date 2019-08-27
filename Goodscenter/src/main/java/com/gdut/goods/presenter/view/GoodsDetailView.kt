package com.gdut.goods.presenter.view

import com.gdut.base.presenter.view.BaseView
import com.gdut.goods.data.protocol.Goods

/**
 * @author  Li Xuyang
 * date  2019/8/27 13:32
 */
interface GoodsDetailView : BaseView {
    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)
    //加入购物车
    fun onAddCartResult(result: Int)
}