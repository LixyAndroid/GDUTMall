package com.gdut.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.gdut.base.ext.onClick
import com.gdut.base.ext.setVisible
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.base.utils.YuanFenConverter
import com.gdut.order.R
import com.gdut.order.data.protocol.Order
import com.gdut.order.event.SelectAddressEvent
import com.gdut.order.injection.component.DaggerOrderComponent
import com.gdut.order.injection.module.OrderModule
import com.gdut.order.presenter.OrderConfirmPresenter
import com.gdut.order.presenter.view.OrderConfirmView
import com.gdut.order.ui.adapter.OrderGoodsAdapter
import com.gdut.provider.common.ProviderConstant.Companion.KEY_ORDER_ID
import com.gdut.provider.router.RouterPath.OrderCenter.Companion.PATH_ORDER_CONFIRM
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * @author  Li Xuyang
 * date  2019/8/28 14:42
 */
@Route(path = PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {


    @Autowired(name = KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    private lateinit var mAdapter: OrderGoodsAdapter
    private var mCurrentOrder: Order? = null

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
            .orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        initObserve()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                mPresenter.submitOrder(it)
            }
        }

        //订单中商品列表
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    /*
        初始化选择收货人事件监听
     */
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
            .subscribe { t: SelectAddressEvent ->
                run {
                    mCurrentOrder?.let {
                        it.shipAddress = t.address
                    }
                    updateAddressView()
                }
            }
            .registerInBus(this)

    }

    /*
        加载订单数据
     */
    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    /*
        获取订单回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        mCurrentOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"

        updateAddressView()
    }

    /*
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
        根据是否有收货人信息，更新视图
     */
    private fun updateAddressView() {
        mCurrentOrder?.let {
            if (it.shipAddress == null) {
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            } else {
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text = it.shipAddress!!.shipUserName + "  " +
                        it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    /*
        提交订单回调
     */
    override fun onSubmitOrderResult(result: Boolean) {
        toast("订单提交成功")
    }


}