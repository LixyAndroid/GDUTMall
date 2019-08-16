package com.gdut.gdutmall.ui.activity


import android.os.Bundle
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.gdutmall.R
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

        Observable.timer(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {mBottomNavBar.checkMsgBadge(true)}

        Observable.timer(5,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {mBottomNavBar.checkCartBadge(0)}

    }
}
