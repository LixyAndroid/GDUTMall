package com.gdut.goods.presenter


import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.goods.data.protocol.Goods
import com.gdut.goods.presenter.view.GoodsListView
import com.gdut.goods.service.GoodsService
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/26 14:48
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var goodsService: GoodsService



    fun getGoodsList(categoryId: Int, pageNo: Int) {

        /*
             业务逻辑
         */

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo)
            .excute(object : BaseSubscriber<MutableList<Goods>?>(mView) {

                override fun onNext(t: MutableList<Goods>?) {
                    mView.onGetGoodsListResult(t)


                }
            }, lifecycleProvider)
    }


}




