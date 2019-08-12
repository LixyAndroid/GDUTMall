package com.gdut.user.ui.activity

import android.os.Bundle
import android.view.View
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.user.R
import com.gdut.user.injection.component.DaggerUserComponent
import com.gdut.user.injection.module.UserModule
import com.gdut.user.presenter.UserInfoPresenter
import com.gdut.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

/**
 * 用户信息
 */

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)


        initView()

    }

    /**
     * 初始化视图
     */
    private fun initView() {

        mUserIconView.onClick {
            showAlertView()
        }


    }


    private fun showAlertView() {
         AlertView("选择图片","", "取消", null, arrayOf("拍照", "从相册中选择"), this,
            AlertView.Style.ActionSheet, object : OnItemClickListener {
                override fun onItemClick(o: Any?, position: Int) {
                        when(position){
                            0 -> toast("拍照")
                            1 -> toast("相册")
                        }
                }

            }
        ).show()

    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


}
