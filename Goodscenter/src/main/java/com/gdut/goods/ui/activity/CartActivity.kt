package com.gdut.goods.ui.activity

import android.os.Bundle
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.goods.R
import com.gdut.goods.ui.fragment.CartFragment

/**
 * @author  Li Xuyang
 * date  2019/8/28 13:44
 */
class CartActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}