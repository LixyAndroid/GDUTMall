package com.gdut.order.ui.activity

import android.content.Intent
import android.os.Bundle
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.order.R
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity

/**
 * @author  Li Xuyang
 * date  2019/8/28 20:19
 */
class ShipAddressActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    private fun initView() {

        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()

        }
    }

}