package com.gdut.base.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.gdut.base.R
import com.gdut.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import org.jetbrains.anko.find

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

    //获取Window中视图content
    val contentView:View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }



}