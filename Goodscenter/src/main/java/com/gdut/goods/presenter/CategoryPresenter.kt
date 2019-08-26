package com.gdut.goods.presenter


import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.goods.data.protocol.Category
import com.gdut.goods.presenter.view.CategoryView
import com.gdut.goods.service.CategoryService

import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/26 14:48
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService


    fun getCategory(parentId: Int) {

        /*
             业务逻辑
         */

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()
        categoryService.getCategory(parentId)
            .excute(object : BaseSubscriber<MutableList<Category>>(mView) {

                override fun onNext(t: MutableList<Category>) {

                    mView.onGetCategoryResult(t)


                }
            }, lifecycleProvider)
    }


}




