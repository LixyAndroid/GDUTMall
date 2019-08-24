package com.gdut.gdutmall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdut.base.ui.fragment.BaseFragment
import com.gdut.gdutmall.R
import com.gdut.gdutmall.ui.adapter.HomeDiscountAdapter
import com.gdut.gdutmall.ui.adapter.TopicAdapter
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.common.*
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow


/**
 * @author  Li Xuyang
 * date  2019/8/24 21:46
 */
class MeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_me, null)
    }



}