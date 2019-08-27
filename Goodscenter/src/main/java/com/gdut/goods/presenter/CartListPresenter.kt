package com.gdut.goods.presenter


import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.goods.data.protocol.CartGoods
import com.gdut.goods.presenter.view.CartListView
import com.gdut.goods.service.CartService
import javax.inject.Inject


/**
 * @author  Li Xuyang
 * date  2019/8/26 14:48
 */
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartService


    fun getCartList() {

        /*
             业务逻辑
         */

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()
        cartService.getCartList().excute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
            override fun onNext(t: MutableList<CartGoods>?) {
                if (t != null) {
                    mView.onGetCartListResult(t)
                }
            }
        }, lifecycleProvider)
    }


    fun deleteCartList(list: List<Int>) {

        /*
             业务逻辑
         */

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()
        cartService.deleteCartList(list).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteCartListResult(t)

            }
        }, lifecycleProvider)
    }


}




