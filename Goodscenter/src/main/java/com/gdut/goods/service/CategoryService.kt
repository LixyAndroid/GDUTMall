package com.gdut.goods.service


import com.kotlin.goods.data.protocol.Category
import rx.Observable


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:01
 */
interface CategoryService {
    fun getCategory(parentId:Int): Observable<MutableList<Category>>


}