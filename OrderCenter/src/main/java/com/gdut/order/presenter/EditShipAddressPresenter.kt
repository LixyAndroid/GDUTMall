package com.gdut.order.presenter

import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.order.data.protocol.ShipAddress
import com.gdut.order.presenter.view.EditShipAddressView
import com.gdut.order.service.ShipAddressService
import javax.inject.Inject

/*
    订单确认页 Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService


    fun addShipAddress(
        shipUserName: String,
        shipUserMobile: String,
        shipAddress: String
    ) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress)
            .excute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    mView.onAddShipAddressResult(t)
                }
            }, lifecycleProvider)

    }


    fun editShipAddress(address:ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address)
            .excute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    mView.onEditShipAddressResult(t)
                }
            }, lifecycleProvider)

    }

}
