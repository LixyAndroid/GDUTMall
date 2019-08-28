package com.gdut.order.presenter

import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.order.presenter.view.EditShipAddressView
import com.gdut.order.service.ShipAddressService
import javax.inject.Inject

/*
    订单确认页 Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /*
        根据ID获取订单
     */
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


}
