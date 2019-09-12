package com.gdut.order.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.gdut.order.common.OrderConstant
import com.gdut.order.ui.fragment.OrderFragment

/**
 * @author  Li Xuyang
 * date  2019/9/4 21:20
 */
class OrderVpAdapter(fm: androidx.fragment.app.FragmentManager, context: Context)
    : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val titles = arrayOf("全部","待付款","待收货","已完成","已取消")

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        val fragment = OrderFragment()
        val bundle = Bundle()
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS,position)
        fragment.arguments = bundle
        return fragment

    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}