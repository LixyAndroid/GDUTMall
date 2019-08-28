package com.gdut.order.presenter

import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.order.data.protocol.ShipAddress
import com.gdut.order.presenter.view.ShipAddressView
import com.gdut.order.service.ShipAddressService
import javax.inject.Inject

/*
    订单确认页 Presenter
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /*
        根据ID获取订单
     */
    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList()
            .excute(object : BaseSubscriber<MutableList<ShipAddress>?>(mView) {
                override fun onNext(t: MutableList<ShipAddress>?) {
                    mView.onGetShipAddressResult(t)
                }
            }, lifecycleProvider)

    }

    /*
      根据ID获取订单
   */
    fun setDefaultShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetDefaultResult(t)
            }
        }, lifecycleProvider)

    }


}
