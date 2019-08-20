package com.gdut.gdutmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdut.base.ui.fragment.BaseFragment
import com.gdut.gdutmall.R
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.common.HOME_BANNER_FOUR
import com.kotlin.mall.common.HOME_BANNER_ONE
import com.kotlin.mall.common.HOME_BANNER_THREE
import com.kotlin.mall.common.HOME_BANNER_TWO
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * @author  Li Xuyang
 * date  2019/8/19 10:54
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_home, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initNews()
    }

    private fun initNews() {
        //公告
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场","新用户立领1000元优惠卷"))


    }

    private fun initBanner() {

        //https://github.com/youth5201314/banner

        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))

        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)

        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()

    }

}