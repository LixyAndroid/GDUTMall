package com.gdut.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.base.utils.YuanFenConverter
import com.gdut.order.R
import com.gdut.order.data.protocol.Order
import com.gdut.order.injection.component.DaggerOrderComponent
import com.gdut.order.injection.module.OrderModule
import com.gdut.order.presenter.OrderConfirmPresenter
import com.gdut.order.presenter.view.OrderConfirmView
import com.gdut.order.ui.adapter.OrderGoodsAdapter
import com.gdut.provider.common.ProviderConstant
import com.gdut.provider.router.RouterPath.OrderCenter.Companion.PATH_ORDER_CONFIRM
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity


/**
 * @author  Li Xuyang
 * date  2019/8/28 14:42
 */
@Route(path = PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {


    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    private lateinit var mAdapter: OrderGoodsAdapter

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
            .orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)



        initView()
        loadData()
    }


    private fun initView() {

        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter

    }

    private fun loadData() {
        // mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {

        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
    }

    override fun onSubmitOrderResult(result: Boolean) {


    }
}