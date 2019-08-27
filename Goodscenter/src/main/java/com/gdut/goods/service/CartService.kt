package com.gdut.goods.service

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
}