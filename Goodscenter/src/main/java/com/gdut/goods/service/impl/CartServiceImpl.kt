package com.gdut.goods.service.impl

import com.gdut.base.ext.convert
import com.gdut.goods.data.repository.CartRepository
import com.gdut.goods.service.CartService
import rx.Observable
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/27 17:00
 */
class CartServiceImpl @Inject constructor():CartService{


    @Inject
    lateinit var repository:CartRepository

    override fun addCart(
        goodsId: Int,
        goodsDesc: String,
        goodsIcon: String,
        goodsPrice: Long,
        goodsCount: Int,
        goodsSku: String
    ): Observable<Int> {

        return repository.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,goodsCount,goodsSku).convert()
    }

}