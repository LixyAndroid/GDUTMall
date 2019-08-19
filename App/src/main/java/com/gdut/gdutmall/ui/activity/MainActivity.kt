package com.gdut.gdutmall.ui.activity


import android.os.Bundle
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.gdutmall.R
import com.gdut.gdutmall.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

        initView()


    }

    private fun initView() {

        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer,HomeFragment())
        manager.commit()
    }
}
