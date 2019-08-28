package com.gdut.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.gdut.base.ext.onClick
import com.gdut.base.ext.startLoading
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.order.R
import com.gdut.order.common.OrderConstant
import com.gdut.order.data.protocol.ShipAddress
import com.gdut.order.injection.component.DaggerShipAddressComponent
import com.gdut.order.injection.module.ShipAddressModule
import com.gdut.order.presenter.ShipAddressPresenter
import com.gdut.order.presenter.view.ShipAddressView
import com.gdut.order.ui.adapter.ShipAddressAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author  Li Xuyang
 * date  2019/8/28 20:19
 */
class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {



    private lateinit var mAdapter: ShipAddressAdapter

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent).shipAddressModule(
            ShipAddressModule()
        ).build().inject(this)
        mPresenter.mView = this

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun onStart() {
        super.onStart()

        loadData()
    }

    private fun initView() {

        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ShipAddressAdapter(this)

        mAddressRv.adapter = mAdapter

        mAdapter.mOptClickListener = object :ShipAddressAdapter.OnOptClickListener{
            override fun onSetDefault(address: ShipAddress) {

                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to  address)


            }

            override fun onDelete(address: ShipAddress) {
                toast("删除")
            }

        }
        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()
        }
    }


    private fun loadData() {

        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()

    }

    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {

        if (result != null && result.size > 0) {
            mAdapter.setData(result)

            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            //没有数据
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY

        }
    }


    /**
     * 设置默认回调
     */
    override fun onSetDefaultResult(result: Boolean) {
       toast("设置默认成功")
        loadData()
    }


}