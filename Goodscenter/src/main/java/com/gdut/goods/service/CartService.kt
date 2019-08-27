package com.gdut.goods.service

import com.gdut.base.data.protocol.BaseResp
import com.gdut.goods.data.protocol.CartGoods
import rx.Observable

/**
 * @author  Li Xuyang
 * date  2019/8/27 16:59
 */
interface CartService {
    /*
       添加商品到购物车
    */
    fun addCart(
        goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
        goodsCount: Int, goodsSku: String
    ): Observable<Int>

    fun getCartList(): Observable<MutableList<CartGoods>?>
}