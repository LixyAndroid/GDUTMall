package com.gdut.goods.data.repository

import com.gdut.base.data.net.RetrofitFactory
import com.gdut.base.data.protocol.BaseResp
import com.kotlin.goods.data.api.CategoryApi
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:03
 * 直接访问网络
 */
class CategoryRepository @Inject constructor() {
     fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>>> {
         return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}