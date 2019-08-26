package com.gdut.goods.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdut.base.ext.setVisible
import com.gdut.base.ext.startLoading
import com.gdut.base.ui.adapter.BaseRecyclerViewAdapter
import com.gdut.base.ui.fragment.BaseFragment
import com.gdut.base.ui.fragment.BaseMvpFragment
import com.gdut.goods.R
import com.gdut.goods.common.GoodsConstant
import com.gdut.goods.data.protocol.Category
import com.gdut.goods.injection.component.DaggerCategoryComponent
import com.gdut.goods.injection.module.CategoryModule
import com.gdut.goods.presenter.CategoryPresenter
import com.gdut.goods.presenter.view.CategoryView
import com.gdut.goods.ui.activity.GoodsActivity
import com.gdut.goods.ui.adapter.SecondCategoryAdapter
import com.gdut.goods.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_category.*


/*
    商品分类 Fragment
 */
class GoodsDetailTabOneFragment : BaseFragment(){





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }





}
