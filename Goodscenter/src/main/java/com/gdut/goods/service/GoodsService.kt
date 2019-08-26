package com.gdut.goods.service


import com.gdut.goods.data.protocol.Goods
import rx.Observable


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:01
 */
interface GoodsService {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

}