package com.gdut.user.ui.activity

import android.os.Bundle
import android.view.View
import com.gdut.base.common.AppManager
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.user.R
import com.gdut.user.injection.component.DaggerUserComponent

import com.gdut.user.injection.module.UserModule
import com.gdut.user.presenter.RegisterPresenter
import com.gdut.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {

    private var pressTime :Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.onClick{
            mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
        }

    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
            toast(result)

    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()

        if (time - pressTime > 2000){
            toast("再按一次退出程序")
            pressTime = time
        }else{
            AppManager.instance.exitApp(this)
        }

    }






}
