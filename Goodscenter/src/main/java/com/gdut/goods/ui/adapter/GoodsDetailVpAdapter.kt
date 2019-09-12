package com.gdut.goods.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.gdut.goods.ui.fragment.GoodsDetailTabOneFragment
import com.gdut.goods.ui.fragment.GoodsDetailTabTwoFragment

/**
 * @author  Li Xuyang
 * date  2019/8/26 22:45
 */
class GoodsDetailVpAdapter(fm: androidx.fragment.app.FragmentManager, context:Context) :
    androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品","详情")
    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return if (position == 0){
            GoodsDetailTabOneFragment()
        }else{
            GoodsDetailTabTwoFragment()
        }


    }

    override fun getCount(): Int {

        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}