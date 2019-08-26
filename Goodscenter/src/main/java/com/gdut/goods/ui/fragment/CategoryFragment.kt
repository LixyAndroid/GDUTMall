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
import com.gdut.base.ui.fragment.BaseMvpFragment
import com.gdut.goods.R
import com.gdut.goods.injection.component.DaggerCategoryComponent
import com.gdut.goods.injection.module.CategoryModule
import com.gdut.goods.presenter.CategoryPresenter
import com.gdut.goods.presenter.view.CategoryView
import com.gdut.goods.ui.adapter.SecondCategoryAdapter
import com.gdut.goods.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import com.gdut.base.ui.adapter.BaseRecyclerViewAdapter
import com.gdut.goods.data.protocol.Category
import com.gdut.goods.ui.activity.GoodsActivity

import kotlinx.android.synthetic.main.fragment_category.*


/*
    商品分类 Fragment
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {


    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    /*
          Dagger注册
       */
    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent)
            .categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {


        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = context?.let { TopCategoryAdapter(it) }!!
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object :
            BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()

                loadData(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = context?.let { SecondCategoryAdapter(it) }!!
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object :
            BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {

               // startActivity<GoodsActivity>("categoryId" to item.id)


                val intent = Intent()
                //获取intent对象
                intent.setClass(context, GoodsActivity::class.java)

                intent.putExtra("categoryId",item.id)
                startActivity(intent)
            }
        })

    }

    /*
        加载数据
     */
    private fun loadData(parentId: Int = 0) {

        if (parentId != 0) {

            mMultiStateView.startLoading()
        }

        mPresenter.getCategory(parentId)

    }


    override fun onGetCategoryResult(result: MutableList<Category>) {

        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }


}
