package com.gdut.order.ui.activity

import android.content.Intent
import android.os.Bundle
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.order.R
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.startActivity

/**
 * @author  Li Xuyang
 * date  2019/8/28 20:21
 */
class ShipAddressEditActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
    }

    private fun initView() {
        btn_location_address.onClick {
            //startActivity<MapLocationActivity>()
            val intent = Intent(this, MapLocationActivity::class.java)
            startActivityForResult(intent, 1001)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 200) {
            if (data != null) {
                val address = data.getStringExtra("address")
                mShipAddressEt.setText(address)

            }
        }

    }

}