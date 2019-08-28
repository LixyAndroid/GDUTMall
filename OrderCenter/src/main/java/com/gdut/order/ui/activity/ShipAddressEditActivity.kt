package com.gdut.order.ui.activity

import android.content.Intent
import android.os.Bundle
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.order.R
import com.gdut.order.injection.component.DaggerShipAddressComponent
import com.gdut.order.injection.module.ShipAddressModule
import com.gdut.order.presenter.EditShipAddressPresenter
import com.gdut.order.presenter.view.EditShipAddressView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * @author  Li Xuyang
 * date  2019/8/28 20:21
 */
class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {


    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent).shipAddressModule(
            ShipAddressModule()
        ).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
    }

    private fun initView() {
        btn_location_address.onClick {
            //startActivity<MapLocationActivity>()
            val intent = Intent(this, MapLocationActivity::class.java)
            startActivityForResult(intent, 1001)
        }

        mSaveBtn.onClick {

            if (mShipNameEt.text.isNullOrEmpty()){
                toast("您的姓名还没填呢")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()){
                toast("手机号码还没填呢")
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()){
                toast("收获地址还没填呢")
                return@onClick
            }
            mPresenter.addShipAddress(mShipNameEt.text.toString(),mShipMobileEt.text.toString(),mShipAddressEt.text.toString())
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 200) {
            if (data != null) {
                val address = data.getStringExtra("address")
                mShipAddressEt.setText(address)

            }
        }
    }


    override fun onAddShipAddressResult(result: Boolean) {

        toast("添加地址成功")
        finish()
    }

}