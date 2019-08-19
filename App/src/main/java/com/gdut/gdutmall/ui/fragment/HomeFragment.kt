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
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import org.jetbrains.anko.find


/**
 * @author  Li Xuyang
 * date  2019/8/19 10:54
 */
class HomeFragment : BaseFragment() {

    private lateinit var mHomeBanner:Banner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_home,null)

        initBanner(rootView)

        return rootView
    }

    private fun initBanner(view: View) {

        //https://github.com/youth5201314/banner
        mHomeBanner = view.find(R.id.mHomeBanner)
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