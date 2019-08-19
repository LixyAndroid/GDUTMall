package com.gdut.gdutmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdut.base.ui.fragment.BaseFragment
import com.gdut.gdutmall.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author  Li Xuyang
 * date  2019/8/19 10:54
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_home,null)

        initView()

        return rootView
    }

    private fun initView() {

        mHomeBanner
    }

}