package com.gdut.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.gdut.base.ext.startLoading
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.goods.R
import com.gdut.goods.common.GoodsConstant
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
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView,
    BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var mGoodsaAdapter: GoodsAdapter
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1

    private var mData:MutableList<Goods>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()

    }


    private fun initView() {

        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsaAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsaAdapter

    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)

    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent)
            .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {

        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()

        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1){
                mGoodsaAdapter.setData(result)
            }else{
                mGoodsaAdapter.dataList.addAll(result)
                mGoodsaAdapter.notifyDataSetChanged()
            }


            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            //没有数据
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    //加载更多
    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {

        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }

    }

    //加载第一页
    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {

        mCurrentPage = 1
        loadData()
    }

}

