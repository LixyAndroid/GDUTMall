package com.gdut.order.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.gdut.base.ext.startLoading
import com.gdut.base.ui.fragment.BaseMvpFragment
import com.gdut.order.R
import com.gdut.order.common.OrderConstant
import com.gdut.order.data.protocol.Order
import com.gdut.order.injection.component.DaggerOrderComponent
import com.gdut.order.injection.module.OrderModule
import com.gdut.order.presenter.OrderListPresenter
import com.gdut.order.presenter.view.OrderListView
import com.kennyc.view.MultiStateView
import com.kotlin.order.ui.adapter.OrderAdapter
import kotlinx.android.synthetic.main.fragment_order.*


/*
    订单列表Fragment
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private lateinit var mAdapter: OrderAdapter

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
            .orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = activity?.let { OrderAdapter(it) }!!
        mOrderRv.adapter = mAdapter


    }

    /*
        取消订单对话框
     */
    private fun showCancelDialog(order: Order) {
        AlertView(
            "取消订单",
            "确定取消该订单？",
            "取消",
            null,
            arrayOf("确定"),
            activity,
            AlertView.Style.Alert,
            OnItemClickListener { o, position ->
                if (position == 0) {
                    mPresenter.cancelOrder(order.id)
                }
            }

        ).show()
    }

    /*
        加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments!!.getInt(OrderConstant.KEY_ORDER_STATUS, -1))
    }

    /*
        获取订单列表回调
     */
    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /*
        订单确认收货回调
     */
    override fun onConfirmOrderResult(result: Boolean) {
        Toast.makeText(context, "确认收货成功", Toast.LENGTH_SHORT).show()
        loadData()
    }

    /*
        取消订单回调
     */
    override fun onCancelOrderResult(result: Boolean) {
        Toast.makeText(context, "取消订单成功", Toast.LENGTH_SHORT).show()
        loadData()
    }
}
