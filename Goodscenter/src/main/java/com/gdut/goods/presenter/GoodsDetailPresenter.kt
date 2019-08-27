package com.gdut.goods.presenter

import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.base.utils.AppPrefsUtils
import com.gdut.goods.common.GoodsConstant
import com.gdut.goods.data.protocol.Goods
import com.gdut.goods.presenter.view.GoodsDetailView
import com.gdut.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/27 13:31
 */
class GoodsDetailPresenter  @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService



    /*
        获取商品详情
     */
    fun getGoodsDetailList(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).excute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailResult(t)
            }
        }, lifecycleProvider)

    }


}