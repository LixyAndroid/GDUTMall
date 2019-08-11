package com.gdut.base.ui.activity

import android.os.Bundle
import com.gdut.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * @author  Li Xuyang
 * date  2019/8/8 15:57
 */
open class BaseActivity :RxAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instance.addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()

        AppManager.instance.finishActivity(this)
    }



}