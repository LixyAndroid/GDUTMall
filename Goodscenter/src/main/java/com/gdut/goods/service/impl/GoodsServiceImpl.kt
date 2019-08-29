package com.gdut.goods.service.impl


import com.gdut.base.ext.convert
import com.gdut.goods.data.protocol.Goods
import com.gdut.goods.data.repository.GoodsRepository
import com.gdut.goods.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/26 15:02
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo).convert()
    }

    override fun getGoodsListByKeyword(
        keyword: String,
        pageNo: Int
    ): Observable<MutableList<Goods>?> {
        return repository.getGoodsListByKeyword(keyword, pageNo).convert()

    }

    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return repository.getGoodsDetail(goodsId).convert()
    }


}