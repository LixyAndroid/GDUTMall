package com.gdut.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.gdut.base.ext.onClick
import com.gdut.base.ext.startLoading
import com.gdut.base.ui.fragment.BaseMvpFragment
import com.gdut.base.utils.YuanFenConverter
import com.gdut.goods.R
import com.gdut.goods.data.protocol.CartGoods
import com.gdut.goods.event.CartAllCheckedEvent
import com.gdut.goods.event.UpdateTotalPriceEvent
import com.gdut.goods.injection.component.DaggerCartComponent
import com.gdut.goods.injection.module.CartModule
import com.gdut.goods.presenter.CartListPresenter
import com.gdut.goods.presenter.view.CartListView
import com.gdut.goods.ui.adapter.CartGoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_cart.*


/**
 * @author  Li Xuyang
 * date  2019/8/27 20:51
 */

class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {


    private lateinit var mAdapter: CartGoodsAdapter

    private var mTotalPrice:Long = 0

    /*
          Dagger注册
       */
    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent)
            .cartModule(CartModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
        initObserve()
    }


    /*
        初始化视图
     */
    private fun initView() {


        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = context?.let { CartGoodsAdapter(it) }!!
        mCartGoodsRv.adapter = mAdapter

        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

    }

    /*
        加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()

    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>().subscribe { t: CartAllCheckedEvent ->
            run {
                mAllCheckedCb.isChecked = t.isAllChecked
                updateTotalPrice()
            }
        }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>().subscribe {
            updateTotalPrice()
        }.registerInBus(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }



    fun updateTotalPrice(){
        //计算总价
        mTotalPrice = mAdapter.dataList
            .filter { it.isSelected }
            .map { it.goodsCount * it.goodsPrice }
            .sum()

        mTotalPriceTv.text = "合计${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {

            //没有数据
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }




}
