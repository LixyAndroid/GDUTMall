package com.gdut.goods.service.impl


import com.gdut.base.ext.convert
import com.gdut.goods.data.repository.CategoryRepository
import com.gdut.goods.service.CategoryService
import com.gdut.goods.data.protocol.Category
import rx.Observable
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/26 15:02
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>> {

        return repository.getCategory(parentId).convert()
    }




}