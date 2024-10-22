package com.gdut.goods.service


import com.gdut.goods.data.protocol.Goods
import rx.Observable


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:01
 */
interface GoodsService {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>
    /*
     获取商品详情
  */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}