package com.gdut.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.gdut.base.ext.startLoading
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.goods.R
import com.gdut.goods.data.protocol.Goods
import com.gdut.goods.injection.component.DaggerGoodsComponent
import com.gdut.goods.injection.module.GoodsModule
import com.gdut.goods.presenter.GoodsListPresenter
import com.gdut.goods.presenter.view.GoodsListView
import com.gdut.goods.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*

/**
 * @author  Li Xuyang
 * date  2019/8/26 16:42
 */
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView {


    private lateinit var mGoodsaAdapter: GoodsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_goods)
        initView()
        loadData()

    }



    private fun initView() {

        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsaAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsaAdapter

    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra("categoryId",1),1)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent)
            .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {


        if (result != null && result.size > 0) {
            mGoodsaAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            //没有数据
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

}

