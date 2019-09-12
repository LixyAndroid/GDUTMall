package com.gdut.gdutmall.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gdut.base.ext.onClick
import com.gdut.base.ui.fragment.BaseFragment
import com.gdut.gdutmall.R
import com.gdut.gdutmall.ui.adapter.HomeDiscountAdapter
import com.gdut.gdutmall.ui.adapter.TopicAdapter
import com.gdut.goods.ui.activity.SearchGoodsActivity
import com.gdut.base.widgets.BannerImageLoader
import com.kotlin.mall.common.*
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow


/**
 * @author  Li Xuyang
 * date  2019/8/19 10:54
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_home, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }


    /*
       初始化视图
    */
    private fun initView() {
        mSearchEt.onClick {
           // startActivity<SearchGoodsActivity>()


            val intent = Intent()
            //获取intent对象
            intent.setClass(context, SearchGoodsActivity::class.java)
            // 获取class是使用::反射
            startActivity(intent)
        }

        mScanIv.onClick {
            Toast.makeText(context,R.string.coming_soon_tip,Toast.LENGTH_LONG).show()
        }
    }


    private fun initNews() {
        //公告
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠卷"))


    }

    private fun initBanner() {

        //https://github.com/youth5201314/banner

        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(
            listOf(
                HOME_BANNER_ONE,
                HOME_BANNER_TWO,
                HOME_BANNER_THREE,
                HOME_BANNER_FOUR
            )
        )

        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)

        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()

    }

    private fun initDiscount() {
        val manager = androidx.recyclerview.widget.LinearLayoutManager(context)
        manager.orientation = androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager

        //val discountAdapter = HomeDiscountAdapter(activity)
        val discountAdapter = activity?.let { HomeDiscountAdapter(it) }

        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter?.setData(
            mutableListOf(
                HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE,
                HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE
            )
        )

    }


    /*
       初始化主题
    */
    private fun initTopic() {
        //话题
        mTopicPager.adapter = context?.let {
            TopicAdapter(
                it,
                listOf(
                    HOME_TOPIC_ONE,
                    HOME_TOPIC_TWO,
                    HOME_TOPIC_THREE,
                    HOME_TOPIC_FOUR,
                    HOME_TOPIC_FIVE
                )
            )
        }
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f)
            .build()
    }

}